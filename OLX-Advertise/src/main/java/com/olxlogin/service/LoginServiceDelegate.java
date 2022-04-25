package com.olxlogin.service;

public interface LoginServiceDelegate {
	
	public boolean isTokenValid(String authToken);
	public String isIdPresent(int categoryId);
	public String returnUserName(String authToken);
	public String getCategoryFromMastedata(int categoryId);
	public String getStatusFromMastedata(int statusId);

}
