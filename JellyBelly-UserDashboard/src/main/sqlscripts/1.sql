# SQL Manager 2010 for MySQL 5+
# ---------------------------------------
# Host     : localhost
# Port     : 3306
# Database : jellybelly_new


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

SET FOREIGN_KEY_CHECKS=0;

DROP DATABASE IF EXISTS `jellybelly_new`;
CREATE DATABASE `jellybelly_new` CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci';
USE `jellybelly_new`;

#
# Structure for the `UserConnection` table : 
#
CREATE TABLE UserConnection (userId varchar(255) not null,
    providerId varchar(255) not null,
    providerUserId varchar(255),
    rank int not null,
    displayName varchar(255),
    profileUrl varchar(512),
    imageUrl varchar(512),
    accessToken varchar(255) not null,
    secret varchar(255),
    refreshToken varchar(255),
    expireTime bigint,
    primary key (userId, providerId, providerUserId));
CREATE UNIQUE INDEX UserConnectionRank on UserConnection(userId, providerId, rank);

#
# Structure for the `user_accounts` table : 
#
CREATE TABLE `user_accounts` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `creation_time` datetime NOT NULL,
  `email` varchar(100) NOT NULL,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `modification_time` datetime NOT NULL,
  `password` varchar(255),
  `role` varchar(20) NOT NULL,
  `sign_in_provider` varchar(20),
  `version` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
