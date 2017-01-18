package com.justdoit.dao;

import org.springframework.stereotype.Repository;

import com.justdoit.dao.base.HibernateDao;
import com.justdoit.entity.Reach;

@Repository
public class ReachDao extends HibernateDao<Reach, Integer> {

}
