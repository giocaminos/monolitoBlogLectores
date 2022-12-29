package com.springboot.app.models.service;

import com.springboot.app.models.entity.UsuarioBlog;

public interface IUsuarioBlogService {

	public UsuarioBlog findByUsuarioAndBlog(Integer userID, Integer blogID);
	public void save(UsuarioBlog entity);
	public void delete(Integer id);
	
}
