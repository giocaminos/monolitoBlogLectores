package com.springboot.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.app.models.entity.Blogs;

public interface IBlogsDao extends JpaRepository<Blogs, Integer> {

	@Query("select b from Blogs b "
			+ "join fetch b.typeBlog t  where t.id = ?1")
	public List<Blogs> findAllBlogsByTypeBlog(Integer id);
}
