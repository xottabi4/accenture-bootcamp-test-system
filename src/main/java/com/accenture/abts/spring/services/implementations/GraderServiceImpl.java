package com.accenture.abts.spring.services.implementations;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.accenture.abts.spring.dao.QuestionDao;
import com.accenture.abts.spring.dao.QuestionOptionDao;
import com.accenture.abts.spring.dao.QuestionTextDao;
import com.accenture.abts.spring.dao.TestDao;
import com.accenture.abts.spring.exceptions.IncorectTestTypeException;
import com.accenture.abts.spring.messages.QuestionJson;
import com.accenture.abts.spring.messages.TestAnswerJson;
import com.accenture.abts.spring.models.Question;
import com.accenture.abts.spring.models.QuestionOption;
import com.accenture.abts.spring.models.Test;
import com.accenture.abts.spring.models.UserTestResponse;
import com.accenture.abts.spring.services.GraderService;

@Service
@Transactional
public class GraderServiceImpl implements GraderService {

	@Autowired
	private TestDao testDao;

	@Autowired
	private  QuestionDao questionDao;

	@Autowired
	private QuestionTextDao questionTextDao;

	@Autowired
	private QuestionOptionDao questionOptionDao;

//	Why it throws error
//	@Autowired
//	@Qualifier("UserTestResponse")
//	private UserTestResponse userTestResponse;

	@Override
	public TestAnswerJson viewTest(String testType) throws IncorectTestTypeException {
		List<String> correctValues = new ArrayList<>();
		correctValues.add("Language");
		correctValues.add("Technical");
		if (!correctValues.contains(testType)) {
			throw new IncorectTestTypeException("test type parameter is incorrect");
		}
		Test test = testDao.findByNameAndIsAlive(testType, true);
		List<Question> questions = questionDao.findByTest(test);

		List<QuestionJson> questionsFinal = new ArrayList<QuestionJson>();
		List<QuestionOption> questionOptions;
		QuestionJson tmp;
		List<String> options = new ArrayList<String>();

		for (Question question : questions) {
			tmp = new QuestionJson(question.getNumber(), question.getText(), null);
			questionOptions = questionOptionDao.findByQuestion(question);
			if (questionOptions.size() > 0) {
				for (QuestionOption questionOption : questionOptions) {
					options.add(questionOption.getOptionVal());
				}
				tmp.setOptions(options);
			}

			questionsFinal.add(tmp);
		}

		return null;
	}

	public GraderServiceImpl() {
		super();
	}
	
	

}
