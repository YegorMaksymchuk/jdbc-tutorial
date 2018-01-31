CREATE SCHEMA jdbc_tutorial DEFAULT CHARACTER SET utf8mb4;


CREATE TABLE jdbc_tutorial.developers(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	first_name VARCHAR(50) NOT NULL,
	last_name VARCHAR(50) NOT NULL,
	specialty VARCHAR(50) NOT NULL,
	salary DECIMAL(10, 2) NOT NULL
);
INSERT INTO jdbc_tutorial.developers(id, first_name, last_name, specialty, salary)
VALUES 	
	(1, 'John', 'Smith',   'C++', 3000.00),
	(2, 'Sarah', 'Jones',  'C#', 3000.00),
	(3, 'Daniel', 'Brown', 'Java', 3000.00),
	(4, 'Olivia', 'Evans', 'Java', 3000.00),
	(5, 'Daniel', 'Smith', 'Java', 3000.00);
  
SELECT * FROM jdbc_tutorial.developers;


CREATE TABLE jdbc_tutorial.projects(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	info VARCHAR(100)
);
INSERT INTO jdbc_tutorial.projects (id, name, info) values (1, 'JBoss', 'RedHat implamintation of WildFly'),(2,'Openshift', 'RedHat cloud devops platform');

SELECT * FROM jdbc_tutorial.projects;

CREATE TABLE jdbc_tutorial.developer_projects(
	developer_id INT NOT NULL,
	project_id INT NOT NULL,
	PRIMARY KEY(developer_id, project_id),
	FOREIGN KEY(developer_id) REFERENCES developers (id),
	FOREIGN KEY(project_id) REFERENCES projects (id)
);
INSERT INTO jdbc_tutorial.developer_projects (developer_id, project_id) values (1,1),(2,1),(3,1),(4,2);

SELECT * FROM jdbc_tutorial.developer_projects;


CREATE TABLE jdbc_tutorial.developer_data(
	developer_id INT NOT NULL,
	photo BLOB (1000000),
	resume BLOB (1000000),
	PRIMARY KEY(developer_id),
	FOREIGN KEY(developer_id) REFERENCES developers (id)
);

SELECT * FROM jdbc_tutorial.developer_data;

DROP SCHEMA `jdbc_tutorial`;
