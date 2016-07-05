package com.accenture.abts.spring.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "question_option")

public class QuestionOption {
	@Id
	@Column(name = "qop_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long qop_id;
	
	
	@Id
	@Column(name = "test_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long test_id;
	
	
	@Id
	@Column(name = "question_no")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long question_no;
	
	

	
	
	@NotNull
	@Column(name = "option_val")
	private String option_val;
	
	@NotNull
	@Column(name = "is_answer")
	private Boolean is_answer;
		
	
	
@ManyToOne(fetch = FetchType.EAGER)
	@JoinTable(name = "questions", 
	joinColumns = { @JoinColumn(name = "question_no", referencedColumnName = "question_no") }, 
	inverseJoinColumns = { @JoinColumn(name = "question_no", table = "answer_text", referencedColumnName = "question_no") })
	private String[] answers;



public Long getQop_id() {
	return qop_id;
}



public void setQop_id(Long qop_id) {
	this.qop_id = qop_id;
}



public Long getTest_id() {
	return test_id;
}



public void setTest_id(Long test_id) {
	this.test_id = test_id;
}



public Long getQuestion_no() {
	return question_no;
}



public void setQuestion_no(Long question_no) {
	this.question_no = question_no;
}





public String getOption_val() {
	return option_val;
}



public void setOption_val(String option_val) {
	this.option_val = option_val;
}



public Boolean getIs_answer() {
	return is_answer;
}



public void setIs_answer(Boolean is_answer) {
	this.is_answer = is_answer;
}



public String[] getAnswers() {
	return answers;
}



public void setAnswers(String[] answers) {
	this.answers = answers;
} 
	
		
	
	

}
