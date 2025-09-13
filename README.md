# MicroSync Social Platform

Một nền tảng mạng xã hội dựa trên kiến trúc microservices được xây dựng bằng **Spring Boot** và **Java 17**, tích hợp nhiều loại cơ sở dữ liệu (MySQL, MongoDB, Neo4j). Hệ thống bao gồm năm dịch vụ chính: API Gateway, Identity, Profile, Post và Notification Service, thể hiện các mẫu kiến trúc doanh nghiệp với xác thực JWT an toàn và thông báo real-time sử dụng Kafka.

## 🏗️ Kiến trúc Microservices

```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│     Client      │────│   API Gateway   │────│ Identity Service│
│   (Frontend)    │    │  (Spring Cloud) │    │    (MySQL)      │
└─────────────────┘    └─────────────────┘    └─────────────────┘
                                │
                    ┌───────────┼───────────┐
                    │                       │
           ┌─────────────────┐    ┌─────────────────┐
           │ Profile Service │    │  Post Service   │
           │      (Neo4j)    │    │    (MongoDB)    │
           └─────────────────┘    └─────────────────┘
                    │                       │
                    └───────────┬───────────┘
                                │
                    ┌──────────────────┐    ┌─────────────────┐
                    │   Notification   │────│  Apache Kafka   │
                    │ Service (MongoDB)│    │ + Brevo Email   │
                    └──────────────────┘    └─────────────────┘
```

## 🚀 Các Services Chính

### 1. **API Gateway** (Spring Cloud Gateway)
- **Vai trò**: Điểm vào trung tâm, định tuyến request và load balancing
- **Tính năng**: CORS configuration, request filtering và transformation

### 2. **Identity Service** (Xác thực & Phân quyền)
- **Database**: MySQL
- **Tính năng**:
  - JWT authentication với BCrypt password encryption
  - OAuth2 Resource Server integration
  - Role-based access control (RBAC)

### 3. **Profile Service** (Quản lý người dùng)
- **Database**: Neo4j (Graph Database)
- **Tính năng**: CRUD operations cho user profile và đồng bộ hóa dữ liệu

### 4. **Post Service** (Quản lý bài viết)
- **Database**: MongoDB
- **Tính năng**: Tạo, chỉnh sửa, xóa bài viết với Strategy Design Pattern cho pagination

### 5. **Notification Service** (Thông báo real-time)
- **Database**: MongoDB + Apache Kafka + Brevo Email
- **Tính năng**: Gửi thông báo real-time và email notifications

## 🛠️ Công nghệ sử dụng

### **Backend & Framework**
- **Spring Boot** - Framework chính
- **Java 17** - Ngôn ngữ lập trình
- **Spring Cloud Gateway** - API Gateway
- **Spring Security** - Xác thực và phân quyền

### **Databases**
- **MySQL** - Identity service
- **MongoDB** - Post và Notification services (Document store)
- **Neo4j** - Profile service (Graph database)

### **Message Queuing & Communication**
- **Apache Kafka** - Event streaming và real-time messaging
- **Brevo** - Dịch vụ gửi email
- **WebClient & FeignClient** - Inter-service communication

### **Development & Testing**
- **Maven** - Build automation
- **Docker** - Containerization
- **JUnit 5** - Unit testing
- **MockMVC & Mockito** - Integration và mock testing
- **MapStruct** - DTO-Entity mapping
- **Lombok** - Giảm boilerplate code

## 🔒 Tính năng bảo mật

- **JWT Authentication** với BCrypt password hashing
- **OAuth2 Resource Server** cho fine-grained access control
- **CORS configuration** cho cross-origin requests
- **Role-based permissions** cho các API endpoints

## 📚 Patterns được áp dụng

- **Microservices Architecture** - Tách biệt services theo business domain
- **API Gateway Pattern** - Centralized entry point
- **Strategy Design Pattern** - Post retrieval optimization
- **Event-driven Architecture** - Kafka message streaming
- **Database per Service** - Data isolation giữa các services
