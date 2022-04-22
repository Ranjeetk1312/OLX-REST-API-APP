package com.olxlogin.service;




import java.util.List;


import com.olxlogin.dto.User;

public interface LoginService {

	public String authenticate(User user);
	public boolean logout(String authToken);
	public User registers(User user);
	public List<User> returnUserInfo(String authToken);
	public boolean validateToken(String authToken);
}
