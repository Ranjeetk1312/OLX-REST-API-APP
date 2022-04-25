package com.olxlogin.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="ADVERTISE")
public class AdvertiseEntity {
	@Id
	@GeneratedValue//(strategy=GenerationType.SEQUENCE)
	private int id;
	private String title;
	private String description;
	private double price;
	@Column(name="created_date")
	private LocalDate createdDate;
	@Column(name="modified_date")
	private LocalDate modifiedDate;
	private String category;
	private String status;
	@Column(name="username")
	private String username;
	
	public AdvertiseEntity(int id, String title, String description, double price,
			LocalDate createdDate, LocalDate modifiedDate, String category,String status, String username) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.price = price;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.category = category;
		this.status = status;
		this.username = username;
	}
	public AdvertiseEntity() {
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
				+ ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate
				+ ", category=" + category + ",status=" + status +  ", username=" + username + "]";
	}

}
