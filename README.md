# online-quiz-application-API

This project is a robust, modular **RESTful API backend** for a modern online quiz and question management system.

## Key Features

* **Question Management:** Supports the creation, retrieval, and validation of questions across various types.
* **Business Logic Validation:** Implements strict validation in the service layer to enforce question and answer rules.
* **Quiz Scoring:** Provides a dedicated endpoint to receive user answers, calculate the final score, and deliver results.
* **Clean Architecture:** Follows a standard Controller-Service-Repository structure, ensuring high maintainability.
* **Technology Stack:** Leverages **Spring Boot 3** for rapid development and **PostgreSQL** for persistence.

## Technology Stack

| Category               | Technology                  |
| :--------------------- | :--------------------------|
| **Backend Framework**  | Spring Boot 3.5.6          |
| **Persistence**        | Spring Data JPA / Hibernate|
| **Database**           | PostgreSQL                 |
| **Build Tool**         | Maven / Gradle             |
| **Language**           | Java 21+                   |

# Local Setup and Running Instructions

Follow these steps to get the application running on your local machine.

## Prerequisites

Ensure you have the following installed:

1. **Java Development Kit (JDK) 21+**
2. **Maven** (or Gradle)
3. **PostgreSQL Database Server** (running locally or accessible)

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

## 🗃️ Postman Collection Example (`Online Quiz API.postman_collection.json`)

You can use the following Postman collection file to quickly set up and test API endpoints.
Save this as `Online Quiz API.postman_collection.json` in your project root or import it directly into Postman.

