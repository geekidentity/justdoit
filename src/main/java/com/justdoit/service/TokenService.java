package com.justdoit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.justdoit.dao.TokenDao;
import com.justdoit.entity.Token;

@Service
@Transactional
public class TokenService {
	@Autowired
	private TokenDao dao;
	
	public Object get(Integer id) {
		return dao.get(id);
	}
	
	public void delete(Integer id) {
		dao.delete(id);
	}

	public Token findByToken(String access_token) {
		List<Token> list = dao.findBy("token", access_token);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public void save(Token token) {
		dao.save(token);
	}

	public boolean deleteByToken(String access_token) {
		List<Token> tokens = dao.findBy("token", access_token);
		if (tokens.size() < 1) {
			return false;
		}
		for (Token t : tokens) {
			dao.delete(t.getId());
		}
		return true;
	}

	public void deleteByUid(Integer id) {
		List<Token> tokens = dao.findBy("uid", id);
		for (Token t : tokens) {
			dao.delete(t.getId());
		}
	}

	public Integer getUidByToken(String access_token) {
		return findByToken(access_token).getUid();
	}

	public List<Token> findListByUid(Integer id) {
		return dao.findBy("uid", id);
	}
}
