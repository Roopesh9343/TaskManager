# TaskManager
Simple task manager web application to create, get, update and delete Tasks.

Prerequisites
1. Java: Ensure you have Java Development Kit (JDK) 17.
2. Maven: Confirm you have Apache Maven installed for building the project.
3. Database: Set up your database connection settings in application.properties.
4. Postman: Optionally, install Postman or any other API testing tool to test your endpoints.

Steps to Run the Spring Boot Project
1. Clone the Project.
2. Configure Database
   * open the 'src/main/resources/application.properties' file and update the database configurations
     '''
      spring.datasource.url=jdbc:mysql://localhost:3306/your_database
      spring.datasource.username=your_username
      spring.datasource.password=your_password
     '''
    
  
