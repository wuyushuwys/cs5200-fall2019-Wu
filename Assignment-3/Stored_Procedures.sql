USE `JDBC`;

# Create Procedures

DROP PROCEDURE IF EXISTS getUnamsweredQuestions;

DELIMITER //

CREATE PROCEDURE getUnamsweredQuestions()
BEGIN

	SELECT q.`id`, q.`text` as `question text`, MAX(COUNT(a.`id`)) 
    FROM `question` q, `answer` a 
    WHERE a.`question's_id`=q.`id` AND a.`corrrect_answer` = FALSE 
    GROUP BY q.`module`;
    
    
END //

DELIMITER ;

DROP PROCEDURE IF EXISTS endorsedUsersForWeek;
DELIMITER //

CREATE PROCEDURE endorsedUsersForWeek(IN week_begin DATE, IN week_end DATE)
BEGIN

	SELECT p.`first_name` as `First Name`, p.`last_name` as `Last Name`, COUNT(a.`correct_answer`)
    FROM `person` p, `answer` a, `user` u
    WHERE a.`posred_on` > weed_begin AND a.`posted_on` < weed_end
		AND p.`id` = u.`id` AND p.`id` = a.`asked_by_user_id`
        AND a.`correct_answer` = TRUE
	GROUP BY(a.`asked_by_user_id`)
    LIMIT 5;
    
    
END //

DELIMITER ;