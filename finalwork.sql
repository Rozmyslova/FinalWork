/*CREATE DATABASE MansFriends;*/

/*USE MansFriends;
CREATE TABLE Class
(
	Id INT AUTO_INCREMENT PRIMARY KEY, 
	ClassName VARCHAR(20)
);
INSERT INTO Class (ClassName)
VALUES ('Home'),
	('Pack'); 

CREATE TABLE HomeAnimals
(
	Id INT AUTO_INCREMENT PRIMARY KEY,
    GenusName VARCHAR (20),
    Class_id INT,
    FOREIGN KEY (Class_id) REFERENCES Class (Id) ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO HomeAnimals (GenusName, Class_id)
VALUES ('Dogs', 1),
	('Cats', 1),  
	('Hamster', 1); 

CREATE TABLE PackAnimals
(
	Id INT AUTO_INCREMENT PRIMARY KEY,
    GenusName VARCHAR (20),
    Class_id INT,
    FOREIGN KEY (Class_id) REFERENCES Class (Id) ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO PackAnimals (GenusName, Class_id)
VALUES ('Horses', 2),
	('Donkey', 2),  
	('Camels', 2); 

CREATE TABLE Dogs 
(       
    Id INT AUTO_INCREMENT PRIMARY KEY, 
    Name VARCHAR(20), 
    Bday DATE,
    Commands VARCHAR(50),
    Genus_id int,
    Foreign KEY (Genus_id) REFERENCES HomeAnimals (Id) ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO Dogs (Name, Bday, Commands, Genus_id)
VALUES ('Archie', '2022-05-01', 'sit, down, stay, come, bark, wait', 1),
('Charlie', '2021-02-10', 'sit, down, stay, come, wait, bite', 1),  
('Max', '2020-01-10', 'sit, down, stay, come, wait, bite, stop', 1);  

CREATE TABLE Cats 
(       
    Id INT AUTO_INCREMENT PRIMARY KEY, 
    Name VARCHAR(20), 
    Bday DATE,
    Commands VARCHAR(50),
    Genus_id int,
    Foreign KEY (Genus_id) REFERENCES HomeAnimals (Id) ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO Cats (Name, Bday, Commands, Genus_id)
VALUES ('Diva', '2015-08-08', 'kitty-kitty-kitty', 1),
('Kelly', '2017-04-07', 'kitty-kitty-kitty', 1); 

CREATE TABLE Hamsters 
(       
    Id INT AUTO_INCREMENT PRIMARY KEY, 
    Name VARCHAR(20), 
    Bday DATE,
    Commands VARCHAR(50),
    Genus_id int,
    Foreign KEY (Genus_id) REFERENCES HomeAnimals (Id) ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO Hamsters (Name, Bday, Commands, Genus_id)
VALUES ('Homa', '2022-10-12', '', 3);

CREATE TABLE Horses 
(       
    Id INT AUTO_INCREMENT PRIMARY KEY, 
    Name VARCHAR(20), 
    Bday DATE,
    Commands VARCHAR(50),
    Genus_id int,
    Foreign KEY (Genus_id) REFERENCES PackAnimals (Id) ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO Horses (Name, Bday, Commands, Genus_id)
VALUES ('Gustav', '2019-01-12', 'gallop, step, stop, trot', 1),
('Elias', '2017-09-09', 'gallop, step, stop', 1),  
('Nadine', '2016-07-14', 'gallop, step, stop, trot', 1), 
('North', '2022-11-10', 'gallop, step, stop', 1);

CREATE TABLE Donkeys 
(       
    Id INT AUTO_INCREMENT PRIMARY KEY, 
    Name VARCHAR(20), 
    Bday DATE,
    Commands VARCHAR(50),
    Genus_id int,
    Foreign KEY (Genus_id) REFERENCES PackAnimals (Id) ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO Donkeys (Name, Bday, Commands, Genus_id)
VALUES ('Jack', '2019-04-28', 'eat', 2),
('Jenny', '2021-03-11', 'eat', 2);  

CREATE TABLE Camels 
(       
    Id INT AUTO_INCREMENT PRIMARY KEY, 
    Name VARCHAR(20), 
    Bday DATE,
    Commands VARCHAR(50),
    Genus_id int,
    Foreign KEY (Genus_id) REFERENCES PackAnimals (Id) ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO Camels (Name, Bday, Commands, Genus_id)
VALUES ('Ida', '2022-04-10', 'stop', 3);*/

/*SET SQL_SAFE_UPDATES = 0;
DELETE FROM Camels;

SELECT Name, Bday, Commands FROM Horses
UNION SELECT  Name, Bday, Commands FROM Donkeys;*/

/*CREATE TEMPORARY TABLE Animals AS 
SELECT *, 'Horses' as Genus FROM Horses
UNION SELECT *, 'Donkeys' AS Genus FROM Donkeys
UNION SELECT *, 'Dogs' AS Genus FROM Dogs
UNION SELECT *, 'Cats' AS Genus FROM Cats
UNION SELECT *, 'Hamsters' AS Genus FROM Hamsters;

CREATE TABLE YangAnimal AS
SELECT Name, Bday, Commands, Genus, TIMESTAMPDIFF(MONTH, Bday, CURDATE()) AS Age_in_month
FROM Animals WHERE Bday BETWEEN ADDDATE(curdate(), INTERVAL -3 YEAR) AND ADDDATE(CURDATE(), INTERVAL -1 YEAR);
 
SELECT * FROM YangAnimal;*/

SELECT d.Name, d.Bday, d.Commands, ha.GenusName, ya.Age_in_month 
FROM Dogs d
LEFT JOIN YangAnimal ya ON ya.Name = d.Name
LEFT JOIN HomeAnimals ha ON ha.Id = d.Genus_id
UNION

SELECT c.Name, c.Bday, c.Commands, ha.GenusName, ya.Age_in_month 
FROM Cats c
LEFT JOIN YangAnimal ya ON ya.Name = c.Name
LEFT JOIN HomeAnimals ha ON ha.Id = c.Genus_id
UNION

SELECT hm.Name, hm.Bday, hm.Commands, ha.GenusName, ya.Age_in_month 
FROM Hamsters hm
LEFT JOIN YangAnimal ya ON ya.Name = hm.Name
LEFT JOIN HomeAnimals ha ON ha.Id = hm.Genus_id
UNION

SELECT h.Name, h.Bday, h.Commands, pa.GenusName, ya.Age_in_month 
FROM Horses h
LEFT JOIN YangAnimal ya ON ya.Name = h.Name
LEFT JOIN PackAnimals pa ON pa.Id = h.Genus_id
UNION 

SELECT dn.Name, dn.Bday, dn.Commands, pa.GenusName, ya.Age_in_month 
FROM Donkeys dn 
LEFT JOIN YangAnimal ya ON ya.Name = dn.Name
LEFT JOIN PackAnimals pa ON pa.Id = dn.Genus_id



