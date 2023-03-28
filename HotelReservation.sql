Drop database If Exists Hotel;

Create database If Not Exists Hotel;  

use Hotel;  

#
# TABLE STRUCTURE FOR: RoomAddOnRateDateRange
#

DROP TABLE IF EXISTS RoomAddOnRate;

CREATE TABLE `RoomAddOnRate` (
  `RoomAddOnRateID` int(11) NOT NULL AUTO_INCREMENT,
  `RoomAddOnRateName` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `StartDate` datetime DEFAULT NULL,
  `EndDate` datetime DEFAULT NULL,
  `Price` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`RoomAddOnRateID`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO RoomAddOnRate VALUES 
(1, 'voluptate', '2015-01-01 00:00:00', '2018-01-01 00:00:00', '34'),
(2, 'voluptate', '2015-01-01 00:00:00', '2018-01-01 00:00:00', '34'),
(3, 'voluptate', '2015-01-01 00:00:00', '2018-01-01 00:00:00', '34'),
(4, 'voluptate', '2015-01-01 00:00:00', '2018-01-01 00:00:00', '34'),
(5, 'voluptate', '2015-01-01 00:00:00', '2018-01-01 00:00:00', '34'), #new values after this
(6, 'defunct rate', '2015-01-01 00:00:00', '2017-01-01 00:00:00', '20'), #problem 5
(7, 'new rate', '2017-01-01 00:00:00', NULL, '25'); #problem 5




#
# TABLE STRUCTURE FOR: RoomAddOnType
#

DROP TABLE IF EXISTS RoomAddOnType;

CREATE TABLE `RoomAddOnType` (
  `RoomAddOnTypeID` int(11) NOT NULL AUTO_INCREMENT,
  `RoomAddOnTypeName` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`RoomAddOnTypeID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO RoomAddOnType (`RoomAddOnTypeID`, `RoomAddOnTypeName`) VALUES (1, 'champagne');
INSERT INTO RoomAddOnType (`RoomAddOnTypeID`, `RoomAddOnTypeName`) VALUES (2, 'error');
INSERT INTO RoomAddOnType (`RoomAddOnTypeID`, `RoomAddOnTypeName`) VALUES (3, 'blanditiis');


#
# TABLE STRUCTURE FOR: TaxRate
#

DROP TABLE IF EXISTS TaxRate;

CREATE TABLE `TaxRate` (
  `TaxRateId` int(11) NOT NULL AUTO_INCREMENT,
  `TaxRateName` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `StartDate` date DEFAULT NULL,
  `EndDate` date DEFAULT NULL,
  PRIMARY KEY (`TaxRateId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO TaxRate (`TaxRateId`, `TaxRateName`, `StartDate`, `EndDate`) VALUES (1, 'dolorum', '2015-01-01', '2018-01-01');
INSERT INTO TaxRate (`TaxRateId`, `TaxRateName`, `StartDate`, `EndDate`) VALUES (2, 'saepe', '2015-01-01', '2018-01-01');
INSERT INTO TaxRate (`TaxRateId`, `TaxRateName`, `StartDate`, `EndDate`) VALUES (3, 'laborum', '2015-01-01', '2018-01-01');


#
# TABLE STRUCTURE FOR: Override
#

DROP TABLE IF EXISTS Override;

CREATE TABLE `Override` (
  `OverrideID` int(11) NOT NULL AUTO_INCREMENT,
  `OverrideReasonName` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `OverrideReasonDescription` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`OverrideID`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;




#
# TABLE STRUCTURE FOR: Season
#

DROP TABLE IF EXISTS Season;

CREATE TABLE `Season` (
  `SeasonID` int(11) NOT NULL AUTO_INCREMENT,
  `SeasonName` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`SeasonID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO Season (`SeasonID`, `SeasonName`) VALUES (1, 'Spring');
INSERT INTO Season (`SeasonID`, `SeasonName`) VALUES (2, 'Summer');
INSERT INTO Season (`SeasonID`, `SeasonName`) VALUES (3, 'Fall');
INSERT INTO Season (`SeasonID`, `SeasonName`) VALUES (4, 'Winter');
INSERT INTO Season (`SeasonID`, `SeasonName`) VALUES (5, 'Smarch');


#
# TABLE STRUCTURE FOR: PromotionCodeType
#

DROP TABLE IF EXISTS PromotionCodeType;

CREATE TABLE `PromotionCodeType` (
  `PromotionCodeTypeID` int(11) NOT NULL AUTO_INCREMENT,
  `PromotionCodeName` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`PromotionCodeTypeID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO PromotionCodeType (`PromotionCodeTypeID`, `PromotionCodeName`) VALUES (1, 'percent');
INSERT INTO PromotionCodeType (`PromotionCodeTypeID`, `PromotionCodeName`) VALUES (2, 'flat discount');


#
# TABLE STRUCTURE FOR: Address
#

DROP TABLE IF EXISTS Address;

CREATE TABLE `Address` (
  `AddressID` int(11) NOT NULL AUTO_INCREMENT,
  `AddressNumber` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Street` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `State` char(2) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ZipCode` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`AddressID`)
) ENGINE=InnoDB AUTO_INCREMENT=201 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO Address (`AddressID`, `AddressNumber`, `Street`, `State`, `ZipCode`) VALUES (1, '6930', 'Bauch Forge', 'NH', '29067-3517');
INSERT INTO Address (`AddressID`, `AddressNumber`, `Street`, `State`, `ZipCode`) VALUES (2, '49762', 'Carli Greens', 'ND', '91763');
INSERT INTO Address (`AddressID`, `AddressNumber`, `Street`, `State`, `ZipCode`) VALUES (3, '0265', 'Bosco Point', 'WY', '84070');
INSERT INTO Address (`AddressID`, `AddressNumber`, `Street`, `State`, `ZipCode`) VALUES (4, '1422', 'Hulda Landing', 'UT', '78412-6459');
INSERT INTO Address (`AddressID`, `AddressNumber`, `Street`, `State`, `ZipCode`) VALUES (5, '6524', 'Swaniawski Lodge', 'CT', '01943-6675');

#
# TABLE STRUCTURE FOR: RoomType
#

DROP TABLE IF EXISTS RoomType;

CREATE TABLE `RoomType` (
  `RoomTypeID` int(11) NOT NULL AUTO_INCREMENT,
  `RoomTypeName` varchar(40) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Occupancy` tinyint(4) DEFAULT NULL,
  `StartDate` datetime DEFAULT NULL,
  `EndDate` datetime DEFAULT NULL,
  PRIMARY KEY (`RoomTypeID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO RoomType VALUES
(1, 'Double', 2, '2015-01-01 00:00:00', NULL),
(2, 'Single', 1, '2015-01-01 00:00:00', NULL),
(3, 'Defunct', 4, '2015-01-01 00:00:00', '2015-01-02 00:00:00');



#
# TABLE STRUCTURE FOR: RoomAmenityType
#

DROP TABLE IF EXISTS RoomAmenityType;

CREATE TABLE `RoomAmenityType` (
  `RoomAmenityTypeID` int(11) NOT NULL AUTO_INCREMENT,
  `RoomAmenityTypeName` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`RoomAmenityTypeID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO RoomAmenityType VALUES 
(1, 'appliance'),
(2, 'miscellaneous');


#
# TABLE STRUCTURE FOR: RoomRateDateRange
#

DROP TABLE IF EXISTS RoomRateDateRange;

CREATE TABLE `RoomRateDateRange` (
  `RoomRateDateRangeID` int(11) NOT NULL AUTO_INCREMENT,
  `Price` decimal(10,0) DEFAULT NULL,
  `StartDate` date DEFAULT NULL,
  `EndDate` date DEFAULT NULL,
  PRIMARY KEY (`RoomRateDateRangeID`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO RoomRateDateRange (`RoomRateDateRangeID`, `Price`, `StartDate`, `EndDate`) VALUES (1, '200', '2015-01-01', '2018-01-01');
INSERT INTO RoomRateDateRange (`RoomRateDateRangeID`, `Price`, `StartDate`, `EndDate`) VALUES (2, '150', '2015-01-01', '2018-01-01');
INSERT INTO RoomRateDateRange (`RoomRateDateRangeID`, `Price`, `StartDate`, `EndDate`) VALUES (3, '216', '2015-01-01', '2018-01-01');
INSERT INTO RoomRateDateRange (`RoomRateDateRangeID`, `Price`, `StartDate`, `EndDate`) VALUES (4, '260', '2015-01-01', '2018-01-01');
INSERT INTO RoomRateDateRange (`RoomRateDateRangeID`, `Price`, `StartDate`, `EndDate`) VALUES (5, '97', '2015-01-01', '2018-01-01');


#
# TABLE STRUCTURE FOR: RoomAmenity
#

DROP TABLE IF EXISTS RoomAmenity;

CREATE TABLE `RoomAmenity` (
  `RoomAmenityID` int(11) NOT NULL AUTO_INCREMENT,
  `StartDate` datetime DEFAULT NULL,
  `EndDate` datetime DEFAULT NULL,
  `RoomAmenityName` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  #`Quantity` int(11) NOT NULL,
  `RoomAmenityTypeID` int(11) NOT NULL,
  PRIMARY KEY (`RoomAmenityID`),
  KEY `fk_roomamenity_roomamenitytype` (`RoomAmenityTypeID`),
  CONSTRAINT `fk_roomamenity_roomamenitytype` FOREIGN KEY (`RoomAmenityTypeID`) REFERENCES `RoomAmenityType` (`RoomAmenityTypeID`) ON DELETE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO RoomAmenity VALUES 
(1, '2015-01-01 00:00:00', NULL, 'refrigerator', 1),
(2, '2015-01-01 00:00:00', NULL, 'television', 1),
(3, '2015-01-01 00:00:00', NULL, 'lock box', 2);




#good
create table if not exists Branch(
BranchID int not null auto_increment, 
TaxRateID int not null,
AddressID int not null,
primary key(BranchID),
constraint fk_Branch_TaxRate Foreign Key(TaxRateID) REFERENCES TaxRate(TaxRateID) on delete no action, #good
constraint fk_Branch_Address Foreign Key(AddressID) REFERENCES Address(AddressID) on delete no action #good
); 

INSERT INTO Branch VALUES 
(1, 1, 1),
(2, 2, 2);


#good
create table if not exists Person(
PersonID int not null auto_increment, 
FirstName varchar(30),
LastName varchar(30), 
PhoneNumber varchar(20),
Birthday DATE, 
Email varchar(30),
AddressID int,
BranchID int, 
primary key(PersonID), 
constraint fk_person_address Foreign Key(AddressID) REFERENCES Address(AddressID) on delete no action, #good
constraint fk_person_Branch Foreign Key(BranchID) REFERENCES Branch(BranchID) on delete no action #good
);

INSERT INTO Person (`PersonID`, `FirstName`, `LastName`, `PhoneNumber`, `Birthday`, `Email`, `AddressID`, `BranchID`) VALUES (1, 'John', 'Smith', '5551234', '1980-01-01', 'bob@dole.com', NULL, NULL);
INSERT INTO Person (`PersonID`, `FirstName`, `LastName`, `PhoneNumber`, `Birthday`, `Email`, `AddressID`, `BranchID`) VALUES (2, 'Jane', 'Smith', '5551233', '1980-01-02', 'jane@dole.com', NULL, NULL);

#good
create table if not exists Customer(
CustomerID int not null auto_increment, 
RegistrationDate DATETIME, 
PersonID int not null, 
primary key(CustomerID),
constraint fk_customer_person Foreign Key(PersonID) REFERENCES Person(PersonID) on delete no action #good
); 

INSERT INTO Customer (`CustomerID`, `RegistrationDate`, `PersonID`) Values (1, '2015-01-01', 1);
INSERT INTO Customer (`CustomerID`, `RegistrationDate`, `PersonID`) Values (2, '2015-02-01', 2);

#good
create table if not exists Reservation(

ReservationID int not null auto_increment, 
StartDate DATETIME,
EndDate DATETIME, 
CustomerID int not null, 
primary key(ReservationID),
constraint fk_reservation_customer Foreign Key(CustomerID) REFERENCES Customer(CustomerID) on delete no action #good
); 

INSERT INTO Reservation VALUES 
(1, '2016-01-01', '2016-01-04', 1),
(2, '2016-01-02', '2016-01-04', 2),
(3, '2017-01-01','2017-01-02', 1);

#good
create table if not exists Bill(
BillID int not null auto_increment, 
GrandTotal DECIMAL,
TotalTax DECIMAL,
BillingDate DATETIME,
ReservationID int not null,
CustomerID int not null,
primary key(BillID),
constraint fk_bill_reservation Foreign Key(ReservationID) REFERENCES Reservation(ReservationID) on delete no action, #good
constraint fk_bill_customer Foreign Key(CustomerID) REFERENCES Customer(CustomerID) on delete no action #good
); 

INSERT INTO Bill VALUES (1, 642.5, 25, '2016-01-04', 1, 1);
INSERT INTO Bill VALUES (2, 470, 20, '2016-01-04', 2, 2);

#good SwitchedPlaces
create table if not exists BillDetailType(
BillDetailTypeID int not null auto_increment,
BillDetailTypeName varchar(30),
primary key(BillDetailTypeID)
); 

INSERT INTO BillDetailType VALUES (1, 'Room Charge');
INSERT INTO BillDetailType VALUES (2, 'Room AddOn Charge');
INSERT INTO BillDetailType VALUES (3, 'Percent Discount');
INSERT INTO BillDetailType VALUES (4, 'Flat Discount');


#good
create table if not exists BillDetail(
#Please check this over again
BillDetailID int not null auto_increment,
BillDetailTypeID int not null,
BillDetailName varchar(40),
Charge DECIMAL(10,2),
OverrideID int null, 
BillID int not null,
primary key(BillDetailID), 
constraint fk_billdetail_override Foreign Key(OverrideID) REFERENCES Override(OverrideID) on delete no action, #good
constraint fk_billdetail_bill Foreign Key(BillID) REFERENCES Bill(BillID) on delete no action, #good
constraint fk_billdetail_type Foreign Key(BillDetailTypeID) REFERENCES BillDetailType(BillDetailTypeID) on delete no action #good
); 

INSERT INTO BillDetail VALUES (1, 1, 'Room Charge', 200, NULL, 1);
INSERT INTO BillDetail VALUES (2, 1, 'Room Charge', 200, NULL, 1);
INSERT INTO BillDetail VALUES (3, 2, 'Room AddOn Charge', 10, NULL, 1);
INSERT INTO BillDetail VALUES (4, 1, 'Room Charge', 200, NULL, 1);
INSERT INTO BillDetail VALUES (5, 2, 'Room AddOn Charge', 15, NULL, 1);
INSERT INTO BillDetail VALUES (6, 1, 'Room Charge', 200, NULL, 1);
INSERT INTO BillDetail VALUES (7, 3, 'Percent Discount', -82.5, NULL, 1);
INSERT INTO BillDetail VALUES (8, 4, 'Flat Discount', -100, NULL, 1);

INSERT INTO BillDetail VALUES (9, 1, 'Room Charge', 150, NULL, 2);
INSERT INTO BillDetail VALUES (10, 1, 'Room Charge', 150, NULL, 2);
INSERT INTO BillDetail VALUES (11, 1, 'Room Charge', 150, NULL, 2);


#good
create table if not exists SpecialEvent(
SpecialEventID int not null auto_increment,
StartDate DATETIME,
EndDate DATETIME, 
BranchID int not null, 
primary key(SpecialEventID), 
constraint fk_SpecialEvent_Branch Foreign Key(BranchID) REFERENCES Branch(BranchID) on delete no action
);

###2 constraints

#good
create table if not exists Room(
RoomID int not null auto_increment, 
RoomTypeID int, #not null,
StartDate DATETIME,
EndDate DATETIME,
RoomNumber varchar(30),
Floor char(3),
BranchID int, #not null,
primary key(RoomID), 
constraint fk_room_roomtype Foreign Key(RoomTypeID) REFERENCES RoomType(RoomTypeID) on delete no action, 
constraint fk_room_branch Foreign Key(BranchID) REFERENCES Branch(BranchID) on delete no action #good

); 

INSERT INTO Room VALUES 
(1, 2, '2015-01-01', NULL, '101', '1st', 1),
(2, 1, '2015-01-01', NULL, '102', '1st', 1),
(3, 2, '2015-01-01', NULL, '103', '1st', 2),
(4, 3, '2015-01-01', '2015-01-02', '104', '1st', 2),
(5, 1, '2015-01-02', NULL, '104', '1st', 2);

#good
create table if not exists RoomType_RoomAmenity(

 
RoomTypeID int not null, 
RoomAmenityID int not null,
Quantity int not null,
constraint fk_a Foreign Key(RoomTypeID) REFERENCES RoomType(RoomTypeID) on delete no action, 
constraint fk_b Foreign Key(RoomAmenityID) REFERENCES RoomAmenity(RoomAmenityID) on delete no action,
primary key(RoomTypeID, RoomAmenityID)
); 

INSERT INTO RoomType_RoomAmenity VALUES
(1, 1, 1),
(1, 2, 2),
(1, 3, 2),
(2, 2, 1);





#good
create table if not exists Room_Reservation(
Room_ReservationID int not null auto_increment,
CheckInDate DATETIME,
CheckOutDate DATETIME,
Cancelled Bool not null, 
RoomID int not null,
ReservationID int not null, 
primary key(Room_ReservationID),
constraint fk_room_reservation_room Foreign Key(RoomID) REFERENCES Room(RoomID) on delete no action, #good
constraint fk_room_reservation_reservation Foreign Key(ReservationID) REFERENCES Reservation(ReservationID) on delete no action #good
); 

INSERT INTO Room_Reservation VALUES
(1,'2016-01-01','2016-01-04', false, 1, 1),
(2,'2016-01-02','2016-01-04', false, 2, 2),
(3,'2016-01-01','2016-01-03', false, 3, 1),
(4,'2017-01-01','2017-01-02', true, 1, 3);

#good
create table if not exists Guest(
GuestID int not null auto_increment,  
PersonID int not null, 
ReservationID int not null, 
primary key(GuestID), 
constraint fk_guest_reservation Foreign Key(ReservationID ) REFERENCES Reservation(ReservationID) on delete no action,
constraint fk_guest_person Foreign Key(PersonID) REFERENCES Person(PersonID) on delete no action
);
#good
create table if not exists Employee(
#check again
EmployeeID int not null auto_increment, 
PersonID int not null,
BranchID int null, 
primary key(EmployeeID),
constraint fk_employee_person Foreign Key(PersonID ) REFERENCES Person(PersonID) on delete no action,
constraint fk_employee_branch Foreign Key(BranchID) REFERENCES Branch(BranchID) on delete no action
);


#good
create table if not exists RoomRate(
#Special event null?
RoomRateID int not null auto_increment, 
Rate DECIMAL,
StartDate datetime,
EndDate Datetime,
Weekend bool,
RoomTypeID int not null,
RoomRateDateRangeID int not null,
SpecialEventID int null, 
SeasonID int not null,
BranchID int not null, 
primary key(RoomRateID),
constraint fk_roomrate_roomtype Foreign Key(RoomTypeID) REFERENCES RoomType(RoomTypeID) on delete no action, #good
constraint fk_roomrate_specialevent Foreign Key(SpecialEventID) REFERENCES SpecialEvent(SpecialEventID) on delete no action, #good
constraint fk_roomrate_season Foreign Key(SeasonID) REFERENCES Season(SeasonID) on delete no action, #good
constraint fk_roomrate_branch Foreign Key(BranchID ) REFERENCES Branch(BranchID ) on delete no action, #good
constraint fk_roomrate_roomratedaterange Foreign Key(RoomRateDateRangeID) REFERENCES RoomRateDateRange(RoomRateDateRangeID) on delete no action #good
); 

INSERT INTO RoomRate VALUES 
(1, 200, '2015-01-01', NULL, true, 1, 1, NULL, 1, 1),
(2, 150, '2015-01-01', NULL, true, 2, 1, NULL, 1, 1);


#good
create table if not exists PromotionCode(
PromotionCodeID int not null auto_increment,
PromotionCodeTypeID int not null,
PromotionCodeName varchar(30), 
PromotionCodeIsPercent BOOL, 
BranchID int not null,
Percent Decimal NULL,
DollarsOff Decimal Null,
StartDate Datetime not null,
EndDate datetime null,
primary key(PromotionCodeID),
constraint fk_PromotionCode_branch Foreign Key(BranchID) REFERENCES Branch(BranchID) on delete no action, #good
constraint fk_PromotionCode_PromotionCodeType Foreign Key(PromotionCodeTypeID) REFERENCES PromotionCodeType(PromotionCodeTypeID) on delete no action, #good
Check(Percent IS Null XOR DollarsOff Is Null)
);

INSERT INTO PromotionCode VALUES 
(1, 1, 'Percent Days', true, 1, 10, NULL, '2015-01-01', NULL),
(2, 2, 'Dollar Days', false, 1, NULL, 100, '2015-01-01', NULL);

#good
create table if not exists PromotionCode_Reservation(
PromotionCode_ReservationID int not null auto_increment,
PromotionCodeID int not null,
ReservationID int not null,
primary key(PromotionCode_ReservationID),
constraint fk_Reservation_promotioncode Foreign Key(PromotionCodeID) REFERENCES PromotionCode(PromotionCodeID) on delete no action, #good
constraint fk_Reservation_Reservation Foreign Key(ReservationID) REFERENCES Reservation(ReservationID) on delete no action #good
); 

INSERT INTO PromotionCode_Reservation VALUES
(1, 1, 1),
(2, 2, 1);

#good
create table if not exists RoomCharge(
RoomChargeID int not null auto_increment,
Charge DECIMAL,
RoomRateID int not null,
BillDetailID int not null,
primary key(RoomChargeID),
constraint fk_RoomCharge_RoomRate Foreign Key(RoomRateID ) REFERENCES RoomRate(RoomRateID) on delete no action, 
constraint fk_RoomCharge_roomcharge Foreign Key(BillDetailID) REFERENCES BillDetail(BillDetailID) on delete no action
); 

INSERT INTO RoomCharge (`RoomChargeID`, `Charge`, `RoomRateID`, `BillDetailID`) VALUES (1, 200, 1, 1);
INSERT INTO RoomCharge (`RoomChargeID`, `Charge`, `RoomRateID`, `BillDetailID`) VALUES (2, 200, 1, 2);
INSERT INTO RoomCharge (`RoomChargeID`, `Charge`, `RoomRateID`, `BillDetailID`) VALUES (3, 200, 1, 4);
INSERT INTO RoomCharge (`RoomChargeID`, `Charge`, `RoomRateID`, `BillDetailID`) VALUES (4, 200, 1, 6);
INSERT INTO RoomCharge (`RoomChargeID`, `Charge`, `RoomRateID`, `BillDetailID`) VALUES (5, 150, 2, 7);
INSERT INTO RoomCharge (`RoomChargeID`, `Charge`, `RoomRateID`, `BillDetailID`) VALUES (6, 150, 2, 8);
INSERT INTO RoomCharge (`RoomChargeID`, `Charge`, `RoomRateID`, `BillDetailID`) VALUES (7, 150, 2, 9);

create table if not exists RoomAddOn(
RoomAddOnId int not null auto_increment,
RoomAddOnName varchar(30), 
RoomAddOnRateID int not null,
RoomAddOnTypeID int not null,
Room_ReservationID int not null,
DateOrdered Date not null,
primary key(RoomAddOnId),
constraint fk_RoomAddOn_RoomAddOnRate Foreign Key(RoomAddOnRateID) REFERENCES RoomAddOnRate(RoomAddOnRateID) on delete no action,
constraint fk_RoomAddOn_RoomAddOnType Foreign Key(RoomAddOnTypeID) REFERENCES RoomAddOnType(RoomAddOnTypeID) on delete no action,
constraint fk_RoomAddOn_Room_Reservation Foreign Key(Room_ReservationID) REFERENCES Room_Reservation(Room_ReservationID) on delete no action
); 

INSERT INTO RoomAddOn VALUES 
(1, 'Hamburger', 1, 1, 1, '2016-01-02'),
(2, 'Cheeseburger', 2, 1, 1, '2016-01-03');


create table if not exists RoomCharge_RoomAddOn(
RoomCharge_RoomAddOnID int not null auto_increment,
RoomChargeID int not null,
RoomAddOnID int not null,
primary key(RoomCharge_RoomAddOnID),
constraint fk_RoomCharge_RoomAddOn_roomcharge Foreign Key(RoomChargeID) REFERENCES RoomCharge(RoomChargeID ) on delete no action,
constraint fk_RoomCharge_RoomAddOn_roomaddon Foreign Key(RoomAddOnID) REFERENCES RoomAddOn(RoomAddOnID) on delete no action
); 

INSERT INTO RoomCharge_RoomAddOn (`RoomCharge_RoomAddOnID`, `RoomChargeID`, `RoomAddOnID`) VALUES (1, 1, 1);
INSERT INTO RoomCharge_RoomAddOn (`RoomCharge_RoomAddOnID`, `RoomChargeID`, `RoomAddOnID`) VALUES (2, 1, 2);