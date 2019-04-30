-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema Tenure
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema Tenure
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Tenure` DEFAULT CHARACTER SET latin1 ;
USE `Tenure` ;

-- -----------------------------------------------------
-- Table `Tenure`.`bu_users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Tenure`.`bu_users` (
  `userNumber` INT(11) NOT NULL DEFAULT '0',
  `loginName` VARCHAR(20) NOT NULL,
  `firstName` VARCHAR(35) NULL DEFAULT '',
  `lastName` VARCHAR(35) NULL DEFAULT '',
  `emailAddress` VARCHAR(60) NULL DEFAULT '',
  `displayName` VARCHAR(60) NULL DEFAULT '',
  `userRole` VARCHAR(30) NOT NULL,
  `lastLoginTime` VARCHAR(25) NULL DEFAULT NULL,
  `loginCount` INT(11) NULL DEFAULT '0',
  PRIMARY KEY (`userNumber`),
  UNIQUE INDEX `loginName` (`loginName` ASC),
  UNIQUE INDEX `userNumber` (`userNumber` ASC),
  INDEX `bu_user_email_index` (`emailAddress` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `Tenure`.`error_logs`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Tenure`.`error_logs` (
  `EVENT_ID` INT(11) NOT NULL AUTO_INCREMENT,
  `EVENT_DATE` DATETIME NULL DEFAULT NULL,
  `LEVEL` VARCHAR(15) NULL DEFAULT NULL,
  `LOGGER` VARCHAR(45) NULL DEFAULT NULL,
  `MSG` VARCHAR(260) NULL DEFAULT NULL,
  `THROWABLE` VARCHAR(260) NULL DEFAULT NULL,
  PRIMARY KEY (`EVENT_ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `Tenure`.`properties`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Tenure`.`properties` (
  `propertyNumber` INT(11) NOT NULL AUTO_INCREMENT,
  `propertyName` VARCHAR(64) NOT NULL,
  `propertyValue` VARCHAR(128) NOT NULL,
  `description` VARCHAR(1024) NULL DEFAULT '',
  `previousValue` VARCHAR(128) NULL DEFAULT NULL,
  `defaultValue` VARCHAR(128) NULL DEFAULT NULL,
  PRIMARY KEY (`propertyNumber`),
  UNIQUE INDEX `propertyNumber` (`propertyNumber` ASC),
  UNIQUE INDEX `propertyName` (`propertyName` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `Tenure`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Tenure`.`users` (
  `userNumber` INT(11) NOT NULL AUTO_INCREMENT,
  `loginName` VARCHAR(50) NOT NULL,
  `userPassword` VARCHAR(64) NOT NULL,
  `firstName` VARCHAR(25) NULL DEFAULT '',
  `lastName` VARCHAR(35) NULL DEFAULT '',
  `emailAddress` VARCHAR(50) NULL DEFAULT '',
  `userRole` VARCHAR(30) NOT NULL,
  `lastLoginTime` VARCHAR(25) NULL DEFAULT NULL,
  `loginCount` INT(11) NULL DEFAULT '0',
  `salt` VARCHAR(50) NULL DEFAULT NULL,
  `LastAttemptedLoginTime` VARCHAR(25) NULL DEFAULT NULL,
  `locked` TINYINT(1) NULL DEFAULT '0',
  `AttemptedLoginCount` INT(11) NULL DEFAULT '0',
  PRIMARY KEY (`userNumber`),
  UNIQUE INDEX `userNumber` (`userNumber` ASC),
  UNIQUE INDEX `loginName` (`loginName` ASC),
  INDEX `user_email_index` (`emailAddress` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `Tenure`.`application`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Tenure`.`application` (
  `applicantID` INT UNSIGNED NOT NULL,
  `applicantUser` INT NOT NULL,
  `applicationType` ENUM('tenure', 'professorship', 'promotion') NULL,
  `creationDate` DATETIME NOT NULL,
  `submissionDate` DATETIME NULL,
  PRIMARY KEY (`applicantID`),
  UNIQUE INDEX `applicantID_UNIQUE` (`applicantID` ASC),
  INDEX `applicantUser_idx` (`applicantUser` ASC),
  CONSTRAINT `applicantUser`
    FOREIGN KEY (`applicantUser`)
    REFERENCES `Tenure`.`bu_users` (`userNumber`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Tenure`.`department`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Tenure`.`department` (
  `departmentID` INT NOT NULL,
  `departmentName` VARCHAR(45) NOT NULL,
  `departmentChair` INT NOT NULL,
  `departmentChairPhone` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`departmentID`),
  INDEX `departmentChair_idx` (`departmentChair` ASC),
  CONSTRAINT `departmentChair`
    FOREIGN KEY (`departmentChair`)
    REFERENCES `Tenure`.`bu_users` (`userNumber`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Tenure`.`profile`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Tenure`.`profile` (
  `profileID` INT NOT NULL,
  `profileUser` INT NOT NULL,
  `campusPhone` VARCHAR(15) NOT NULL,
  `department` INT NOT NULL,
  PRIMARY KEY (`profileID`),
  INDEX `profileUser_idx` (`profileUser` ASC),
  INDEX `department_idx` (`department` ASC),
  CONSTRAINT `profileUser`
    FOREIGN KEY (`profileUser`)
    REFERENCES `Tenure`.`bu_users` (`userNumber`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `department`
    FOREIGN KEY (`department`)
    REFERENCES `Tenure`.`department` (`departmentID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Tenure`.`media`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Tenure`.`media` (
  `mediaID` INT NOT NULL,
  `mediaFile` LONGBLOB NOT NULL,
  `mediaFilename` VARCHAR(45) NOT NULL,
  `mediaName` VARCHAR(45) NOT NULL,
  `creationDate` DATETIME NOT NULL,
  `mediaHash` CHAR(40) NOT NULL,
  PRIMARY KEY (`mediaID`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
