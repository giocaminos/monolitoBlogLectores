package com.springboot.app.models.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "usuario_blog")
public class UsuarioBlog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@JoinColumn(name = "users", referencedColumnName = "id", nullable = false)
	@ManyToOne(optional = false)
	private Usuario usuario;
	@JoinColumn(name = "blogs", referencedColumnName = "id", nullable = false)
	@ManyToOne(optional = false)
	private Blogs blogs;
	
	public UsuarioBlog() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Blogs getBlogs() {
		return blogs;
	}
	public void setBlogs(Blogs blogs) {
		this.blogs = blogs;
	}
	
}
