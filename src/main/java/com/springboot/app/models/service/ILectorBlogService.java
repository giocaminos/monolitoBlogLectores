package com.springboot.app.models.service;

import java.util.List;

import com.springboot.app.models.entity.LectorBlog;

public interface ILectorBlogService {

	public List<LectorBlog> findAllLectorBlog();
	public void save(LectorBlog entity);
}
