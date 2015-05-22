INSERT INTO language (name,description,date_created,created_by,last_updated,last_updated_by) VALUES ('Hindi','Sample','2015-05-22 23:20:00','1','2015-05-22 23:20:00','1');
INSERT INTO country (code,capital,description,name,population,date_created,created_by,last_updated,last_updated_by) values ('IN','New Delhi','Sample','India',120,'2015-05-22 23:20:00','1','2015-05-22 23:20:00','1');

INSERT INTO language (name,description,date_created,created_by,last_updated,last_updated_by) VALUES ('Arabic','Sample','2015-05-22 23:22:00','1','2015-05-22 23:22:00','1');
INSERT INTO country (code,capital,description,name,population,date_created,created_by,last_updated,last_updated_by) values ('EG','Cairo','Sample','Egypt',120,'2015-05-22 23:22:00','1','2015-05-22 23:22:00','1');

INSERT INTO language_country(code,name,date_created,created_by,last_updated,last_updated_by) VALUES('IN','Hindi','2015-05-22 23:20:00','1','2015-05-22 23:20:00','1');
INSERT INTO language_country(code,name,date_created,created_by,last_updated,last_updated_by) VALUES('EG','arabic','2015-05-22 23:22:00','1','2015-05-22 23:22:00','1');

INSERT INTO user (email,password,username,preferred_language,date_created,created_by,last_updated,last_updated_by) VALUES ('imkanchwala@gmail.com','123456','mkanchwala','Hindi','2015-05-22 23:20:00','1','2015-05-22 23:20:00','1');
INSERT INTO user (email,password,username,preferred_language,date_created,created_by,last_updated,last_updated_by) VALUES ('mohamed@ewan.care','123456','ewan.gy','Arabic','2015-05-22 23:20:00','1','2015-05-22 23:20:00','1');
INSERT INTO user (email,password,username,preferred_language,date_created,created_by,last_updated,last_updated_by) VALUES ('guest@ewan.care','123456','guest.gy','Hindi','2015-05-22 23:20:00','1','2015-05-22 23:20:00','1');

INSERT INTO role(id, name) VALUES (1,'ROLE_USER');
INSERT INTO role(id, name) VALUES (2,'ROLE_ADMIN');
INSERT INTO role(id, name) VALUES (3,'ROLE_GUEST'); 

INSERT INTO user_role(user_id, role_id) VALUES (1,1);
INSERT INTO user_role(user_id, role_id) VALUES (1,2);
INSERT INTO user_role(user_id, role_id) VALUES (2,1);
INSERT INTO user_role(user_id, role_id) VALUES (3,1);