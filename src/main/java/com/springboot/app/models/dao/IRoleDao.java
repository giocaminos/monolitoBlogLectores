package com.springboot.app.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.app.models.entity.Role;

public interface IRoleDao extends JpaRepository<Role, Integer> {

}
