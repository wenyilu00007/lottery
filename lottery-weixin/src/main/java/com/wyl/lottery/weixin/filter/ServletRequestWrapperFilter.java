package com.wyl.lottery.weixin.filter;

import org.bouncycastle.util.encoders.UrlBase64Encoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.Base64;

public class ServletRequestWrapperFilter extends OncePerRequestFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServletRequestWrapperFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        ServletRequest requestWrapper = null;
        requestWrapper = new BodyReaderHttpServletRequestWrapper(httpServletRequest);
        String method = httpServletRequest.getMethod();
        //获取请求参数
        String paramString = "";
        if (HttpMethod.GET.name().equals(method.toUpperCase())) {
            paramString = URLDecoder.decode(httpServletRequest.getQueryString(),"utf-8");
        } else {
            paramString = getReq(requestWrapper);
        }
        LOGGER.info("访问请求参数:" + paramString);
        filterChain.doFilter(requestWrapper, httpServletResponse);
    }


    /**
     * 获取所有的请求内容
     *
     * @param request
     * @return
     */
    public static String getReq(ServletRequest request) {
        StringBuilder sb = new StringBuilder();
        BufferedInputStream bis = null;
        InputStream is = null;
        try {
            is = request.getInputStream();
            bis = new BufferedInputStream(is);
            byte[] tmp = new byte[4096];
            int n = 0;
            while ((n = bis.read(tmp)) > 0) {
                String temp = new String(tmp, 0, n);
                sb.append(temp);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            try {
                if (bis != null) {
                    bis.close();
                }
                if (is != null) {
                    is.close();
                }
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
        return sb.toString();
    }


}
