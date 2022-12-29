package com.springboot.app.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.app.models.dao.IUsuarioBlogDao;
import com.springboot.app.models.entity.UsuarioBlog;
import com.springboot.app.models.service.IUsuarioBlogService;

@Service
public class UsuarioBlogServiceImpl implements IUsuarioBlogService {

	@Autowired
	private IUsuarioBlogDao service;

	@Override
	public void save(UsuarioBlog entity) {
		service.save(entity);

	}

	@Override
	public void delete(Integer id) {
		service.deleteById(id);

	}

	@Override
	public UsuarioBlog findByUsuarioAndBlog(Integer userID, Integer blogID) {
		return service.findByUsuarioAndBlog(userID, blogID);
	}

}
