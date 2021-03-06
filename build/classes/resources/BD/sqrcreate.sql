-- MySQL Script generated by MySQL Workbench
-- 09/20/17 20:40:41
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema CDsalebd
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema CDsalebd
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `CDsalebd` DEFAULT CHARACTER SET utf8 ;
USE `CDsalebd` ;

-- -----------------------------------------------------
-- Table `CDsalebd`.`company`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CDsalebd`.`company` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `login` VARCHAR(45) NULL DEFAULT NULL,
  `pass` VARCHAR(45) NULL DEFAULT NULL,
  `type` VARCHAR(45) NULL DEFAULT NULL,
  `cash` INT(11) NULL DEFAULT '0',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `CDsalebd`.`disk`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CDsalebd`.`disk` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `storeid` INT(11) NOT NULL,
  `Type` VARCHAR(45) NULL DEFAULT NULL,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `info` VARCHAR(45) NULL DEFAULT NULL,
  `cost` INT(11) NOT NULL,
  `num` INT(11) NOT NULL,
  `serialnum` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `storeid_eq_fk_idx` (`storeid` ASC),
  CONSTRAINT `storeid_eq_fk`
    FOREIGN KEY (`storeid`)
    REFERENCES `CDsalebd`.`company` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `CDsalebd`.`stockdisk`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CDsalebd`.`stockdisk` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `stockid` INT(11) NULL DEFAULT NULL,
  `type` VARCHAR(45) NULL DEFAULT NULL,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `info` VARCHAR(45) NULL DEFAULT NULL,
  `serialnum` VARCHAR(45) NULL DEFAULT NULL,
  `cost` INT(11) NOT NULL,
  `num` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `stockid_fk_idx` (`stockid` ASC),
  INDEX `stockideq_fk_idx` (`stockid` ASC),
  CONSTRAINT `stockid_eq_fk`
    FOREIGN KEY (`stockid`)
    REFERENCES `CDsalebd`.`company` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `CDsalebd`.`order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CDsalebd`.`order` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `ordertype` VARCHAR(45) NULL DEFAULT NULL,
  `orderstatus` VARCHAR(45) NULL DEFAULT NULL,
  `clientid` INT(11) NULL DEFAULT NULL,
  `storeid` INT(11) NULL DEFAULT NULL,
  `servicecenterid` INT(11) NULL DEFAULT NULL,
  `stockid` INT(11) NULL DEFAULT NULL,
  `diskid` INT(11) NULL DEFAULT NULL,
  `stockdiskid` INT(11) NULL DEFAULT NULL,
  `oldorder` INT(11) NULL DEFAULT NULL,
  `price` INT(11) NULL DEFAULT NULL,
  `num` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `clientidfk_idx` (`clientid` ASC),
  INDEX `storeidfk_idx` (`storeid` ASC),
  INDEX `serviceidfk_idx` (`servicecenterid` ASC),
  INDEX `stockidfk_idx` (`stockid` ASC),
  INDEX `eqidfk_idx` (`diskid` ASC),
  INDEX `stockeqidfk_idx` (`stockdiskid` ASC),
  INDEX `oldorderid_fk_idx` (`oldorder` ASC),
  CONSTRAINT `clientid_fk`
    FOREIGN KEY (`clientid`)
    REFERENCES `CDsalebd`.`company` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `eqid_fk`
    FOREIGN KEY (`diskid`)
    REFERENCES `CDsalebd`.`disk` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `oldorderid_fk`
    FOREIGN KEY (`oldorder`)
    REFERENCES `CDsalebd`.`order` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `serviceid_fk`
    FOREIGN KEY (`servicecenterid`)
    REFERENCES `CDsalebd`.`company` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `stockeqid_fk`
    FOREIGN KEY (`stockdiskid`)
    REFERENCES `CDsalebd`.`stockdisk` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `stockid_fk`
    FOREIGN KEY (`stockid`)
    REFERENCES `CDsalebd`.`company` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `storeid_fk`
    FOREIGN KEY (`storeid`)
    REFERENCES `CDsalebd`.`company` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `CDsalebd`.`payment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CDsalebd`.`payment` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `price` INT(11) NULL DEFAULT NULL,
  `orderid` INT(11) NULL DEFAULT NULL,
  `status` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `orderid_idx` (`orderid` ASC),
  CONSTRAINT `orderid_pay_fk`
    FOREIGN KEY (`orderid`)
    REFERENCES `CDsalebd`.`order` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `CDsalebd`.`check`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CDsalebd`.`check` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `payid` INT(11) NOT NULL,
  `num` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `payid_check_fk_idx` (`payid` ASC),
  CONSTRAINT `payid_check_fk`
    FOREIGN KEY (`payid`)
    REFERENCES `CDsalebd`.`payment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
