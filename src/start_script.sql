CREATE TABLE PetTypes
(
	Id INT AUTO_INCREMENT PRIMARY KEY,
  GenusName VARCHAR (20)
);

INSERT INTO PetTypes (GenusName)
VALUES ('Cat'),
	('Dog'),
	('Hamster');

CREATE TABLE Pets
(
    Id INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(20),
    Bday DATE,
    Commands VARCHAR(300),
    Genus_id int,
    Foreign KEY (Genus_id) REFERENCES PetTypes (Id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO Pets (Name, Bday, Commands, Genus_id)
VALUES ('Archie', '2022-05-01', 'sit, down, stay, come, bark, wait', 2),
('Charlie', '2021-02-10', 'sit, down, stay, come, wait, bite', 2),
('Max', '2020-01-10', 'sit, down, stay, come, wait, bite, stop', 2),
('Diva', '2015-08-08', 'kitty-kitty-kitty', 1),
('Kelly', '2017-04-07', 'kitty-kitty-kitty', 1),
('Homa', '2022-10-12', '', 3);