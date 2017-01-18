package com.justdoit.base.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.justdoit.entity.Employee;
import com.justdoit.entity.Token;



public class JsonResponse {
	
	public static String ADD_SUCCESS = "添加成功";
	public static String ADD_FAILED = "添加失败";
	public static String UPDATE_SUCCESS = "更新成功";
	public static String UPDATE_FAILED = "更新失败";
	public static String DELETE_SUCCESS = "删除成功";
	public static String DELETE_FAILED = "删除失败";
	
	public static void response(Object data,String msg, int code, ServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		JsonObject jo = new JsonObject(msg, "200");
		ObjectMapper mapper = new ObjectMapper();
		String result = "";
		try {
			result = mapper.writeValueAsString(jo);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			PrintWriter out = response.getWriter();
			out.print(result);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void response(Object data, ServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		ObjectMapper mapper = new ObjectMapper();
		String result = "";
		try {
			result = mapper.writeValueAsString(data);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			PrintWriter out = response.getWriter();
			out.print(result);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 只返回消息和状态码，没有数据
	 * @param msg
	 * @param code
	 * @param response
	 */
	public static void response(String msg, int code, ServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		JsonObject jo = new JsonObject(msg, String.valueOf(code));
		ObjectMapper mapper = new ObjectMapper();
		String result = "";
		try {
			result = mapper.writeValueAsString(jo);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			PrintWriter out = response.getWriter();
			out.print(result);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * 登陆成功后返回的数据
	 */
	public static void loginSuccessResponse(HttpServletRequest request, HttpServletResponse response, Token token) {
		response.setContentType("text/html;charset=UTF-8");
		LoginSccuessMsg msg = new LoginSccuessMsg("登陆成功", 1);
		String access_token = token.getToken();
		msg.setAccess_token(access_token);
		msg.setUid(String.valueOf(token.getUid()));
		ObjectMapper mapper = new ObjectMapper();
		String result = "";
		try {
			result = mapper.writeValueAsString(msg);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			PrintWriter out = response.getWriter();
			out.print(result);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class LoginSccuessMsg extends JsonObject {
	
	private String access_token;
	private String uid;

	public LoginSccuessMsg(String msg, int code) {
		super(msg, code);
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}
}
