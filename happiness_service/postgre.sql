-- Create Table: Event_types
--------------------------------------------------------------------------------
CREATE TABLE Event_types
(
	id SMALLINT NOT NULL 
	,name VARCHAR(32) NOT NULL 
	,CONSTRAINT PK_Event_types_id PRIMARY KEY (id)
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



-- Create Table: Preferences
--------------------------------------------------------------------------------
CREATE TABLE Preferences
(
	user_id BIGINT NOT NULL 
	,cat_id BIGINT NOT NULL 
	,id BIGINT NOT NULL 
	,CONSTRAINT PK_Preferences_id PRIMARY KEY (id)
);



-- Create Table: Departments
--------------------------------------------------------------------------------
CREATE TABLE Departments
(
	id INTEGER NOT NULL 
	,name VARCHAR(64) NOT NULL 
	,manager_id BIGINT  NULL 
	,CONSTRAINT PK_Departments_id PRIMARY KEY (id)
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



-- Create Table: Money
--------------------------------------------------------------------------------
CREATE TABLE Money
(
	hist_id BIGINT NOT NULL 
	,money BIGINT NOT NULL 
	,money_max BIGINT NOT NULL 
	,CONSTRAINT PK_Money_hist_id PRIMARY KEY (hist_id)
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



-- Create Table: Gifts
--------------------------------------------------------------------------------
CREATE TABLE Gifts
(
	id BIGINT NOT NULL 
	,cat_id BIGINT NOT NULL 
	,name VARCHAR(64) NOT NULL 
	,CONSTRAINT PK_Gifts_id PRIMARY KEY (id)
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



-- Create Table: Gift_history
--------------------------------------------------------------------------------
CREATE TABLE Gift_history
(
	gift_id BIGINT NOT NULL 
	,user_id BIGINT NOT NULL 
	,id BIGINT NOT NULL 
	,CONSTRAINT PK_Gift_history_id PRIMARY KEY (id)
);



-- Create Table: Roles
--------------------------------------------------------------------------------
CREATE TABLE Roles
(
	id SMALLINT NOT NULL 
	,role VARCHAR(16) NOT NULL 
	,CONSTRAINT PK_Roles_id PRIMARY KEY (id)
);



-- Create Foreign Key: Vote.gift_id -> Gifts.id
ALTER TABLE Vote ADD CONSTRAINT FK_Vote_gift_id_Gifts_id FOREIGN KEY (gift_id) REFERENCES Gifts(id);


-- Create Foreign Key: Events.type_id -> Event_types.id
ALTER TABLE Events ADD CONSTRAINT FK_Events_type_id_Event_types_id FOREIGN KEY (type_id) REFERENCES Event_types(id);


-- Create Foreign Key: Events.user_id -> Users.id
ALTER TABLE Events ADD CONSTRAINT FK_Events_user_id_Users_id FOREIGN KEY (user_id) REFERENCES Users(id);


-- Create Foreign Key: Gifts.cat_id -> Categories.id
ALTER TABLE Gifts ADD CONSTRAINT FK_Gifts_cat_id_Categories_id FOREIGN KEY (cat_id) REFERENCES Categories(id);


-- Create Foreign Key: Events.manager_id -> Users.id
ALTER TABLE Events ADD CONSTRAINT FK_Events_manager_id_Users_id FOREIGN KEY (manager_id) REFERENCES Users(id);


-- Create Foreign Key: Money.hist_id -> Events.id
ALTER TABLE Money ADD CONSTRAINT FK_Money_hist_id_Events_id FOREIGN KEY (hist_id) REFERENCES Events(id);


-- Create Foreign Key: Vote.hist_id -> Events.id
ALTER TABLE Vote ADD CONSTRAINT FK_Vote_hist_id_Events_id FOREIGN KEY (hist_id) REFERENCES Events(id);


-- Create Foreign Key: Users.dep_id -> Departments.id
ALTER TABLE Users ADD CONSTRAINT FK_Users_dep_id_Departments_id FOREIGN KEY (dep_id) REFERENCES Departments(id);


-- Create Foreign Key: Preferences.user_id -> Users.id
ALTER TABLE Preferences ADD CONSTRAINT FK_Preferences_user_id_Users_id FOREIGN KEY (user_id) REFERENCES Users(id);


-- Create Foreign Key: Preferences.cat_id -> Categories.id
ALTER TABLE Preferences ADD CONSTRAINT FK_Preferences_cat_id_Categories_id FOREIGN KEY (cat_id) REFERENCES Categories(id);


-- Create Foreign Key: Departments.manager_id -> Users.id
ALTER TABLE Departments ADD CONSTRAINT FK_Departments_manager_id_Users_id FOREIGN KEY (manager_id) REFERENCES Users(id);


-- Create Foreign Key: Categories.parent_id -> Categories.id
ALTER TABLE Categories ADD CONSTRAINT FK_Categories_parent_id_Categories_id FOREIGN KEY (parent_id) REFERENCES Categories(id);


-- Create Foreign Key: Users.role_id -> Roles.id
ALTER TABLE Users ADD CONSTRAINT FK_Users_role_id_Roles_id FOREIGN KEY (role_id) REFERENCES Roles(id);


-- Create Foreign Key: Gift_history.user_id -> Users.id
ALTER TABLE Gift_history ADD CONSTRAINT FK_Gift_history_user_id_Users_id FOREIGN KEY (user_id) REFERENCES Users(id);


-- Create Foreign Key: Logins.user_id -> Users.id
ALTER TABLE Logins ADD CONSTRAINT FK_Logins_user_id_Users_id FOREIGN KEY (user_id) REFERENCES Users(id);


