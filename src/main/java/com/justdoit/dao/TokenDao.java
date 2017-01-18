package com.justdoit.dao;

import org.springframework.stereotype.Repository;

import com.justdoit.dao.base.HibernateDao;
import com.justdoit.entity.Token;

@Repository
public class TokenDao extends HibernateDao<Token, Integer> {

}
