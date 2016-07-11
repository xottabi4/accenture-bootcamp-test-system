package com.accenture.abts.spring.services.implementations;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.abts.spring.dao.QuestionDao;
import com.accenture.abts.spring.dao.QuestionOptionDao;
import com.accenture.abts.spring.dao.RoleDao;
import com.accenture.abts.spring.dao.TestDao;
import com.accenture.abts.spring.dao.UserDao;
import com.accenture.abts.spring.messages.QuestionJson;
import com.accenture.abts.spring.messages.TestJson;
import com.accenture.abts.spring.messages.UserJson;
import com.accenture.abts.spring.models.Question;
import com.accenture.abts.spring.models.QuestionOption;
import com.accenture.abts.spring.models.Role;
import com.accenture.abts.spring.models.Test;
import com.accenture.abts.spring.models.User;
import com.accenture.abts.spring.services.AdminService;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

	@Autowired
	UserDao userDao;

	@Autowired
	RoleDao roleDao;

	@Autowired
	TestDao testDao;

	@Autowired
	QuestionDao questionDao;

	@Autowired
	QuestionOptionDao questionOptionDao;

	@Override
	public void createUser(UserJson user) {
		userDao.save(new User(user.getEmail(), user.getName(), user.getSurname(),
				new HashSet<Role>(Arrays.asList(new Role(user.getRole())))));
	}

	@Override
	public void createTest(TestJson test) {
		Test newTest = new Test(test.getName(), test.getDuration(), false);
		Test savedTest = testDao.save(newTest);
		List<QuestionJson> questions = test.getQuestions();
		for (QuestionJson question : questions) {
			Question newQuestion = new Question(question.getId(), savedTest, question.getQuestionText());
			questionDao.save(newQuestion);
			if (question.getOptions() != null || question.getOptions().size() > 0) {
				List<String> options = question.getOptions();
				List<Boolean> answers = question.getAnswers();
				for (int i = 0; i < options.size(); i++) {
					QuestionOption questionOption = new QuestionOption(newQuestion, options.get(i), answers.get(i));
					questionOptionDao.save(questionOption);
				}
			}

		}

	}

}
