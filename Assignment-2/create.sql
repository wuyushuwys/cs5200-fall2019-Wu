# Part 1
DROP SCHEMA IF EXISTS `RM`;
CREATE SCHEMA `RM`;
USE `RM`;


# Part 2

DROP TABLE IF EXISTS person;
CREATE TABLE person(
`id` INT NOT NULL AUTO_INCREMENT,
`first_name`  VARCHAR(255) DEFAULT NULL,
`last_name` VARCHAR(255) default null,
`username` varchar(255) default null,
`password` varchar(255) default null,
`email` varchar(255) default null,
PRIMARY KEY(id)
);

DROP TABLE IF EXISTS developer;
CREATE TABLE developer(
`id` INT PRIMARY KEY REFERENCES person(id) 
	ON DELETE CASCADE,
`developer_key` VARCHAR(255) DEFAULT NULL,
CONSTRAINT developer_person_generalization
	FOREIGN KEY(id) REFERENCES person(id)
    ON DELETE CASCADE
);


DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`(
`id` INT PRIMARY KEY REFERENCES person(id) 
	ON DELETE CASCADE,
`user_agreement` BOOLEAN DEFAULT NULL,
CONSTRAINT user_person_generalization
	FOREIGN KEY(id) REFERENCES person(id)
    ON DELETE CASCADE
);


# Part 3
DROP TABLE IF EXISTS website;
CREATE TABLE website(
`id` INT NOT NULL AUTO_INCREMENT,
`developer_id` INT DEFAULT NULL,
`name` VARCHAR(255) NOT NULL,
`description` VARCHAR(255) DEFAULT NULL,
`created` DATE DEFAULT NULL,
`updated` DATE DEFAULT NULL,
`visits` INT DEFAULT NULL,
PRIMARY KEY(id),
FOREIGN KEY(`developer_id`) 
	REFERENCES developer(id)
	ON DELETE CASCADE
);


DROP TABLE IF EXISTS `page`;
CREATE TABLE `page`(
`id` INT NOT NULL AUTO_INCREMENT,
`website_id` INT DEFAULT NULL,
`title` VARCHAR(255) NOT NULL,
`description` VARCHAR(255) DEFAULT NULL,
`created` DATE DEFAULT NULL,
`updated` DATE DEFAULT NULL,
`views` INT DEFAULT NULL,
PRIMARY KEY(id),
FOREIGN KEY(`website_id`) 
	REFERENCES website(id)
    ON DELETE CASCADE
);

##  protable enumeration
DROP TABLE IF EXISTS `dtype`;
CREATE TABLE `dtype`(
dtype VARCHAR(255) NOT NULL DEFAULT '',
PRIMARY KEY(dtype)
);
INSERT INTO dtype(dtype) VALUES('HEADING');
INSERT INTO dtype(dtype) VALUES('IMAGE');
INSERT INTO dtype(dtype) VALUES('YOUTUBE');
INSERT INTO dtype(dtype) VALUES('HTML');



DROP TABLE IF EXISTS `widget`;
CREATE TABLE `widget`(
`dtype` VARCHAR(255) DEFAULT '',
`id` INT NOT NULL AUTO_INCREMENT,
`page_id` INT DEFAULT NULL,
`name` VARCHAR(255) DEFAULT NULL,
`width` INT DEFAULT NULL,
`height` INT DEFAULT NULL,
`css_class` VARCHAR(255) DEFAULT NULL,
`css_style` VARCHAR(255) DEFAULT NULL,
`text` VARCHAR(255) DEFAULT NULL,
`order` INT DEFAULT NULL,
`youtube_url` VARCHAR(255) DEFAULT NULL,
`youtube_shareble` BOOLEAN DEFAULT NULL,
`youtube_expandable` BOOLEAN DEFAULT NULL,
`image_src` VARCHAR(255) DEFAULT NULL,
`heading_size` INT DEFAULT 2,
`html` VARCHAR(255) DEFAULT NULL,
PRIMARY KEY(id),
FOREIGN KEY(dtype) 
	REFERENCES dtype(dtype)
    ON DELETE CASCADE,
FOREIGN KEY(page_id) 
	REFERENCES `page`(id)
	ON DELETE CASCADE
);


#

DROP TABLE IF EXISTS `address`;
CREATE TABLE `address`(
`id` INT NOT NULL AUTO_INCREMENT,
`person_id` INT NOT NULL,
`street1` VARCHAR(255) NOT NULL,
`street2` VARCHAR(255) DEFAULT NULL,
`city` VARCHAR(255) DEFAULT NULL,
`state` VARCHAR(255) DEFAULT NULL,
`zip` INT DEFAULT NULL,
`primary` BOOL DEFAULT NULL,
PRIMARY KEY(id),
FOREIGN KEY(`person_id`) 
	REFERENCES person(id)
    ON DELETE CASCADE
);

DROP TABLE IF EXISTS `phone`;
CREATE TABLE `phone`(
`id` INT NOT NULL AUTO_INCREMENT,
`person_id` INT NOT NULL,
`phone` VARCHAR(255) NOT NULL,
`primary` BOOL DEFAULT NULL,
PRIMARY KEY(id),
FOREIGN KEY(`person_id`) 
	REFERENCES person(id)
    ON DELETE CASCADE
);



# Part 4
### role & priviledge
##  protable enumeration
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`(
role VARCHAR(255) NOT NULL DEFAULT '',
PRIMARY KEY(role)
);
INSERT INTO role(role) VALUES('owner');
INSERT INTO role(role) VALUES('admin');
INSERT INTO role(role) VALUES('writer');
INSERT INTO role(role) VALUES('editor');
INSERT INTO role(role) VALUES('reviewer');

