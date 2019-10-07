SET SQL_SAFE_UPDATES=0;
USE `RM`;
# Delete developer - Delete Alice's primary address
DELETE `address`.* FROM `address`, `developer`, `person`
	WHERE `person`.`id` = `developer`.`id`
		AND `person`.`username` = 'Alice'
        AND `address`.`person_id` = `person`.`id`
        AND `address`.`primary` = TRUE;
        
# Delete widget - Remove the last widget in the Contact page. 
# The last widget is the one with the highest value in the order field
DELETE `widget`.* FROM `widget`, `page`, (SELECT MAX(`widget`.`order`) AS `max_order` FROM `widget`) x
	WHERE `widget`.`order` = x.`max_order`
		AND `page`.`title` = 'Contact'
        AND `widget`.`page_id` = `page`.`id`;
        
# Delete page - Remove the last updated page in Wikipedia
DELETE `page`.* FROM `page`, `website`, (SELECT MAX(`page`.`updated`) AS `last_update` FROM `page`) x
	WHERE `page`.`updated` = x.`last_update`
		AND `website`.`name` = 'Wikipedia'
        AND `page`.`website_id` = `website`.`id`;
        
# Delete website
# Remove the CNET website, as well as all related roles and privileges relating developers to the Website and Pages
DELETE FROM `website` WHERE `name` = 'CNET';






SET SQL_SAFE_UPDATES=0;