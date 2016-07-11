SET FOREIGN_KEY_CHECKS = 0;

-- users and roles inserts
INSERT INTO roles(role_type) VALUES ('grader'), ('applicant'), ('recruiter');
INSERT INTO user(name,surname,email,security_code) VALUES ('name','surname','g@g.g','a'), ('name','surname','r@r.r','a'), ('name','surname','a@a.a','a');
INSERT INTO user_roles(user_id,role_id) VALUES ((SELECT user_id from user WHERE email='a@a.a'),2),((SELECT user_id from user WHERE email='g@g.g'),1),((SELECT user_id from user WHERE email='r@r.r'),3);

-- question inserts
INSERT INTO `answer_text` (`at_id`,`question_id`,`answer_text`) VALUES (7,1,'Sunlight reaches Earth\'s atmosphere and is scattered in all directions by all the gases and particles in the air. Blue light is scattered in all directions by the tiny molecules of air in Earth\'s atmosphere. Blue is scattered more than other colors because it travels as shorter, smaller waves. This is why we see a blue sky most of the time.');
INSERT INTO `answer_text` (`at_id`,`question_id`,`answer_text`) VALUES (9,3,'Yes');
INSERT INTO `answer_text` (`at_id`,`question_id`,`answer_text`) VALUES (11,5,'class is an extensible program-code-template for creating objects');
INSERT INTO `answer_text` (`at_id`,`question_id`,`answer_text`) VALUES (12,6,'Food, medicine');

INSERT INTO `question_option` (`qop_id`,`question_id`,`option_val`,`is_answer`) VALUES (8,8,'Dog',1);
INSERT INTO `question_option` (`qop_id`,`question_id`,`option_val`,`is_answer`) VALUES (10,10,'4',1);

INSERT INTO `questions` (`q_id`,`question_id`,`test_id`,`question_text`) VALUES (7,1,1,'Why is the sky blue?');
INSERT INTO `questions` (`q_id`,`question_id`,`test_id`,`question_text`) VALUES (8,2,1,'What is your favourite pet?');
INSERT INTO `questions` (`q_id`,`question_id`,`test_id`,`question_text`) VALUES (9,3,1,'Do you even lift?');
INSERT INTO `questions` (`q_id`,`question_id`,`test_id`,`question_text`) VALUES (10,4,2,'2+2?');
INSERT INTO `questions` (`q_id`,`question_id`,`test_id`,`question_text`) VALUES (11,5,2,'Write the definition of class');
INSERT INTO `questions` (`q_id`,`question_id`,`test_id`,`question_text`) VALUES (12,6,2,'Where NaCl is used?');

INSERT INTO `test` (`test_id`,`test_name`,`duration`,`is_alive`) VALUES (1,'Language',3600000,1);
INSERT INTO `test` (`test_id`,`test_name`,`duration`,`is_alive`) VALUES (2,'Technical',3600000,1);

INSERT INTO `user_response` (`user_response_id`,`question_id`,`test_id`,`option_val`,`answered_text`) VALUES (7,1,1,'text answer','Blue light is shattered in all directions, like it goes through the prism');
INSERT INTO `user_response` (`user_response_id`,`question_id`,`test_id`,`option_val`,`answered_text`) VALUES (8,8,1,'Cat','selected option');
INSERT INTO `user_response` (`user_response_id`,`question_id`,`test_id`,`option_val`,`answered_text`) VALUES (9,3,1,'text answer','Yes');
INSERT INTO `user_response` (`user_response_id`,`question_id`,`test_id`,`option_val`,`answered_text`) VALUES (10,10,2,'4','selected option');
INSERT INTO `user_response` (`user_response_id`,`question_id`,`test_id`,`option_val`,`answered_text`) VALUES (11,5,2,'text answer','Class is a template for creating, or instantiating, specific objects within');
INSERT INTO `user_response` (`user_response_id`,`question_id`,`test_id`,`option_val`,`answered_text`) VALUES (12,6,2,'text answer','It is used as food');

INSERT INTO `user_test` (`user_test_id`,`user_id`,`test_id`,`date`) VALUES (1,1,1,'2016-01-01');
INSERT INTO `user_test` (`user_test_id`,`user_id`,`test_id`,`date`) VALUES (7,1,1,'2016-01-01');
INSERT INTO `user_test` (`user_test_id`,`user_id`,`test_id`,`date`) VALUES (8,1,1,'2016-01-01');
INSERT INTO `user_test` (`user_test_id`,`user_id`,`test_id`,`date`) VALUES (9,4,2,'2016-01-05');
INSERT INTO `user_test` (`user_test_id`,`user_id`,`test_id`,`date`) VALUES (10,4,2,'2016-01-05');
INSERT INTO `user_test` (`user_test_id`,`user_id`,`test_id`,`date`) VALUES (11,4,2,'2016-01-05');
