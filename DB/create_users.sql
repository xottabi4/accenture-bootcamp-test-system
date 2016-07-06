INSERT INTO user(name,surname,email,security_code)
VALUES ('name','surname','a@a.a','asd'),
('name','surname','g@g.g','zxc');

INSERT INTO roles(role_type)
VALUES ('grader'),
('applicant'),
('reviewer');

insert into user_roles(user_id,role_id)
Values ((SELECT user_id from user WHERE email='a@a.a'),2),
((SELECT user_id from user WHERE email='g@g.g'),1);

select * from user;
select * from user_roles;
select * from roles;


-- INSERT INTO test(test_name,duration)
-- VALUES ('first',10000);
-- 
-- INSERT INTO test(test_name)
-- VALUES ('second');
-- select * from test;
-- 
-- 
-- 
