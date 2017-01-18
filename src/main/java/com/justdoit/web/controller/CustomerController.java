package com.justdoit.web.controller;

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
import com.justdoit.base.util.JsonResponse;
import com.justdoit.entity.Customer;
import com.justdoit.entity.Token;
import com.justdoit.service.CustomerService;
import com.justdoit.service.TokenService;

@Controller
@RequestMapping("/customers")
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	@Autowired
	private TokenService tokenService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public @ResponseBody Object get(@PathVariable("id") Integer id) {
		return customerService.get(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Object add(@RequestBody Customer customer, @RequestParam(value="access_token") String access_token) {
		Token token = tokenService.findByToken(access_token);
		customer.setEmployeeId(token.getUid());
		customer.setCreateById(token.getUid());
		customer.setLastModifiedById(token.getUid());
		if(customerService.save(customer)) {
			return new JsonObject("添加成功", 1);
		}
		return new JsonObject(JsonResponse.ADD_FAILED, -1);
	}

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public @ResponseBody Object delete(@PathVariable("id") Integer id) {
		if (customerService.delete(id)) {
			return new JsonObject(JsonResponse.DELETE_SUCCESS, 1);
		} else {
			return new JsonObject(JsonResponse.DELETE_FAILED, -1);
		}
	}
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public @ResponseBody Object list() {
		return customerService.getAll();
	}
}
