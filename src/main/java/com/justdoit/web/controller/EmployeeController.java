package com.justdoit.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.justdoit.entity.Employee;
import com.justdoit.entity.Token;
import com.justdoit.service.EmployeeService;
import com.justdoit.service.TokenService;

@Controller
@RequestMapping(value="/employees")
public class EmployeeController {
	
	private Logger logger = Logger.getLogger(Employee.class);
	@Autowired
	private EmployeeService service;
	@Autowired
	private TokenService tokenService;
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public @ResponseBody Object get(@PathVariable("id") Integer id) {
		Employee employee = service.get(id);
		logger.debug("get employee: "+ employee);
		return employee;
	}
	
	/**
	 * 查询员工
	 * @param name
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody Object find(@RequestParam(value="name", required=true) String name) {
		logger.debug("find" + name);
		List<Employee> list = service.find(name);
		return list;
	}
	/**
	 * 2.1.	添加员工
	 * @param employee
	 * @return
	 */
	@RequestMapping(value="/add", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Object add(@RequestBody Employee employee, @RequestParam("access_token") String access_token) {
		
		List<String> info = checkProperty(employee);
		logger.debug("add employee:" + employee.getEno());
		//如果不符合要求，返回提示信息
		if (info.size() > 0) {
			logger.info(info.toString());
			return new JsonObject("添加失败", -1);
		}
		Token token = tokenService.findByToken(access_token);	
		employee.setCreateById(token.getUid());//创建人ID
		employee.setLastModifiedById(token.getUid());
		employee.setGmtCreate(new Date());
		employee.setGmtModified(new Date());
		if (employee.getStatus() == null||employee.getStatus() == 0) {
			employee.setStatus(1);
		}
		service.add(employee);
		return new JsonObject("添加成功", 1);
	}
	
	/*
	 * 当前账号信息
	 */
	@RequestMapping(value="/show", method=RequestMethod.GET)
	public @ResponseBody Object show(HttpServletRequest request, @RequestParam(value="id",required=true) Integer id) {
		try {
			Employee employee = service.get(id);
			employee.getId();//让sql语句提前触发
			return employee;
		} catch (Exception e) {
			logger.error("没有该ID的员工" + id, e);
			return new JsonObject("没有该ID的员工", -1);
		}
	}
	
	/*
	 * 员工详情
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public @ResponseBody Object delete(@PathVariable("id") Integer id) {
		logger.info("user:" + id+ "was deleted");
		if(service.delete(id)){
			tokenService.deleteByUid(id);
			return new JsonObject("删除成功", 1);
		}
		return new JsonObject("删除失败", -1);
	}
	
	/**
	 * 添加用户时检查必填信息
	 * @param employee
	 * @return
	 */
	private List<String> checkProperty(Employee employee) {
		List<String> result = new ArrayList<String>();
		if (employee == null) {
			result.add("用户为空");
			return result;
		}
		if (employee.getEno()==null||"".equals(employee.getEno())) {
			result.add("员工工号不能为空");
		}
		if (employee.getName()==null || "".equals(employee.getName())) {
			result.add("员工姓名不能为空");
		}
		if (employee.getPassword()==null || "".equals(employee.getPassword())) {
			result.add("密码不能为空");
		}
		if (employee.getDepartment()==null || "".equals(employee.getDepartment())) {
			result.add("部门ID不能为空");
		}
		if (employee.getPosition()==null || "".equals(employee.getPosition())) {
			result.add("职位ID不能为空");
		}
		return result;
	}
	
	@RequestMapping(value="/colleagues", method=RequestMethod.GET)
	public @ResponseBody Object colleagues(String access_token) {
		Token token = tokenService.findByToken(access_token);
		return service.getColleaguesByEmpId(token.getUid());
	}
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public @ResponseBody Object getAll() {
		return service.getAll();
	}
	
	/*
	 * 更新员工信息
	 */
	@RequestMapping(method=RequestMethod.PUT)
	public @ResponseBody Object update(@RequestBody Employee employee, String access_token) {
		Token token = tokenService.findByToken(access_token);
		try{
			employee.setLastModifiedById(token.getUid());
			employee.setGmtModified(new Date());
			service.update(employee);
			return new JsonObject("更新成功", 1);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonObject("更新失败", -1);
		}
	}
}
