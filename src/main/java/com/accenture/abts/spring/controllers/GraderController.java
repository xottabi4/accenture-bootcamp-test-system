package com.accenture.abts.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.abts.spring.exceptions.IncorectTestTypeException;
import com.accenture.abts.spring.messages.Error;
import com.accenture.abts.spring.messages.Response;
import com.accenture.abts.spring.messages.TestAnswerJson;
import com.accenture.abts.spring.services.GraderService;

@RestController
@RequestMapping(value = "/grader")
public class GraderController {

	@Autowired
	GraderService graderService;

	@RequestMapping(value = "/view-test", method = RequestMethod.GET)
	public @ResponseBody TestAnswerJson viewTest(@RequestParam(value = "testType") String testType)
			throws IncorectTestTypeException {
		return graderService.viewTest(testType);
	}

	@ExceptionHandler(value = IncorectTestTypeException.class)
	public @ResponseBody Response ittHandler(IncorectTestTypeException e) {
		return new Response(400, null, new Error("incorect parameter", e.getMessage()));
	}
}
