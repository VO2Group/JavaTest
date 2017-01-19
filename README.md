<h1>Sample JAVA App with embedded server</h1>

<h2>Quick start!</h2>

1. Ensure your JAVA_HOME is well defined and pointing to a java8+ JDK

2. Checkout the project and run the following command to start the server :

    - on windows :

        `mvnw.cmd spring-boot:run`

    - on linux/mac :

        `./mvnw spring-boot:run`

    - from your IDE :
    run or debug [JavaTestApplication](./src/main/java/com/vo2/JavaTestApplication.java) (class annotated with @SpringBootApplication)
 
3. Checking all is working :
    Visit 
    [localhost:8080](http://localhost:8080/)
    for web sample page or 
    [/rest/hellovo2](http://localhost:8080/rest/hellovo2) for REST API
     
    
<h2>In depth..</h2>

A default embedded server (embedded tomcat) and an in memory relational database (hsql) are used.
There are 2 mains controllers/entry points, a service component and some domain's staff. 
- _REST Controller_ : com.vo2.javatest.mvc.controllers.SampleRestController
    - It exposes "sample" resource containing just "id" (key) and "message" properties
    - You can use [Postman](https://www.getpostman.com/docs/) to quick test REST API
    - To list all available "samples" :
        
        `GET http://localhost:8080/rest/samples`
        
        {see [SampleRestController#allMessages](./src/main/java/com/vo2/javatest/mvc/controllers/SampleRestController.java#L37) method}
    - To access a single sample by its id
        
        `GET http://localhost:8080/rest/sample/1`
        
        {see   [SampleRestController#byIdMessage](./src/main/java/com/vo2/javatest/mvc/controllers/SampleRestController.java#L49) method}

    - To list all samples which message contains a substring
         
        `GET http://localhost:8080/rest/sample/like/mess` (will list all available samples with message that contains "mess")
        
        {see [SampleRestController#likeMessage](./src/main/java/com/vo2/javatest/mvc/controllers/SampleRestController.java#L61) method}
    - //TODO Add CRUD samples (POST/PUT/PATCH/DELETE)
- _Web Controller_ : com.vo2.javatest.mvc.controllers.SampleWebController
    There is only one method on root "/" using a thymeleaf template (src/main/resources/templates/sample.html)
    
- _Service Layer_ : packaged in com.vo2.javatest.services.SampleService which calls the data layer and convert returned database entities into disconnected DTOs
- _Domain / DATA Layer_ : packaged in com.vo2.javatest.domain => JPA entities definition, DTOs and low level data components (DAO/Spring DATA Repositories)

<h2>How to test with junit?</h2>

- For a simple unit test for a service class, please refer to [SampleServiceTest](./src/test/java/com/vo2/javatest/services/SampleServiceTest.java)
- There is a _**real integration test**_ with embedded server running on a random port in [JavaTestOnRandomPortRESTTests](./src/test/java/com/vo2/JavaTestOnRandomPortRESTTests.java)
- There is a _**mocked integration test**_ without starting a real servlet context but a mocked one and with a mock behaviour for the service in [JavaTestOnMockRESTTests](./src/test/java/com/vo2/JavaTestOnMockRESTTests.java)
- Finally, to test spring data repository with a real call to JPA behind a fresh in memory database, please look at [SampleRepositoryTest](./src/test/java/com/vo2/javatest/domain/repositories/SampleRepositoryTest.java)

<h2>Where is the documentation?</h2>

The Spring Boot project is well documented [here](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/),
the Spring DATA JPA sub project is [here](http://docs.spring.io/spring-data/jpa/docs/current/reference/html/). Visit this link for complete documentation about
[testing](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-testing.html) with Spring Boot
