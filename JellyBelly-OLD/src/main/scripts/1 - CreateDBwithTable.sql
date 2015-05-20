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

INSERT INTO `user` (`username`, `password`, `is_enabled`, `is_verified`, `sms_code`, `date_created`, `last_updated`, `created_by`, `last_updated_by`) VALUES 
  ('mkanchwala', 'babuguddu', 1, 0, 0, '2015-05-19 21:52:29', '2015-05-19 21:52:29', NULL, NULL),
  ('nchandra', 'jellybelly', 1, 0, 0, '2015-05-19 22:12:32', '2015-05-19 21:52:29', NULL, NULL);
COMMIT;

