package com.justdoit.service;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.justdoit.dao.CustomerDao;
import com.justdoit.entity.Customer;

@Service
@Transactional
public class CustomerService {
	private Logger log = Logger.getLogger(this.getClass());
	@Autowired
	private CustomerDao customerDao;

	public Customer get(Integer id) {
		return customerDao.get(id);
	}

	public boolean save(Customer customer) {
		try {
			customer.setGmtCreate(new Date());
			customer.setGmtModified(new Date());
			customerDao.save(customer);
			return true;
		} catch (Exception e) {
			log.error("添加顾客失败", e);
			return false;
		}
	}

	public boolean delete(Integer id) {
		try {
			customerDao.delete(id);
			return true;
		} catch (Exception e) {
			log.error("删除顾客失败", e);
			return false;
		}
	}

	public List<Customer> getAll() {
		return customerDao.getAll();
	}
}
