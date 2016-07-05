/*
package com.accenture.abts.spring.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user_response")


public class UserResponse {
	@Id
	@Column(name = "response_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long response_id;
	
	@Id
	@Column(name = "test_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long test_id;
	
	
	@NotNull
	@Column(name = "option_val")
	private String option_val;
	
	
	@NotNull
	@Column(name = "answered_text")
	private String answered_text;


	public Long getResponse_id() {
		return response_id;
	}


	public void setResponse_id(Long response_id) {
		this.response_id = response_id;
	}


	public Long getTest_id() {
		return test_id;
	}


	public void setTest_id(Long test_id) {
		this.test_id = test_id;
	}


	public String getOption_val() {
		return option_val;
	}


	public void setOption_val(String option_val) {
		this.option_val = option_val;
	}


	public String getAnswered_text() {
		return answered_text;
	}


	public void setAnswered_text(String answered_text) {
		this.answered_text = answered_text;
	}
	
	
	
	

}
*/