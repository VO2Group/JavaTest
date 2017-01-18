<h1>Sample JAVA App with embedded server</h1>

<h2>Quick start!</h2>
1. Ensure your JAVA_HOME is welle defined and pointing to a java8+ JDK

2. Checkout the project and run the following command to start the server :

    - on windows :

        `mvnw.cmd spring-boot:run`

    - on linux/mac :

        `./mvnw spring-boot:run`
 
3. Checking all is working :
    Visit 
    http://localhost:8080/
    for web sample page or 
    http://localhost:8080/rest/samples for REST API
     
    
<h2>In depth..</h2>
A default embedded server (embedded tomcat) and an in memory relational database (hsql) are used.
There are 2 mains controllers/entry points, a service component and some domain's staff. 
- _REST Controller_ : com.vo2.javatest.mvc.controllers.SampleRestController
    - It exposes "sample" resource containing just "id" (key) and "message" properties
    - You can use Postman to quick test REST API 
    (https://www.getpostman.com/docs/)
    - To list all available "samples" : 
        
        `GET http://localhost:8080/rest/samples`
        
        {see SampleRestController#allMessages method}
    - To access a single sample by its id
        
        `GET http://localhost:8080/rest/sample/1`
        
        {see SampleRestController#byIdMessage method}
    - To list all samples which message contains a substring
         
        `GET http://localhost:8080/rest/sample/like/mess` (will list all available samples with message contains "mess")
        
        {see SampleRestController#likeMessage method}
    - //TODO Add CRUD samples (POST/PUT/PATCH/DELETE)
- _Web Controller_ : com.vo2.javatest.mvc.controllers.SampleWebController
    There is only one method on root "/" using a thymeleaf template (src/main/resources/templates/sample.html)
    
- _Service Layer_ : packaged in com.vo2.javatest.services.SampleService which calls the data layer and convert returned database entities into disconnected DTOs
- _Domain / DATA Layer_ : packaged in com.vo2.javatest.domain => JPA entities definition, DTOs and low level data components (DAO/Spring DATA Repositories)

<h2>How to unit test?</h2>
//TODO

<h2>Where is the documentation?</h2>
The Spring Boot project is well documented here 
https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/

The Spring DATA JPA sub project : http://docs.spring.io/spring-data/jpa/docs/current/reference/html/
 
//TODO other links
