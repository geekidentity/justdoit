package com.justdoit.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.justdoit.base.util.JsonResponse;
import com.justdoit.service.TokenService;

public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {
	@Autowired
	private TokenService tokenService;

	@Override
	public void onLogoutSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication auth)
			throws IOException, ServletException {
		String access_token = request.getParameter("access_token");
		if (access_token == null||"".equals(access_token)) {
			JsonResponse.response("token不能为空", -1, response);
		} else {
			if(tokenService.deleteByToken(access_token)){
				JsonResponse.response("注销成功", 1, response);
			} else {
				JsonResponse.response("注销失败，没有您的登录信息", -1, response);
			}
			
		}
	}

}
