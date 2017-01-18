package com.justdoit.web.controller;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.justdoit.base.util.JsonObject;
import com.justdoit.entity.LegWork;
import com.justdoit.service.LegWorkService;
import com.justdoit.service.TokenService;

@Controller
@RequestMapping(value="/legwork")
public class LegWorkController {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private LegWorkService legworkServcie;
	@Autowired
	private TokenService tokenService;

	/*
	 * 外勤详情
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public @ResponseBody Object get(@PathVariable("id") Integer id) {
		return legworkServcie.get(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Object applyLegwork(@RequestBody LegWork legWork, String access_token,
			HttpServletRequest request) {
		int uid = tokenService.getUidByToken(access_token);
		legWork.setEmployee(uid);
		legWork.setIp(request.getRemoteHost());
		legWork.setApplyTime(new Date());
		legWork.setStatus(Short.valueOf("0"));
		log.debug("员工 " + uid + "申请外勤失败：" + legWork);
		if (legworkServcie.apply(legWork)) {
			log.debug("员工申请外勤" + legWork);
			return new JsonObject("外勤申请成功", 1);
		}
		return new JsonObject("外勤申请失败", -1);
	}
	
	@RequestMapping(value="/reach", method=RequestMethod.POST)
	public @ResponseBody Object reach(@RequestBody LegWork legWork, 
			@RequestParam("access_token") String access_token) {
		if (legWork.getId() == null) {
			return new JsonObject("ID不能为空", -1);
		}
		int uid = tokenService.getUidByToken(access_token);
		legWork.setEmployee(uid);
		legWork.setReachTime(new Date());
		log.debug("员工签到：" + legWork);
		if (legworkServcie.reach(legWork)) {
			return new JsonObject("签到成功", 1);
		}
		return new JsonObject("签到失败", 1);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public @ResponseBody Object cancel(@PathVariable("id") Integer id) {
		if (legworkServcie.cancel(id)) {
			return new JsonObject("取消成功", 1);
		} 
		return new JsonObject("取消失败", -1);
	}
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public @ResponseBody Object list(Short status, String access_token) {
		Integer id = tokenService.getUidByToken(access_token);
		System.out.println(legworkServcie.findList(status, id));
		return legworkServcie.findList(status, id);
	}
}
