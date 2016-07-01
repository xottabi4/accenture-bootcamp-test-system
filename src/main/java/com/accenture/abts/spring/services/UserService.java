package com.accenture.abts.spring.services;

import com.accenture.abts.spring.models.User;

public interface UserService {
	public User findUser(String email, String securityCode);
}
