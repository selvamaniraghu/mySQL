MySQL Fundamentals

	1. What is a Database?

		- A database is an organized collection of structured data stored electronically 
		that allows efficient storage, retrieval, modification, and management of data using a database management system.

	2. What is MySQL?

		- MySQL is an open-source Relational Database Management System (RDBMS) used to store, manage, and retrieve structured data using SQL (Structured Query Language).

	3. RDBMS vs DBMS

		- DBMS is a system used to store and manage data, It organizes data in files or simple structures and provides operations like insert, update, delete, and retrieve.
		while RDBMS is an advanced form of DBMS that stores data in related tables organized in rows and columns and maintains relationships using keys like primary key and foreign key


		# DBMS (Database Management System)

			Key Features

				- Data stored as files or simple tables
				- Supports single-user or limited multi-user
				- Less strict relationships between data
				- Used for smaller applications

		# RDBMS (Relational Database Management System)

			Key Features

				- Data stored in multiple related tables
				- Supports relationships between tables
				- Ensures data integrity and normalization
				- Supports multiple users and large systems
				- Uses SQL language

			DBMS (Single Table)

				| StudentID | Name  | Course | Instructor |
				| --------- | ----- | ------ | ---------- |
				| 1         | Ravi  | Java   | Kumar      |
				| 2         | Priya | Python | Raj        |

				// All data stored in one table, which may cause redundancy.

			RDBMS (Multiple Related Tables)

				| StudentID | Name  |
				| --------- | ----- |
				| 1         | Ravi  |
				| 2         | Priya |

				| CourseID | CourseName |
				| -------- | ---------- |
				| 101      | Java       |
				| 102      | Python     |

				| StudentID | CourseID |
				| --------- | -------- |
				| 1         | 101      |
				| 2         | 102      |

				// Tables are connected using keys, reducing duplication.

	4. Tables, Rows, Columns

		=> Table

			A table is a structure in a database used to store data in an organized format.

			It consists of rows and columns, similar to an Excel sheet.

			Each table represents a specific entity.

		=> Row (Record / Tuple)

			A row represents a single record in a table.

			Each row contains complete information about one entity.

		=> Column (Field / Attribute)

			A column represents a specific attribute or property of the data.

			Each column stores one type of information for all rows.

	5. Primary Key

		- A Primary Key is a column (or combination of columns) in a table that uniquely identifies each row (record) in that table.

		- It ensures that no two rows have the same value in the primary key column.

		=> Key Characteristics of a Primary Key

			1. Unique (Each value must be different)
			2. Not NULL (A primary key cannot contain NULL values)
			3. One Primary Key per Table (A table can have only one primary key, but it can contain multiple columns (composite key))

			=> Composite Primary Key

				A primary key made of multiple columns.

				Example:

					CREATE TABLE enrollment (
					 student_id INT,
					 course_id INT,
					 PRIMARY KEY(student_id, course_id)
					);

			=> Why Primary Key is Important

				- Identifies each record uniquely
				- Prevents duplicate records
				- Improves query performance
				- Used to create relationships between tables

	6. Foreign Key

		- A foreign key is a field in a table that refers to the primary key of another table to establish a relationship between them.

		- It is used to create a relationship between two tables and maintain data integrity.

		Relationship:

			Customers Table
			customer_id (Primary Key)
			        ↑
			        |
			Orders Table
			customer_id (Foreign Key)

		Example:

		 	CREATE TABLE customers (
			 customer_id INT PRIMARY KEY,
			 name VARCHAR(50)
			);

			CREATE TABLE orders (
			 order_id INT PRIMARY KEY,
			 customer_id INT,
			 product VARCHAR(50),
			 FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
			);

		=> Why Foreign Key is Important

			1️. Maintains relationship between tables
			2️. Ensures referential integrity
			3️. Prevents invalid data (You cannot insert an order with customer_id = 5 if that customer does not exist)

		=> Primary Key vs Foreign Key

		| Feature     | Primary Key                 | Foreign Key           |
		| ----------- | --------------------------- | --------------------- |
		| Purpose     | Uniquely identifies records | Links two tables      |
		| Duplicates  | Not allowed                 | Allowed               |
		| NULL values | Not allowed                 | Allowed (sometimes)   |
		| Table       | Exists in parent table      | Exists in child table |

	7. Unique Key

		- A Unique Key is a constraint used to ensure that all values in a column are unique.

		- It prevents duplicate values in the column but allows NULL values (depending on the database configuration).

		Example:

			CREATE TABLE users (
			 id INT PRIMARY KEY,
			 email VARCHAR(100) UNIQUE,
			 name VARCHAR(50)
			);

		=> Adding Unique Key to Existing Table

			ALTER TABLE users
			ADD UNIQUE (email);	

		=> Composite Unique Key

			A Unique Key can also include multiple columns.

			Example:

				CREATE TABLE employee (
				 id INT,
				 department_id INT,
				 email VARCHAR(100),
				 UNIQUE (department_id, email)
				);

		=> Primary Key vs Unique Key

			| Feature          | Primary Key          | Unique Key               |
			| ---------------- | -------------------- | ------------------------ |
			| Uniqueness       | Unique               | Unique                   |
			| NULL values      | Not allowed          | Allowed                  |
			| Number per table | Only one             | Multiple allowed         |
			| Purpose          | Identify each record | Prevent duplicate values |

	8. Null vs Not Null

		- NULL represents a missing or unknown value in a column, while NOT NULL is a constraint that ensures a column must always contain a value.

		=> NULL

			- NULL means that no value is stored in the column.

			- It represents missing, unknown, or undefined data.

			Important:

				NULL is not the same as 0
				NULL is not an empty string ('')

			Example:

				CREATE TABLE employees (
				 id INT,
				 name VARCHAR(50),
				 phone VARCHAR(15)
				);

				// Since NOT NULL is not specified, columns can contain NULL values.

		=> NOT NULL

			- NOT NULL is a constraint that ensures a column must always have a value.

			- You cannot insert NULL into this column.

			Example:

				CREATE TABLE employees (
				 id INT PRIMARY KEY,
				 name VARCHAR(50) NOT NULL,
				 email VARCHAR(100) NOT NULL
				);

		=> Difference Between NULL and NOT NULL

			| Feature      | NULL                     | NOT NULL            |
			| ------------ | ------------------------ | ------------------- |
			| Meaning      | No value / unknown value | Value is mandatory  |
			| Data allowed | Can store NULL           | Cannot store NULL   |
			| Constraint   | Default behavior         | Explicit constraint |
			| Example      | phone number optional    | name required       |

	9. Default values

		- A Default Value is a value that is automatically assigned to a column when no value is provided during insertion.

		- It helps ensure that a column always has a predefined value if the user does not specify one.

		Example:

			CREATE TABLE employees (
			 id INT PRIMARY KEY,
			 name VARCHAR(50),
			 department VARCHAR(50),
			 status VARCHAR(20) DEFAULT 'Active'
			);

		=> Benefits of Default Values

			- Prevents NULL values
			- Ensures consistent data
			- Reduces the need to enter repetitive values
			- Simplifies data insertion

	10. Basic Commands

		# CREATE DATABASE

			- CREATE DATABASE company_db;

			- USE company_db;

		# CREATE TABLE

			CREATE TABLE employees (
			    id INT PRIMARY KEY,
			    name VARCHAR(50),
			    department VARCHAR(50),
			    salary INT
			);

		# INSERT

			INSERT INTO employees VALUES
			(2, 'Priya', 'HR', 45000),
			(3, 'Arun', 'Finance', 60000);

		# DELETE

			DELETE FROM employees WHERE id = 2;

		# DROP (Used to delete the entire table or database permanently)

			DROP TABLE employees;

			DROP DATABASE company_db;

		# TRUNCATE (Used to remove all rows from a table quickly, but the table structure remains)

			TRUNCATE TABLE employees;


	11. SQL Query Basics

			=> Filtering Data

				WHERE
				AND / OR
				BETWEEN
				IN
				LIKE
				LIMIT

			=> Sorting Data

				ORDER BY
				ASC / DESC

			=> Aggregate Functions

					COUNT() - SELECT COUNT(*) FROM employees;
					SUM() - SELECT SUM(salary) FROM employees;
					AVG() - SELECT AVG(salary) FROM employees;
					MIN() - SELECT MIN(salary) FROM employees;
					MAX() - SELECT MAX(salary) FROM employees;

	12. GROUP BY & HAVING

		- Both GROUP BY and HAVING are used with aggregate functions like COUNT(), SUM(), AVG(), etc.

			# GROUP BY → groups rows with the same values.
			# HAVING → filters grouped results.

	13. JOINS

		- JOIN is used to combine rows from two or more tables based on a related column between them.

		Example Tables

			Employee table

			| emp_id | name  | department_id |
			| ------ | ----- | ------------- |
			| 1      | Ravi  | 101           |
			| 2      | Priya | 102           |
			| 3      | Arun  | 101           |
			| 4      | Kiran | 103           |

			Departments Table

			| department_id | department_name |
			| ------------- | --------------- |
			| 101           | IT              |
			| 102           | HR              |
			| 103           | Finance         |

		1. INNER JOIN

			INNER JOIN returns only the matching records from both tables.

			Example:

				SELECT employees.name, departments.department_name
				FROM employees
				INNER JOIN departments
				ON employees.department_id = departments.department_id;

			Result:

				| name  | department_name |
				| ----- | --------------- |
				| Ravi  | IT              |
				| Priya | HR              |
				| Arun  | IT              |
				| Kiran | Finance         |

		2. LEFT JOIN (LEFT OUTER JOIN)

			- LEFT JOIN returns all rows from the left table and matching rows from the right table.

			- If there is no match, NULL is returned.

			Example:

				SELECT employees.name, departments.department_name
				FROM employees
				LEFT JOIN departments
				ON employees.department_id = departments.department_id;

				// Result includes all employees even if department doesn't exist.

		3. RIGHT JOIN (RIGHT OUTER JOIN)

			- RIGHT JOIN returns all rows from the right table and matching rows from the left table.

			Example

				SELECT employees.name, departments.department_name
				FROM employees
				RIGHT JOIN departments
				ON employees.department_id = departments.department_id;

				// Result includes all departments, even if no employees exist.

		4. FULL JOIN (Concept)

			- MySQL does not support FULL JOIN directly.

			- FULL JOIN returns all records from both tables.

			- It can be simulated using UNION.

			Example:

				SELECT employees.name, departments.department_name
				FROM employees
				LEFT JOIN departments
				ON employees.department_id = departments.department_id

				UNION

				SELECT employees.name, departments.department_name
				FROM employees
				RIGHT JOIN departments
				ON employees.department_id = departments.department_id;

			1. How do you find records that exist in one table but not another?

				SELECT employees.name
				FROM employees
				LEFT JOIN departments
				ON employees.department_id = departments.department_id
				WHERE departments.department_id IS NULL;

				// This finds employees without departments.

	14. Subqueries

		- A subquery is a query written inside another SQL query. The inner query executes first and its result is used by the outer query.

		1. Single Row Subquery (Returns one value)

			SELECT name, salary
			FROM employees
			WHERE salary > (
			    SELECT AVG(salary)
			    FROM employees
			);

		2. Multiple Row Subquery (Returns multiple values)

			SELECT name
			FROM employees
			WHERE department IN (
			    SELECT dept_id
			    FROM departments
			    WHERE location = 'Delhi'
			);

		3. Subquery with EXISTS (Checks if records exist)

			SELECT name
			FROM employees e
			WHERE EXISTS (
			    SELECT 1
			    FROM departments d
			    WHERE e.department = d.dept_id
			);

		4. Correlated Subquery

			- A subquery that depends on the outer query. It runs once for each row of the outer query.

			SELECT name, salary, department
			FROM employees e
			WHERE salary > (
			    SELECT AVG(salary)
			    FROM employees
			    WHERE department = e.department
			);

		=> Advantages of Subqueries

			- Simplifies complex queries
			- Improves readability
			- Helps compare values from another query
			- Useful with IN, EXISTS, ANY, ALL

		Very Common Interview Example

			- Find the second highest salary

				SELECT MAX(salary)
				FROM employees
				WHERE salary < (
				    SELECT MAX(salary)
				    FROM employees
				);

	15. Indexing

		- An index is a database structure that improves the speed of data retrieval by allowing MySQL to locate rows quickly without scanning the entire table.

		=> Without an index:

			- MySQL scans every row in the table
			- This is called Full Table Scan
			- Query becomes slow

		=> Example With Index

			CREATE INDEX idx_employee_name ON employees(name);

		=> Types of Indexes in MySQL

			1. Primary Index

				- Automatically created when a Primary Key is defined.

					CREATE TABLE employees (
					 id INT PRIMARY KEY,
					 name VARCHAR(50)
					);

			2. Unique Index

				- Ensures all values are unique.

					CREATE UNIQUE INDEX idx_email ON users(email);

			3. Single Column Index

				- Index created on one column.

					CREATE INDEX idx_name ON employees(name);

			4. Composite Index - Very Important

				- Index created on multiple columns.

					CREATE INDEX idx_name_dept ON employees(name, department);

		=> When to Use Index

			- Columns used in WHERE clause
			- Columns used in JOIN
			- Columns used in ORDER BY
			- Columns used in GROUP BY

		=> When NOT to Use Index

			- Table is very small
			- Column has many duplicate values
			- Column is rarely used in queries

		=> Disadvantage of Index

			- Takes extra storage
			- Slows down INSERT, UPDATE, DELETE because index must be updated.

		=> Check Query Performance

			Use EXPLAIN:

				EXPLAIN SELECT * FROM employees WHERE name = 'Ravi';

					- It shows whether MySQL uses an index or scans the table.

		=> Very Common Interview Question

			- Difference between Clustered Index and Non-Clustered Index

				| Clustered Index                 | Non-Clustered Index    |
				| ------------------------------- | ---------------------- |
				| Data stored physically in order | Separate structure     |
				| Only one per table              | Multiple allowed       |
				| Usually Primary Key             | Used for other columns |

	16. Constraints

		- Constraints are rules applied to table columns to restrict the type of data that can be inserted into a table.

		=> Types of Constraints

			1. PRIMARY KEY
			2. FOREIGN KEY
			3. UNIQUE
			4. NOT NULL
			5. DEFAULT
			6. CHECK

				- Ensures that values in a column meet a specific condition.

					CREATE TABLE employees (
					 id INT PRIMARY KEY,
					 salary INT CHECK (salary > 0)
					);

					// This prevents negative salary values.

		=> Example with Multiple Constraints

			CREATE TABLE employees (
			 id INT PRIMARY KEY,
			 name VARCHAR(50) NOT NULL,
			 email VARCHAR(100) UNIQUE,
			 salary INT CHECK (salary > 0),
			 status VARCHAR(20) DEFAULT 'Active'
			);

		=> Why Constraints Are Important

			- Maintain data integrity
			- Prevent invalid data
			- Ensure consistency in the database
			- Define relationships between tables