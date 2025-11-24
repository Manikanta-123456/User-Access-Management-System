# User Access Management System (UAMS)

**Java Servlets • JSP • PostgreSQL • Maven • Tomcat**

A simple role-based User Access Management System where Employees can request access to software, Managers approve/reject requests, and Admins create software entries.

## Features
- User Registration (default role: Employee)
- Login with role-based redirection
- Admin: create software entries with access levels
- Employee: request access to software (Read/Write/Admin)
- Manager: view pending requests and approve/reject
- Passwords hashed with BCrypt

## Tech Stack
- Java 17+
- Java Servlets + JSP
- PostgreSQL
- Apache Tomcat
- Maven
- BCrypt for password hashing

## Project Structure
(see repository file tree)

## SQL
Database schema is in `sql/database_schema.sql`. Run it to create tables.

## How to run (short)
1. Configure PostgreSQL and create a DB (see detailed steps in SETUP section).
2. Edit DB credentials in `DBUtil.java`.
3. `mvn clean package`
4. Deploy generated WAR to Tomcat (`target/*.war`) or run from IDE.
5. Open `http://localhost:8080/UAMS/` (or the webapp context you set).

---

## SETUP (Detailed)
Follow the full SETUP and run guide in the repository README (below main README) — includes creating DB, running SQL, creating initial users, and running on Tomcat.

## License
MIT
