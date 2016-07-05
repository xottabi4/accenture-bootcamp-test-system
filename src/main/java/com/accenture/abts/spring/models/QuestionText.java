package com.accenture.abts.spring.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

public class QuestionText implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8339479888880767113L;

	@Id
	@Column(name = "at_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@Column(name = "test_id")
	private Test test;

	@ManyToOne
	@Column(name = "question_no")
	private Question question;

	@NotNull
	@Column(name = "answer_text")
	private String text;
	
}
