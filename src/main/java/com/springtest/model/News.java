package com.springtest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class News extends BaseModel<Long> {

	@Column
	private String title;

	@Column(length = 4096)
	private String content;

	@Column(name = "views_amount", nullable = false)
	private Long viewsAmount;

	@ManyToOne
	private User user;

	public Long getViewsAmount() {
		return viewsAmount;
	}

	public void setViewsAmount(Long viewsAmount) {
		this.viewsAmount = viewsAmount;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
