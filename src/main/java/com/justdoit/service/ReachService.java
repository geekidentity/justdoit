package com.justdoit.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.justdoit.dao.EmployeeDao;
import com.justdoit.dao.ReachDao;
import com.justdoit.entity.Employee;
import com.justdoit.entity.Reach;

@Service
@Transactional
public class ReachService {
	@Autowired
	private ReachDao dao;
	@Autowired
	private EmployeeDao employeeDao;
	
	/**
	 * 员工签到
	 * @param reach
	 * @return
	 */
	public boolean reach(Reach reach) {
		try {
			dao.save(reach);
			//更新员工的最后一次位置信息
			Employee employee = employeeDao.get(reach.getEmployee());
			employee.setLastPosition(reach.getPosition());
			employee.setLat(reach.getLat());
			employee.setLng(reach.getLng());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 根据用户获得签到历史记录
	 * @param id
	 * @param num
	 * @param type
	 * @return
	 */
	public List<Reach> find(Integer id, Integer num, Integer type) {
		
		Criterion employeeId = Restrictions.eq("employee", id);
		Criteria criteria = null;
		
		if (type == null) {
			criteria = dao.createCriteria(employeeId);
		} else {
			Criterion bytype = Restrictions.eq("type", type);
			criteria = dao.createCriteria(employeeId, bytype);
		}
		
		criteria.addOrder(Order.desc("reachTime"));
		if (num==null) {
			num = 10;
		}
		criteria.setMaxResults(num);
		
		return dao.find(criteria);
	}

	public Reach get(Integer id) {
		return dao.get(id);
	}
	
}
