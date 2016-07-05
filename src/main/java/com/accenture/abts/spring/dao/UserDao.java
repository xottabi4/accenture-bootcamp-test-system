package com.accenture.abts.spring.dao;

import org.springframework.data.repository.CrudRepository;

import com.accenture.abts.spring.models.User;

public interface UserDao extends CrudRepository<User, Long> {
	User findByEmailAndSecurityCode(String email, String securityCode);
	User findByEmail(String email);
}
