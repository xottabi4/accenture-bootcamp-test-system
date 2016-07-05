package com.accenture.abts.spring.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.accenture.abts.spring.models.QuestionOption;

@Transactional
public interface QuestionOptionDao extends CrudRepository<QuestionOption, Long> {
	
	
	
	
}
