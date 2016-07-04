package com.accenture.abts.spring.services.implementations;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.abts.spring.dao.UserDao;
import com.accenture.abts.spring.models.User;
import com.accenture.abts.spring.services.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	UserDao userDao;

	public User findUser(String email, String securityCode) {
		return userDao.findByEmailAndSecurityCode(email, securityCode);
	}

	public User findUser(String email) {
		return userDao.findByEmail(email);
	}

}
