ALTER TABLE `CDsalebd`.`check` 
DROP FOREIGN KEY `payid_check_fk`;
ALTER TABLE `CDsalebd`.`check` 
DROP INDEX `payid_check_fk_idx` ;

ALTER TABLE `CDsalebd`.`disk` 
DROP FOREIGN KEY `storeid_eq_fk`;
ALTER TABLE `CDsalebd`.`disk` 
DROP INDEX `storeid_eq_fk_idx` ;

ALTER TABLE `CDsalebd`.`stockdisk` 
DROP FOREIGN KEY `stockid_eq_fk`;
ALTER TABLE `CDsalebd`.`stockdisk` 
DROP INDEX `stockid_fk_idx` ;

ALTER TABLE `CDsalebd`.`payment` 
DROP FOREIGN KEY `orderid_pay_fk`;
ALTER TABLE `CDsalebd`.`payment` 
DROP INDEX `orderid_idx` ;

ALTER TABLE `CDsalebd`.`order` 
DROP FOREIGN KEY `storeid_fk`,
DROP FOREIGN KEY `stockid_fk`,
DROP FOREIGN KEY `stockeqid_fk`,
DROP FOREIGN KEY `serviceid_fk`,
DROP FOREIGN KEY `oldorderid_fk`,
DROP FOREIGN KEY `eqid_fk`,
DROP FOREIGN KEY `clientid_fk`;
ALTER TABLE `CDsalebd`.`order` 
DROP INDEX `oldorderid_fk_idx` ,
DROP INDEX `stockeqidfk_idx` ,
DROP INDEX `eqidfk_idx` ,
DROP INDEX `stockidfk_idx` ,
DROP INDEX `serviceidfk_idx` ,
DROP INDEX `storeidfk_idx` ,
DROP INDEX `clientidfk_idx` ;


DROP TABLE IF EXISTS company; 
DROP TABLE IF EXISTS `order`;
DROP TABLE IF EXISTS disk;
DROP TABLE IF EXISTS stockdisk;
DROP TABLE IF EXISTS payment;
DROP TABLE IF EXISTS `check`;




CREATE TABLE company (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `login` VARCHAR(45) NULL DEFAULT NULL,
  `pass` VARCHAR(45) NULL DEFAULT NULL,
  `type` VARCHAR(45) NULL DEFAULT NULL,
  `cash` INT(11) NULL DEFAULT '0',
  PRIMARY KEY (`id`));

CREATE TABLE `disk` (
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
    ON UPDATE NO ACTION);

CREATE TABLE `stockdisk` (
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
    ON UPDATE NO ACTION);

CREATE TABLE `order` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `ordertype` VARCHAR(45) NULL DEFAULT NULL,
  `orderstatus` VARCHAR(45) NULL DEFAULT NULL,
  `servicecomment` VARCHAR(500) NULL DEFAULT NULL,
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
    ON UPDATE NO ACTION);

CREATE TABLE `payment` (
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
    ON UPDATE NO ACTION);

CREATE TABLE `check` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `payid` INT(11) NOT NULL,
  `num` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `payid_check_fk_idx` (`payid` ASC),
  CONSTRAINT `payid_check_fk`
    FOREIGN KEY (`payid`)
    REFERENCES `CDsalebd`.`payment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);