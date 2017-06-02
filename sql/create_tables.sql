
CREATE TABLE `users` (
  `username` varchar(256) NOT NULL,
  `password` varchar(256) NOT NULL,
  `enabled` tinyint(1) DEFAULT NULL,
  `isr` int(11) DEFAULT NULL,
  `icr` int(11) DEFAULT NULL,
  `created_at` date DEFAULT NULL,
  `updated_at` date DEFAULT NULL,
  `insulin_type` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`username`)
);

CREATE TABLE `authorities` (
  `username` varchar(256) NOT NULL,
  `authority` varchar(256) NOT NULL,
  PRIMARY KEY (`username`,`authority`)
);

CREATE TABLE `entry` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` varchar(256) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_entry_user` (`user_id`),
  CONSTRAINT `fk_entry_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`username`)
);

CREATE TABLE `carbs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(256) DEFAULT NULL,
  `servingsize` varchar(30) DEFAULT NULL,
  `carbsperserving` int(11) DEFAULT NULL,
  `numservings` int(11) DEFAULT NULL,
  `totalcarbs` int(11) NOT NULL,
  `entry_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_carbs_entry` (`entry_id`),
  CONSTRAINT `fk_carbs_entry` FOREIGN KEY (`entry_id`) REFERENCES `entry` (`id`)
);

CREATE TABLE `bloodsugar` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bloodsugar` int(11) NOT NULL,
  `entry_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `entry_id` (`entry_id`),
  CONSTRAINT `fk_bloodsugar_entry` FOREIGN KEY (`entry_id`) REFERENCES `entry` (`id`)
);

CREATE TABLE `insulin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `insulin` double DEFAULT NULL,
  `entry_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `entry_id` (`entry_id`),
  CONSTRAINT `fk_insulin_entry` FOREIGN KEY (`entry_id`) REFERENCES `entry` (`id`)
);

insert into users (username, password, enabled, icr, isr) values ('brian','sheen',true,10,40);

insert into authorities (username, authority) values ('brian', 'ROLE_USER');

insert into users (username, password, enabled) values ('admin','admin',true);

insert into authorities (username, authority) values ('admin', 'ROLE_ADMIN');


