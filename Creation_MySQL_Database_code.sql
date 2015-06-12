CREATE SCHEMA `renter` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci ;

Use renter;		

CREATE TABLE `renter`.`users` (
  `idUser` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
`email` VARCHAR(45) NOT NULL,
  `phoneNumber` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idUser`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC)
 )
  ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

insert into users (login, password, name, surname, email, phoneNumber) values ("user", "user", " Zack", "Snyder", "zack@i.ua","063-149-17-40");
insert into users (login, password, name, surname, email, phoneNumber) values ("1111", "1111", "Angelina", "Jolie", "jolie@gmail.com","095-150-13-14");
insert into users (login, password, name, surname, email, phoneNumber) values ("xxx", "xxx", "Eva", "Green", "eg@gmail.com","095-192-11-14");
insert into users (login, password, name, surname, email, phoneNumber) values ("root", "root", " Clint", "Eastwood", "clint@gmail.com","044-545-12-89");

CREATE TABLE `renter`.`addresses` (
  `idAddress` INT NOT NULL AUTO_INCREMENT,
  `street` VARCHAR(45) NOT NULL,
  `house` VARCHAR(10) NOT NULL,
  `appartment` VARCHAR(10) NOT NULL,
 `idUser` INT NOT NULL,
 PRIMARY KEY (`idAddress`),
FOREIGN KEY (idUser) REFERENCES users(idUser))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

insert into addresses (street, house, appartment, idUser) values ("Parnikova", "22", "150", 1);
insert into addresses (street, house, appartment, idUser) values ("Balukova", "1-a", "17", 2);
insert into addresses (street, house, appartment, idUser) values ("Korolenko", "13", "7b", 3);

CREATE TABLE `renter`.`admins` (
  `idAdmin` INT NOT NULL AUTO_INCREMENT,
  `idUser` INT NOT NULL,
  PRIMARY KEY (`idAdmin`),
  FOREIGN KEY (idUser) REFERENCES users(idUser))
  ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

insert into admins values (1,4);


CREATE TABLE `renter`.`applications` (
  `idApplication` INT NOT NULL AUTO_INCREMENT,
  `idUser` INT NOT NULL,
  `about` VARCHAR(150) NOT NULL,
  `status` ENUM('CREATED','ASSIGNED','COMPLETED') NOT NULL,
  `typeOfWork` ENUM('ELECTRIC', 'SANTECHNIC', 'HEATING', 'AIR_CONDITIONING', 'OTHER') NOT NULL,
  `creation` DATETIME NOT NULL,
  `desirable` DATETIME  NOT NULL,
  `start` DATETIME NULL,
  `end` DATETIME NULL,
   PRIMARY KEY (`idApplication`),
FOREIGN KEY (idUser) REFERENCES users(idUser)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

insert into applications (idUser, about, status, typeOfWork, creation, desirable, start, end) values (1, "Socket is broken", 'ASSIGNED', 'HEATING', "2015-05-28 17:41:00", "2015-07-01 13:00:00", "2015-07-01 14:00:00", "2015-07-01 16:00:00");
insert into applications (idUser, about, status, typeOfWork, creation, desirable, start, end) values (2, "Radiator is cold", 'ASSIGNED', 'HEATING', "2015-05-29 10:34:00", "2015-07-02 10:00:00","2015-07-02 10:00:00", "2015-07-02 16:00:00");
insert into applications (idUser, about, status, typeOfWork, creation, desirable) values (3, "Conditioner is broken", 'CREATED', 'AIR_CONDITIONING', "2015-05-28 17:41:00", "2015-06-12 19:00:00");

CREATE TABLE `renter`.`workers` (
  `idWorker` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,  
  `typeOfWork` ENUM('ELECTRIC', 'SANTECHNIC', 'HEATING', 'AIR_CONDITIONING', 'OTHER') NOT NULL,
  PRIMARY KEY (`idWorker`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

insert into workers values(1,"Bruce","Willis","Electric");
insert into workers values(2,"Jared","Leto","Electric");
insert into workers values(3," Edward", "Norton","Santechnic");
insert into workers values(4,"Tom","Hardy","Santechnic");
insert into workers values(5,"Ben","Affleck","Heating");
insert into workers values(6,"Matt","Damon","Heating");
insert into workers values(7,"Ryan","Gosling","Air_conditioning");
insert into workers values(8,"Leo","DiCaprio","Air_conditioning");
insert into workers values(9, "Ewan","McGregor","Other");
insert into workers values(10,"Joseph","Gordon-Levitt","Other");

CREATE TABLE `renter`.`works` (
  `idWork` INT NOT NULL AUTO_INCREMENT,
  `idApplication` INT NOT NULL,
  `idWorker` INT NOT NULL,
  PRIMARY KEY (`idWork`),
FOREIGN KEY (idApplication) REFERENCES applications(idApplication),
FOREIGN KEY (idWorker) REFERENCES workers(idWorker))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

insert into works values(1,1,1);
insert into works values(2,1,2);
insert into works values(3,2,5);
insert into works values(4,2,6);
