package com.springboot.app.models.service;

import java.util.List;

import com.springboot.app.models.entity.Blogs;

public interface IBlogsService {

	public List<Blogs> findAll();
	public List<Blogs> findAllBlogsByTypeBlog(Integer id);
	public Blogs findById(Integer id);
	public void delete(Integer id);
	public Blogs save (Blogs blog);
	
}
