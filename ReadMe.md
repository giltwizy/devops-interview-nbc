# NBC Bank Transaction Service

## Overview
This is a Spring Boot application designed to handle banking transactions for NBC Bank Tanzania. It provides a RESTful API to process transactions, store them in a MySQL database, and handle duplicate reference checks.

## Features
- **Endpoint**: `POST /nbc-bank/devops/v1/transactions`
- **Request Payload**:
  ```json
  {
      "service": "DEVOPS_INTERVIEW",
      "name": "NBC BANK TANZANIA",
      "amount": "103,000",
      "account": "AC1234567890",
      "reference": "REF0987654321"
  }
  ```
- **Success Response** (Status Code: 600):
  ```json
  {
      "statusCode": "600",
      "message": "Transaction has been processed"
  }
  ```
- **Failure Response** (Status Code: 601 for duplicate reference):
  ```json
  {
      "statusCode": "601",
      "message": "Duplicate reference received, try with one"
  }
  ```
- Stores transaction data in a MySQL database
- Ensures unique transaction references

## Prerequisites
- Java 17
- Maven 3.8.x or higher
- MySQL 8.x
- IDE (e.g., IntelliJ IDEA, Eclipse) or terminal for running the application

## Project Structure
```
src
└── main
    ├── java
    │   └── com.nbcbank
    │       ├── NbcBankApplication.java
    │       ├── config
    │       │   └── DatabaseConfig.java
    │       ├── entity
    │       │   └── Transaction.java
    │       ├── repository
    │       │   └── TransactionRepository.java
    │       ├── service
    │       │   └── TransactionService.java
    │       ├── controller
    │       │   └── TransactionController.java
    │       └── dto
    │           ├── TransactionRequest.java
    │           └── TransactionResponse.java
    └── resources
        └── application.properties
```

## Setup Instructions

1. **Clone the Repository**
   ```bash
   git clone <repository-url>
   cd nbc-bank-transaction
   ```

2. **Set Up MySQL Database**
    - Create a MySQL database:
      ```sql
      CREATE DATABASE nbc_bank;
      ```
    - Create the transactions table:
      ```sql
      CREATE TABLE transactions (
          id BIGINT AUTO_INCREMENT PRIMARY KEY,
          service VARCHAR(255) NOT NULL,
          name VARCHAR(255) NOT NULL,
          amount VARCHAR(50) NOT NULL,
          account VARCHAR(50) NOT NULL,
          reference VARCHAR(50) NOT NULL UNIQUE
      );
      ```

3. **Configure Application Properties**
    - Open `src/main/resources/application.properties`
    - Update the MySQL credentials:
      ```properties
      spring.datasource.url=jdbc:mysql://localhost:3306/nbc_bank?useSSL=false&serverTimezone=UTC
      spring.datasource.username=<your-username>
      spring.datasource.password=<your-password>
      spring.jpa.hibernate.ddl-auto=update
      spring.jpa.show-sql=true
      spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
      ```

4. **Build the Project**
   ```bash
   mvn clean install
   ```

5. **Run the Application**
   ```bash
   mvn spring-boot:run
   ```
   The application will start on `http://localhost:8765`.

## Usage
- **Endpoint**: `POST /nbc-bank/devops/v1/transactions`
- **Sample Request** (using curl):
  ```bash
  curl -X POST http://localhost:8765/nbc-bank/devops/v1/transactions \
  -H "Content-Type: application/json" \
  -d '{
      "service": "DEVOPS_INTERVIEW",
      "name": "NBC BANK TANZANIA",
      "amount": "103,000",
      "account": "AC1234567890",
      "reference": "REF0987654321"
  }'
  ```
- **Expected Success Response**:
  ```json
  {
      "statusCode": "600",
      "message": "Transaction has been processed"
  }
  ```
- **Expected Failure Response** (if reference is duplicated):
  ```json
  {
      "statusCode": "601",
      "message": "Duplicate reference received, try with one"
  }
  ```

- You can also test the endpoint using Postman or any other API client.

## Dependencies
- Spring Boot Starter Web
- Spring Boot Starter Data JPA
- MySQL Connector/J
- Lombok

## Notes
- The application uses Spring Data JPA for database operations.
- The `reference` field in the transactions table is unique to prevent duplicate transactions.
- Ensure your MySQL server is running before starting the application.
- The application logs SQL queries for debugging (configured in `application.properties`).

## Deployment and Rollback Plan
- Copy the docker-compose.yml file to your environment
- run the the docker-compose 'docker compose up -d'
- Monitor the services if they are both running (Web application and Database). If anything odd is happening such that services aren't running you can rollback by docker-compose down command and reach out to the developer

## Troubleshooting
- **Database Connection Issues**: Verify MySQL is running and credentials in `application.properties` are correct.
- **Port Conflicts**: Ensure port 8080 is free or change the port in `application.properties` by adding `server.port=<new-port>`.
- **Dependency Issues**: Run `mvn clean install` to resolve dependency conflicts.