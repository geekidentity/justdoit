package com.justdoit.dao;

import org.springframework.stereotype.Repository;

import com.justdoit.dao.base.HibernateDao;
import com.justdoit.entity.LegWork;

@Repository
public class LegWorkDao extends HibernateDao<LegWork, Integer> {
}
