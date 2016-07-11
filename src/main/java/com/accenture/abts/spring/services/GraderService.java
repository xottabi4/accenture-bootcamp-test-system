package com.accenture.abts.spring.services;

import com.accenture.abts.spring.exceptions.IncorectTestTypeException;
import com.accenture.abts.spring.messages.TestAnswerJson;

public interface GraderService {
	public TestAnswerJson viewTest(String testType) throws IncorectTestTypeException ;
}
