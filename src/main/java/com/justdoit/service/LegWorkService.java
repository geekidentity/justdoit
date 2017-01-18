package com.justdoit.service;


import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.justdoit.dao.EmployeeDao;
import com.justdoit.dao.LegWorkDao;
import com.justdoit.entity.Employee;
import com.justdoit.entity.LegWork;

@Service
@Transactional
public class LegWorkService {
	private Logger log = Logger.getLogger(this.getClass());
	@Autowired
	private LegWorkDao legWorkDao;
	@Autowired
	private EmployeeDao employeeDao;
	
	/**
	 * 申请外勤
	 * @param legWork
	 * @return
	 */
	public boolean apply(LegWork legWork) {
		try {
			legWorkDao.add(legWork);
		} catch (Exception e) {
			log.error("用户" + legWork.getEmployee() + "申请外勤失败", e);
			return false;
		}
		return true;
	}
	
	/**
	 * 取消外勤
	 * @param id 要取消外勤的ID
	 * @return
	 */
	public boolean cancel(Integer id) {
		LegWork t = legWorkDao.get(id);
		t.setStatus(new Short("-1"));
		return true;
	}

	/**
	 * 外勤签到
	 * @param id
	 * @param reachLng
	 * @param reachLat
	 * @return
	 */
	public boolean reach(LegWork legWork) {
		try {
			legWorkDao.save(legWork);
			//更新员工的最后一次位置信息
			Employee employee = employeeDao.get(legWork.getEmployee());
			employee.setLastPosition(legWork.getPosition());
			employee.setLat(legWork.getLat());
			employee.setLng(legWork.getLng());
		} catch (Exception e) {
			log.error("外勤签到失败", e);
			return false;
		}
		return true;
	}

	/**
	 * 外勤详情
	 * @param id
	 * @return
	 */
	public LegWork get(Integer id) {
		return legWorkDao.get(id);
	}

	/**
	 * 删除外勤
	 * @param id
	 * @return
	 */
	public boolean delete(Integer id) {
		try {
			delete(id);
		} catch (Exception e) {
			log.error("外勤" + id + "删除失败", e);
			return false;
		}
		return true;
	}

	/**
	 * 外勤列表，默认查询未完成的
	 * @param status
	 * @return
	 */
	public List<LegWork> findList(Short status, Integer id) {
		if (status == null) {
			status = 0;
		}
		Criterion byStatus = Restrictions.eq("status", status);
		Criterion byEmployee = Restrictions.eq("employee", id);
		Criteria criteria = legWorkDao.createCriteria(byEmployee, byStatus);
		return legWorkDao.find(criteria);
	}

}
