-- MySQL Script generated by MySQL Workbench
-- Fri 01 Jul 2016 14:14:17 EEST
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering
-- Made by Denchik

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema onlinetestDB
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema onlinetestDB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `onlinetestDB` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `onlinetestDB` ;

-- -----------------------------------------------------
-- Table `onlinetestDB`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `onlinetestDB`.`user` (
  `user_id` INT(8) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NULL,
  `surname` VARCHAR(60) NULL,
  `email` VARCHAR(100) NULL,
  `security_code` CHAR(32) NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `onlinetestDB`.`test`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `onlinetestDB`.`test` (
  `test_id` INT(8) NOT NULL,
  `test_name` VARCHAR(45) NULL,
  `duration` TIMESTAMP NULL,
  PRIMARY KEY (`test_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `onlinetestDB`.`questions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `onlinetestDB`.`questions` (
  `question_no` INT(4) NOT NULL AUTO_INCREMENT,
  `test_id` INT(8) NULL,
  `question_text` TEXT(500) NULL COMMENT 'question text',
  PRIMARY KEY (`question_no`),
  CONSTRAINT `testid_fk`
    FOREIGN KEY (`test_id`)
    REFERENCES `onlinetestDB`.`test` (`test_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `testid_fk_idx` ON `onlinetestDB`.`questions` (`test_id` ASC);


-- -----------------------------------------------------
-- Table `onlinetestDB`.`question_option`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `onlinetestDB`.`question_option` (
  `qop_id` INT(8) NOT NULL AUTO_INCREMENT,
  `test_id` INT(8) NULL,
  `question_no` INT(4) NULL,
  `option_no` VARCHAR(10) NULL,
  `is_answer` TINYINT(1) NULL,
  PRIMARY KEY (`qop_id`),
  CONSTRAINT `question_no`
    FOREIGN KEY (`question_no`)
    REFERENCES `onlinetestDB`.`questions` (`question_no`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `question_no_idx` ON `onlinetestDB`.`question_option` (`question_no` ASC);


-- -----------------------------------------------------
-- Table `onlinetestDB`.`answer_text`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `onlinetestDB`.`answer_text` (
  `at_id` INT NOT NULL AUTO_INCREMENT,
  `test_id` INT(8) NULL,
  `question_no` INT(4) NULL COMMENT 'q number.',
  `answer_text` TEXT(500) NULL COMMENT 'answer to question in text form.',
  PRIMARY KEY (`at_id`),
  CONSTRAINT `question_no_foreign`
    FOREIGN KEY (`question_no`)
    REFERENCES `onlinetestDB`.`questions` (`question_no`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `question_no_foreign_idx` ON `onlinetestDB`.`answer_text` (`question_no` ASC);


-- -----------------------------------------------------
-- Table `onlinetestDB`.`user_response`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `onlinetestDB`.`user_response` (
  `response_id` INT(8) NOT NULL AUTO_INCREMENT,
  `test_id` INT(8) NULL,
  `option_no` VARCHAR(10) NULL,
  PRIMARY KEY (`response_id`),
  CONSTRAINT `test_id_fk1`
    FOREIGN KEY (`test_id`)
    REFERENCES `onlinetestDB`.`question_option` (`qop_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `testidforeign`
    FOREIGN KEY (`test_id`)
    REFERENCES `onlinetestDB`.`answer_text` (`at_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `test_id_fk1_idx` ON `onlinetestDB`.`user_response` (`test_id` ASC);


-- -----------------------------------------------------
-- Table `onlinetestDB`.`user_test`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `onlinetestDB`.`user_test` (
  `user_id` INT(8) NOT NULL,
  `test_id` INT(8) NOT NULL,
  `date` DATE NULL,
  PRIMARY KEY (`user_id`, `test_id`),
  CONSTRAINT `user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `onlinetestDB`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `test_id`
    FOREIGN KEY (`test_id`)
    REFERENCES `onlinetestDB`.`user_response` (`response_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `test_id_idx` ON `onlinetestDB`.`user_test` (`test_id` ASC);


-- -----------------------------------------------------
-- Table `onlinetestDB`.`roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `onlinetestDB`.`roles` (
  `role_id` INT(8) NOT NULL AUTO_INCREMENT,
  `role_type` VARCHAR(45) NULL,
  PRIMARY KEY (`role_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `onlinetestDB`.`user_roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `onlinetestDB`.`user_roles` (
  `user_id` INT(8) NOT NULL,
  `role_id` INT(8) NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`),
  CONSTRAINT `fkuserid`
    FOREIGN KEY (`user_id`)
    REFERENCES `onlinetestDB`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fkroleid`
    FOREIGN KEY (`role_id`)
    REFERENCES `onlinetestDB`.`roles` (`role_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fkroleid_idx` ON `onlinetestDB`.`user_roles` (`role_id` ASC);


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
