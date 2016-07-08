package com.accenture.abts.spring.services.implementations;

import java.util.Arrays;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.abts.spring.dao.RoleDao;
import com.accenture.abts.spring.dao.UserDao;
import com.accenture.abts.spring.messages.UserJson;
import com.accenture.abts.spring.models.Role;
import com.accenture.abts.spring.models.User;
import com.accenture.abts.spring.services.AdminService;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

	@Autowired
	UserDao userDao;

	@Autowired
	RoleDao roleDao;

	@Override
	public void createUser(UserJson user) {
		userDao.save(new User(user.getEmail(), user.getName(), user.getSurname(),
				new HashSet<Role>(Arrays.asList(new Role(user.getRole())))));
//		User test=userDao.findByEmail(user.getEmail());
//		System.out.println(test.getEmail());
//		System.out.println(test.getRoles());
	}

}
