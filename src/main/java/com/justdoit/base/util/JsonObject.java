package com.justdoit.base.util;

/**
 * json返回的对象
 * @author geek1994
 *
 */
public class JsonObject {
	private String msg;
	private String code;
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public JsonObject(String msg, String code) {
		this.msg = msg;
		this.code = code;
	}
	public JsonObject(String msg, int code) {
		this.msg = msg;
		this.code = String.valueOf(code);
	}
	
	
}
