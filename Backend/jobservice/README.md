# Job Service

## Overview
The **Job Service** is a core microservice in the LinkedIn Clone project, enabling users to post, search, and apply for jobs. It streamlines the process of connecting job seekers with employers, offering a seamless and efficient job board experience.

---

## Features
- **Post Job Openings**: Employers can create detailed job postings.
- **Search Jobs**: Users can search for jobs by keywords, location, and other filters.
- **Apply for Jobs**: Submit applications directly through the platform.
- **Track Applications**: Monitor the status of submitted job applications.
- **Error Handling**: Robust error management for invalid actions or conflicts.

---

## Technologies Used
- **Java Spring Boot**: For building the RESTful API.
- **PostgreSQL**: For storing job and application data.
- **JWT Authentication**: For secure access to endpoints.
- **Docker**: To containerize the service for consistent deployments.
- **Maven**: For dependency and project management.

---

## API Endpoints

### **1. Post a Job**
**Endpoint**: `POST /jobs`
- **Description**: Creates a new job posting.
- **Request Body**:
  ```json
  {
    "employerId": "123",
    "title": "Software Engineer",
    "description": "Develop and maintain software solutions.",
    "location": "Remote",
    "salaryRange": "$70,000 - $100,000"
  }
  ```
- **Response**:
  ```json
  {
    "jobId": "456",
    "message": "Job posted successfully."
  }
  ```

### **2. Search for Jobs**
**Endpoint**: `GET /jobs/search`
- **Description**: Searches for jobs based on filters.
- **Query Parameters**:
  - `title`: Job title or keywords.
  - `location`: Job location.
  - `salaryRange`: Desired salary range.
- **Response**:
  ```json
  [
    {
      "jobId": "456",
      "title": "Software Engineer",
      "location": "Remote",
      "salaryRange": "$70,000 - $100,000"
    }
  ]
  ```

### **3. Apply for a Job**
**Endpoint**: `POST /jobs/{jobId}/apply`
- **Description**: Submits an application for a specific job.
- **Request Body**:
  ```json
  {
    "userId": "789",
    "resume": "http://example.com/resume.pdf",
    "coverLetter": "I am excited to apply for this role..."
  }
  ```
- **Response**:
  ```json
  {
    "applicationId": "321",
    "message": "Application submitted successfully."
  }
  ```

### **4. Track Application Status**
**Endpoint**: `GET /applications/{userId}`
- **Description**: Retrieves the status of all applications submitted by a user.
- **Response**:
  ```json
  [
    {
      "applicationId": "321",
      "jobId": "456",
      "status": "Under Review"
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
1. Clone the repository and navigate to the `jobservice` directory:
   ```bash
   git clone https://github.com/yazandahood8/Linkedin-clone.git
   cd Backend/jobservice
   ```
2. Update the `application.properties` file with your PostgreSQL credentials.
3. Build and run the application:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```
4. Alternatively, use Docker:
   ```bash
   docker build -t job-service .
   docker run -p 8080:8080 job-service
   ```

---

## Scalability and Future Improvements
- **Advanced Filters**: Add filters like industry, job type, and experience level.
- **Notifications**: Notify users when a new job matches their profile or application status changes.
- **Recommendation System**: Suggest jobs based on user skills and preferences.

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

