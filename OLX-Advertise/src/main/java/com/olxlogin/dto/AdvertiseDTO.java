package com.olxlogin.dto;

import java.time.LocalDate;

public class AdvertiseDTO {
	private long id;
	private String title;
	private String description;
	private double price;
	private long category;
	private LocalDate createdDate;
	private LocalDate modifiedDate;
	private String active;
	private String username;
	public AdvertiseDTO(long id, String title, String description, double price, long category, LocalDate createdDate,
			LocalDate modifiedDate, String active, String username) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.price = price;
		this.category = category;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.active = active;
		this.username = username;
	}
	public AdvertiseDTO() {
		super();
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
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
	public long getCategory() {
		return category;
	}
	public void setCategory(long category) {
		this.category = category;
	}
	public LocalDate getCreatedDate() {
		return createdDate;
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
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
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
				+ ", category=" + category + ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate
				+ ", active=" + active + ", username=" + username + "]";
	}
	

}
