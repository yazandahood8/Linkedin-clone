# Frontend

## Overview
The **Frontend** of the LinkedIn Clone project is a modern, responsive, and user-friendly interface designed to deliver a seamless user experience. Built with Angular, it supports key functionalities like user authentication, profile management, posts interaction, and job exploration.

---

## Features
- **Authentication**: Secure login and registration forms.
- **Profile Management**: Users can view and update their profiles, including skills, education, and experience.
- **Feed Interaction**: Browse, like, comment, and share posts in real-time.
- **Job Board**: Explore job listings and submit applications.
- **Responsive Design**: Optimized for all screen sizes.

---

## Technologies Used
- **Angular**: For building the dynamic user interface.
- **TypeScript**: Ensuring a robust development process.
- **HTML5 & SCSS**: For creating structured and styled web pages.
- **Node.js**: For dependency management using npm.

---

## Project Structure
The project is structured as follows:

```
Frontend/
├── src/
│   ├── app/
│   │   ├── components/       # Reusable UI components
│   │   ├── pages/            # Pages for different views (e.g., login, feed, profile)
│   │   ├── services/         # Services for API calls
│   │   └── app.module.ts     # Application module setup
│   ├── assets/               # Static files (images, icons, etc.)
│   └── styles/               # Global styles
├── angular.json              # Angular project configuration
├── package.json              # Project dependencies
└── README.md                 # Project documentation
```

---

## Getting Started

### Prerequisites
- **Node.js** (v14 or later)
- **npm** (v6 or later)

### Steps
1. Clone the repository and navigate to the `Frontend` directory:
   ```bash
   git clone https://github.com/yazandahood8/Linkedin-clone.git
   cd Frontend
   ```
2. Install dependencies:
   ```bash
   npm install
   ```
3. Start the development server:
   ```bash
   ng serve
   ```
4. Open your browser and navigate to:
   ```
   http://localhost:4200
   ```

---

## Available Scripts

### **Start the Application**
```bash
ng serve
```
Runs the app in the development mode. Open `http://localhost:4200` to view it in the browser.

### **Build the Application**
```bash
ng build
```
Builds the app for production. The output will be located in the `dist/` directory.

### **Lint the Code**
```bash
ng lint
```
Runs linting to identify and fix coding style issues.

---

## Future Improvements
- **Dark Mode**: Add a toggle for light and dark themes.
- **Push Notifications**: Notify users of new posts, job matches, and connection requests.
- **Localization**: Support multiple languages.

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

