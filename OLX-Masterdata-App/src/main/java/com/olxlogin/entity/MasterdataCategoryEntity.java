package com.olxlogin.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CATEGORIES")
public class MasterdataCategoryEntity {
	
	@Id
	@GeneratedValue
	private int id;
	private String category;
	public MasterdataCategoryEntity(int id, String category) {
		super();
		this.id = id;
		this.category = category;
	}
	public MasterdataCategoryEntity() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	@Override
	public String toString() {
		return "masterdataCategory [id=" + id + ", category=" + category + "]";
	}
	
}
