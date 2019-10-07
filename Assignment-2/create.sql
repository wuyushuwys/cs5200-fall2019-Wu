USE `heroku_ef8a2aff76398f9`;

DROP TABLE IF EXISTS sample;
CREATE TABLE sample(
id INT NOT NULL AUTO_INCREMENT,
message VARCHAR(45) NULL,
PRIMARY KEY (id));

SET SQL_SAFE_UPDATES=0;
DELETE FROM sample;
SET SQL_SAFE_UPDATES=1;

INSERT INTO sample(id, message)
	VALUES(1, 'Hello Yushu Wu');
    
SELECT * FROM sample;

INSERT INTO sample(id, message)
	VALUES
		(2, 'SQL is greate'),
		(3, 'Jave is awesome');
        
SELECT * FROM sample;
