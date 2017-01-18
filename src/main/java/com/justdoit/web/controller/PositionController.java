package com.justdoit.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.justdoit.base.util.JsonObject;
import com.justdoit.base.util.JsonResponse;
import com.justdoit.entity.Position;
import com.justdoit.service.PositionService;

@Controller
@RequestMapping("/positions")
public class PositionController {
	
	@Autowired
	private PositionService positionService;
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public @ResponseBody Object getAll() {
		return positionService.getAll();
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody Object add(@RequestBody Position position) {
		if(positionService.save(position)) {
			return new JsonObject(JsonResponse.ADD_SUCCESS, 1);
		} else {
			return new JsonObject(JsonResponse.ADD_FAILED, -1);
		}
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public @ResponseBody Object get(@PathVariable("id") Integer id) {
		return positionService.get(id);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public @ResponseBody Object delete(@PathVariable("id") Integer id) {
		if (positionService.delete(id)) {
			return new JsonObject(JsonResponse.DELETE_SUCCESS, 1);
		} else {
			return new JsonObject(JsonResponse.DELETE_FAILED, -1);
		}
	}
}
