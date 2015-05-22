INSERT INTO language (name,description) VALUES ('Hindi','Sample');
INSERT INTO country (capital, code,description,name,population) values ('New Delhi','IN','Sample','India',120);
INSERT INTO language_country(country_id,language_id) VALUES(1,1);

INSERT INTO user (email,password,username,preferred_language) VALUES ('imkanchwala@gmail.com','123456','mkanchwala',1);  