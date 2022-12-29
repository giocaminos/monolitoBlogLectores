package com.springboot.app.models.service;

import java.util.List;

import com.springboot.app.models.entity.TypeBlog;

public interface ITypeBlogService {

	public List<TypeBlog> findAll();
	public void save(TypeBlog entity);
	public void delete(Integer id);
	
}
