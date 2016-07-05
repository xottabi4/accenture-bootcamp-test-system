package com.accenture.abts.spring.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.accenture.abts.spring.models.Question;
import com.accenture.abts.spring.models.QuestionText;

@Transactional
public interface QuestionTextDao extends CrudRepository<QuestionText, Long> {
	List<QuestionText> findByQuestion(Question question);
}
