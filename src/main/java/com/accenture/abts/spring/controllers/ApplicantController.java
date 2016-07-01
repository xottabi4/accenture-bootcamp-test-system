package com.accenture.abts.spring.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/application")
public class ApplicantController {
	@RequestMapping("")
	  @ResponseBody
	  public String homee() {
	    return "helloeee";
	  }
	@RequestMapping("/")
	  @ResponseBody
	  public String home() {
	    return "hello";
	  }
}
