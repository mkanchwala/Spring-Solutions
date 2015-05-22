CREATE DATABASE IF NOT Exists smartsoft;
USE smartsoft;

INSERT INTO language (name,description,date_created,created_by,last_updated,last_updated_by) VALUES ('Hindi','Sample','2015-05-22 23:20:00','1','2015-05-22 23:20:00','1');
INSERT INTO country (code,capital,description,name,population,date_created,created_by,last_updated,last_updated_by) values ('IN','New Delhi','Sample','India',120,'2015-05-22 23:20:00','1','2015-05-22 23:20:00','1');

INSERT INTO language (name,description,date_created,created_by,last_updated,last_updated_by) VALUES ('Arabic','Sample','2015-05-22 23:22:00','1','2015-05-22 23:22:00','1');
INSERT INTO country (code,capital,description,name,population,date_created,created_by,last_updated,last_updated_by) values ('EG','Cairo','Sample','Egypt',120,'2015-05-22 23:22:00','1','2015-05-22 23:22:00','1');

INSERT INTO language_country(code,name,date_created,created_by,last_updated,last_updated_by) VALUES('IN','Hindi','2015-05-22 23:20:00','1','2015-05-22 23:20:00','1');
INSERT INTO language_country(code,name,date_created,created_by,last_updated,last_updated_by) VALUES('EG','arabic','2015-05-22 23:22:00','1','2015-05-22 23:22:00','1');
