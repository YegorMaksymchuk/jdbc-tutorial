CREATE SCHEMA jdbc_tutorial DEFAULT CHARACTER SET utf8mb4;

USE jdbc_tutorial;
CREATE TABLE developers(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	first_name VARCHAR(50) NOT NULL,
	last_name VARCHAR(50) NOT NULL,
	specialty VARCHAR(50) NOT NULL,
	salary DECIMAL(10, 2) NOT NULL
);

INSERT INTO developers(id, first_name, last_name, specialty, salary)
VALUES 	
	(1, 'John', 'Smith',   'C++', 3000.00),
	(2, 'Sarah', 'Jones',  'C#', 3000.00),
	(3, 'Daniel', 'Brown', 'Java', 3000.00),
	(4, 'Olivia', 'Evans', 'Java', 3000.00),
	(5, 'Daniel', 'Smith', 'Java', 3000.00);
  

ALTER TABLE developers ADD COLUMN photo BLOB AFTER slary;



CREATE TABLE projects(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	info VARCHAR(100)
);


DROP TABLE developers;