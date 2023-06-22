-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema rubik
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema rubik
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `rubik` DEFAULT CHARACTER SET latin1 ;
USE `rubik` ;

-- -----------------------------------------------------
-- Table `rubik`.`combinaciones`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rubik`.`combinaciones` (
  `idCombinacion` INT(11) NOT NULL,
  `Movimientos` VARCHAR(80) NOT NULL,
  PRIMARY KEY (`idCombinacion`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `rubik`.`penalizaciones`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rubik`.`penalizaciones` (
  `idPenalizaciones` INT(11) NOT NULL,
  `Nombre` VARCHAR(45) NOT NULL,
  `Tipo` VARCHAR(45) NOT NULL,
  `Tiempo` DOUBLE NULL DEFAULT NULL,
  PRIMARY KEY (`idPenalizaciones`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `rubik`.`tiempos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rubik`.`tiempos` (
  `idTiempos` INT(11) NOT NULL,
  `fechaRegistro` DATE NOT NULL,
  `valido` CHAR(1) NOT NULL,
  `idCombinacion` INT(11) NOT NULL,
  `minutos` INT(11) NOT NULL,
  `segundos` DOUBLE NOT NULL,
  PRIMARY KEY (`idTiempos`),
  CONSTRAINT `fk_Tiempos_Combinaciones1`
    FOREIGN KEY (`idCombinacion`)
    REFERENCES `rubik`.`combinaciones` (`idCombinacion`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `rubik`.`ti_pe`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rubik`.`ti_pe` (
  `idTiempos` INT(11) NOT NULL,
  `idPenalizaciones` INT(11) NOT NULL,
  PRIMARY KEY (`idTiempos`, `idPenalizaciones`),
  CONSTRAINT `fk_Ti_Pe_Penalizaciones1`
    FOREIGN KEY (`idPenalizaciones`)
    REFERENCES `rubik`.`penalizaciones` (`idPenalizaciones`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Ti_Pe_Tiempos1`
    FOREIGN KEY (`idTiempos`)
    REFERENCES `rubik`.`tiempos` (`idTiempos`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `rubik`.`usuarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rubik`.`usuarios` (
  `idUsuario` INT(11) NOT NULL,
  `Nombre` VARCHAR(45) NOT NULL,
  `Correo` VARCHAR(45) NOT NULL,
  `Pass` VARCHAR(45) NOT NULL,
  `Colores` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idUsuario`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `rubik`.`us_ti`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rubik`.`us_ti` (
  `idUsuario` INT(11) NOT NULL,
  `idTiempos` INT(11) NOT NULL,
  PRIMARY KEY (`idUsuario`, `idTiempos`),
  CONSTRAINT `fk_Us_Ti_Tiempos1`
    FOREIGN KEY (`idTiempos`)
    REFERENCES `rubik`.`tiempos` (`idTiempos`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Us_Ti_Usuarios`
    FOREIGN KEY (`idUsuario`)
    REFERENCES `rubik`.`usuarios` (`idUsuario`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

insert into combinaciones values (1, "U F R B2 R2 B F2 D R B L' D U L2 R B' F2 D L2 R2 F2 R U' L2 U2");
insert into combinaciones values (2, "R' D L2 D2 R2 B2 L R2 D' U R' B2 F2 R2 D B2 L' D U' L B' F D' L2 D2");
insert into combinaciones values (3, "L' R2 U' B F L' B2 R' F D2 F2 D U' F U B2 F2 L' R' U2 R B D U R");

insert into penalizaciones values(1,"Limite","Descalificar",0);
insert into penalizaciones values(2,"Inspeccion","Tiempo",2);
insert into penalizaciones values(3,"Tocar","Tiempo",2);
insert into penalizaciones values(4,"Movimiento","Tiempo",2);
insert into penalizaciones values(5,"Inspeccion tardia","Descalificar",0);
insert into penalizaciones values(6,"Falta movimientos","No resuelto",0);

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
