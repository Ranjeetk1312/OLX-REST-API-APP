package com.olxlogin.dto;

public class MasterdataCategory {
	
	private int id;
	private String category;
	public MasterdataCategory(int id, String category) {
		super();
		this.id = id;
		this.category = category;
	}
	public MasterdataCategory() {
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
		return "AdvertisementCategory [id=" + id + ", category=" + category + "]";
	}
	
}
