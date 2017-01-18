package com.justdoit.web.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.justdoit.entity.Employee;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {
		"classpath*:/spring/applicationContext-*.xml",
		"classpath:springServlet-servlet.xml" })
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class EmployeeControllerTest {

	@Autowired
	private WebApplicationContext wac;
	@Autowired
	private EmployeeController employeeController;
	private MockMvc mockMvc;

	@Before
	public void before() {
		// 绑定需要测试的Controller到MockMvc
		mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
	}

	@Test
	@Ignore
	public void test() throws Exception {
		mockMvc.perform(
				get("/employees/1")
						.accept(MediaType
								.parseMediaType("application/json;charset=UTF-8")))
				.andExpect(status().isOk())
				.andExpect(
						content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.id").value("1"));
		assertSame("", "");
	}

	/**
	 * 测试add方法
	 * @throws Exception
	 */
	@Test
	@Ignore
	public void add() throws Exception {
		Employee employee = new Employee();
		employee.setId(1);
		mockMvc.perform(
				post("/employees/")
						.accept(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsBytes(employee))
						.content("{\"json\":\"request to be send\"}"))
				.andDo(print())
				.andExpect(status().isOk())//期望的结果
				.andExpect(
						content().contentType("application/json;charset=UTF-8"));
	}

}
