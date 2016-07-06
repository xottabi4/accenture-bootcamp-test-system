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
@Table(name = "answer_text")
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
	@JoinColumn(name = "test_id")
	private Test test;

	@ManyToOne
	@JoinColumn(name = "question_no")
	private Question question;

	@NotNull
	@Column(name = "answer_text")
	private String text;
	
}
