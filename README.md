# 📦 Inventory Management System

An **Inventory Management System** built using **Java Spring Boot**, **Thymeleaf**, and **MySQL**.
This project helps manage products, stock, and inventory operations efficiently.

---

## 🚀 Features

- 🔐 Admin Login
- 📦 Add, Update, Delete Products
- 📊 View Inventory Details
- 🔍 Search Products
- 💾 MySQL Database Integration
- 🌐 Web Interface using Thymeleaf

---

## 🛠️ Technologies Used

- Java
- Spring Boot
- Thymeleaf
- MySQL
- Spring Data JPA
- Hibernate
- Maven
- Bootstrap

---

## 📂 Project Structure
src
├── main
│ ├── java
│ │ ├── controller
│ │ ├── service
│ │ ├── repository
│ │ └── model
│ └── resources
│ ├── templates
│ ├── static





---

## ⚙️ How to Run the Project

1. Clone the repository  
```bash
git clone https://github.com/riyasonar25/Inventory-Management-system8081.git


Create MySQL database

CREATE DATABASE inventory_db;


Update application.properties

spring.datasource.url=jdbc:mysql://localhost:3306/inventory_db
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update


Run the project using:

mvn spring-boot:run

🌐 Application URL
http://localhost:8081/login
│ └── application.properties
