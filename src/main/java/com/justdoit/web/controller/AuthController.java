package com.justdoit.web.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.justdoit.base.util.JsonObject;
import com.justdoit.entity.Token;
import com.justdoit.service.EmployeeService;
import com.justdoit.service.TokenService;

@Controller
@RequestMapping(value="/auth")
public class AuthController {
	
	@Autowired
	private TokenService tokenService;
	@Autowired
	private EmployeeService employeeService;
	/*
	 * 用户登录后获取access_token及相关信息
	 */
	@RequestMapping(value="/access_token")
	public @ResponseBody Object token(String access_token) {
		if (access_token == null) {
			return new JsonObject("token不能为空", -1);
		}
		Token token = tokenService.findByToken(access_token);
		AccessToken accessToken = new AccessToken();
		accessToken.setAccess_token(access_token);
		accessToken.setExpires_in(token.getTimeOut().toString());
		accessToken.setUid(token.getUid());
		accessToken.setUsername(employeeService.get(token.getUid()).getName());
		return accessToken;
	}
	
	/*
	 * 检查token状态
	 */
	@RequestMapping(value="/check")
	public @ResponseBody Object check(String access_token) {
		Token token = tokenService.findByToken(access_token);
		if (token == null) {
			return new JsonObject("该验证信息无效", 0);
		}
		Date timeout = token.getTimeOut();
		Date now = new Date();
		int result = timeout.compareTo(now);
		if (result >= 0) {
			return new JsonObject("token正常", 1);
		} else {
			return new JsonObject("token已过期，请重新登录", -1);
		}
	}
}

//access_token返回的JSON
class AccessToken {
	private String access_token;
	private String expires_in;
	private int uid;
	private String username;
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public String getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
}