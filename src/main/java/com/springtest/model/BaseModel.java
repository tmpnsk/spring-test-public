package com.springtest.model;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public abstract class BaseModel<ID extends Serializable> {

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private ID id;

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}
}
