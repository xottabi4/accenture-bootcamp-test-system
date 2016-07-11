package com.accenture.abts.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.abts.spring.messages.Error;
import com.accenture.abts.spring.messages.Response;
import com.accenture.abts.spring.messages.UserJson;
import com.accenture.abts.spring.services.AdminService;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {
	@Autowired
	AdminService adminService;

	@RequestMapping(value = "/create-user", method = RequestMethod.POST)
	public @ResponseBody Response createUser(@RequestParam(value = "user") UserJson user) {
		try {
			adminService.createUser(user);
			return new Response(201, "User created", null);
		} catch (Exception e) {
			return new Response(400, null, new Error(e.getStackTrace().toString(), e.getMessage()));
		}
	}

	@RequestMapping(value = "/create-test", method = RequestMethod.POST)
	public @ResponseBody Response createTest(@RequestParam(value = "user") UserJson user) {
		try {
			adminService.createUser(user);
			return new Response(201, "User created", null);
		} catch (Exception e) {
			return new Response(400, null, new Error(e.getStackTrace().toString(), e.getMessage()));
		}
	}
}
