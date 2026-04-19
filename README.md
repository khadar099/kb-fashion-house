# 🛍️ Fashion House Backend (Spring Boot)

## 📌 Overview

This is a backend application built using **Spring Boot** for the Fashion House project.
It provides REST APIs for user authentication, product listing, and password management.

The backend is connected to a **MySQL database** and is deployed on an EC2 server.

---

## 🏗️ Tech Stack

* Java 17
* Spring Boot
* Spring Security
* Spring Data JPA (Hibernate)
* MySQL
* Maven

---

## 🚀 Features

### 🔐 Authentication

* User Registration
* Login (Email / Mobile)
* Password Encryption (BCrypt)

### 🔁 Password Management

* Forgot Password
* Reset Password via Token

### 🛒 Product Management

* View all products
* (Currently using in-memory data)

---

## 📂 Project Structure

```
com.kb.fashionhouse
│
├── controller       # REST Controllers
├── service          # Business Logic
├── repository       # Database access
├── model            # Entity classes
├── config           # Security configuration
└── FashionHouseApplication.java
```

---

## 🔗 API Endpoints

### 🧾 Authentication APIs

#### ✅ Register User

```
POST /api/auth/register
```

**Request Body:**

```json
{
  "email": "test@gmail.com",
  "password": "1234",
  "mobile": "9876543210"
}
```

**Response:**

```
User registered successfully
```

---

#### ✅ Login

```
POST /api/auth/login
```

**Request Body:**

```json
{
  "email": "test@gmail.com",
  "password": "1234"
}
```

**Response:**

```
Login successful
```

---

#### 🔐 Forgot Password

```
POST /api/auth/forgot-password
```

---

#### 🔐 Reset Password

```
POST /api/auth/reset-password
```

---

### 🏠 Home API

```
GET /api/home
```

**Response:**

```
Welcome to Fashion House API
```

---

### 🛒 Product APIs

#### Get All Products

```
GET /api/products
```

**Response:**

```json
[
  {
    "id": 1,
    "name": "T-Shirt",
    "price": 499
  }
]
```

---

## ⚙️ Configuration

### application.properties

```
server.port=8181
server.address=0.0.0.0

spring.datasource.url=jdbc:mysql://localhost:3306/your_db
spring.datasource.username=your_username
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## 🔐 Security

* CSRF disabled (for API usage)
* All `/api/**` endpoints are publicly accessible
* Passwords are encrypted using BCrypt
* Spring Security configured for REST (no form login)

---

## 🧪 How to Run the Application

### 1️⃣ Clone the repository

```
git clone <your-repo-url>
cd kb-fashion-house
```

---

### 2️⃣ Install dependencies & run

```
mvn spring-boot:run
```

---

### 3️⃣ Verify application

Open browser:

```
http://localhost:8181/api/home
```

---

### 4️⃣ Test APIs (Postman)

Example:

```
POST http://<your-ec2-ip>:8181/api/auth/register
```

---

## 🌐 Deployment

* Deployed on EC2 server
* Ensure port **8181** is open in security group
* Backend accessible via public IP

---

## ⚠️ Notes

* Angular frontend will consume these APIs
* Current product data is hardcoded (can be moved to DB later)
* Email service for reset password is currently mocked (console output)

---

## 📈 Future Improvements

* JWT Authentication
* Role-based access control
* Product CRUD with database
* Email integration (SMTP)
* Angular frontend integration

---

## 👨‍💻 Author

Developed as part of a full-stack project using Spring Boot + Angular.
