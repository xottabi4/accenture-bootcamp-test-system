package com.accenture.abts.spring.messages;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QuestionJson implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1130817324023504443L;

	private Long id;
	private String questionText;
	private List<String> options;
	private List<Boolean> answers;

	public QuestionJson(Long id, String questionText, List<String> options) {
		super();
		this.id = id;
		this.questionText = questionText;
		this.options = options;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
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

	public List<Boolean> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Boolean> answers) {
		this.answers = answers;
	}

}
