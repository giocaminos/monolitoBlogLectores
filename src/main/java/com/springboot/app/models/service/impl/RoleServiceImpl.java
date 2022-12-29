package com.springboot.app.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.app.models.dao.IRoleDao;
import com.springboot.app.models.entity.Role;
import com.springboot.app.models.service.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private IRoleDao dao;
	
	@Override
	public void save(Role role) {
		dao.save(role);
	}

}
