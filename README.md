# Inditex Core Platform Challenge

## 📌 Overview
This project implements a REST API that expose an endpoint to retrieve the price information for a desired product being provided by the `product_id`, the `brand_id` and a particular `date`.

## 💡 Approach to Solution
The solution:
- applies hexagonal architecture, vertical slicing and DDD as design patterns.
- is built using **Java 21** and **Spring Boot 3.3.4**.
- uses Maven to handle dependencies and automate builds.
- adopts JUnit 5 to implement integration tests and unit tests.
- ensures the coverage of use cases with quality assurance tests.
- manages data with a memory persistance H2 database.
- includes a github workflow to check that Pull Requests built Maven successfully.

### 🚀 Running the Application

## 📁 Project Structure
```
src  
 ├── main  
 │   ├── java  
 │   │   └── com.inditex.challenge.coreplatform  
 │   │       ├── prices  
 │   │       │   ├── application    # Business logic  
 │   │       │   ├── domain         # Domain models  
 │   │       │   ├── infrastructure # Repositories and controllers  
 │   │       └── shared  
 │   │           └── exceptions     # Logic for custom exceptions  
 │   └── resources    # Database schema & data
 └── test  
     ├── unittests  
     ├── integrationtests  
     └── qa    # Use case testing
```

## 📝 Endpoint

## 🧪 QA Use Case Tests
