package com.justdoit.dao;

import org.springframework.stereotype.Repository;

import com.justdoit.dao.base.HibernateDao;
import com.justdoit.entity.Position;

@Repository
public class PositionDao extends HibernateDao<Position, Integer> {

}
