package com.accenture.abts.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.abts.spring.models.User;
import com.accenture.abts.spring.services.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public @ResponseBody User getUserAccount(@RequestParam(value = "email") String email,
			@RequestParam(value = "password") String securityCode) {
		System.out.println(email+" "+ securityCode);
		try {
			User user = userService.findUser(email, securityCode);
			user.setSecurityCode(null);
			return user;
			
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

}
