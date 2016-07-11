package com.accenture.abts.spring.services.implementations;

import java.util.ArrayList;
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
	private UserDao userDao;

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private TestDao testDao;

	@Autowired
	private QuestionDao questionDao;

	@Autowired
	private QuestionOptionDao questionOptionDao;

	@Override
	public void createUser(UserJson user) {
		Iterable<Role> rolesIter = roleDao.findAll();
		List<Role> roles = new ArrayList<>();
		rolesIter.forEach(roles::add);
		if (!roles.contains(user.getRole())) {
			// throw role doesnt exist
		}

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

	@Override
	public void makeTestAlive(Long testId) {
		Test test = testDao.findOne(testId);
		test.setIsAlive(true);
	}

	@Override
	public void makeTestDead(Long testId) {
		Test test = testDao.findOne(testId);
		test.setIsAlive(false);
	}

}
