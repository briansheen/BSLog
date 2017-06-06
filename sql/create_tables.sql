
CREATE TABLE `user` (
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
  `eid` int(11) NOT NULL AUTO_INCREMENT,
  `date_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `username` varchar(256) NOT NULL,
  PRIMARY KEY (`eid`),
  KEY `fk_entry_user` (`username`),
  CONSTRAINT `fk_entry_user` FOREIGN KEY (`username`) REFERENCES `user` (`username`)
);

CREATE TABLE `carb` (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(256) DEFAULT NULL,
  `serving_size` varchar(30) DEFAULT NULL,
  `carbs_per_serving` int(11) DEFAULT NULL,
  `num_servings` int(11) DEFAULT NULL,
  `total_carbs` int(11) DEFAULT NULL,
  `entry_id` int(11) NOT NULL,
  PRIMARY KEY (`cid`),
  KEY `fk_carbs_entry` (`entry_id`),
  CONSTRAINT `fk_carbs_entry` FOREIGN KEY (`entry_id`) REFERENCES `entry` (`eid`)
);

CREATE TABLE `bloodsugar` (
  `bsid` int(11) NOT NULL AUTO_INCREMENT,
  `bloodsugar` int(11) NOT NULL,
  `entry_id` int(11) NOT NULL,
  PRIMARY KEY (`bsid`),
  UNIQUE KEY `entry_id` (`entry_id`),
  CONSTRAINT `fk_bloodsugar_entry` FOREIGN KEY (`entry_id`) REFERENCES `entry` (`eid`)
);

CREATE TABLE `insulin` (
  `iid` int(11) NOT NULL AUTO_INCREMENT,
  `insulin` double DEFAULT NULL,
  `entry_id` int(11) NOT NULL,
  PRIMARY KEY (`iid`),
  UNIQUE KEY `entry_id` (`entry_id`),
  CONSTRAINT `fk_insulin_entry` FOREIGN KEY (`entry_id`) REFERENCES `entry` (`eid`)
);

insert into user (username, password, enabled, icr, isr) values ('brian','sheen',true,10,40);

insert into authorities (username, authority) values ('brian', 'ROLE_USER');

insert into user (username, password, enabled) values ('admin','admin',true);

insert into authorities (username, authority) values ('admin', 'ROLE_ADMIN');


