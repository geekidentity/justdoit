package com.justdoit.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.justdoit.dao.PositionDao;
import com.justdoit.entity.Position;

@Service
@Transactional
public class PositionService {
	@Autowired
	private PositionDao positionDao;
	private Logger log = Logger.getLogger(this.getClass());
	
	public List<Position> getAll() {
		return positionDao.getAll();
	}

	public boolean save(Position position) {
		try {
			positionDao.save(position);
			return true;
		} catch (Exception e) {
			log.error("添加职位失败", e);
			return false;
		}
	}
	
	public Position get(Integer id) {
		return positionDao.get(id);
	}
	
	public boolean delete(Integer id) {
		try {
			positionDao.delete(id);
			return true;
		} catch (Exception e) {
			log.error("删除职位失败", e);
			return false;
		}
	}
}
