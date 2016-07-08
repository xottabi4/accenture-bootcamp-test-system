
package com.accenture.abts.spring.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user_response")
public class UserResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6321249853809905735L;

	@Id
	@Column(name = "user_response_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "question_id")
	private Question question;

	@ManyToOne
	@JoinColumn(name = "test_id")
	private UserTest userTest;

	@NotNull
	@Column(name = "option_val")
	private String optionVal;

	@NotNull
	@Column(name = "answered_text")
	private String answeredText;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public UserTest getUserTest() {
		return userTest;
	}

	public void setUserTest(UserTest userTest) {
		this.userTest = userTest;
	}

	public String getOptionVal() {
		return optionVal;
	}

	public void setOptionVal(String optionVal) {
		this.optionVal = optionVal;
	}

	public String getAnsweredText() {
		return answeredText;
	}

	public void setAnsweredText(String answeredText) {
		this.answeredText = answeredText;
	}

}