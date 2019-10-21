DROP DATABASE IF EXISTS SuperSightings;

CREATE DATABASE SuperSightings;

USE SuperSightings;

CREATE TABLE characters(
CharacterID INT PRIMARY KEY auto_increment,
`name` varchar(30) NOT NULL,
`description` varchar(30) NOT NULL,
isHero boolean NOT NULL
);

CREATE TABLE organizations(
organizationID INT PRIMARY KEY auto_increment,
`name` varchar(55) NOT NULL,
`description` varchar(100) NOT NULL,
address varchar(100) NOT NULL,
contactInfo varchar(100) NOT NULL
);


CREATE TABLE belongsToOrganization(
belongsToOrganizationID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
characterID INT NOT NULL,
FOREIGN KEY fk_belongsToOrganization_characters(characterID) REFERENCES characters(characterID),
organizationID INT NOT NULL,
FOREIGN KEY  fk_belongsToOrganization_organizations(organizationID) REFERENCES organizations(organizationID)
);


CREATE TABLE Location(
LocationID INT PRIMARY KEY AUTO_INCREMENT,
LocationName varchar(30) NOT NULL,
LocationAddress varchar(30),
Latitude FLOAT (10,6) NOT NULL,
Longitude FLOAT (10,6) NOT NULL
);

CREATE TABLE Sightings(
SightingsID INT PRIMARY KEY AUTO_INCREMENT,
LocationID INT NOT NULL,
FOREIGN KEY fk_Sightings_Location(LocationID) references Location(LocationID),
CharacterID INT NOT NULL,
FOREIGN KEY fk_Sightings_characters(CharacterID) references characters(CharacterID),
timeOfSighting timestamp NOT NULL
);

CREATE TABLE superPower(
superPowerID INT PRIMARY KEY auto_increment,
`description` varchar(155) NOT NULL
);

CREATE TABLE hasSuperPowers(
hasSuperPowersID INT PRIMARY KEY AUTO_INCREMENT,
CharacterID INT NOT NULL,
FOREIGN KEY fk_hasSuperPowers_characters(CharacterID) references characters(CharacterID),
superPowerID INT NOT NULL,
foreign key fk_hasSuperPowers_superPower(superPowerID) references superPower(superPowerID));


INSERT INTO Location(LocationName, LocationAddress, Latitude, Longitude)
values("Bahamas", "1835", 100.01, 109.01);

INSERT INTO organizations(name, description, address, contactInfo)
values('john','hector','roshna','sam');


SELECT * FROM organizations;