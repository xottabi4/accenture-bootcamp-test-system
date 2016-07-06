package com.accenture.abts.spring.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.abts.spring.messages.Response;

@RestController
@RequestMapping(value = "/grader")
public class GraderController {
	
	@RequestMapping(value = "/save-questions", method = RequestMethod.POST)
	public @ResponseBody Response createTest() {

		return null;
	}
}
