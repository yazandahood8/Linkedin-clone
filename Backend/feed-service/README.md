# Feed Service

## Overview
The **Feed Service** is a microservice in the LinkedIn Clone project responsible for managing and delivering user posts and interactions. This service enables users to create, view, like, and comment on posts, forming the backbone of the application's social networking features.

---

## Features
- **Create Posts**: Users can create text and multimedia posts.
- **Like Posts**: Interact with posts by liking them.
- **Comment on Posts**: Add comments to existing posts to foster discussions.
- **Feed Delivery**: Retrieve a personalized feed of posts from connections and interests.
- **Error Handling**: Comprehensive error management for invalid or unauthorized actions.

---

## Technologies Used
- **Java Spring Boot**: For building the RESTful API.
- **PostgreSQL**: For storing post and interaction data.
- **JWT Authentication**: For secure API access.
- **Docker**: To containerize the service for consistent deployments.
- **Maven**: For project and dependency management.

---

## API Endpoints

### **1. Create a Post**
**Endpoint**: `POST /feed/posts`
- **Description**: Creates a new post.
- **Request Body**:
  ```json
  {
    "userId": "123",
    "content": "This is a new post!",
    "mediaUrl": "http://example.com/image.jpg"
  }
  ```
- **Response**:
  ```json
  {
    "postId": "456",
    "message": "Post created successfully."
  }
  ```

### **2. Like a Post**
**Endpoint**: `POST /feed/posts/{postId}/like`
- **Description**: Likes a post.
- **Response**:
  ```json
  {
    "message": "Post liked successfully."
  }
  ```

### **3. Comment on a Post**
**Endpoint**: `POST /feed/posts/{postId}/comments`
- **Description**: Adds a comment to a post.
- **Request Body**:
  ```json
  {
    "userId": "123",
    "comment": "Great post!"
  }
  ```
- **Response**:
  ```json
  {
    "commentId": "789",
    "message": "Comment added successfully."
  }
  ```

### **4. Get User Feed**
**Endpoint**: `GET /feed/{userId}`
- **Description**: Retrieves a personalized feed for the user.
- **Response**:
  ```json
  [
    {
      "postId": "456",
      "userId": "123",
      "content": "This is a new post!",
      "likes": 5,
      "comments": 2
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
1. Clone the repository and navigate to the `feed-service` directory:
   ```bash
   git clone https://github.com/yazandahood8/Linkedin-clone.git
   cd Backend/feed-service
   ```
2. Update the `application.properties` file with your PostgreSQL credentials.
3. Build and run the application:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```
4. Alternatively, use Docker:
   ```bash
   docker build -t feed-service .
   docker run -p 8080:8080 feed-service
   ```

---

## Scalability and Future Improvements
- **Real-Time Updates**: Use WebSocket for live feed updates.
- **Post Analytics**: Add insights like post reach and engagement metrics.
- **Content Moderation**: Implement automatic filtering of inappropriate content.

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

## Contact
For any questions or collaboration opportunities, please reach out:
- **GitHub**: [yazandahood8](https://github.com/yazandahood8)
- **Email**: [dahood.yazan8@gmail.com](mailto:dahood.yazan8@gmail.com)
