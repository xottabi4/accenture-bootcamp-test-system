package com.accenture.abts.spring.services;

import com.accenture.abts.spring.exceptions.IncorectTestTypeException;
import com.accenture.abts.spring.messages.TestAnswerJson;
import com.accenture.abts.spring.messages.TestJson;

public interface ApplicantService {
	public TestJson getTest(String testType) throws IncorectTestTypeException;

	public void saveTest(TestAnswerJson testAnswer);
}
