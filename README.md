# 🧩 User Access Management System  
### Java Servlets • JSP • PostgreSQL • Maven • Tomcat

![Java](https://img.shields.io/badge/Java-17-orange?logo=java)
![JSP](https://img.shields.io/badge/JSP-JavaServer%20Pages-blue)
![Servlets](https://img.shields.io/badge/Servlets-Java%20EE-red)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?logo=postgresql)
![Tomcat](https://img.shields.io/badge/Tomcat-Server-yellow?logo=apache-tomcat)
![Maven](https://img.shields.io/badge/Maven-Build-6f42c1?logo=apache-maven)

---

## 📌 Overview  
The **User Access Management System (UAMS)** is a complete web-based Java application to manage controlled access to software applications inside an organization.  
It supports **User Registration**, **Login**, **Role-Based Redirection**, **Software Management**, **Access Request Workflow**, and **Manager Approval System**.

This project is built using **Java Servlets**, **JSP**, **Apache Tomcat**, and **PostgreSQL**, following a clean MVC-style structure.

---

## 🚀 Features

### 🔐 Authentication & Authorization
- Employee Sign-Up  
- Login with session handling  
- Role-based home page redirection  

### 👥 User Roles & Permissions  
| Role | Actions |
|------|---------|
| **Employee** | Sign up, Log in, Request Access |
| **Manager** | View pending requests, Approve/Reject |
| **Admin** | Add software, Full access, Manager + Employee rights |

---

## 🧠 System Modules

### 1️⃣ **Sign-Up System (SignUpServlet)**  
- Default role = *Employee*  
- Stores user into database  
- Redirects to login  

### 2️⃣ **Login System (LoginServlet)**  
- Validates user credentials  
- Creates session  
- Redirects to pages based on role  

### 3️⃣ **Software Management (Admin Only)**  
Admins can:
- Add new software  
- Set access levels (Read / Write / Admin)  

### 4️⃣ **Employee Access Request System (RequestServlet)**  
Employees can:
- Choose software  
- Select access type  
- Provide reason  

### 5️⃣ **Manager Approval System (ApprovalServlet)**  
Managers can:
- View pending requests  
- Approve or Reject  
- Status updated in database  

---

## 🗄️ Database Schema (PostgreSQL)

### ✔ **users**
| Column | Type |
|--------|------|
| id | Serial PK |
| username | Text |
| password | Text |
| role | Text |

### ✔ **software**
| Column | Type |
|--------|------|
| id | Serial PK |
| name | Text |
| description | Text |
| access_levels | Text |

### ✔ **requests**
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
- **Java Servlets**
- **JSP**
- **PostgreSQL**
- **Apache Tomcat**
- **Maven**
- **HTML / CSS / JavaScript**

---

## 📂 Project Structure

