package com.accenture.abts.spring.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.accenture.abts.spring.models.Question;
import com.accenture.abts.spring.models.QuestionOption;

public interface QuestionOptionDao extends CrudRepository<QuestionOption, Long> {
	List<QuestionOption> findByQuestion(Question question);
	
	
	
}
