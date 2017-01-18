package com.justdoit.dao;

import org.springframework.stereotype.Repository;

import com.justdoit.dao.base.HibernateDao;
import com.justdoit.entity.Employee;

@Repository
public class EmployeeDao extends HibernateDao<Employee, Integer> {

}
