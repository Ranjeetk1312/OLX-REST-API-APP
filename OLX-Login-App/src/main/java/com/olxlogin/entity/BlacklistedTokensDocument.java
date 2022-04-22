package com.olxlogin.entity;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="BlacklistedTokens")
public class BlacklistedTokensDocument {
	//@Id
	//private int id;
	private String token;
	private LocalDate createdDate;
	public BlacklistedTokensDocument(String token, LocalDate createdDate) {
		super();
		this.token = token;
		this.createdDate = createdDate;
	}
	public BlacklistedTokensDocument() {
		super();
	}
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public LocalDate getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}
	@Override
	public String toString() {
		return "BlacklistedTokensDocument [token=" + token + ", createdDate=" + createdDate + "]";
	}
	
}
