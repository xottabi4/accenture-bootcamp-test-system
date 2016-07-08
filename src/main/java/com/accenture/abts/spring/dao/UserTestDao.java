package com.accenture.abts.spring.dao;

import org.springframework.data.repository.CrudRepository;

import com.accenture.abts.spring.models.Test;
import com.accenture.abts.spring.models.User;
import com.accenture.abts.spring.models.UserTest;

public interface UserTestDao extends CrudRepository<UserTest, Long> {
	UserTest findByUserAndTest(User user,Test test);
}
