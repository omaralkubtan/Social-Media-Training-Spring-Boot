# Social Media Backend Application

This repository contains the backend implementation of a social media platform using Java Spring Boot. The application includes functionalities for user authentication, creating and managing posts, liking posts, and commenting on posts. The API is well-documented and can be explored using Swagger UI.

## Table of Contents

- [Features](#features)
- [Technology Stack](#technology-stack)
- [Installation](#installation)
- [Configuration](#configuration)
- [Running the Application](#running-the-application)
- [Swagger API Documentation](#swagger-api-documentation)
- [Contributing](#contributing)

## Features

- **User Authentication**: Secure JWT-based authentication.
- **Post Management**: Create, view, and delete posts.
- **Likes**: Like and unlike posts.
- **Comments**: Add comments to posts.
- **Caching**: Implemented caching to reduce unnecessary API calls.
- **Swagger Documentation**: Interactive API documentation with Swagger UI.

## Technology Stack

- **Java**: Java 11+
- **Spring Boot**: Backend framework
- **Spring Security**: Security and authentication
- **JWT**: JSON Web Token for authentication
- **Spring Data JPA**: ORM for database interactions
- **PostgreSQL**: Relational database
- **Swagger**: API documentation

## Installation

### Prerequisites

- Java 11 or higher
- Maven 3.6 or higher
- PostgreSQL

### Clone the Repository

```bash
git clone https://github.com/yourusername/social-media-backend.git
cd social-media-backend
````

### Build the Project

mvn clean install

### Configuration

Before running the application, configure the database connection and other properties in the `application.properties` file:
```bash
# PostgreSQL Database Configuration
spring.jpa.database=POSTGRESQL
spring.datasource.url=jdbc:postgresql://localhost:5432/social_media
spring.datasource.platform=postgres
spring.datasource.username=postgres
spring.datasource.password=5238738
spring.datasource.driver-class-name=org.postgresql.Driver

# JPA/Hibernate Settings
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.generate-ddl=true
spring.jpa.open-in-view=true
spring.jpa.properties.hibernate.order_by.default_null_ordering=last

# Server Port
server.port=8080
```

### Running the Application
To start the application, run the following command:
```bash
mvn spring-boot:run
```
The application will start on `http://localhost:8080`.

## Swagger API Documentation
After starting the application, you can view the API documentation through Swagger UI by navigating to:

`http://localhost:8080/swagger-ui/index.html`

This interactive interface allows you to explore and test the API endpoints directly from your browser.

## Contributing
Contributions are welcome! Please fork the repository, make your changes, and submit a pull request.

Fork the Project
1. Create your Feature Branch (git checkout -b feature/AmazingFeature)
2. Commit your Changes (git commit -m 'Add some AmazingFeature')
3. Push to the Branch (git push origin feature/AmazingFeature)
4. Open a Pull Request
