# Part 1
DROP SCHEMA IF EXISTS `JDBC`;    
CREATE SCHEMA `JDBC`;
USE `JDBC`;


# Part 2

DROP TABLE IF EXISTS person;
CREATE TABLE person(
`id` INT NOT NULL AUTO_INCREMENT,
`first_name`  VARCHAR(255) DEFAULT NULL,
`last_name` VARCHAR(255) default null,
`username` varchar(255) default null,
`password` varchar(255) default null,
`email` varchar(255) default null,
`dob` Date default null,
`address` VARCHAR(255) DEFAULT NULL,
`phone` VARCHAR(255) DEFAULT NULL,
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
`approved_user` BOOLEAN DEFAULT NULL,
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
`youtube_shareable` BOOLEAN DEFAULT NULL,
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


# trigger 

# part 1 website
# create website priviledge AFTER role created
DROP TRIGGER IF EXISTS `after_website_role_insert`;
delimiter $$
CREATE TRIGGER `after_website_role_insert`
	AFTER INSERT ON `website_role`
	FOR EACH ROW
BEGIN
	IF NEW.`role` = 'owner' OR NEW.`role` = 'admin' THEN
		INSERT INTO `website_priviledge`(`priviledge`, `website_id`, `developer_id`)
        VALUES
        ('create', NEW.`website_id`, NEW.`developer_id`),
        ('read', NEW.`website_id`, NEW.`developer_id`),
        ('update', NEW.`website_id`, NEW.`developer_id`),
        ('delete', NEW.`website_id`, NEW.`developer_id`);
	ELSEIF NEW.`role` = 'writer' THEN
		INSERT INTO `website_priviledge`(`priviledge`, `website_id`, `developer_id`)
        VALUES
		('create', NEW.`website_id`, NEW.`developer_id`),
        ('read', NEW.`website_id`, NEW.`developer_id`),
        ('update', NEW.`website_id`, NEW.`developer_id`);
	ELSEIF NEW.`role` = 'editor' THEN
		INSERT INTO `website_priviledge`(`priviledge`, `website_id`, `developer_id`)
        VALUES
        ('read', NEW.`website_id`, NEW.`developer_id`),
        ('update', NEW.`website_id`, NEW.`developer_id`);
	ELSEIF NEW.`role` = 'reviewer' THEN
		INSERT INTO `website_priviledge`(`priviledge`, `website_id`, `developer_id`)
        VALUES
        ('read', NEW.`website_id`, NEW.`developer_id`);
	END IF;
END$$

delimiter ;

# update website priviledge after role updated
DROP TRIGGER IF EXISTS `after_website_role_update`;

delimiter $$
CREATE TRIGGER `after_website_role_update`
	AFTER UPDATE ON `website_role`
    FOR EACH ROW
BEGIN
	DELETE FROM `website_priviledge`
		WHERE `developer_id`=NEW.`developer_id` AND `website_id`=NEW.`website_id`;
	IF NEW.`role` = 'owner' OR NEW.`role` = 'admin' THEN
		INSERT INTO `website_priviledge`(`priviledge`, `website_id`, `developer_id`)
        VALUES
        ('create', NEW.`website_id`, NEW.`developer_id`),
        ('read', NEW.`website_id`, NEW.`developer_id`),
        ('update', NEW.`website_id`, NEW.`developer_id`),
        ('delete', NEW.`website_id`, NEW.`developer_id`);
	ELSEIF NEW.`role` = 'writer' THEN
		INSERT INTO `website_priviledge`(`priviledge`, `website_id`, `developer_id`)
        VALUES
		('create', NEW.`website_id`, NEW.`developer_id`),
        ('read', NEW.`website_id`, NEW.`developer_id`),
        ('update', NEW.`website_id`, NEW.`developer_id`);
	ELSEIF NEW.`role` = 'editor' THEN
		INSERT INTO `website_priviledge`(`priviledge`, `website_id`, `developer_id`)
        VALUES
        ('read', NEW.`website_id`, NEW.`developer_id`),
        ('update', NEW.`website_id`, NEW.`developer_id`);
	ELSEIF NEW.`role` = 'reviewer' THEN
		INSERT INTO `website_priviledge`(`priviledge`, `website_id`, `developer_id`)
        VALUES
        ('read', NEW.`website_id`, NEW.`developer_id`);
	END IF;
END$$

delimiter ;

# delete website priviledge after delete role
DROP TRIGGER IF EXISTS `after_website_role_delete`;

delimiter $$
CREATE TRIGGER `after_website_role_delete`
	AFTER DELETE ON `website_role`
    FOR EACH ROW
BEGIN
	DELETE FROM `website_priviledge`
		WHERE `developer_id`=OLD.`developer_id` AND `website_id`=OLD.`website_id`;
END$$

delimiter ;


# part 2 page
# create website priviledge after roles created
DROP TRIGGER IF EXISTS `after_page_role_insert`;

delimiter $$
CREATE TRIGGER `after_page_role_insert`
	AFTER INSERT ON `page_role`
	FOR EACH ROW
BEGIN
	IF NEW.`role` = 'owner' OR NEW.`role` = 'admin' THEN
		INSERT INTO `page_priviledge`(`priviledge`, `page_id`, `developer_id`)
        VALUES
        ('create', NEW.`page_id`, NEW.`developer_id`),
        ('read', NEW.`page_id`, NEW.`developer_id`),
        ('update', NEW.`page_id`, NEW.`developer_id`),
        ('delete', NEW.`page_id`, NEW.`developer_id`);
	ELSEIF NEW.`role` = 'writer' THEN
		INSERT INTO `page_priviledge`(`priviledge`, `page_id`, `developer_id`)
        VALUES
		('create', NEW.`page_id`, NEW.`developer_id`),
        ('read', NEW.`page_id`, NEW.`developer_id`),
        ('update', NEW.`page_id`, NEW.`developer_id`);
	ELSEIF NEW.`role` = 'editor' THEN
		INSERT INTO `page_priviledge`(`priviledge`, `page_id`, `developer_id`)
        VALUES
        ('read', NEW.`page_id`, NEW.`developer_id`),
        ('update', NEW.`page_id`, NEW.`developer_id`);
	ELSEIF NEW.`role` = 'reviewer' THEN
		INSERT INTO `page_priviledge`(`priviledge`, `page_id`, `developer_id`)
        VALUES
        ('read', NEW.`page_id`, NEW.`developer_id`);
	END IF;
END $$
delimiter ;

# update page priviledge after role updated
DROP TRIGGER IF EXISTS `after_page_role_update`;

delimiter $$
CREATE TRIGGER `after_page_role_update`
	BEFORE UPDATE ON `page_role`
	FOR EACH ROW
BEGIN
	DELETE FROM `page_priviledge`
		WHERE `developer_id`=NEW.`developer_id` AND `page_id`= NEW.`page_id`; 
	IF NEW.`role` = 'owner' OR NEW.`role` = 'admin' THEN
		INSERT INTO `page_priviledge`(`priviledge`, `page_id`, `developer_id`)
        VALUES
        ('create', NEW.`page_id`, NEW.`developer_id`),
        ('read', NEW.`page_id`, NEW.`developer_id`),
        ('update', NEW.`page_id`, NEW.`developer_id`),
        ('delete', NEW.`page_id`, NEW.`developer_id`);
	ELSEIF NEW.`role` = 'writer' THEN
		INSERT INTO `page_priviledge`(`priviledge`, `page_id`, `developer_id`)
        VALUES
		('create', NEW.`page_id`, NEW.`developer_id`),
        ('read', NEW.`page_id`, NEW.`developer_id`),
        ('update', NEW.`page_id`, NEW.`developer_id`);
	ELSEIF NEW.`role` = 'editor' THEN
		INSERT INTO `page_priviledge`(`priviledge`, `page_id`, `developer_id`)
        VALUES
        ('read', NEW.`page_id`, NEW.`developer_id`),
        ('update', NEW.`page_id`, NEW.`developer_id`);
	ELSEIF NEW.`role` = 'reviewer' THEN
		INSERT INTO `page_priviledge`(`priviledge`, `page_id`, `developer_id`)
        VALUES
        ('read', NEW.`page_id`, NEW.`developer_id`);
	END IF;
END $$

delimiter ;

# delete page priviledge after role deleted
DROP TRIGGER IF EXISTS `after_page_role_delete`;

delimiter $$
CREATE TRIGGER `after_page_role_delete`
	AFTER DELETE ON `page_role`
    FOR EACH ROW
BEGIN
	DELETE FROM `page_priviledge`
		WHERE `developer_id`=OLD.`developer_id` AND `page_id`=OLD.`page_id`;
END$$

delimiter ;


# Create Answer, Question, User, Module Table


DROP TABLE IF EXISTS `module`; 
CREATE TABLE IF NOT EXISTS `module`(
module VARCHAR(255) NOT NULL DEFAULT '',
PRIMARY KEY(module)
);
INSERT INTO module(module) VALUES('Project1');
INSERT INTO module(module) VALUES('Project2');
INSERT INTO module(module) VALUES('Assignment1');
INSERT INTO module(module) VALUES('Assignment2');
INSERT INTO module(module) VALUES('Quiz1');
INSERT INTO module(module) VALUES('Exam');
INSERT INTO module(module) VALUES('Logistics');

DROP TABLE IF EXISTS `question`;
CREATE TABLE `question`(
`id` INT PRIMARY KEY REFERENCES `widget`(`id`),
`asked_by_user_id` INT DEFAULT NULL,
`text` VARCHAR(255) DEFAULT NULL,
`posted_on` DATE DEFAULT NULL,
`length` INT DEFAULT NULL,
`views` INT DEFAULT NULL,
`endorsed_by_instructor` BOOLEAN DEFAULT NULL,
`module` VARCHAR(20) DEFAULT '',
FOREIGN KEY(`asked_by_user_id`)
	REFERENCES `user`(`id`)
    ON DELETE CASCADE,
FOREIGN KEY(`module`)
	REFERENCES `module`(`module`)
    ON DELETE CASCADE
);

DROP TABLE IF EXISTS `answer`;
CREATE TABLE `answer`(
`id` INT PRIMARY KEY REFERENCES `widget`(`id`),
`question's_id` INT DEFAULT NULL,
`posted_by_user_id` INT DEFAULT NULL,
`text` VARCHAR(255) DEFAULT NULL,
`posted_on` DATE DEFAULT NULL,
`correct_answer` BOOLEAN DEFAULT NULL,
`up_votes` INT DEFAULT NULL,
`down_votes` INT DEFAULT NULL,
FOREIGN KEY(`question's_id`)
	REFERENCES `question`(`id`)
    ON DELETE CASCADE,
FOREIGN KEY(`posted_by_user_id`) 
	REFERENCES `user`(`id`)
    ON DELETE CASCADE
);
