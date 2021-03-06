package com.wyl.lotterycommon.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author
 * @version V1.0
 * @Title: CorsFilter
 * @Package com.wyl.zodiac.web.filter
 * @Description: 跨域过滤器
 * @date 2017/8/15 20:16
 */
public class CorsFilter implements Filter {

    final static org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(CorsFilter.class);

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;

        HttpServletRequest reqs = (HttpServletRequest) req;
        response.setHeader("Access-Control-Allow-Origin", reqs.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "access-control-allow-headers,access-control-allow-origin,content-type");
        chain.doFilter(req, res);
    }

    public void init(FilterConfig filterConfig) {
    }

    public void destroy() {
    }
}