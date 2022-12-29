package com.springboot.app.models.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.app.models.dao.ITypeBlogDao;
import com.springboot.app.models.entity.TypeBlog;
import com.springboot.app.models.service.ITypeBlogService;

@Service
public class TypeBlogServiceImpl implements ITypeBlogService {

	@Autowired
	ITypeBlogDao dao;
	
	@Override
	@Transactional(readOnly = true)
	public List<TypeBlog> findAll() {
		return dao.findAll();
	}

	@Override
	public void save(TypeBlog entity) {
		dao.save(entity);
	}

	@Override
	public void delete(Integer id) {
		dao.deleteById(id);
	}

}
