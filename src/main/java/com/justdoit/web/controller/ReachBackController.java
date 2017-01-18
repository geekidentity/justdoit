package com.justdoit.web.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.justdoit.base.util.JsonObject;
import com.justdoit.entity.Reach;
import com.justdoit.service.ReachService;
import com.justdoit.service.TokenService;

/**
 * 签到签退
 * @author geek1994
 * 
 * 2016年3月20日
 */
@Controller
@SessionAttributes("curUser")
public class ReachBackController {
	private Logger log = Logger.getLogger(getClass());
	@Autowired
	private ReachService reachService;
	@Autowired
	private TokenService tokenService;
	/**
	 * 员工打卡请求
	 * 
	 * @param reach
	 * @return
	 */
	@RequestMapping(value = "/reach", method = RequestMethod.POST)
	public @ResponseBody Object reach(@RequestBody Reach reach, HttpServletRequest request,
			@RequestParam(value="access_token",required=true) String access_token) {
		int uid = tokenService.getUidByToken(access_token);
		reach.setEmployee(uid);
		reach.setReachTime(new Date());
		reach.setIp(request.getRemoteHost());
		if (reachService.reach(reach)) {
			if (reach.getType() == 1) {
				return new JsonObject("签到成功", 1);
			} else {
				return new JsonObject("签退成功", 1);
			}
		}else {
			if (reach.getType() == 1) {
				return new JsonObject("签到失败", 1);
			} else {
				return new JsonObject("签退失败", 1);
			}
		}
	}

	/**
	 * 根据用户获得签到历史记录
	 * 
	 * @param num
	 * @param curEmployee
	 * @return
	 */
	@RequestMapping(value = "/reach/log", method = RequestMethod.GET)
	public @ResponseBody Object reachHistory(Integer num, Integer type,
			String access_token) {
		log.debug("reach log num:" + num);
		return reachService.find(tokenService.getUidByToken(access_token), num, type);
	}
	
	/*
	 * 签到签退详情
	 */
	@RequestMapping(value="/reach/{id}", method=RequestMethod.GET)
	public @ResponseBody Object get(@PathVariable("id") Integer id) {
		return reachService.get(id);
	}

}
