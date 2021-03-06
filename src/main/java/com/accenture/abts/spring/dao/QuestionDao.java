package com.accenture.abts.spring.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.accenture.abts.spring.models.Question;
import com.accenture.abts.spring.models.Test;

public interface QuestionDao  extends CrudRepository<Question, Long>{
	List<Question> findByTest(Test test);
}
