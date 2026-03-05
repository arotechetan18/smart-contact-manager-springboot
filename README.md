
---

# 📇 Smart Contact Manager (Spring Boot)

Smart Contact Manager is a **full-stack Java web application** built using **Spring Boot** and **Thymeleaf** that enables users to securely store and manage their personal contacts online. It follows the MVC architecture and demonstrates key backend and frontend web development skills.

---
## 🌐 Live Demo

👉 https://smart-contact-manager-springboot.onrender.com

---
## 📌 Repository Link

👉 https://github.com/arotechetan18/Smart-Contact-Manager-springboot

---

## 📌 Project Overview

This application allows users to:

✔ Register and log in securely<br>
✔ Add, update, view, and delete contacts<br>
✔ Search and navigate through contacts<br>
✔ View contacts with pagination<br>
✔ Manage user profiles<br>
✔ Send Email without changing page <br>
✔ Export contact through CSV <br>

It follows a layered architecture with Controllers, Services, Repositories using Spring Data JPA, and integrates Spring Security for authentication.
---

## 🛠️ Tech Stack

| Component       | Technology                  |
| --------------- | --------------------------- |
| Backend         | Java, Spring Boot           |
| Frontend        | Thymeleaf, HTML,tailwindCSS|
| Security        | Spring Security             |
| ORM             | Hibernate (Spring Data JPA) |
| Database        | MySQL                       |
| Build Tool      | Maven                       |
| Version Control | Git & GitHub                |

---

## 📁 Project Structure

```
Smart-Contact-Manager-springboot/
│
├─ src/main/java/...         # Java source code
│   ├─ controllers           # Web controllers
│   ├─ services              # Business logic
│   ├─ repositories          # DB access (JPA)
│   ├─ entities              # Data models
│   └─ config                # Security/config classes
│
├─ src/main/resources/
│   ├─ templates/            # Thymeleaf views (UI pages)
│   ├─ static/               # CSS/JS assets
│   └─ application.properties
│
├─ pom.xml                   # Maven dependencies
└─ README.md                 # Project documentation
```

---

## 🧩 Features

### 🔐 Authentication

* Secure user registration and login using Spring Security
* Password encryption for safe storage

### 📇 Contact Management

* Create, Read, Update, Delete (CRUD) contacts
* Pagination of contact list
* Search contacts by name or phone

### 📈 UI and Interaction

* User-friendly web interface with Thymeleaf
* MVC architecture for clear separation of concerns
* Dark / Light Mode Toggle (User Interface Theme Switching)
* Persistent Theme Preference using Local Storage

---
## 📊 Learning Outcomes

✔ Built a dynamic full-stack Java web app<br>
✔ Implemented Spring Security authentication<br>
✔ Worked with Spring MVC + Thymeleaf<br>
✔ Used Spring Data JPA for DB operations<br>
✔ Learned pagination and web form handling<br>
✔ Implemented Dark/Light mode using TailwindCSS and JavaScript<br>

---
## 🛠️ Installation & Setup

### 1️⃣ Clone the Repository

```bash
git clone https://github.com/arotechetan18/Smart-Contact-Manager-springboot.git
cd Smart-Contact-Manager-springboot
```

---

### 2️⃣ Configure Database

1. Install & start **MySQL**
2. Create database:

```sql
CREATE DATABASE scm;
```

3. Edit `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/scm
spring.datasource.username=<YOUR_MYSQL_USERNAME>
spring.datasource.password=<YOUR_PASSWORD>

spring.jpa.hibernate.ddl-auto=update
```


---

# 🔐 Authentication & OAuth Configuration

This project supports:

* ✅ Traditional Login & Registration
* ✅ Google OAuth 2.0 Login
* ✅ GitHub OAuth 2.0 Login

### 🔹 Google OAuth Setup

1. Go to Google Cloud Console
2. Create OAuth Client ID
3. Add Redirect URI:

```
http://localhost:8081/login/oauth2/code/google
```

4. Add environment variables:

```
GOOGLE_CLIENT_ID=your_client_id
GOOGLE_CLIENT_SECRET=your_client_secret
```

---

### 🔹 GitHub OAuth Setup

1. Go to GitHub → Settings → Developer Settings → OAuth Apps
2. Add Callback URL:

```
http://localhost:8081/login/oauth2/code/github
```

3. Add environment variables:

```
GITHUB_CLIENT_ID=your_client_id
GITHUB_CLIENT_SECRET=your_client_secret
```

---

# ☁️ Cloudinary Image Upload

This project integrates **Cloudinary** for storing and managing contact profile images.

### 🔹 Setup Steps

1. Create account at [https://cloudinary.com](https://cloudinary.com)

2. Get:

   * Cloud Name
   * API Key
   * API Secret

3. Add environment variables:

```
CLOUD_API_KEY=your_api_key
CLOUD_API_SECRET=your_api_secret
```

---

# 📧 Email Configuration (Mailtrap)

Email functionality is configured using Mailtrap for development testing.

### 🔹 Setup

1. Create Mailtrap account
2. Copy SMTP credentials
3. Add in `application.properties`

```
spring.mail.host=sandbox.smtp.mailtrap.io
spring.mail.port=587
spring.mail.username=your_username
spring.mail.password=your_password
spring.mail.properties.mail.smtp.auth=LOGIN
spring.mail.properties.mail.smtp.starttls.enable=true
```

---

# 🔒 Environment Variables

To protect sensitive credentials, this project uses environment variables instead of hardcoding secrets.

Example (Windows PowerShell):

```
setx GOOGLE_CLIENT_ID "your_client_id"
setx GOOGLE_CLIENT_SECRET "your_client_secret"
setx GITHUB_CLIENT_ID "your_client_id"
setx GITHUB_CLIENT_SECRET "your_client_secret"
setx CLOUD_API_KEY "your_key"
setx CLOUD_API_SECRET "your_secret"
```

After setting variables, restart your IDE.

---



---

### 3️⃣ Run the Application

```bash
mvn spring-boot:run
```

After startup, open in browser:

```
http://localhost:8080
```

## 📸 Project Screenshots
<div align="center">
<h1>🏠 Home & profile Pages</h1>
<p> <img src="Screenshot 2026-03-05 111457.png" width="45%" style="margin:10px" />  <img src="Screenshot 2026-03-05 111636.png" width="45%" style="margin:10px" /> </p>
<h1>📊 Dashboard &  Add Contact </h1><P>
<img src="Screenshot 2026-03-05 111552.png" width="45%" style="margin:10px" />
  <img src="Screenshot 2026-03-05 111717.png" width="45%" style="margin:10px" /> </p> </div>

## 🚀 Future Enhancements

✔ Password reset via email<br>
✔ Profile image upload<br>
✔ REST API support for contacts<br>
✔ Mobile responsive UI<br>
✔ Acount activate/deactivate<br>

---

## 🙌 Author

**Chetan Arote** — Aspiring Full-Stack Developer ✨

---



