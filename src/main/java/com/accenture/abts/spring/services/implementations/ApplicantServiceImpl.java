package com.accenture.abts.spring.services.implementations;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.abts.spring.dao.QuestionDao;
import com.accenture.abts.spring.dao.QuestionOptionDao;
import com.accenture.abts.spring.dao.TestDao;
import com.accenture.abts.spring.messages.QuestionJson;
import com.accenture.abts.spring.messages.TestJson;
import com.accenture.abts.spring.models.Question;
import com.accenture.abts.spring.models.QuestionOption;
import com.accenture.abts.spring.models.Test;
import com.accenture.abts.spring.services.ApplicantService;

@Service
@Transactional
public class ApplicantServiceImpl implements ApplicantService {

	@Autowired
	TestDao testDao;

	@Autowired
	QuestionDao questionDao;

	@Autowired
	QuestionOptionDao questionOptionDao;

	public TestJson getTest(String testType) {
		Test test = testDao.findByNameAndIsAlive(testType, true);
		List<Question> questions = questionDao.findByTest(test);

		List<QuestionJson> questionsFinal = new ArrayList<QuestionJson>();

		List<QuestionOption> questionOptions;
		QuestionJson tmp;
		List<String> options = new ArrayList<String>();

		for (Question question : questions) {
			questionOptions = questionOptionDao.findByQuestion(question);
			tmp = new QuestionJson(question.getNumber(), question.getText(), null);
			if (questionOptions.size() > 0) {
				for (QuestionOption questionOption : questionOptions) {
					options.add(questionOption.getOptionVal());
				}
				tmp.setOptions(options);
			}
			questionsFinal.add(tmp);
			options.clear();
		}
		TestJson testFinal = new TestJson(test.getName(), test.getDuration(), questionsFinal);
		return testFinal;
	}

}
