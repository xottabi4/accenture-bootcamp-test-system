package com.accenture.abts.spring.services.implementations;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.accenture.abts.spring.dao.QuestionDao;
import com.accenture.abts.spring.dao.QuestionOptionDao;
import com.accenture.abts.spring.dao.TestDao;
import com.accenture.abts.spring.dao.UserDao;
import com.accenture.abts.spring.dao.UserResponseDao;
import com.accenture.abts.spring.dao.UserTestDao;
import com.accenture.abts.spring.messages.QuestionAnswerJson;
import com.accenture.abts.spring.messages.TestAnswerJson;
import com.accenture.abts.spring.models.Question;
import com.accenture.abts.spring.models.User;
import com.accenture.abts.spring.models.UserResponse;
import com.accenture.abts.spring.models.UserTest;

@RunWith(MockitoJUnitRunner.class)
public class ApplicantServiceImplTest {
}/*
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

	// @Before
	// public void setUp() {
	// testDaoMock = Mockito.mock(TestDao.class);
	// questionDaoMock = Mockito.mock(QuestionDao.class);
	// questionOptionDaoMock = Mockito.mock(QuestionOptionDao.class);
	// userResponseDaoMock = Mockito.mock(UserResponseDao.class);
	// userTestDaoMock = Mockito.mock(UserTestDao.class);
	// userDaoMock = Mockito.mock(UserDao.class);
	// applicantService = new ApplicantServiceImpl(testDaoMock, questionDaoMock,
	// questionOptionDaoMock,
	// userResponseDaoMock, userTestDaoMock, userDaoMock);
	// }

	@Test
	public void saveTestSuccessfuly() throws Exception {
		TestAnswerJson json = new TestAnswerJson();
		json.setDate("asd");
		List<QuestionAnswerJson> questionsText = new ArrayList<>();
		List<String> options = new ArrayList<>();
		options.add("");
		questionsText.add(new QuestionAnswerJson((long) 1, "answer", options));
		json.setQuestionsText(questionsText);
		json.setTestName("Language");
		json.setUserEmail("m@m.m");

//		 when(accountDAO.save(any(Account.class))).thenReturn(persistedAccount);
		
		
		User user = userDaoMock.save(new User("m@m.m", "name"));
		System.out.println(user.getEmail());

		com.accenture.abts.spring.models.Test test = testDaoMock
				.save(new com.accenture.abts.spring.models.Test("Language", (long) 36000, true));
		Question question = questionDaoMock.save(new Question((long) 1, test, "this is question"));
		applicantService.saveTest(json);

		UserTest userTest = userTestDaoMock.findByUserAndTest(user, test);
		UserResponse userResponse = userResponseDaoMock.findByUserTest(userTest);

		assertNotNull(userTest);
		assertNotNull(userResponse);
		// assertEquals(question, userResponse);
		// assertEquals(test, userTest.getTest());
		// assertEquals(user, userTest.getUser());
	}

}
*/