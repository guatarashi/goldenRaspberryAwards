DROP TABLE film IF EXISTS;

CREATE TABLE `film`  (
    `year` VARCHAR(255),
    title VARCHAR(255),
    studios VARCHAR(255),
    producers VARCHAR(255),
    winner VARCHAR(255),
    id INT PRIMARY KEY AUTO_INCREMENT
);

insert into film (`year`, title, studios, producers, winner)
SELECT `year`, title, studios, producers, winner FROM CSVREAD('classpath:/input/movielist.csv', NULL, 'fieldSeparator=;');