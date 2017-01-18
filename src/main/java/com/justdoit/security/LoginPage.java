package com.justdoit.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.GenericFilterBean;

import com.justdoit.base.util.JsonResponse;
import com.justdoit.base.util.MD5Util;
import com.justdoit.entity.Employee;
import com.justdoit.entity.Token;
import com.justdoit.service.TokenService;

public class LoginPage extends GenericFilterBean {
	
	@Autowired
	private TokenService tokenService;

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		if (!isLogin(request)&&!isAuthed(request)) {
			JsonResponse.response("请登陆", -1, response);
			return;
		}
		chain.doFilter(request, response);
	}
	
	private boolean isLogin(HttpServletRequest request) {
		return matches(request, "login");
	}

	private boolean matches(HttpServletRequest request, String url) {
		if (!"GET".equals(request.getMethod()) || url == null) {
			return false;
		}
		String uri = request.getRequestURI();
		int pathParamIndex = uri.indexOf(';');

		if (pathParamIndex > 0) {
			// strip everything after the first semi-colon
			uri = uri.substring(0, pathParamIndex);
		}

		if (request.getQueryString() != null) {
			uri += "?" + request.getQueryString();
		}

		if ("".equals(request.getContextPath())) {
			return uri.endsWith(url);
		}

		return uri.endsWith(request.getContextPath() + url);
	}
	/**
	 * 用户是否登陆，数据库中是否有
	 * @param request
	 * @return
	 */
	private boolean isAuthed(HttpServletRequest request) {
		String access_token = request.getParameter("access_token");
		if (access_token == null) {
			logger.warn("access_token is Null");
			return false;
		}
		Token token = tokenService.findByToken(access_token);
		logger.debug("find token" + token);
		if (token == null) {
			return false;
		}
		return true;
	}
}
