package com.accenture.abts.spring.dao;

import org.springframework.data.repository.CrudRepository;

import com.accenture.abts.spring.models.UserResponse;
import com.accenture.abts.spring.models.UserTest;

public interface UserResponseDao extends CrudRepository<UserResponse, Long> {
	UserResponse findByUserTest(UserTest userTest);
}
