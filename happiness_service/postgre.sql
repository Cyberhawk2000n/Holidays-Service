-- Create Table: Departments
--------------------------------------------------------------------------------
CREATE TABLE Departments
(
	id INTEGER NOT NULL 
	,name VARCHAR(64) NOT NULL 
	,manager_id BIGINT  NULL 
	,CONSTRAINT PK_Departments_id PRIMARY KEY (id)
);



-- Create Table: Logins
--------------------------------------------------------------------------------
CREATE TABLE Logins
(
	user_id BIGINT NOT NULL 
	,login VARCHAR(32) NOT NULL 
	,password VARCHAR(32) NOT NULL 
	,CONSTRAINT PK_Logins_user_id PRIMARY KEY (user_id)
);



-- Create Table: Users
--------------------------------------------------------------------------------
CREATE TABLE Users
(
	id BIGINT NOT NULL 
	,name VARCHAR(32) NOT NULL 
	,surname VARCHAR(32) NOT NULL 
	,patronymic VARCHAR(32)  NULL 
	,birthday TIMESTAMP NOT NULL 
	,role_id BIGINT NOT NULL 
	,email VARCHAR(64) NOT NULL 
	,about VARCHAR(256)  NULL 
	,dep_id INTEGER NOT NULL 
	,give_gift BOOLEAN NOT NULL 
	,CONSTRAINT PK_Users_id PRIMARY KEY (id)
);



-- Create Table: Categories
--------------------------------------------------------------------------------
CREATE TABLE Categories
(
	id BIGINT NOT NULL 
	,parent_id BIGINT  NULL 
	,name VARCHAR(64) NOT NULL 
	,CONSTRAINT PK_Categories_id PRIMARY KEY (id)
);



-- Create Table: Vote
--------------------------------------------------------------------------------
CREATE TABLE Vote
(
	hist_id BIGINT NOT NULL 
	,gift_id BIGINT NOT NULL 
	,count BIGINT NOT NULL 
	,id BIGINT NOT NULL 
	,CONSTRAINT PK_Vote_id PRIMARY KEY (id)
);



-- Create Table: Roles
--------------------------------------------------------------------------------
CREATE TABLE Roles
(
	id SMALLINT NOT NULL 
	,role VARCHAR(16) NOT NULL 
	,CONSTRAINT PK_Roles_id PRIMARY KEY (id)
);



-- Create Table: Money
--------------------------------------------------------------------------------
CREATE TABLE Money
(
	hist_id BIGINT NOT NULL 
	,money BIGINT NOT NULL 
	,money_max BIGINT NOT NULL 
	,CONSTRAINT PK_Money_hist_id PRIMARY KEY (hist_id)
);



-- Create Table: Events
--------------------------------------------------------------------------------
CREATE TABLE Events
(
	id BIGINT NOT NULL 
	,name VARCHAR(64) NOT NULL 
	,type_id BIGINT NOT NULL 
	,every_year BOOLEAN NOT NULL 
	,user_id BIGINT  NULL 
	,date TIMESTAMP NOT NULL 
	,active BOOLEAN NOT NULL 
	,template VARCHAR(256) NOT NULL 
	,manager_id BIGINT  NULL 
	,CONSTRAINT PK_Events_id PRIMARY KEY (id)
);



-- Create Table: Event_types
--------------------------------------------------------------------------------
CREATE TABLE Event_types
(
	id SMALLINT NOT NULL 
	,name VARCHAR(32) NOT NULL 
	,CONSTRAINT PK_Event_types_id PRIMARY KEY (id)
);



-- Create Table: Gifts
--------------------------------------------------------------------------------
CREATE TABLE Gifts
(
	id BIGINT NOT NULL 
	,cat_id BIGINT NOT NULL 
	,name VARCHAR(64) NOT NULL 
	,CONSTRAINT PK_Gifts_id PRIMARY KEY (id)
);



-- Create Table: User_vote
--------------------------------------------------------------------------------
CREATE TABLE User_vote
(
	id BIGINT NOT NULL 
	,user_id BIGINT NOT NULL 
	,hist_id BIGINT NOT NULL 
	,vote_id BIGINT NOT NULL 
	,CONSTRAINT PK_User_vote_id PRIMARY KEY (id)
);



-- Create Table: Preferences
--------------------------------------------------------------------------------
CREATE TABLE Preferences
(
	user_id BIGINT NOT NULL 
	,cat_id BIGINT NOT NULL 
	,id BIGINT NOT NULL 
	,CONSTRAINT PK_Preferences_id PRIMARY KEY (id)
);



-- Create Table: Gift_history
--------------------------------------------------------------------------------
CREATE TABLE Gift_history
(
	gift_id BIGINT NOT NULL 
	,user_id BIGINT NOT NULL 
	,id BIGINT NOT NULL 
	,CONSTRAINT PK_Gift_history_id PRIMARY KEY (id)
);

