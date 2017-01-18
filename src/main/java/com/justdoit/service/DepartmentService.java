package com.justdoit.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.justdoit.dao.DepartmentDao;
import com.justdoit.entity.Department;

@Service
@Transactional
public class DepartmentService {
	@Autowired
	private DepartmentDao dao;
	private Logger log = Logger.getLogger(this.getClass());
	
	/**
	 * 根据ID获取部门信息
	 * @param id 部门ID
	 * @return 部门信息
	 */
	public Department get(Integer id) {
		return dao.get(id);
	}
	
	/**
	 * 获取所有部门信息
	 * @return 所有部门信息列表
	 */
	public List<Department> getAll() {
		return dao.getAll();
	}
	
	/**
	 * 根据部门名获取部门信息
	 * @param name 部门名
	 * @return
	 */
	public Department getDepartmentByName(String name) {
		return dao.findUniqueBy("name", name);
	}
	
	/**
	 * 增加一个部门
	 * @param department 增加一个部门
	 * @return
	 */
	public boolean add(Department department) {
		try {
			dao.add(department);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean delete(Integer id) {
		try {
			dao.delete(dao.get(id));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public void update(Department department) {
		log.debug(department.getId());
		dao.getSession().update(department);
	}
}
