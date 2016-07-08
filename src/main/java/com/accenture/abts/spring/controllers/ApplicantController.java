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
import com.accenture.abts.spring.messages.TestJson;
import com.accenture.abts.spring.services.ApplicantService;

@RestController
@RequestMapping(value = "/applicant")
public class ApplicantController {
	@Autowired
	ApplicantService applicantService;

	@RequestMapping(value = "/get-questions", method = RequestMethod.GET)
	public @ResponseBody TestJson getTest(@RequestParam(value = "testType") String testType)
			throws IncorectTestTypeException {
		return applicantService.getTest(testType);
		// try {
		// return applicantService.getTest(testType);
		// } catch (IncorectTestTypeException e) {
		// return new Response(400, null, new Error("incorect parameter",
		// e.getMessage()));
		// // e.printStackTrace();
		// }
	}

	@RequestMapping(value = "/save-answers", method = RequestMethod.POST)
	public @ResponseBody Response saveTest(@RequestParam(value = "testAnswer") TestAnswerJson testAnswer) {
		applicantService.saveTest(testAnswer);
		return new Response(200, "test answers saved", null);
	}

	@ExceptionHandler(value = IncorectTestTypeException.class)
	public @ResponseBody Response ittHandler(IncorectTestTypeException e) {
		return new Response(400, null, new Error("incorect parameter", e.getMessage()));
	}
}
