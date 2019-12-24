package com.chengyuqi.cms.entity;

import java.io.Serializable;
import java.util.Date;

public class Link implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7776909343013384717L;
	
	private Integer id;
	
	private String url;
	
	private String name;
	
	private Date created;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Override
	public String toString() {
		return "Link [id=" + id + ", url=" + url + ", name=" + name + ", created=" + created + "]";
	}

	public Link(Integer id, String url, String name, Date created) {
		super();
		this.id = id;
		this.url = url;
		this.name = name;
		this.created = created;
	}

	public Link() {
		super();
	}
	
	
	
}
