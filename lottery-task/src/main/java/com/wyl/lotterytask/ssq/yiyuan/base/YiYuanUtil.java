package com.wyl.lotterytask.ssq.yiyuan.base;

import com.wyl.lotterytask.ssq.constants.Constants;
import com.wyl.lotterytask.ssq.constants.YiYuanConstants;
import com.wyl.lotterytask.utils.StringUtils;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 万维易源工具类
 */
public class YiYuanUtil {

    public static final String appName = "just_test_app";
    public static final String appId = "74352";
    public static final String appSecret = "e23903921f5b4d0f89058a6dc46e43de";


    /**
     * 给请求签名。
     *
     * @param params 所有字符型的请求参数
     * @param secret 签名密钥
     * @return 签名
     * @throws IOException
     */
    public static String signRequest(Map<String, String> params, String secret) throws IOException {
        //1.去除file字段 ,同时排序
        TreeSet<String> keys = new TreeSet<String>();
        Iterator<String> it = params.keySet().iterator();
        while (it.hasNext()) {
            String k = it.next();
            Object v = params.get(k);
            if (!(v instanceof FileItem)) {
                keys.add(k);
            }
        }
        //2.把所有参数名和参数值串在一起
        StringBuilder query = new StringBuilder();
        for (String key : keys) {
            if (key.equals(YiYuanConstants.SHOWAPI_SIGN)) {
                continue;//此字段不参与签名
            }
            String value = params.get(key) + "";
            if (StringUtils.areNotEmpty(key, value)) {
                query.append(key).append(value);
            }
        }
        //3.加入密钥和api段
        query.append(secret);
        byte[] bytes;
        bytes = encryptMD5(query.toString());
        //4.把二进制转化为大写的十六进制
        return byte2hex(bytes);
    }


    private static String getStringFromException(Throwable e) {
        String result = "";
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(bos);
        e.printStackTrace(ps);
        try {
            result = bos.toString(Constants.CHARSET_UTF8);
        } catch (IOException ioe) {
        }
        return result;
    }

    public static byte[] encryptMD5(String data) throws IOException {
        byte[] bytes = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            bytes = md.digest(data.getBytes(Constants.CHARSET_UTF8));
        } catch (GeneralSecurityException gse) {
            String msg = getStringFromException(gse);
            throw new IOException(msg);
        }
        return bytes;
    }

    public static String byte2hex(byte[] bytes) {
        StringBuilder sign = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                sign.append("0");
            }
            sign.append(hex.toUpperCase());
        }
        return sign.toString();
    }

    /**
     * 获取文件的真实后缀名。目前只支持JPG, GIF, PNG, BMP四种图片文件。
     *
     * @param bytes 文件字节流
     * @return JPG, GIF, PNG or null
     */
    public static String getFileSuffix(byte[] bytes) {
        if (bytes == null || bytes.length < 10) {
            return null;
        }

        if (bytes[0] == 'G' && bytes[1] == 'I' && bytes[2] == 'F') {
            return "GIF";
        } else if (bytes[1] == 'P' && bytes[2] == 'N' && bytes[3] == 'G') {
            return "PNG";
        } else if (bytes[6] == 'J' && bytes[7] == 'F' && bytes[8] == 'I' && bytes[9] == 'F') {
            return "JPG";
        } else if (bytes[0] == 'B' && bytes[1] == 'M') {
            return "BMP";
        } else {
            return null;
        }
    }

    /**
     * 获取文件的真实媒体类型。目前只支持JPG, GIF, PNG, BMP四种图片文件。
     *
     * @param bytes 文件字节流
     * @return 媒体类型(MEME - TYPE)
     */
    public static String getMimeType(byte[] bytes) {
        String suffix = getFileSuffix(bytes);
        String mimeType;

        if ("JPG".equals(suffix)) {
            mimeType = "image/jpeg";
        } else if ("GIF".equals(suffix)) {
            mimeType = "image/gif";
        } else if ("PNG".equals(suffix)) {
            mimeType = "image/png";
        } else if ("BMP".equals(suffix)) {
            mimeType = "image/bmp";
        } else {
            mimeType = "application/octet-stream";
        }
        return mimeType;
    }


    public void getCommonParam(Map<String, Object> param) throws UnsupportedEncodingException {
        param.put("showapi_appid", appId);
        param.put("showapi_sign", sign(param));
    }

    public String sign(Map<String, Object> param) throws UnsupportedEncodingException {
        Set<String> keys = param.keySet();
        List<String> afterSortKeys = keys.stream().sorted().collect(Collectors.toList());
        StringBuilder sb = new StringBuilder();
        afterSortKeys.forEach(item -> sb.append(item).append(param.get(item)));
        sb.append(appSecret);
        return DigestUtils.md5Hex(sb.toString().getBytes("utf-8"));
    }

}
