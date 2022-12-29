package com.springboot.app.models.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.app.models.dao.IBlogsDao;
import com.springboot.app.models.entity.Blogs;
import com.springboot.app.models.service.IBlogsService;

@Service
public class BlogsServiceImpl implements IBlogsService {

	@Autowired
	IBlogsDao service;
	
	@Override
	@Transactional(readOnly = true)
	public List<Blogs> findAll() {
		return service.findAll();
	}

	@Override
	public Blogs findById(Integer id) {
		return service.findById(id).orElse(null);
	}

	@Override
	public void delete(Integer id) {	
		service.deleteById(id);
	}

	@Override
	public Blogs save(Blogs blog) {
		return service.save(blog);
	}

	@Override
	public List<Blogs> findAllBlogsByTypeBlog(Integer id) {
		return service.findAllBlogsByTypeBlog(id);
	}

}
