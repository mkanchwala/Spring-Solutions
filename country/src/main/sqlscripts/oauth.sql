drop database smartsoft;

create database smartsoft;

use smartsoft;

create table oauth_client_details (
  client_id VARCHAR(256) PRIMARY KEY,
  resource_ids VARCHAR(256),
  client_secret VARCHAR(256),
  scope VARCHAR(256),
  authorized_grant_types VARCHAR(256),
  web_server_redirect_uri VARCHAR(256),
  authorities VARCHAR(256),
  autoapprove VARCHAR(256),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096)
);

create table oauth_client_token (
  token_id VARCHAR(256),
  token BLOB,
  authentication_id VARCHAR(256),
  user_name VARCHAR(256),
  client_id VARCHAR(256)
);

create table oauth_access_token (
  token_id VARCHAR(256),
  token BLOB,
  authentication_id VARCHAR(256),
  user_name VARCHAR(256),
  client_id VARCHAR(256),
  authentication BLOB,
  refresh_token VARCHAR(256)
);

create table oauth_refresh_token (
  token_id VARCHAR(256),
  token BLOB,
  authentication BLOB
);

create table oauth_code (
  code VARCHAR(256), authentication BLOB
);

-- customized oauth_client_details table


CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `ix_auth_username` (`username`,`authority`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

curl -X POST -k -vu clientapp:123456 http://localhost:8080/oauth/token -H "Accept: application/json" -d "password=spring123&username=mkanchwala&grant_type=password&scope=read%20write&client_secret=123456&client_id=clientapp"
curl http://localhost:8080/countries -H "Authorization: Bearer 60c4fe1f-2ff6-4316-8d32-9dab0c36cd1c"

