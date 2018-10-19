package com.wyl.lotterytask.ssq.yiyuan.base;

import com.wyl.lotterytask.ssq.constants.YiYuanConstants;

import java.io.IOException;
import java.io.UnsupportedEncodingException;


/**
 * 基于REST的客户端。
 */
public class ShowApiRequest extends NormalRequest {
    private String appSecret;


    public ShowApiRequest(String url, String appid, String appSecret) {
        super(url);
        this.appSecret = appSecret;
        this.addTextPara("showapi_appid", appid);
        this.addHeadPara("User-Agent", "showapi-sdk-java");//设置默认头
    }


    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }


    @Override
    public String post() {
        String res = null;
        try {
            byte b[] = postAsByte();
            res = new String(b, "utf-8");
        } catch (Exception e) {
            if (printException){
                e.printStackTrace();
            }
        }
        return res;
    }

    @Override
    public byte[] postAsByte() {
        byte res[] = null;
        try {
            String signResult = addSign();
            if (signResult != null) {
                return signResult.getBytes("utf-8");
            }
            res = WebUtils.doPostAsByte(this);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                res = ("{showapi_res_code:-1,showapi_res_error:\"" + e.toString() + "\"}").getBytes("utf-8");
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }
        }
        return res;
    }

    private String addSign() throws IOException {
        if (textMap.get(YiYuanConstants.SHOWAPI_APPID) == null)
        {
            return errorMsg(YiYuanConstants.SHOWAPI_APPID + "不得为空!");
        }
        textMap.put(YiYuanConstants.SHOWAPI_SIGN, YiYuanUtil.signRequest(textMap, appSecret));
        return null;
    }

    @Override
    public String get() {
        String res = null;
        try {
            byte b[] = getAsByte();
            res = new String(b, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
            res = "{showapi_res_code:-1,showapi_res_error:\"" + e.toString() + "\"}";
        }
        return res;
    }

    @Override
    public byte[] getAsByte() {
        byte[] res = null;
        try {
            String signResult = addSign();
            if (signResult != null) {
                return signResult.getBytes("utf-8");
            }
            res = WebUtils.doGetAsByte(this);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                res = ("{showapi_res_code:-1,showapi_res_error:\"" + e.toString() + "\"}").getBytes("utf-8");
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }
        }
        return res;
    }

    private String errorMsg(String msg) {
        String str = "{" + YiYuanConstants.SHOWAPI_RES_CODE + ":-1," + YiYuanConstants.SHOWAPI_RES_ERROR + ":\"" + msg + "\"," + YiYuanConstants.SHOWAPI_RES_BODY + ":{}}";
        return str;
    }

}

