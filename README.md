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



  
  
    
      

  