```json
{
	"info": {
		"_postman_id": "45794196-a679-498d-a230-05cecb0eb46f",
		"name": "Online Quiz API",
		"description": "Postman collection for Online Quiz Application",
		"schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json",
		"_exporter_id": "36644025",
		"_collection_link": "https://aman66-1100.postman.co/workspace/Aman~a2f22306-097d-44b4-8946-e89b169d4997/collection/36644025-45794196-a679-498d-a230-05cecb0eb46f?action=share&source=collection_link&creator=36644025"
	},
	"item": [
		{
			"name": "Create Quiz",
			"request": {
				"method": "POST",
				"header": [
					{
						"key": "Content-Type",
						"value": "application/json"
					}
				],
				"body": {
					"mode": "raw",
					"raw": "{\n  \"title\": \"Java Basics Quiz 2\"\n}"
				},
				"url": {
					"raw": "http://localhost:8080/api/quiz",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"quiz"
					]
				}
			},
			"response": []
		},
		{
			"name": "Get All Quizzes",
			"request": {
				"method": "GET",
				"header": [],
				"url": {
					"raw": "http://localhost:8080/api/quiz",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"quiz"
					]
				}
			},
			"response": []
		},
		{
			"name": "Add Question to Quiz",
			"request": {
				"method": "POST",
				"header": [
					{
						"key": "Content-Type",
						"value": "application/json"
					}
				],
				"body": {
					"mode": "raw",
					"raw": ""
				},
				"url": {
					"raw": "http://localhost:8080/api/quiz/1/questions",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"quiz",
						"1",
						"questions"
					]
				}
			},
			"response": [
				{
					"name": "single",
					"originalRequest": {
						"method": "POST",
						"header": [
							{
								"key": "Content-Type",
								"value": "application/json"
							}
						],
						"body": {
							"mode": "raw",
							"raw": "{\n  \"text\": \"Which of these is a programming language?\",\n   \"points\": 5,\n   \"type\": \"SINGLE_CHOICE\",\n   \"options\": [\n     {\n       \"text\": \"HTML\",\n       \"correct\": false\n    },\n     {\n       \"text\": \"Python\",\n      \"correct\": true     },\n     {\n       \"text\": \"CSS\",\n      \"correct\": false\n     }\n   ]\n    }\n\n"
						},
						"url": {
							"raw": "http://localhost:8080/api/quiz/1/questions",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"api",
								"quiz",
								"1",
								"questions"
							]
						}
					},
					"_postman_previewlanguage": null,
					"header": null,
					"cookie": [],
					"body": null
				},
				{
					"name": "Multiple",
					"originalRequest": {
						"method": "POST",
						"header": [
							{
								"key": "Content-Type",
								"value": "application/json"
							}
						],
						"body": {
							"mode": "raw",
							"raw": "\n\n {\n   \"text\": \"Select all backend technologies:\",\n   \"points\": 10,\n   \"type\": \"MULTIPLE_CHOICE\",\n   \"options\": [\n     {\n       \"text\": \"React\",\n       \"correct\": false\n     },\n    {\n       \"text\": \"Spring Boot\",\n       \"correct\": true     },\n     {\n       \"text\": \"Node.js\",\n       \"correct\": true\n     }\n   ]\n }\n\n"
						},
						"url": {
							"raw": "http://localhost:8080/api/quiz/1/questions",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"api",
								"quiz",
								"1",
								"questions"
							]
						}
					},
					"_postman_previewlanguage": null,
					"header": null,
					"cookie": [],
					"body": null
				},
				{
					"name": "text",
					"originalRequest": {
						"method": "POST",
						"header": [
							{
								"key": "Content-Type",
								"value": "application/json"
							}
						],
						"body": {
							"mode": "raw",
							"raw": "\n\n\n{\n  \"text\": \"Explain the concept of Dependency Injection in 300 words or less.\",\n  \"points\": 20,\n  \"type\": \"TEXT\",\n  \"options\": []\n}"
						},
						"url": {
							"raw": "http://localhost:8080/api/quiz/1/questions",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"api",
								"quiz",
								"1",
								"questions"
							]
						}
					},
					"_postman_previewlanguage": null,
					"header": null,
					"cookie": [],
					"body": null
				}
			]
		},
		{
			"name": "Get Questions for Quiz",
			"request": {
				"method": "GET",
				"header": [],
				"url": {
					"raw": "http://localhost:8080/api/quiz/1/questions",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"quiz",
						"1",
						"questions"
					]
				}
			},
			"response": []
		},
		{
			"name": "Submit Quiz & Get Score",
			"request": {
				"method": "POST",
				"header": [
					{
						"key": "Content-Type",
						"value": "application/json"
					}
				],
				"body": {
					"mode": "raw",
					"raw": "{\n  \"answers\": [\n    {\"questionId\": 1, \"selectedOptionId\": [2]},\n    {\"questionId\": 2, \"selectedOptionId\": [1,2]},\n    {\"questionId\": 3, \"textAnswer\": \"temp answer for testing\"}\n\n  ]\n}"
				},
				"url": {
					"raw": "http://localhost:8080/api/quiz/1/submit",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"quiz",
						"1",
						"submit"
					]
				}
			},
			"response": [
				{
					"name": "Correct answers",
					"originalRequest": {
						"method": "POST",
						"header": [
							{
								"key": "Content-Type",
								"value": "application/json"
							}
						],
						"body": {
							"mode": "raw",
							"raw": "{\n  \"answers\": [\n    {\"questionId\": 1, \"selectedOptionId\": [2]},\n    {\"questionId\": 2, \"selectedOptionId\": [5,6]},\n    {\"questionId\": 3, \"answerText\": \"temp answer for testing\"}\n\n  ]\n}"
						},
						"url": {
							"raw": "http://localhost:8080/api/quiz/1/submit",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"api",
								"quiz",
								"1",
								"submit"
							]
						}
					},
					"_postman_previewlanguage": null,
					"header": null,
					"cookie": [],
					"body": null
				},
				{
					"name": "Wrong answers",
					"originalRequest": {
						"method": "POST",
						"header": [
							{
								"key": "Content-Type",
								"value": "application/json"
							}
						],
						"body": {
							"mode": "raw",
							"raw": "{\n  \"answers\": [\n    {\"questionId\": 1, \"selectedOptionId\": [1]},\n    {\"questionId\": 2, \"selectedOptionId\": [1,2]},\n    {\"questionId\": 3, \"answerText\": \"\"}\n\n  ]\n}"
						},
						"url": {
							"raw": "http://localhost:8080/api/quiz/1/submit",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8080",
							"path": [
								"api",
								"quiz",
								"1",
								"submit"
							]
						}
					},
					"_postman_previewlanguage": null,
					"header": null,
					"cookie": [],
					"body": null
				}
			]
		}
	]
}
```

---

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
