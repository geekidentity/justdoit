package com.justdoit.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.justdoit.dao.EmployeeDao;
import com.justdoit.dao.TokenDao;
import com.justdoit.entity.Employee;
import com.justdoit.entity.Token;

@Service
@Transactional
public class EmployeeService implements UserDetailsService {
	
	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private EmployeeDao dao;
	@Autowired
	private TokenDao tokenDao;
	public Employee get(Integer id) {
		Employee employee = dao.get(id);
		return employee;
	}
	/**
	 * 增加一个员工
	 * @param employee
	 * @return
	 */
	public boolean add(Employee employee) {
		try {
			dao.add(employee);
			return true;
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		Employee e = dao.findUniqueBy("eno", username);
		return e;
	}
	
	/**
	 * 查找用户，支持模糊查询
	 * @param name
	 * @return
	 */
	public List<Employee> find(String name) {
		Criterion likeName = Restrictions.like("name", name + "%");
		Criteria criteria = dao.createCriteria(likeName);
		return dao.find(criteria);
	}
	public boolean delete(Integer id) {
		try {
			dao.delete(id);
			return true;
		} catch (Exception e) {
			logger.error("删除员工失败", e);
		}
		return false;
	}
	public void setLastLoginTime(Employee employee) {
		employee.setLastLogin(new Date());
		dao.save(employee);
	}
	
	/**
	 * 根据员工ID查找其在同一部门的同事
	 * @param uid
	 * @return
	 */
	public List<Employee> getColleaguesByEmpId(Integer uid) {
		Employee employee = dao.get(uid);
		logger.debug(employee.getDepartment().getId());
		
		List<Employee> list = dao.findBy("position", employee.getDepartment().getId());
		List<Token> tokens = tokenDao.getAll();
		List<Integer> uids = new ArrayList<Integer>();
		for (Token t : tokens) {
			uids.add(t.getUid());
		}
		for (Employee e : list) {
			if (uids.contains(e.getId())) {
				e.setOnline(1);
			}
		}
		return list;
	}
	
	public List<Employee> getAll() {
		return dao.getAll();
	}
	public void update(Employee employee) {
		dao.getSession().update(employee);
	}

}
