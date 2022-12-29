package com.springboot.app.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "blogs")
public class Blogs {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 100)
	private String name;
	
	@Column(length = 10000)
	private String description;
	
	@Column(length = 10000)
	private String url;
	
	@JoinColumn(name = "type_blog_id", referencedColumnName = "id", nullable = true)
    @ManyToOne(optional = false)
	private TypeBlog typeBlog;

	public Blogs() {
		super();
	}

	public Blogs(Integer id, String name, String description, String url) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.url = url;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public TypeBlog getTypeBlog() {
		return typeBlog;
	}

	public void setTypeBlog(TypeBlog typeBlog) {
		this.typeBlog = typeBlog;
	}

	@Override
	public String toString() {
		return "Blogs [id=" + id + ", name=" + name + ", description=" + description + ", url=" + url + "]";
	}
	
}
