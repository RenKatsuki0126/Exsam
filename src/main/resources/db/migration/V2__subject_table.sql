CREATE TABLE `subject` (
	`school_cd` CHAR(3) NOT NULL,
	`cd` CHAR(3) NOT NULL,
	`name` VARCHAR(20) DEFAULT NULL,
	PRIMARY KEY (`school_cd`)
	) ENGINE=InnoDB;