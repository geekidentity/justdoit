package com.justdoit.web.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.justdoit.base.util.JsonObject;
import com.justdoit.entity.Department;
import com.justdoit.service.DepartmentService;
import com.justdoit.service.TokenService;

@Controller
@RequestMapping(value="/departments")
public class DepartmentController {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private TokenService tokenService;
	
	/*
	 * 添加部门
	 */
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody Object add(@RequestBody Department department, String access_token) {
		
		if (departmentService.add(department)) {
			log.debug("添加部门" + department.getName());
			return new JsonObject("添加成功", 1);
		} else {
			log.error("添加部门失败");
			return new JsonObject("添加失败", -1);
		}
	}
	
	/*
	 * 删除部门
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public @ResponseBody Object delete(@PathVariable("id") Integer id) {
		if (departmentService.delete(id)) {
			return new JsonObject("删除成功", 1);
		} else {
			return new JsonObject("删除失败", -1);
		}
	}
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public @ResponseBody Object list() {
		return departmentService.getAll();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public @ResponseBody Object get(@PathVariable("id") Integer id) {
		return departmentService.get(id);
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public @ResponseBody Object update(@RequestBody Department department) {
		try {
			departmentService.update(department);
			return new JsonObject("更新成功", 1);
		} catch (Exception e) {
			log.error(e);
			return new JsonObject("更新失败", -1);
		}
	}
}
