package com.olxlogin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="Masterdata Status DTO")
public class MasterdataStatus {
	
	@ApiModelProperty(value="Masterdata Status Identifier")
	private int id;
	@ApiModelProperty(value="Masterdata Status")
	private String status;
	public MasterdataStatus(int id, String status) {
		super();
		this.id = id;
		this.status = status;
	}
	public MasterdataStatus() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "AdvertiseStatus [id=" + id + ", status=" + status + "]";
	}
	
}
