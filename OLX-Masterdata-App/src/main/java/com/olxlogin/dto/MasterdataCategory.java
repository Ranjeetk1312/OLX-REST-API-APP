package com.olxlogin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="Masterdata Category DTO")
public class MasterdataCategory {
	
	@ApiModelProperty(value="Masterdata Category Identifier")
	private int id;
	@ApiModelProperty(value="Masterdata Category")
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
