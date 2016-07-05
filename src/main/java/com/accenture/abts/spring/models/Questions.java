package com.accenture.abts.spring.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "questions")

public class Questions {
	@Id
	@Column(name = "question_no")
	@GeneratedValue(strategy = GenerationType.AUTO)

private Long question_no;
	
	@Id
	@Column(name = "test_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	@Column(name = "question_text")
	private String question_text;
	


	
	
	
	public Long getQuestion_no() {
		return question_no;
	}

	public void setQuestion_no(Long question_no) {
		this.question_no = question_no;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getQuestion_text() {
		return question_text;
	}

	public void setQuestion_text(String question_text) {
		this.question_text = question_text;
	}
	
	






















}



