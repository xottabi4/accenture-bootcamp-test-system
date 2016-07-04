package com.accenture.abts.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.abts.spring.configs.security.SecurityUtils;
import com.accenture.abts.spring.models.User;
import com.accenture.abts.spring.services.UserService;

@RestController
public class SecurityController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/security/account", method = RequestMethod.GET)
	public @ResponseBody User getUserAccount() {
		User user = userService.findUser(SecurityUtils.getCurrentLogin());
		user.setSecurityCode(null);
		return user;
	}

}