CREATE TABLE Passwd
(
	id SMALLINT NOT NULL 
	,passwd VARCHAR(64) NOT NULL 
	,CONSTRAINT PK_Passwd_id PRIMARY KEY (id)
);

INSERT INTO Roles (id, role) VALUES (0, 'root');
INSERT INTO Roles (id, role) VALUES (1, 'moderator');
INSERT INTO Roles (id, role) VALUES (2, 'user');

INSERT INTO Event_types (id, name) VALUES (0, 'Birthday');
INSERT INTO Event_types (id, name) VALUES (1, 'Corporate');
INSERT INTO Event_types (id, name) VALUES (2, 'Family adding');

INSERT INTO Categories (id, name) VALUES (0, 'Devices');
INSERT INTO Categories (id, name) VALUES (1, 'Vehicle');
INSERT INTO Categories (id, name) VALUES (2, 'Other');
INSERT INTO Categories (id, parent_id, name) VALUES (3, 0, 'Smartphone');
INSERT INTO Categories (id, parent_id, name) VALUES (4, 0, 'Laptop');
INSERT INTO Categories (id, parent_id, name) VALUES (5, 1, 'Motorcycle');
INSERT INTO Categories (id, parent_id, name) VALUES (6, 1, 'Bicycle');
INSERT INTO Categories (id, parent_id, name) VALUES (7, 2, 'Others');

INSERT INTO Gifts (id, cat_id, name) VALUES (0, 4, 'Asus K53SJ');
INSERT INTO Gifts (id, cat_id, name) VALUES (1, 3, 'Prestigio PAP3350');
INSERT INTO Gifts (id, cat_id, name) VALUES (2, 5, 'Diablo 1000 N');

INSERT INTO Departments (id, name) VALUES (1, 'Dep1');
INSERT INTO Departments (id, name) VALUES (5, 'Dep5');

INSERT INTO Users(id, name, surname, patronymic, birthday, role_id, email, about, dep_id, give_gift)
	VALUES(0, 'Dmitriy', 'Nochevnoy', 'Sergeevich', '1995-12-26', 0, 'nochds@gmail.com', 'about', 5, TRUE);
INSERT INTO Users(id, name, surname, patronymic, birthday, role_id, email, about, dep_id, give_gift)
	VALUES(1, 'Eujene', 'Reevs', 'Olegovich', '1995-01-07', 2, 'nochds22@gmail.com', 'about', 5, TRUE);
INSERT INTO Users(id, name, surname, patronymic, birthday, role_id, email, about, dep_id, give_gift)
	VALUES(2, 'John', 'King', 'Dunbar', '1995-01-05', 2, 'nochds11@gmail.com', 'about', 5, TRUE);
INSERT INTO Users(id, name, surname, birthday, role_id, email, about, dep_id, give_gift)
	VALUES(3, 'Jim', 'Read', '1995-12-12', 1, 'cyberhawk2000n@gmail.com', 'about', 5, TRUE);

INSERT INTO Logins(user_id, login, password)
	VALUES(0, 'nochds@gmail.com', 'e8207de0d491981009b44d9afd944000');
INSERT INTO Logins(user_id, login, password)
	VALUES(1, 'nochds22@gmail.com', 'e48506d075c0d1acdabcaaa3c6ed56d8');
INSERT INTO Logins(user_id, login, password)
	VALUES(2, 'nochds11@gmail.com', '1e41b95c74646074e22bca6e48c12680');
INSERT INTO Logins(user_id, login, password)
	VALUES(3, 'cyberhawk2000n@gmail.com', 'da1077dd4155e10eb1d447211ad095c9');

INSERT INTO Events(id, name, type_id, every_year, user_id, date, active, template)
	VALUES(0, 'Birthday of nochds@gmail.com', 0, TRUE, 0, '2016-12-26', TRUE, 'template');
INSERT INTO Events(id, name, type_id, every_year, user_id, date, active, template)
	VALUES(1, 'Birthday of nochds22@gmail.com', 0, TRUE, 1, '2017-01-07', FALSE, 'template');
INSERT INTO Events(id, name, type_id, every_year, user_id, date, active, template)
	VALUES(2, 'Birthday of nochds11@gmail.com', 0, TRUE, 2, '2017-01-05', FALSE, 'template');
INSERT INTO Events(id, name, type_id, every_year, user_id, date, active, template)
	VALUES(3, 'Birthday of cyberhawk2000n@gmail.com', 0, TRUE, 3, '2017-12-12', FALSE, 'template');

INSERT INTO Preferences (id, user_id, cat_id) VALUES (0, 0, 3);
INSERT INTO Preferences (id, user_id, cat_id) VALUES (1, 0, 5);
INSERT INTO Preferences (id, user_id, cat_id) VALUES (2, 0, 6);

INSERT INTO Gift_history (id, user_id, gift_id) VALUES (0, 0, 1);