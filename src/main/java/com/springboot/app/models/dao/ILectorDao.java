package com.springboot.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.app.models.entity.LectorBlog;

public interface ILectorDao extends JpaRepository<LectorBlog, Integer> {

	List<LectorBlog> findByOrderByIdDesc();
}
