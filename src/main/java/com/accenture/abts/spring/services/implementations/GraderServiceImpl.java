package com.accenture.abts.spring.services.implementations;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.abts.spring.dao.QuestionDao;
import com.accenture.abts.spring.dao.QuestionOptionDao;
import com.accenture.abts.spring.dao.QuestionTextDao;
import com.accenture.abts.spring.dao.TestDao;
import com.accenture.abts.spring.messages.TestJson;
import com.accenture.abts.spring.services.GraderService;

@Service
@Transactional
public class GraderServiceImpl implements GraderService {

	@Autowired
	TestDao testDao;

	@Autowired
	QuestionDao questionDao;

	@Autowired
	QuestionTextDao questionTextDao;

	@Autowired
	QuestionOptionDao questionOptionDao;

	public void createTest(TestJson test) {
//		Test newTest = new Test(test.getName(), test.getDuration());
//		Test savedTest = testDao.save(newTest);
//		Question savedQuestions = questionDao.save(test.get);
		
	}

}
