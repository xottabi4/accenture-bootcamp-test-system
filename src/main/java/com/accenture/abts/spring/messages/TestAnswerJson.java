package com.accenture.abts.spring.messages;

import java.util.List;

public class TestAnswerJson {
	private String testName;
	private String userEmail;
	private String date;
	private List<QuestionAnswerJson> questionsText;

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<QuestionAnswerJson> getQuestionsText() {
		return questionsText;
	}

	public void setQuestionsText(List<QuestionAnswerJson> questionsText) {
		this.questionsText = questionsText;
	}

}