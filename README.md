# online-quiz-application-API

This project is a robust, modular **RESTful API backend** for a modern online quiz and question mana[...]
## Key Features ('

* **Question Management:** Supports the creation, retrieval, and validation of questions acros[...]
* **Business Logic Validation:** Implements strict validation in the service layer to enforce ques[...]
* **Quiz Scoring:** Provides a dedicated endpoint to receive user answers, calculate the final sco[...]
* **Clean Architecture:** Follows a standard Controller-Service-Repository structure, ensuring hig[...]
* **Technology Stack:** Leverages **Spring Boot 3** for rapid development and **PostgreSQL** for p[...]

## Technology Stack =����

| Category       | Technology                  |
| :---           |:---------------------------|
| **Backend Framework** | Spring Boot 3.5.6       |
| **Persistence** | Spring Data JPA / Hibernate |
| **Database**    | PostgreSQL                  |
| **Build Tool**  | Maven / Gradle              |
| **Language**    | Java 21+                    |

# Local Setup and Running Instructions =؀�

Follow these steps to get the application running on your local machine.

## Prerequisites

Ensure you have the following installed:

1.  **Java Development Kit (JDK) 21+**
2.  **Maven** (or Gradle)
3.  **PostgreSQL Database Server** (running locally or accessible)

---

---

## 🚀 Local Setup and Instructions

### **Step 1: Clone and Navigate 🧭**

Clone the project repository and change into the directory:

```bash
git clone https://github.com/AnujLo1c/online-quiz-application-API.git
cd online-quiz-application-API
```

---

### **Step 2: Configure the Database ⚙️**

#### **Create Database**

In your PostgreSQL server, create a database:

```sql
CREATE DATABASE quizdb;
```

#### **Update Configuration**

Open the configuration file at `src/main/resources/application.properties` and set your actual PostgreSQL credentials:

```properties
# --- PostgreSQL Connection ---
spring.datasource.url=jdbc:postgresql://localhost:5432/quizdb
spring.datasource.username=postgres_user
spring.datasource.password=your_db_password
spring.datasource.driver-class-name=org.postgresql.Driver

# --- JPA/Hibernate Settings ---
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

---

### **Step 3: Build and Run the Application ▶️**

#### **Option A: Using Maven (Command Line)**

**Build:** Compile and package the application into a JAR file:

```bash
mvn clean install
```

**Run:** Execute the generated JAR file:

```bash
java -jar target/online-quiz-application-*.jar
```

#### **Option B: Using an IDE**

Open the project in your IDE (IntelliJ, Eclipse, etc.), locate the main class (e.g., `OnlineQuizApplication.java`), and **Right-click → Run**.

---

### **Step 4: Access the API 🔗**

The application will start on port `8080`.

- **Base URL:** [http://localhost:8080/api](http://localhost:8080/api)

You can use Postman or any HTTP client to interact with the API endpoints.

---

## 🧪 Running Test Cases

Test cases are executed separately from the main application and help verify your business logic.

### **Option 1: Using the Command Line (Maven)**

Run all test files (`*Test.java`) located in your `src/test/java` directory:

```bash
# Runs all tests and generates a report
mvn test
```

### **Option 2: Using an Integrated Development Environment (IDE)**

This is the fastest way to run individual tests during development.

- **Run All Tests in a Package:** Right-click the main test package (e.g., `com.anujl.online_quiz_application`) in `src/test/java` and select **"Run Tests"**.
- **Run a Single Test Class:** Open the test file (e.g., `QuestionServiceTest.java`), right-click the class name, and select **"Run 'QuestionServiceTest'"**.
- **Run a Single Test Method:** Click the green arrow next to the specific `@Test` method you want to execute and select **"Run 'testMethodName()'"**.


## 📝 Assumptions & Design Choices

### **Assumptions**
- The frontend is responsible for interpreting the question JSON body according to the `questionType`. It decides how to render and validate user inputs based on this type.
- For `TEXT` type questions, the backend does **not** need to validate the content of `answerText`. Any string is accepted as a valid answer.

### **Design Choices**
- **RESTful Architecture:** The API follows proper REST conventions, using HTTP methods and resource-oriented endpoints.
- **Logical API Design:** Endpoints are named and structured to be intuitive and aligned with RESTful principles.
- **Clean Data Models:** Entities are designed to reflect real-world quiz concepts, ensuring clarity and extensibility.
- **Separation of Concerns:** The codebase is organized into routes, controllers, and services for maintainability and scalability.
- **Validation Layer:** All business logic validations (such as answer checking, question structure) are handled in the service layer.
- **Extensibility:** The modular structure allows new question types or features to be added easily.

---


