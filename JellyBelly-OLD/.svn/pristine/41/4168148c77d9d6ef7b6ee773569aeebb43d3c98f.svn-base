# MYSQL
# ---------------------------------------
# Host     : localhost
# Port     : 3306
# Database : jellybelly


SET FOREIGN_KEY_CHECKS=0;

#
# Structure for the `user` table : 
#

CREATE DATABASE jellybelly;

USE jellybelly;

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(256) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `is_enabled` tinyint(1) DEFAULT 1,
  `is_verified` tinyint(1) DEFAULT 0,
  `sms_code` int(11) DEFAULT 0000,
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_updated` datetime DEFAULT NULL,
  `created_by` varchar(256) DEFAULT NULL,
  `last_updated_by` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

