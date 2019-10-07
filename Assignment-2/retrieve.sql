USE `RM`;
# 1. Retrieve developers

# a) all developers
SELECT * 
	FROM `person` , `developer` 
    WHERE `person`.`id` = `developer`.`id`;
    
# b) a developer with id=34
SELECT * 
	FROM `person`, `developer` 
    WHERE `person`.`id`=`developer`.`id` 
		AND `developer`.`id`=34;
        
# c) all developers who have role in twitter other than owner
SELECT * 
	FROM `person`, `developer`, `website`, `website_role` 
    WHERE `person`.`id` = `developer`.`id` 
		AND `website`.`name`='Twitter' 
		AND `website_role`.`website_id`=`website`.`id` 
        AND `website_role`.`developer_id` = `developer`.`id`
        AND `website_role`.`role` <> 'owner';
        
# d) all developers who are page reviewers of pages with less than 300000 visits 
SELECT * 
	FROM `person`, `developer`, `page`, `page_role`
    WHERE `person`.`id` = `developer`.`id` 
		AND `page`.`views` < 300000 
        AND `page_role`.`role` = 'reviewer'
        AND `page_role`.`developer_id` = `developer`.`id`
        AND `page_role`.`page_id` = `page`.`id`;

# e) the writer developer who added a heading widget to CNET's home page 
SELECT * 
	FROM `person`, `developer`, `website`, `page`, `page_role`
    WHERE `person`.`id` = `developer`.`id` 
		AND `website`.`id` = `page`.`website_id`
        AND `website`.`name` = 'CNET'
		AND `page`.`title`= 'Home'
        AND `page_role`.`role` = 'writer'
        AND `page_role`.`developer_id` = `developer`.`id`
        AND `page_role`.`page_id` = `page`.`id`;


# 2. Retrieve writer
# a) the website with the least number of visits
SELECT *
	FROM `website`
    WHERE `website`.`visits`= (SELECT MIN(`website`.`visits`) FROM `website`);

# b) Retrieve the name of a website whose id is 678 
SELECT `name`
	FROM `website`
    WHERE `website`.`id`=678;
    
# c) Retrieve all websites with videos reviewed by bob 
SELECT *
	FROM `person`, `website`, `page`, `page_role`, `widget`
    WHERE `person`.`username`='bob'
		AND `person`.`id` = `page_role`.`developer_id`
        AND `page`.`id` = `page_role`.`page_id`
        AND `page`.`website_id` = `website`.`id`
        AND `widget`.`dtype` = 'YOUTUBE'
        AND `widget`.`page_id` = `page`.`id`;
    
# d) Retrieve all websites where alice is an owner 
SELECT * 
	FROM `person`, `developer`, `website`, `website_role`
    WHERE `person`.`username`='alice'
		AND `person`.`id` = `developer`.`id`
        AND `developer`.`id` = `website_role`.`developer_id`
        AND `website`.`id` = `website_role`.`website_id`
        AND `website_role`.`role` = 'owner';

# e) Retrieve all websites where charlie is an admin and get more than 6000000 visits
SELECT * 
	FROM `person`, `developer`, `website`, `website_role`
    WHERE `person`.`username`='charlie'
		AND `person`.`id` = `developer`.`id`
        AND `developer`.`id` = `website_role`.`developer_id`
        AND `website`.`visits` > 6000000
        AND `website`.`id` = `website_role`.`website_id`
        AND `website_role`.`role` = 'admin';


# 3. Retrieve pages
# a) Retrieve the page with the most number of views
SELECT *
	FROM `page`
    WHERE `page`.`views` = (SELECT MAX(`page`.`views`) FROM `page`);

# b) Retrieve the title of a page whose id is 234
SELECT `title`
	FROM `page`
    WHERE `page`.`id` = 234;

# c) Retrieve all pages where alice is an editor 
SELECT *
	FROM `page`, `developer`, `person`, `page_role`
    WHERE `person`.`id` = `developer`.`id`
		AND `person`.`username` = 'alice'
        AND `page_role`.`page_id` = `page`.`id`
        AND `page_role`.`developer_id` = `developer`.`id`
        AND `page_role`.`role` = 'editor';

# d) Retrieve the total number of pageviews in CNET
SELECT `website`.`name`, SUM(`page`.`views`) AS `pageviews`
    FROM `page`, `website`
    WHERE `page`.`website_id` = `website`.`id`
		AND `website`.`name` = 'CNET';

# e) Retrieve the average number of page views in the Website Wikipedia
SELECT `website`.`name`, AVG(`page`.`views`) AS `average_page_views`
	FROM `website`, `page`
	WHERE `website`.`id` = `page`.`website_id`
		AND `website`.`name` = 'Wikipedia';


# 4. Retrieve widgets

# a) Retrieve all widgets in CNET's Home page
SELECT `widget`.*
	FROM `widget`, `website`, `page`
    WHERE `website`.`name` = 'CNET'
		AND `page`.`title` = 'Home'
        AND `widget`.`page_id` = `page`.`id`
        AND `page`.`website_id` = `website`.`id`;

# b) Retrieve all youtube widgets in CNN
SELECT `widget`.*
	FROM `widget`, `website`, `page`
    WHERE `website`.`name` = 'CNN'
		AND `widget`.`dtype` = 'YOUTUBE'
        AND `widget`.`page_id` = `page`.`id`
        AND `page`.`website_id` = `website`.`id`;
        
# c) Retrieve all image widgets on pages reviewed by Alice
SELECT `widget`.*
	FROM `widget`, `developer`, `page`, `person`, `page_role`
    WHERE `person`.`first_name` = 'Alice'
		AND `person`.`id` = `developer`.`id`
		AND `widget`.`dtype` = 'IMAGE'
        AND `widget`.`page_id` = `page`.`id`
        AND `page`.`id` = `page_role`.`page_id`
        AND `developer`.`id` = `page_role`.`developer_id`;
        
# d) Retrieve how many widgets are in Wikipedia
SELECT COUNT(*) AS `Widgets' number in Wikipedia`
	FROM `website`, `page`, `widget`
    WHERE `website`.`name` = 'Wikipedia'
		AND `website`.`id` = `page`.`website_id`
		AND `widget`.`page_id` = `page`.`id`;


# 5. To verify the page and website triggers written earlier function properly
# a) Retrieve the names of all the websites where Bob has DELETE privileges
SELECT `website`.`name`
	FROM `person`, `developer`, `website`, `website_priviledge`
    WHERE `person`.`id` = `developer`.`id`
		AND `person`.`first_name` = 'Bob'
        AND `website_priviledge`.`developer_id` = `developer`.`id`
        AND `website_priviledge`.`website_id` = `website`.`id`
        AND `website_priviledge`.`priviledge` = 'delete';

# b) Retrieve the names of all the pages where Charlie has CREATE privileges
SELECT `page`.`title`
	FROM `person`, `developer`, `page`, `page_priviledge`
    WHERE `person`.`id` = `developer`.`id`
		AND `person`.`first_name` = 'Charles'
        AND `page_priviledge`.`developer_id` = `developer`.`id`
        AND `page_priviledge`.`page_id` = `page`.`id`
        AND `page_priviledge`.`priviledge` = 'create';







