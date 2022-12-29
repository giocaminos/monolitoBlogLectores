package com.springboot.app.models.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "lector_blog")
public class LectorBlog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private String typeBlog;

	@Column
	private String nameBlog;

	@Column
	private String user;

	@Column
	private String date;

	public LectorBlog() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LectorBlog(Integer id, String typeBlog, String nameBlog, String user, String date) {
		super();
		this.id = id;
		this.typeBlog = typeBlog;
		this.nameBlog = nameBlog;
		this.user = user;
		this.date = date;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTypeBlog() {
		return typeBlog;
	}

	public void setTypeBlog(String typeBlog) {
		this.typeBlog = typeBlog;
	}

	public String getNameBlog() {
		return nameBlog;
	}

	public void setNameBlog(String nameBlog) {
		this.nameBlog = nameBlog;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@PrePersist
	public void prepersist() {
		SimpleDateFormat objSDF = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		date = objSDF.format(new Date());
	}
}
