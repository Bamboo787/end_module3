create table users
(
    id         BIGINT auto_increment primary key,
    username   VARCHAR(255) UNIQUE,
    `password` VARCHAR(255),
    fullname   VARCHAR(255),
    phone      VARCHAR(255) UNIQUE,
    email      VARCHAR(255) UNIQUE,
    cityId     INT,
    `Role`     Enum("ADMIN", "USER"),
    updatedAt  DATE,
    createdAt  DATETIME,
    img        BLOB,

    FOREIGN KEY (cityId) REFERENCES cities (id)

);

CREATE TABLE `cities`
(
    `id`   int NOT NULL AUTO_INCREMENT,
    `name` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
);


CREATE TABLE `product`
(
    `id`         int            NOT NULL AUTO_INCREMENT,
    `title`      varchar(45) DEFAULT NULL,
    `price`      decimal(12, 0) NOT NULL,
    `quantity`   int         DEFAULT NULL,
    `updateAT`   date        DEFAULT NULL,
    `createAT`   date        DEFAULT NULL,
    `idCategory` int            NOT NULL,
    `size`       int            NOT NULL,
    `color`      varchar(45)    NOT NULL,
    `img`        blob,
    PRIMARY KEY (`id`),
    KEY          `FK_idCategory_idx` (`idCategory`),
    CONSTRAINT `FK_idCategory` FOREIGN KEY (`idCategory`) REFERENCES `category` (`id`)
);


CREATE TABLE `java_servlet_manager`.`category`
(
    `id`   INT NOT NULL,
    `name` VARCHAR(45),
    PRIMARY KEY (`id`)
);