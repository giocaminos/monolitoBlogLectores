package com.springboot.app.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.app.models.entity.TypeBlog;

public interface ITypeBlogDao extends JpaRepository<TypeBlog, Integer> {

}
