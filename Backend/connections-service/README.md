# Connections Service

## Overview
The **Connections Service** is a microservice in the LinkedIn Clone project responsible for managing user connections. This service allows users to send, accept, and manage connection requests, track connection status, and retrieve user connection data efficiently.

---

## Features
- **Send Connection Requests**: Users can send requests to connect with others.
- **Accept or Decline Requests**: Manage incoming connection requests.
- **Connection Status**: View and update the status of connections (e.g., Pending, Accepted).
- **User Connections**: Retrieve a list of all connections for a specific user.
- **Error Handling**: Comprehensive error handling for invalid requests or conflicts.

---

## Technologies Used
- **Java Spring Boot**: For building the RESTful API.
- **PostgreSQL**: For storing user connection data.
- **JWT Authentication**: For secure API endpoints.
- **Docker**: To containerize the service for consistent deployments.
- **Maven**: For project and dependency management.

---

## API Endpoints

### **1. Send Connection Request**
**Endpoint**: `POST /connections/request`
- **Description**: Sends a connection request to another user.
- **Request Body**:
  ```json
  {
    "fromUserId": "123",
    "toUserId": "456"
  }
  ```
- **Response**:
  ```json
  {
    "message": "Connection request sent successfully."
  }
  ```

### **2. Accept Connection Request**
**Endpoint**: `POST /connections/accept`
- **Description**: Accepts an incoming connection request.
- **Request Body**:
  ```json
  {
    "requestId": "789"
  }
  ```
- **Response**:
  ```json
  {
    "message": "Connection request accepted."
  }
  ```

### **3. Get User Connections**
**Endpoint**: `GET /connections/{userId}`
- **Description**: Retrieves a list of all connections for a specific user.
- **Response**:
  ```json
  [
    {
      "connectionId": "123",
      "userId": "456",
      "status": "Accepted"
    }
  ]
  ```

---

## Getting Started

### Prerequisites
- **Java 11**
- **Maven**
- **PostgreSQL**
- **Docker**

### Steps
1. Clone the repository and navigate to the `connections-service` directory:
   ```bash
   git clone https://github.com/yazandahood8/Linkedin-clone.git
   cd Backend/connections-service
   ```
2. Update the `application.properties` file with your PostgreSQL credentials.
3. Build and run the application:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```
4. Alternatively, use Docker:
   ```bash
   docker build -t connections-service .
   docker run -p 8080:8080 connections-service
   ```

---

## Scalability and Future Improvements
- **Batch Processing**: Handle bulk connection requests efficiently.
- **Notifications Integration**: Notify users in real-time about connection updates.
- **Analytics**: Provide insights into user connection patterns.

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


