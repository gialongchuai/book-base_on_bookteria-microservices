# MicroSync Social Platform

## Description

**MicroSync Social Platform** is a scalable, microservices-based social platform developed using **Spring Boot** and **Java 17**. The platform is composed of five independent services:
- **API Gateway**: Routes traffic and manages requests across the microservices.
- **Identity Service**: Handles user authentication and authorization.
- **Profile Service**: Manages user profiles and synchronization.
- **Post Service**: Manages posts, including CRUD operations and pagination.
- **Notification Service**: Delivers real-time notifications and email alerts.

### Key Features
- **Secure User Authentication**: Implemented with **JWT** tokens and **BCrypt** password hashing for secure login and registration.
- **Identity and Profile Management**: Integrated with **MySQL** for identity management and **MongoDB/Neo4j** for other services, supporting CRUD operations and profile synchronization.
- **Post System**: Built a paginated post system using the **Strategy Design Pattern** for optimized post retrieval and management.
- **Real-Time Notifications**: Enabled using **Kafka**, with email delivery integrated via **Brevo** on successful user actions.
- **Service Communication**: Seamless inter-service communication using **WebClient** and **FeignClient** through the **API Gateway**, leveraging **Spring Cloud Gateway**.
- **Cross-Origin Resource Sharing (CORS)**: Configured CORS policies to ensure proper handling of cross-origin requests.
- **Role-Based Access Control (RBAC)**: Secured endpoints using **OAuth2** Resource Server for fine-grained access control.
- **Dockerized Services**: Containerized services using Docker for consistent and reliable deployments.
- **Unit and Integration Testing**: Comprehensive testing with **Postman**, **MockMVC**, **Mockito**, **JUnit 5**, and **Testcontainers** for both unit and integration testing.
- **Code Quality and Coverage**: Enhanced code quality using **Spotless** and **SonarQube**, ensuring high standards and maintaining excellent test coverage.

## Technologies Used
- **Spring Boot**: Framework used to build the microservices.
- **Java 17**: Programming language for the development of the services.
- **JWT**: Secure token-based authentication for user sessions.
- **BCrypt**: Password hashing for secure user credentials storage.
- **Spring Data JPA**: For managing data persistence in MySQL.
- **MapStruct**: For efficient data mapping between layers.
- **MongoDB** & **Neo4j**: NoSQL and graph databases for profile and post management.
- **Kafka**: Real-time messaging system for event-driven notifications.
- **Brevo**: Email delivery service for user notifications.
- **Spring Cloud Gateway**: API Gateway for routing and handling service communications.
- **OAuth2**: Resource server for secure role-based access control.
- **Docker**: For containerizing services for deployment.
- **JUnit 5** & **Mockito**: Unit and integration testing framework.
- **Testcontainers**: For testing containerized services.
- **Spotless** & **SonarQube**: For maintaining clean and high-quality code.
