package com.justdoit.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import com.justdoit.base.util.JsonResponse;

public class AuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		String msg = "";
		msg += (request.getParameter("username")==null) ? "用户名不能为空\n":"";
		msg += (request.getParameter("password")==null) ? "密码不能为空":"";
		if (msg.equals("")) {
			msg = "登录失败";
		}
		JsonResponse.response(msg,-1, response);
	}
}
