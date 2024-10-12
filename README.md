# TaskManager
Simple task manager web application to create, get, update and delete Tasks.

## Prerequisites
1. Java: Ensure you have Java Development Kit (JDK) 17.
2. Maven: Confirm you have Apache Maven installed for building the project.
3. Database: Set up your database connection settings in application.properties.
4. Postman: Optionally, install Postman or any other API testing tool to test your endpoints.

## Steps to Run the Spring Boot Project
1. Clone the Project.
2. Configure Database
   * open the 'src/main/resources/application.properties' file and update the database configurations
      ```
      spring.datasource.url=jdbc:mysql://localhost:3306/your_database
      spring.datasource.username=your_username
      spring.datasource.password=your_password
      ```
3. Build the Project:
    * Navigate to the project directory and build the project using Maven:
      ```
      mvn clean install
      ```
4. Run the Project:
    * After a successful build, start the Spring Boot application:
      ```
      mvn spring-boot:run
      ```
    * to configure custom service port, add this in application.properties file:
      ```
      server.port=9300
      ```
5. Access Swagger Documentation:
    *Once the application is running, open your browser and go to:
      ```
        http://localhost:<server.port>/swagger-ui/index.html#/task-manager-controller/viewHomePage
      ```


## Testing the APIs
### Using Postman or a Similar API Testing Tool
+ Set Up a New Request:
     *Open Postman and import the curl cmds.

1. Create

    ```
          curl --location 'http://localhost:9300/api/taskManger/create' \
      --header 'Content-Type: application/json' \
      --data '{
          "taskName": "Complete Project Report13",
          "description": "Prepare the final report for the project including all sections.",
          "dueDate": "2024-10-15T17:00:00",
          "createdDate": "2024-10-11T09:30:00",
          "updatedDate": "2024-10-11T09:30:00"
      }
      '
    ```
2. Update
    ```
    curl --location --request PUT 'http://localhost:9300/api/taskManger/update/16' \
    --header 'Content-Type: application/json' \
    --data '{
        "taskName": "Update changes ",
        "description": "updating changes  ",
        "dueDate": "2024-10-15T17:00:00",
        "createdDate": "2024-10-11T10:30:00",
        "updatedDate": "2024-10-11T10:30:00"
    }
    '
    ```
3. GetById
    ```
    curl --location --globoff 'http://localhost:9300/api/taskManger/{id}'
    ```
5. GetAll
    ```
    curl --location --globoff 'http://localhost:9300/api/taskManger/'
    ```
7. Delete
    ```
    curl --location --request DELETE 'http://localhost:9300/api/taskManger/2' \
    --data ''
    ```
### Using the GUI 
* once the application is started open a browser and paste the below url:
    ```
    http://localhost:<server.port>/
    ```
## Technologies and Libraries used in this project
1. **Spring Boot**
    * _Description_: Spring Boot is a framework that simplifies the creation of stand-alone, production-grade Spring       
      applications. It provides pre-configured templates and settings to help with rapid development.
    * _Usage_: This project uses Spring Boot to create a RESTful API server. It automatically handles various configurations, 
      reducing the boilerplate code needed for setup.
2. **Java**
   * _Description_: Java is a widely-used programming language known for its portability, performance, and large ecosystem. 
     It is the language in which Spring Boot is built and used.
   * _Usage_: Java is the primary language for implementing the logic, models, and controllers in this project.
3. **Swagger**
   * _Description_: Swagger is an open-source toolset for API documentation and testing. It provides a user-friendly 
     interface to view and test APIs.
   * _Usage_: Swagger is included to auto-generate API documentation, allowing you to test and explore endpoints 
     interactively via swagger-ui.
4. **Thymeleaf**
    * _Description_: Thymeleaf is a Java template engine for web applications. It allows for seamless integration of Java and 
      HTML, making it easy to build dynamic web pages.
    * _Usage_: Used to render HTML pages on the client side, Thymeleaf templates enable easy integration with Spring Boot to 
      create dynamic views.
5. **HTML, CSS, and AJAX**
    * _HTML_: Markup language used to structure and display content on the web.
    * _CSS_: Stylesheet language used to style and layout the web pages.
    * _AJAX_: Asynchronous JavaScript and XML, a set of web development techniques used for creating asynchronous web 
      applications.
    * _Usage_: HTML and CSS are used to create the front-end of the project, while AJAX enables asynchronous communication 
      with the server for a more dynamic user experience.
6.** Postman**
    * _Description_: Postman is a tool used to develop, test, and document APIs. It allows users to create and send HTTP 
      requests to APIs.
    * _Usage_: Postman is used to manually test the API endpoints created in this project, especially during development.
7. **Lombok**
    * _Description_: Lombok is a Java library that helps reduce boilerplate code. It provides annotations to auto-generate 
      getters, setters, constructors, and more.
    * _Usage_: Lombok is used to simplify Java code in the project by eliminating repetitive code like getters, setters, and 
      constructors.
8. **Spring Web**
    * _Description_: Part of the Spring Framework, Spring Web provides tools to build web applications, including RESTful 
      services.
    * _Usage_: Spring Web is used to handle HTTP requests and responses, facilitating the development of RESTful endpoints for the project.
9. **Spring Data JPA**
    * _Description_: Spring Data JPA is part of the Spring Data project and simplifies data access, abstracting away the     
      underlying database interactions.
    * _Usage_: It allows for easy integration with JPA (Java Persistence API), providing a repository abstraction over the 
      database (e.g., MySQL or H2).
10. **MySQL & H2 Database**
    * _MySQL_: A popular relational database management system used for production environments.
    * _H2_: An in-memory database often used for development and testing.
    * _Usage_: MySQL is used as the primary database in this project, with H2 serving as an in-memory alternative for testing 
      and quick prototyping.
11. **Spring Boot Starter Test**
    * _Description_: This starter includes libraries like JUnit, Mockito, and AssertJ, which are essential for unit and 
      integration testing in Spring applications.
    * _Usage_: Spring Boot Starter Test is used to test various parts of the application, including the API endpoints and 
      business logic, ensuring quality and stability.



  
  
    
      

  
