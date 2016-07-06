package com.accenture.abts.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.abts.spring.messages.TestJson;
import com.accenture.abts.spring.services.ApplicantService;

@RestController
@RequestMapping(value = "/applicant")
public class ApplicantController {
	@Autowired
	ApplicantService applicantService;

	@RequestMapping(value = "/get-questions", method = RequestMethod.GET)
	public @ResponseBody TestJson getTest(@RequestParam(value = "testType") String testType) {
		return applicantService.getTest(testType);
	}
}
