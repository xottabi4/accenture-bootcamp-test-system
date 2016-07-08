package com.accenture.abts.spring.messages;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class QuestionAnswerJson implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4115843232250662720L;

	private Long id;
	private String answer;
	private List<String> options;

	public QuestionAnswerJson(Long id, String answer, List<String> options) {
		super();
		this.id = id;
		this.answer = answer;
		this.options = options;
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public List<String> getOptions() {
		return options;
	}

	public void setOptions(List<String> options) {
		List<String> newOptions = new ArrayList<>();
		for (String option : options) {
			newOptions.add(option);
		}
		this.options = newOptions;
	}

}
