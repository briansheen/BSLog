
CREATE TABLE `user` (
  `username` varchar(256) NOT NULL,
  `enabled` tinyint(1) DEFAULT NULL,
  `isr` int(11) DEFAULT NULL,
  `icr` int(11) DEFAULT NULL,
  `created_at` date DEFAULT NULL,
  `updated_at` date DEFAULT NULL,
  `insulin_type` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB;

CREATE TABLE `authorities` (
  `username` varchar(256) NOT NULL,
  `authority` varchar(256) NOT NULL,
  PRIMARY KEY (`username`,`authority`)
) ENGINE=InnoDB;

CREATE TABLE `entry` (
  `eid` int(11) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `time` time NOT NULL,
  `username` varchar(256) NOT NULL,
  `bloodsugar` int(11) NOT NULL,
  `bolus` double DEFAULT NULL,
  `total_carbs` int(11) DEFAULT NULL,
  PRIMARY KEY (`eid`),
  KEY `fk_entry_user` (`username`),
  CONSTRAINT `fk_entry_user` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE `carb` (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(256) DEFAULT NULL,
  `serving_size` varchar(30) DEFAULT NULL,
  `carbs_per_serving` int(11) DEFAULT NULL,
  `num_servings` double DEFAULT NULL,
  `total_carbs` int(11) DEFAULT NULL,
  `entry_id` int(11) NOT NULL,
  PRIMARY KEY (`cid`),
  KEY `fk_carbs_entry` (`entry_id`),
  CONSTRAINT `fk_carbs_entry` FOREIGN KEY (`entry_id`) REFERENCES `entry` (`eid`) ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE `insulin` (
  `iid` int(11) NOT NULL AUTO_INCREMENT,
  `bolus` double DEFAULT NULL,
  `entry_id` int(11) NOT NULL,
  PRIMARY KEY (`iid`),
  UNIQUE KEY `entry_id` (`entry_id`),
  CONSTRAINT `fk_insulin_entry` FOREIGN KEY (`entry_id`) REFERENCES `entry` (`eid`) ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB;

insert into user (username, enabled, icr, isr) values ('brian',true,10,40);

insert into authorities (username, authority) values ('brian', 'ROLE_USER');

insert into user (username, enabled) values ('admin',true);

insert into authorities (username, authority) values ('admin', 'ROLE_ADMIN');


