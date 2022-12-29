package com.springboot.app.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.app.models.entity.UsuarioBlog;

public interface IUsuarioBlogDao extends JpaRepository<UsuarioBlog, Integer> {

	@Query("select ub from UsuarioBlog ub "
			+ "join  ub.blogs b "
			+ "join  ub.usuario u "
			+ "where b.id = ?2 and u.id = ?1")
	public UsuarioBlog findByUsuarioAndBlog(Integer userId, Integer blogID);
}
