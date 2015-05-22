INSERT INTO users (email,password,username,preferred_language,date_created,created_by,last_updated,last_updated_by) VALUES ('imkanchwala@gmail.com','spring123','mkanchwala','Hindi','2015-05-22 23:20:00','1','2015-05-22 23:20:00','1');
INSERT INTO users (email,password,username,preferred_language,date_created,created_by,last_updated,last_updated_by) VALUES ('mohamed@ewan.care','spring123','ewan.gy','Arabic','2015-05-22 23:20:00','1','2015-05-22 23:20:00','1');
INSERT INTO users (email,password,username,preferred_language,date_created,created_by,last_updated,last_updated_by) VALUES ('guest@ewan.care','spring123','guest.gy','Hindi','2015-05-22 23:20:00','1','2015-05-22 23:20:00','1');

INSERT INTO role(id, name) VALUES (1,'ROLE_USER');
INSERT INTO role(id, name) VALUES (2,'ROLE_ADMIN');
INSERT INTO role(id, name) VALUES (3,'ROLE_GUEST'); 

INSERT INTO user_role(user_id, role_id) VALUES (1,1);
INSERT INTO user_role(user_id, role_id) VALUES (1,2);
INSERT INTO user_role(user_id, role_id) VALUES (2,1);
INSERT INTO user_role(user_id, role_id) VALUES (3,1);