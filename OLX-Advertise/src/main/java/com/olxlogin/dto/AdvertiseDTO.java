package com.olxlogin.dto;

import java.time.LocalDate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="Advertise DTO")
public class AdvertiseDTO {
	@ApiModelProperty(value="Advertise Identifier")
	private int id;
	@ApiModelProperty(value="Advertise Title")
	private String title;
	@ApiModelProperty(value="Advertise Description")
	private String description;
	@ApiModelProperty(value="Advertise Price")
	private double price;
	@ApiModelProperty(value="Advertise CategoryId")
	private int categoryId;
	@ApiModelProperty(value="Advertise StatuseId")
	private int statusId;
	@ApiModelProperty(value="Advertise CreatedDate")
	private LocalDate createdDate;
	@ApiModelProperty(value="Advertise MadifiedData")
	private LocalDate modifiedDate;
	@ApiModelProperty(value="Advertise category")
	private String category;
	@ApiModelProperty(value="Advertise status")
	private String status;
	@ApiModelProperty(value="Advertise UserName")
	private String username;
	
	public AdvertiseDTO(int id, String title, String description, double price, int categoryId, int statusId,
			LocalDate createdDate, LocalDate modifiedDate, String category,String status, String username) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.price = price;
		this.categoryId = categoryId;
		this.statusId = statusId;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.category = category;
		this.status = status;
		this.username = username;
	}
	public AdvertiseDTO() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public LocalDate getCreatedDate() {
		return createdDate;
	}
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}
	public LocalDate getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(LocalDate modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Override
	public String toString() {
		return "AdvertiseDTO [id=" + id + ", title=" + title + ", description=" + description + ", price=" + price
				+ ", categoryId=" + categoryId + ", statusId=" + statusId + ", createdDate=" + createdDate
				+ ", modifiedDate=" + modifiedDate + ", category=" + category +
				",status=" + status + ", username=" + username + "]";
	}
	

}
