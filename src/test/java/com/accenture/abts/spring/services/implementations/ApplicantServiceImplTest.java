package com.accenture.abts.spring.services.implementations;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.accenture.abts.spring.dao.QuestionDao;
import com.accenture.abts.spring.dao.QuestionOptionDao;
import com.accenture.abts.spring.dao.TestDao;
import com.accenture.abts.spring.dao.UserDao;
import com.accenture.abts.spring.dao.UserResponseDao;
import com.accenture.abts.spring.dao.UserTestDao;
import com.accenture.abts.spring.messages.QuestionAnswerJson;
import com.accenture.abts.spring.messages.TestAnswerJson;

@RunWith(MockitoJUnitRunner.class)
public class ApplicantServiceImplTest {

	@InjectMocks
	private ApplicantServiceImpl applicantService = new ApplicantServiceImpl();
	@Mock
	private TestDao testDaoMock;
	@Mock
	private QuestionDao questionDaoMock;
	@Mock
	private QuestionOptionDao questionOptionDaoMock;
	@Mock
	private UserResponseDao userResponseDaoMock;
	@Mock
	private UserTestDao userTestDaoMock;
	@Mock
	private UserDao userDaoMock;

	@Before
	public void setUp() {
		testDaoMock = Mockito.mock(TestDao.class);
		questionDaoMock = Mockito.mock(QuestionDao.class);
		questionOptionDaoMock = Mockito.mock(QuestionOptionDao.class);
		userResponseDaoMock = Mockito.mock(UserResponseDao.class);
		userTestDaoMock = Mockito.mock(UserTestDao.class);
		userDaoMock = Mockito.mock(UserDao.class);
		applicantService = new ApplicantServiceImpl(testDaoMock, questionDaoMock, questionOptionDaoMock,
				userResponseDaoMock, userTestDaoMock, userDaoMock);
	}

	@Test
	public void saveTestSuccessfuly() throws Exception {

//		final TestAnswerJson json = createTestAnswer();
//		applicantService.saveTest(json);
//		Mockito.verify(questionDaoMock, Mockito.times(1)).save(json.getQuestionsText().get(0).g);

	}

	private static TestAnswerJson createTestAnswer() {
		TestAnswerJson json = new TestAnswerJson();
		json.setDate("asd");
		List<QuestionAnswerJson> questionsText = new ArrayList<>();
		List<String> options = new ArrayList<>();
		options.add("");
		questionsText.add(new QuestionAnswerJson((long) 1, "answer", options));
		json.setQuestionsText(questionsText);
		json.setTestName("Language");
		json.setUserEmail("m@m.m");
		return json;
	}
}