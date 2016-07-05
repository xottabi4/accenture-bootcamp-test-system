package com.accenture.abts.spring.messages;

import java.util.List;

public class TestJson {
	private String name;
	private Long duration;
	private List<QuestionJson> questions;

	public TestJson(String name, Long duration, List<QuestionJson> questions) {
		super();
		this.name = name;
		this.duration = duration;
		this.questions = questions;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public List<QuestionJson> getQuestions() {
		return questions;
	}

	public void setQuestions(List<QuestionJson> questions) {
		this.questions = questions;
	}

}