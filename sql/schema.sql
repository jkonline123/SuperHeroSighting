CREATE DATABASE IF NOT EXISTS SuperSightings;

USE SuperSightings;

CREATE TABLE IF NOT EXISTS characters(
CharacterID INT PRIMARY KEY auto_increment,
`name` varchar(30) NOT NULL,
`description` varchar(30) NOT NULL,
isHero boolean NOT NULL
);

CREATE TABLE IF NOT EXISTS organizations(
organizationID INT PRIMARY KEY auto_increment,
`name` varchar(55) NOT NULL,
`description` varchar(100) NOT NULL,
address varchar(100) NOT NULL,
contactInfo varchar(100) NOT NULL
);


CREATE TABLE IF NOT EXISTS belongsToOrganization(
belongsToOrganizationID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
characterID INT NOT NULL,
FOREIGN KEY fk_belongsToOrganization_characters(characterID) REFERENCES characters(characterID),
organizationID INT NOT NULL,
FOREIGN KEY  fk_belongsToOrganization_organizations(organizationID) REFERENCES organizations(organizationID)
);


CREATE TABLE IF NOT EXISTS Location(
LocationID INT PRIMARY KEY AUTO_INCREMENT,
LocationName varchar(30) NOT NULL,
LocationAddress varchar(30),
Latitude FLOAT (10,6) NULL,
Longitude FLOAT (10,6) NULL
);

CREATE TABLE IF NOT EXISTS Sightings(
SightingsID INT PRIMARY KEY AUTO_INCREMENT,
LocationID INT NOT NULL,
FOREIGN KEY fk_Sightings_Location(LocationID) references Location(LocationID),
CharacterID INT NOT NULL,
FOREIGN KEY fk_Sightings_characters(CharacterID) references characters(CharacterID),
timeOfSighting timestamp NOT NULL
);

CREATE TABLE IF NOT EXISTS superPower(
superPowerID INT PRIMARY KEY auto_increment,
`name` varchar(55) NOT NULL,
`description` varchar(155) NOT NULL
);

CREATE TABLE IF NOT EXISTS hasSuperPowers(
hasSuperPowersID INT PRIMARY KEY AUTO_INCREMENT,
CharacterID INT NOT NULL,
FOREIGN KEY fk_hasSuperPowers_characters(CharacterID) references characters(CharacterID),
superPowerID INT NOT NULL,
foreign key fk_hasSuperPowers_superPower(superPowerID) references superPower(superPowerID));
