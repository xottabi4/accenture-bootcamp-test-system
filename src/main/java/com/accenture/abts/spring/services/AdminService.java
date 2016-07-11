package com.accenture.abts.spring.services;

import com.accenture.abts.spring.messages.TestJson;
import com.accenture.abts.spring.messages.UserJson;

public interface AdminService {
	public void createUser(UserJson user);

	public void createTest(TestJson test);
	
	public void makeTestAlive(Long testId);
	
	public void makeTestDead(Long testId);
}
