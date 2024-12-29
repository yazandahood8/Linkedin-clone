# User Service

## Overview
The **User Service** is a critical microservice in the LinkedIn Clone project, responsible for managing user data and authentication. This service handles user profiles, account management, and secure access to the platform.

---

## Features
- **User Registration**: Create new user accounts with detailed profiles.
- **User Authentication**: Secure login and session management using JWT.
- **Profile Management**: Update user information, including skills, education, and experience.
- **Role-Based Access Control**: Differentiate access levels based on user roles.
- **Error Handling**: Comprehensive error management for invalid actions.

---

## Technologies Used
- **Java Spring Boot**: For building the RESTful API.
- **PostgreSQL**: For storing user data.
- **JWT Authentication**: For secure access to endpoints.
- **Docker**: To containerize the service for consistent deployments.
- **Maven**: For dependency and project management.

---

## API Endpoints

### **1. User Registration**
**Endpoint**: `POST /users/register`
- **Description**: Registers a new user.
- **Request Body**:
  ```json
  {
    "name": "John Doe",
    "email": "johndoe@example.com",
    "password": "securepassword",
    "role": "USER"
  }
  ```
- **Response**:
  ```json
  {
    "userId": "123",
    "message": "User registered successfully."
  }
  ```

### **2. User Login**
**Endpoint**: `POST /users/login`
- **Description**: Authenticates a user and provides a JWT token.
- **Request Body**:
  ```json
  {
    "email": "johndoe@example.com",
    "password": "securepassword"
  }
  ```
- **Response**:
  ```json
  {
    "token": "jwt-token-string",
    "message": "Login successful."
  }
  ```

### **3. Get User Profile**
**Endpoint**: `GET /users/{userId}`
- **Description**: Retrieves detailed information about a specific user.
- **Response**:
  ```json
  {
    "userId": "123",
    "name": "John Doe",
    "email": "johndoe@example.com",
    "role": "USER",
    "profile": {
      "skills": ["Java", "Spring Boot"],
      "experience": [
        {
          "company": "TechCorp",
          "position": "Software Engineer",
          "years": 3
        }
      ]
    }
  }
  ```

### **4. Update User Profile**
**Endpoint**: `PUT /users/{userId}`
- **Description**: Updates the user profile information.
- **Request Body**:
  ```json
  {
    "name": "John Doe",
    "profile": {
      "skills": ["Java", "Spring Boot", "Docker"],
      "experience": [
        {
          "company": "TechCorp",
          "position": "Senior Software Engineer",
          "years": 5
        }
      ]
    }
  }
  ```
- **Response**:
  ```json
  {
    "message": "User profile updated successfully."
  }
  ```

---

## Getting Started

### Prerequisites
- **Java 11**
- **Maven**
- **PostgreSQL**
- **Docker**

### Steps
1. Clone the repository and navigate to the `user-service` directory:
   ```bash
   git clone https://github.com/yazandahood8/Linkedin-clone.git
   cd Backend/user-service
   ```
2. Update the `application.properties` file with your PostgreSQL credentials.
3. Build and run the application:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```
4. Alternatively, use Docker:
   ```bash
   docker build -t user-service .
   docker run -p 8080:8080 user-service
   ```

---

## Scalability and Future Improvements
- **Advanced Search**: Allow users to search for profiles based on skills, location, or experience.
- **Two-Factor Authentication**: Add an extra layer of security.
- **Notifications**: Notify users of profile updates or login attempts.

---

## Contributing
We welcome contributions! To contribute:
1. Fork the repository.
2. Create a feature branch:
   ```bash
   git checkout -b feature-name
   ```
3. Commit your changes and push to your fork.
4. Submit a pull request for review.

---

