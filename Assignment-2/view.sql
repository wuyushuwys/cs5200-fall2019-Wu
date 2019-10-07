USE `RM`;

# Join person info with developer info
DROP VIEW IF EXISTS	`developer_roles_and_priviledge`;
CREATE VIEW `developer_roles_and_priviledge`
	(`Developer's first name`,
    `Developer's last name`,
    `Developer's username`,
    `Developer's email`,
	`Website's name`,
    `Website's visits`,
    `Website's last updated date`,
    `Developer's role in that website`,
    `Developer's priviledge in that website`,
    `Page's title`, 
    `Page's views`,
    `Page's last updated date`,
    `Developer's role in that page`,
    `Developer's privileges in that page`)
	AS SELECT
		p.`first_name`,
        p.`last_name`, 
        p.`username`,
        p.`email`,
        w.`name`,
        w.`visits`,
        w.`updated`,
        wr.`role`,
        wp.`priviledge`,
        pg.`title`,
        pg.`views`,
        pg.`updated`,
        pgr.`role`,
        pgp.`priviledge`
	FROM
		`person` p ,
        `developer` d,
        `website` w,
        `website_role` wr,
        `website_priviledge` wp,
        `page` pg,
        `page_role` pgr,
        `page_priviledge` pgp
	WHERE
		p.`id` = d.`id` 
        AND w.`id`=wr.`website_id` 
        AND w.`id`=wp.`website_id` 
        AND wr.`developer_id`=d.`id` 
        AND wp.`developer_id`=d.`id` 
        AND w.`id`=pg.`website_id` 
        AND pgr.`developer_id` = d.`id` 
        AND pgr.`page_id` = pg.`id` 
        AND pgp.`developer_id` = d.`id` 
        AND pgp.`page_id` = pg.`id`;

SELECT * FROM developer_roles_and_priviledge;
