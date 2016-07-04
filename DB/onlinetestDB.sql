-- MySQL Script generated by MySQL Workbench
-- Thu 30 Jun 2016 14:00:51 BST
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema accenture
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema accenture
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `accenture` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `accenture` ;

-- -----------------------------------------------------
-- Table `accenture`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `accenture`.`user` (
  `user_id` INT(8) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `surname` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `security_code` CHAR(32) NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `accenture`.`test`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `accenture`.`test` (
  `test_id` INT(8) NOT NULL,
  `test_name` VARCHAR(45) NULL,
  `duration` TIMESTAMP NULL,
  PRIMARY KEY (`test_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `accenture`.`questions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `accenture`.`questions` (
  `question_no` INT(4) NOT NULL AUTO_INCREMENT,
  `test_id` INT(8) NULL,
  `question_text` TEXT(500) NULL COMMENT 'question text',
  PRIMARY KEY (`question_no`),
  CONSTRAINT `testid_fk`
    FOREIGN KEY (`test_id`)
    REFERENCES `accenture`.`test` (`test_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `testid_fk_idx` ON `accenture`.`questions` (`test_id` ASC);


-- -----------------------------------------------------
-- Table `accenture`.`question_option`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `accenture`.`question_option` (
  `qop_id` INT(8) NOT NULL AUTO_INCREMENT,
  `test_id` INT(8) NULL,
  `question_no` INT(4) NULL,
  `option_no` VARCHAR(10) NULL,
  `is_answer` TINYINT(1) NULL,
  PRIMARY KEY (`qop_id`),
  CONSTRAINT `question_no`
    FOREIGN KEY (`question_no`)
    REFERENCES `accenture`.`questions` (`question_no`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `question_no_idx` ON `accenture`.`question_option` (`question_no` ASC);


-- -----------------------------------------------------
-- Table `accenture`.`answer_text`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `accenture`.`answer_text` (
  `at_id` INT NOT NULL AUTO_INCREMENT,
  `test_id` INT(8) NULL,
  `question_no` INT(4) NULL COMMENT 'q number.',
  `answer_text` TEXT(500) NULL COMMENT 'answer to question in text form.',
  PRIMARY KEY (`at_id`),
  CONSTRAINT `question_no_foreign`
    FOREIGN KEY (`question_no`)
    REFERENCES `accenture`.`questions` (`question_no`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `question_no_foreign_idx` ON `accenture`.`answer_text` (`question_no` ASC);


-- -----------------------------------------------------
-- Table `accenture`.`user_response`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `accenture`.`user_response` (
  `response_id` INT(8) NOT NULL AUTO_INCREMENT,
  `test_id` INT(8) NULL,
  `option_no` VARCHAR(10) NULL,
  PRIMARY KEY (`response_id`),
  CONSTRAINT `test_id_fk1`
    FOREIGN KEY (`test_id`)
    REFERENCES `accenture`.`question_option` (`qop_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `testidforeign`
    FOREIGN KEY (`test_id`)
    REFERENCES `accenture`.`answer_text` (`at_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `test_id_fk1_idx` ON `accenture`.`user_response` (`test_id` ASC);


-- -----------------------------------------------------
-- Table `accenture`.`user_test`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `accenture`.`user_test` (
  `user_id` INT(8) NOT NULL,
  `test_id` INT(8) NOT NULL,
  `date` DATE NULL,
  PRIMARY KEY (`user_id`, `test_id`),
  CONSTRAINT `user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `accenture`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `test_id`
    FOREIGN KEY (`test_id`)
    REFERENCES `accenture`.`user_response` (`response_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `test_id_idx` ON `accenture`.`user_test` (`test_id` ASC);


-- -----------------------------------------------------
-- Table `accenture`.`grader`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `accenture`.`grader` (
  `grader_id` INT(8) NOT NULL,
  `user_id` INT(8) NULL,
  PRIMARY KEY (`grader_id`),
  CONSTRAINT `fk_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `accenture`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_user_id_idx` ON `accenture`.`grader` (`user_id` ASC);


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
