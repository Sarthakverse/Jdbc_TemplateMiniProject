# 📚 JdbcTemplate MiniProject
### This is a mini-project demonstrating the usage of Spring Boot with JDBC Template for performing CRUD operations on a PostgreSQL database. It provides RESTful APIs for managing student records.
![image](https://github.com/Sarthakverse/Jdbc_TemplateMiniProject/assets/117356021/cb2c5c3f-2837-4842-9c50-b4235bd54771)
![Screenshot 2024-04-07 224950](https://github.com/Sarthakverse/Jdbc_TemplateMiniProject/assets/117356021/778f61dc-ff0f-4aa7-8507-83289cc90605)

## Documentation

[Documentation](https://linktodocumentation)

# 🚀 Features:

## Add New Student:
### 📝 Allows adding a new student to the database.


## Fetch All Students:
### 📋 Retrieves a list of all students from the database.


## Fetch Student by ID:
### 🔍 Retrieves a specific student by their ID from the database.


## Update Student:
### 🔄 Updates the information of an existing student in the database.


## Delete Student:
### 🗑️ Deletes a student from the database based on their ID.


# 💻 Technologies Used:
## Java 17
## Spring Boot
## Spring JDBC Template
## PostgreSQL
## Maven

# 🏃‍♂️ How to Run:
### Ensure you have PostgreSQL installed and running on your local machine.
### Clone this repository.
### Open the project in your preferred IDE.
### Configure the database connection properties in the application.properties file.
### Run the JdbcTemplateMiniProjectApplication.java class to start the Spring Boot application.
### The application will be accessible at http://localhost:8080.

# 🛠️ Endpoints:

## Add New Student:
### URL: POST /students/addStudent
### Request Body: JSON object representing the student details.
### Response: Success message or error message.

## Fetch All Students:
### URL: POST /students/getStudents
### Response: List of all students or error message.

## Fetch Student by ID:
### URL: GET /students/{id}
### Path Variable: id - ID of the student to fetch.
### Response: Student details or error message.

## Update Student:
### URL: PUT /students/updateStudent
### Request Body: JSON object representing the updated student details.
### Response: Success message or error message.

## Delete Student:
### URL: DELETE /students/{id}
### Path Variable: id - ID of the student to delete.
### Response: Success message or error message.

# 🚀 Deployment:
### You can deploy this application on any cloud platform like Heroku, AWS, or Azure by packaging it into a JAR file and configuring the necessary environment variables for the database connection.