DROP TABLE IF EXISTS `page_role`;
CREATE TABLE `page_role`(
`id` INT NOT NULL AUTO_INCREMENT,
`developer_id` INT NOT NULL,
`page_id` INT NOT NULL,
`role` VARCHAR(255) NOT NULL,
PRIMARY KEY(id),
FOREIGN KEY(`developer_id`) 
	REFERENCES developer(id)
    ON DELETE CASCADE,
FOREIGN KEY(`page_id`) 
	REFERENCES `page`(id)
    ON DELETE CASCADE,
FOREIGN KEY(`role`) 
	REFERENCES `role`(role)
    ON DELETE CASCADE
);


DROP TABLE IF EXISTS `website_role`;
CREATE TABLE `website_role`(
`id` INT NOT NULL AUTO_INCREMENT,
`developer_id` INT NOT NULL,
`website_id` INT NOT NULL,
`role` VARCHAR(255) NOT NULL,
PRIMARY KEY(id),
FOREIGN KEY(`developer_id`) 
	REFERENCES developer(id)
    ON DELETE CASCADE,
FOREIGN KEY(`website_id`) 
	REFERENCES `website`(id)
    ON DELETE CASCADE,
FOREIGN KEY(`role`) 
	REFERENCES `role`(role)
    ON DELETE CASCADE
);



##  protable enumeration
DROP TABLE IF EXISTS `priviledge`;
CREATE TABLE `priviledge`(
priviledge VARCHAR(255) NOT NULL DEFAULT '',
PRIMARY KEY(priviledge)
);
INSERT INTO priviledge(priviledge) VALUES('create');
INSERT INTO priviledge(priviledge) VALUES('read');
INSERT INTO priviledge(priviledge) VALUES('update');
INSERT INTO priviledge(priviledge) VALUES('delete');


DROP TABLE IF EXISTS `page_priviledge`;
CREATE TABLE `page_priviledge`(
`id` INT NOT NULL AUTO_INCREMENT,
`developer_id` INT NOT NULL,
`page_id` INT NOT NULL,
`priviledge` VARCHAR(255) NOT NULL,
PRIMARY KEY(id),
FOREIGN KEY(`developer_id`) 
	REFERENCES developer(id)
    ON DELETE CASCADE,
FOREIGN KEY(`page_id`) 
	REFERENCES `page`(id)
    ON DELETE CASCADE,
FOREIGN KEY(`priviledge`) 
	REFERENCES `priviledge`(priviledge)
    ON DELETE CASCADE
);


DROP TABLE IF EXISTS `website_priviledge`;
CREATE TABLE `website_priviledge`(
`id` INT NOT NULL AUTO_INCREMENT,
`developer_id` INT NOT NULL,
`website_id` INT NOT NULL,
`priviledge` VARCHAR(255) NOT NULL,
PRIMARY KEY(id),
FOREIGN KEY(`developer_id`) 
	REFERENCES developer(id)
    ON DELETE CASCADE,
FOREIGN KEY(`website_id`) 
	REFERENCES `website`(id)
    ON DELETE CASCADE,
FOREIGN KEY(`priviledge`) 
	REFERENCES `priviledge`(priviledge)
    ON DELETE CASCADE
);