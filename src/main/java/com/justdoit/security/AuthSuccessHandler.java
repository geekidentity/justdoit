package com.justdoit.security;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import com.justdoit.base.util.JsonResponse;
import com.justdoit.base.util.MD5Util;
import com.justdoit.dao.EmployeeDao;
import com.justdoit.entity.Employee;
import com.justdoit.entity.Token;
import com.justdoit.service.EmployeeService;
import com.justdoit.service.TokenService;

/**
 * 登录验证成功后进行的处理
 * @author geek1994
 * 
 * 2016年4月25日
 */
@Resource(name = "auth")
public class AuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	@Autowired
	private EmployeeDao dao;
	@Autowired
	private TokenService tokenService;
	@Autowired
	private EmployeeService employeeService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication auth)
			throws IOException, ServletException {

		Employee employee = dao.findUniqueBy("name", auth.getName());
		Token token = saveToken(request, employee);
		employeeService.setLastLoginTime(employee);
		JsonResponse.loginSuccessResponse(request, response, token);
	}
	private Token saveToken(HttpServletRequest request, Employee employee) {
		//token 进行MD5加密
		String access_token = MD5Util.encode(employee.getEno() + employee.getPassword() + request.getRemoteHost());
		if (access_token == null) {
			return null;
		}
		Token token = new Token(access_token);
		String remember = request.getParameter("remember");
		if (remember != null && remember.equals("true")) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String timeout = dateFormat.format(new Date(new Date().getTime() + 7*24*60*60*1000));
			logger.debug("timeout:" + timeout);
			try {
				token.setTimeOut(dateFormat.parse(timeout));
			} catch (ParseException e) {
				logger.error("timeout 设置错误", e);
				token.setTimeOut(new Date());
			}
		} else {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String timeout = dateFormat.format(new Date(new Date().getTime() + 1*60*60*1000));
			logger.debug("timeout:" + timeout);
			try {
				token.setTimeOut(dateFormat.parse(timeout));
			} catch (ParseException e) {
				logger.error("timeout 设置错误", e);
				token.setTimeOut(new Date());
			}
		}
		String remoteIP = request.getRemoteHost();
		token.setRemote_ip(remoteIP);
		logger.debug("remoteIp:"+ remoteIP + " length is:"+ remoteIP.length());
		token.setUid(employee.getId());
		//如果有token，删除之前的token然后存入
		List<Token> list = tokenService.findListByUid(employee.getId());
		for (Token t : list) {
			tokenService.delete(t.getId());
		}
		tokenService.save(token);
		return token;
	}

}
