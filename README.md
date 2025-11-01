# 🐾 Animal Adoption Management System

### 📖 Overview
The **Animal Adoption Management System** is a Java-based console application that helps manage animals available for adoption.  
It uses **MySQL** as the backend database and **JDBC** for connectivity.  
Admins or staff can **add, view, update, mark adopted, or delete** animal records efficiently.

---

##  Tech Stack
- **Language:** Java (JDK 8 or later)  
- **Database:** MySQL  
- **Connector:** MySQL JDBC Driver (Connector/J)  
- **IDE (Optional):** IntelliJ IDEA / Eclipse / VS Code  

---

##  Database Setup
Run the following SQL commands in **MySQL Workbench** or **MySQL CLI**:

```sql
CREATE DATABASE animal_adoption;
USE animal_adoption;

CREATE TABLE animals (
    id INT PRIMARY KEY,
    name VARCHAR(30),
    age INT,
    breed VARCHAR(30),
    status VARCHAR(20)   -- Available / Adopted
);

SELECT * FROM animals;
