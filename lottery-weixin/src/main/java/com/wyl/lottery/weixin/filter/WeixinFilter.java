package com.wyl.lottery.weixin.filter;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * SSO客户端模块Filter
 * @author lijianjun
 * @date 2018/4/11 11:12
 * @return
 */
public class WeixinFilter implements Filter {

	private static Logger LOGGER = LoggerFactory.getLogger(WeixinFilter.class);

	private static final String WEI_XIN_OAUTH2_URL = "https://open.weixin.qq.com/connect/oauth2/authorize";

	private static final String WEIXIN_ERROR = "/weixin/error";

	/**
	 * 微信配置
	 */
	private WeiXinConfig weiXinConfig;

	private ApplicationContext applicationContext;


	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		String origUrl = req.getRequestURL().toString();

		//如果是白名单，不用登录，往下走

		//如果是微信
			this.handleWeiXinFilter(req, resp, chain);
	}


	private void handleWeiXinFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {

		String origUrl = req.getRequestURL().toString();

		HttpSession session = req.getSession();

		String queryStr = req.getQueryString();

		Object user = session.getAttribute("user");

		//如果用户不存在或session无效，需要登录，跳到授权页面
		if(null == user ) {

			LOGGER.info("需要授权url:"+origUrl);

			String queryString = req.getQueryString();

			origUrl = origUrl.concat(StringUtils.isNotBlank(queryString) ? "?".concat(queryString) : "");

			String checkOauthUrl = weiXinConfig.getBaseurl().concat("/weixin/checkOauth?")
					.concat("redirectto=").concat(origUrl);
			checkOauthUrl = URLEncoder.encode(checkOauthUrl,"utf-8");

			StringBuilder sb = new StringBuilder();
			sb.append(WEI_XIN_OAUTH2_URL).append("?appid=").append(weiXinConfig.getAppid())
			.append("&redirect_uri=").append(checkOauthUrl)
			.append("&response_type=code&scope=snsapi_base#wechat_redirect");

			LOGGER.info("进入授权url:"+sb.toString());

			resp.sendRedirect(sb.toString());
		}else{
			nextFilter(req,resp,chain);
		}
	}



	@Override
	public void init(FilterConfig config) throws ServletException {
		this.applicationContext= WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
	}

	@Override
	public void destroy() {
	}


	public WeiXinConfig getWeiXinConfig() {
		return weiXinConfig;
	}

	public void setWeiXinConfig(WeiXinConfig weiXinConfig) {
		this.weiXinConfig = weiXinConfig;
	}

	public boolean isContain(String url, String list){
		list = list.replaceAll("\r|\n|\t", "");
		String[] listArray = list.split(",");
		for(String str : listArray){
			if(url.indexOf(str.trim())>=0){
				return true;
			}
		}
		return false;
	}

	public boolean getIsGoLogin(String origUrl, HttpServletRequest req, HttpSession session){
		boolean isGOLogin = true;
		if(isGOLogin){
			if(req.isRequestedSessionIdValid()){
				//如果session属性被意外，则重新登录
				Object user = session.getAttribute("user");
				isGOLogin = (user==null);
			}
		}
		return isGOLogin;
	}



	public void nextFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
		try{
			chain.doFilter(req, resp);
		}catch(Exception e){
			if("org.apache.catalina.connector.ClientAbortException".equals(e.getClass().getName())){
				return;
			}
			throw e;
		}
	}

}
