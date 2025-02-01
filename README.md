# FAQ Service

## 🛠️ About the Project
This project is a backend service designed for managing frequently asked questions (FAQ). It enables adding, updating, retrieving, and deleting FAQs in a system, offering a simple REST API for users to interact with.

## 🌟 Getting Started

### Fork and Clone
1. **Fork this Repository**: Click the Fork button on the top right.
2. **Clone Your Fork**:
   ```bash
   git clone https://github.com/<your-username>/faq-service.git
   cd faq-service
### 🛠️ Set Up Your Environment

Ensure you have the following installed:

- **Java 21+**: Download and install the latest Java version.
- **Maven 3.9.9**: Install Maven.
- **IDE (e.g., IntelliJ IDEA, Eclipse)**: Choose an IDE for development. You can download IntelliJ IDEA or Eclipse.
- **Terminal/Command Prompt**: Use the terminal or command prompt for running Maven commands and the application.
## 🛠️ Technologies Used

- **Java**: The core programming language used for developing the backend service.
- **Spring Boot**: A framework for building Java-based backend applications quickly and easily, simplifying the development process.
- **H2 Database**: An in-memory database used for quick and lightweight testing of the application. It allows for fast data storage and retrieval during development.
- **JUnit and Mockito**: JUnit is used for unit testing, while Mockito is used for mocking dependencies during tests to ensure that each component functions as expected in isolation.

### 🛠️ Build the Project:
 ```bash
    mvn clean install
```
### 🛠️ Run the Application:
 ```bash
    mvn spring-boot:run
```
## 📝 Features

- **Dynamic Contact Consolidation**: Handles multiple email and phone identifiers seamlessly.
- **Primary/Secondary Contacts**: Assigns primary or secondary precedence to contacts based on reconciliation logic.
- **Robust Error Handling**: Includes custom exceptions and validation mechanisms.
- **Testing and In-Memory DB**: Pre-configured H2 database for seamless development and testing.

## 🚀 Usage
Test the Endpoint
Use tools like Postman or curl to interact with the /contacts/identify endpoint.
### Endpoint:
 ```bash
   POST http://localhost:8080/contacts/identify
```
### Sample Request:
 ```bash
   {
  "email": "example@email.com",
  "phoneNumber": "1234567890"
}
```
### Expected Response:
```bash
   {
  "primaryContactId": 1,
  "emails": ["example@email.com"],
  "phoneNumbers": ["1234567890"],
  "secondaryContactIds": []
}
```
## 📂 File Structure

- **Controller**: Manages API requests and responses.
- **DTOs**: Defines the structure for request/response objects.
- **Service**: Contains the business logic.
- **Model**: Defines the database schema.
- **Repository**: Interfaces for database interactions.
- **Exception Handling**: Global error handling classes.

