CREATE TABLE `test` (
	`id` SERIAL,
	`studentno` VARCHAR(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
	`subjectcd` CHAR(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
	`schoolcd` CHAR(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
	`no` INT(10) NOT NULL,
	`point` INT(10) DEFAULT NULL,
	`classnum` VARCHAR(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
	PRIMARY KEY (`studentno`,`subjectcd`,`schoolcd`,`no`)
) ENGINE=InnoDB;