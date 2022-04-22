package com.olxlogin.dto;

public class MasterdataStatus {
	
	private int id;
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
