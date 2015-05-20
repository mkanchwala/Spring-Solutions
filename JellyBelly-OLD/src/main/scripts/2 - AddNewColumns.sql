ALTER TABLE `user` ADD `first_name` VARCHAR(256) NOT NULL after `password`;
ALTER TABLE `user` ADD `last_name` VARCHAR(256) DEFAULT NULL after `first_name`;

ALTER TABLE `user` ADD `email` VARCHAR(256) NOT NULL after `last_name`;

ALTER TABLE `user` ADD `provider` VARCHAR(256) DEFAULT NULL after `email`;
ALTER TABLE `user` ADD `provider_id` VARCHAR(256) DEFAULT NULL after `provider`;
ALTER TABLE `user` ADD `provider_secret` VARCHAR(256) DEFAULT NULL after `provider_id`;
