package com.accenture.abts.spring.services.implementations;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.abts.spring.dao.QuestionDao;
import com.accenture.abts.spring.dao.QuestionOptionDao;
import com.accenture.abts.spring.dao.TestDao;
import com.accenture.abts.spring.dao.UserDao;
import com.accenture.abts.spring.dao.UserResponseDao;
import com.accenture.abts.spring.dao.UserTestDao;
import com.accenture.abts.spring.exceptions.IncorectTestTypeException;
import com.accenture.abts.spring.messages.QuestionAnswerJson;
import com.accenture.abts.spring.messages.QuestionJson;
import com.accenture.abts.spring.messages.TestAnswerJson;
import com.accenture.abts.spring.messages.TestJson;
import com.accenture.abts.spring.models.Question;
import com.accenture.abts.spring.models.QuestionOption;
import com.accenture.abts.spring.models.Test;
import com.accenture.abts.spring.models.UserResponse;
import com.accenture.abts.spring.models.UserTest;
import com.accenture.abts.spring.services.ApplicantService;

@Service
@Transactional
public class ApplicantServiceImpl implements ApplicantService {

	@Autowired
	private TestDao testDao;

	@Autowired
	private QuestionDao questionDao;

	@Autowired
	private QuestionOptionDao questionOptionDao;

	@Autowired
	private UserResponseDao userResponseDao;

	@Autowired
	private UserTestDao userTestDao;

	@Autowired
	private UserDao userDao;

	@Override
	public TestJson getTest(String testType) throws IncorectTestTypeException {
		if (testType!="Language"||testType!="Technical") {
			throw new IncorectTestTypeException("test type parameter is incorrect");
		}
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

	@Override
	public void saveTest(TestAnswerJson testAnswer) {
		UserTest userTest = new UserTest(userDao.findByEmail(testAnswer.getUserEmail()),
				testDao.findByNameAndIsAlive(testAnswer.getTestName(), true), testAnswer.getDate());
		UserTest newUserTest = userTestDao.save(userTest);
		UserResponse userResponse;
		for (QuestionAnswerJson questionAnswer : testAnswer.getQuestionsText()) {
			if ((questionAnswer.getOptions() == null) || (questionAnswer.getOptions().size() < 1)) {
				userResponse = new UserResponse();
				userResponse.setQuestion(questionDao.findOne(questionAnswer.getId()));
				userResponse.setUserTest(newUserTest);
				userResponse.setAnsweredText(questionAnswer.getAnswer());
				userResponse.setOptionVal(null);
				userResponseDao.save(userResponse);
			} else {
				for (String option : questionAnswer.getOptions()) {
					userResponse = new UserResponse();
					userResponse.setQuestion(questionDao.findOne(questionAnswer.getId()));
					userResponse.setUserTest(newUserTest);
					userResponse.setAnsweredText(null);
					userResponse.setOptionVal(option);
					userResponseDao.save(userResponse);
				}
			}
		}
	}

	public ApplicantServiceImpl() {
		super();
	}

	public ApplicantServiceImpl(TestDao testDao, QuestionDao questionDao, QuestionOptionDao questionOptionDao,
			UserResponseDao userResponseDao, UserTestDao userTestDao, UserDao userDao) {
		super();
		this.testDao = testDao;
		this.questionDao = questionDao;
		this.questionOptionDao = questionOptionDao;
		this.userResponseDao = userResponseDao;
		this.userTestDao = userTestDao;
		this.userDao = userDao;
	}

}
