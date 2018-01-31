CREATE SCHEMA jdbc_tutorial DEFAULT CHARACTER SET utf8mb4;
COMMIT;
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
  
SELECT * FROM developers;

USE jdbc_tutorial;
CREATE TABLE projects(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	info VARCHAR(100)
);

INSERT INTO projects (id, name, info) values (1, 'JBoss', 'RedHat implamintation of WildFly'),(2,'Openshift', 'RedHat cloud devops platform');
SELECT * FROM projects;

USE jdbc_tutorial;
CREATE TABLE developer_projects(
	developer_id INT NOT NULL,
	project_id INT NOT NULL,
	PRIMARY KEY(developer_id, project_id),
	FOREIGN KEY(developer_id) REFERENCES developers (id),
	FOREIGN KEY(project_id) REFERENCES projects (id)
);

INSERT INTO developer_projects (developer_id, project_id) values (1,1),(2,1),(3,1),(4,2);
SELECT * FROM developer_projects;

USE jdbc_tutorial;
CREATE TABLE developer_avatar(
	developer_id INT NOT NULL,
	avatar BLOB (1000000),
	cv TEXT (1000000),
	PRIMARY KEY(developer_id),
	FOREIGN KEY(developer_id) REFERENCES developers (id)
);

SELECT * FROM developer_avatar;

DROP SCHEMA `jdbc_tutorial`;

COMMIT;