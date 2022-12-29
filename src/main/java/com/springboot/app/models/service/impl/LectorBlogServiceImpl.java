package com.springboot.app.models.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.app.models.dao.ILectorDao;
import com.springboot.app.models.entity.LectorBlog;
import com.springboot.app.models.service.ILectorBlogService;

@Service
public class LectorBlogServiceImpl implements ILectorBlogService {

	@Autowired
	ILectorDao dao;
	
	@Override
	public List<LectorBlog> findAllLectorBlog() {
		return dao.findByOrderByIdDesc();
	}

	@Override
	public void save(LectorBlog entity) {
		dao.save(entity);
	}

}
