USE `RM`;

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
	BEFORE DELETE ON `website_role`
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
	BEFORE DELETE ON `page_role`
    FOR EACH ROW
BEGIN
	DELETE FROM `page_priviledge`
		WHERE `developer_id`=OLD.`developer_id` AND `page_id`=OLD.`page_id`;
END$$

delimiter ;