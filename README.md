# Spring Boot Product & Cart Management Project

## Project Overview
This is a Spring Boot web application that allows managing products, adding them to a shopping cart, and performing basic administrative tasks. The project includes features like:

- Add, edit, delete products
- View product catalog
- Add and remove products from a shopping cart
- Flash messages for user actions
- Admin login with role-based access
- Export database as SQL
- Search functionality for products

---

## Technology Stack
- **Java Version:** 25
- **Spring Boot Version:** 3.x
- **Build Tool:** Maven
- **Template Engine:** Thymeleaf
- **Database:** H2 (in-memory)
- **Front-end:** Bootstrap 5

---

## Features

### User Features
- View product catalog
- Add products to the shopping cart
- Remove products from the cart
- Flash messages for user feedback

### Admin Features
- Add, edit, delete products
- View all products
- Export database as SQL
- Search products by name
- Admin login with role restriction

---

## Installation & Running the Project

### Prerequisites
- Java 25 installed
- Maven installed
- Git installed

### Steps
1. Clone the repository:
   ```bash
   git clone <repo-url>
   cd <repo-name>
   ```

Open the project in your favorite IDE (IntelliJ IDEA recommended).

Build and run the application using Maven:

```bash
mvn spring-boot:run
```

### Access the application:

Home page / Product catalog: http://localhost:8080/productos

Admin dashboard: http://localhost:8080/admin

Cart: http://localhost:8080/cart

Login page: http://localhost:8080/login

### Admin Credentials

Use these credentials to access admin features. You can modify them in SecurityConfig if needed.

```bash
Username: admin

Password: admin
```

### Notes

The project uses an in-memory H2 database, so all data is lost on server restart.

The application is not mobile-optimized (desktop layout only).

Only users with ADMIN role can access admin pages.