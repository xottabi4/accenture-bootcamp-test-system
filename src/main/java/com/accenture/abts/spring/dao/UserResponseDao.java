package com.accenture.abts.spring.dao;

import org.springframework.data.repository.CrudRepository;

import com.accenture.abts.spring.models.UserTestResponse;
import com.accenture.abts.spring.models.UserTest;

public interface UserResponseDao extends CrudRepository<UserTestResponse, Long> {
	UserTestResponse findByUserTest(UserTest userTest);
}
