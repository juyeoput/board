# Board API

A RESTful board API built with Spring Boot, featuring user authentication and CRUD operations for posts and comments.

## Tech Stack

- Java 17
- Spring Boot 4.0.4
- Spring Security
- Spring Data JPA
- MySQL
- JWT (JSON Web Token)
- BCrypt Password Encoding

## Features

- User registration and login with JWT authentication
- BCrypt password encryption
- Post CRUD (Create, Read, Update, Delete)
- Comment CRUD
- Authorization: only the author can update or delete their own posts/comments

## API Endpoints

### Members
| Method | URL | Description | Auth |
|--------|-----|-------------|------|
| POST | /api/members/join | Sign up | No |
| POST | /api/members/login | Login | No |

### Posts
| Method | URL | Description | Auth |
|--------|-----|-------------|------|
| POST | /api/posts | Create post | Yes |
| GET | /api/posts | Get all posts | Yes |
| GET | /api/posts/{id} | Get post by ID | Yes |
| PUT | /api/posts/{id} | Update post | Yes |
| DELETE | /api/posts/{id} | Delete post | Yes |

### Comments
| Method | URL | Description | Auth |
|--------|-----|-------------|------|
| POST | /api/posts/{postId}/comments | Create comment | Yes |
| GET | /api/posts/{postId}/comments | Get comments by post | Yes |
| PUT | /api/posts/{postId}/comments/{commentId} | Update comment | Yes |
| DELETE | /api/posts/{postId}/comments/{commentId} | Delete comment | Yes |

## Project Structure
```
src/main/java/com/example/board
├── config          # Security, JWT filter, Exception handler
├── controller      # REST API controllers
├── dto             # Data Transfer Objects
├── entity          # JPA entities
├── repository      # Spring Data JPA repositories
├── service         # Business logic
└── util            # JWT utility
```

## Environment Variables

| Variable | Description |
|----------|-------------|
| DB_PASSWORD | MySQL database password |
| JWT_SECRET | JWT signing secret key (min 256-bit) |
