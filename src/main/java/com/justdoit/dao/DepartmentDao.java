package com.justdoit.dao;

import org.springframework.stereotype.Repository;

import com.justdoit.dao.base.HibernateDao;
import com.justdoit.entity.Department;

@Repository
public class DepartmentDao extends HibernateDao<Department, Integer> {

}
