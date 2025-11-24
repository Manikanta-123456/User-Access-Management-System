# 🧩 User Access Management System  
### Java Servlets • JSP • PostgreSQL • Maven • Tomcat

![Java](https://img.shields.io/badge/Java-17-orange?logo=java)
![JSP](https://img.shields.io/badge/JSP-JavaServer%20Pages-blue)
![Servlets](https://img.shields.io/badge/Servlets-Java%20EE-red)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-316192?logo=postgresql)
![Tomcat](https://img.shields.io/badge/Tomcat-Server-yellow?logo=apache-tomcat)
![Maven](https://img.shields.io/badge/Maven-Build-6f42c1?logo=apache-maven)

---

## 📌 Overview  
The **User Access Management System (UAMS)** is a web-based Java application used to manage access to software applications inside an organization.  
It includes **Sign-Up**, **Login**, **Software Management**, **Access Requests**, and an **Approval Workflow** with strict **role-based access control**.

---

## 🚀 Features

### 🔐 Authentication & Authorization
- User Sign-Up (Employee)
- Login with Session Management
- Role-based dashboard routing

### 👥 User Roles  
| Role | Features |
|------|----------|
| **Employee** | Signup, Login, Request Access |
| **Manager** | Login, View Pending Requests, Approve/Reject |
| **Admin** | Create Software, Full Access, All Manager + Employee features |

---

## 🧠 System Modules

### 1️⃣ **User Registration (SignUpServlet)**
- Default role = Employee  
- Stores user in database  
- Redirects to login page  

### 2️⃣ **User Login (LoginServlet)**
- Validates credentials  
- Creates session  
- Redirects based on role  

### 3️⃣ **Software Management (Admin Only)**
Admins can:
- Add new software  
- Select access levels (Read / Write / Admin)  

### 4️⃣ **Access Request System (Employee)**
Employees can:
- Choose software  
- Choose access level  
- Provide reason  

### 5️⃣ **Approval System (Manager)**
Managers:
- View all pending requests  
- Approve or reject  
- Status gets updated  

---

## 🗄️ Database Schema (PostgreSQL)

### ✔ `users` table
| Column | Type |
|--------|------|
| id | Serial PK |
| username | Text |
| password | Text |
| role | Text |

### ✔ `software` table
| Column | Type |
|--------|------|
| id | Serial PK |
| name | Text |
| description | Text |
| access_levels | Text |

### ✔ `requests` table
| Column | Type |
|--------|------|
| id | Serial PK |
| user_id | FK → users.id |
| software_id | FK → software.id |
| access_type | Text |
| reason | Text |
| status | Text |

---

## 🛠️ Tech Stack
- **Java 17**
- **Java Servlets**
- **JSP**
- **PostgreSQL**
- **Apache Tomcat**
- **HTML / CSS / JavaScript**
- **Maven**

---

## 📂 Project Structure

