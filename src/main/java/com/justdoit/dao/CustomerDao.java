package com.justdoit.dao;

import org.springframework.stereotype.Repository;

import com.justdoit.dao.base.HibernateDao;
import com.justdoit.entity.Customer;

@Repository
public class CustomerDao extends HibernateDao<Customer, Integer> {
	
}
