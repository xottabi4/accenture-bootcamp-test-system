package com.accenture.abts.spring.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.accenture.abts.spring.models.User;

@Transactional
public interface UserDao extends CrudRepository<User, Long> {
	User findByEmailAndSecurityCode(String email, String securityCode);
	User findByEmail(String email);
}
