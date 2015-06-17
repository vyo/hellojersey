# hellojersey [![Build Status](https://api.shippable.com/projects/54f9d4f35ab6cc135294191b/badge?branchName=master)](https://app.shippable.com/projects/54f9d4f35ab6cc135294191b/builds/latest)

A sample REST app built with Jersey to try out and assess the Jersey Test Framework


###Technology

We use                 | as our                       | from here
-----------------------|------------------------------|---------------------------
Jersey                 |  REST framework              | https://jersey.java.net/
Tomcat (embedded)      |  Application server          | http://tomcat.apache.org/download-80.cgi
H2                     |  Database                    | http://www.h2database.com/
Hibernate              |  Database abstraction        | http://hibernate.org/
Swagger                |  API generation/presentation | http://swagger.io/
Mockito                |  Mocking framework           | http://mockito.org/
GSON                   |  JSON library                | https://code.google.com/p/google-gson/
JUnit                  |  Java test framework         | http://junit.org/
RESTassured            |  REST testing (client API)   | https://code.google.com/p/rest-assured/
Jersey Test Framework  |  REST testing (unit tests)   | https://jersey.java.net/documentation/latest/test-framework.html

###Requirements
You will need
- java 8 for compiling and running the project
- maven 3 for building the project
- a modern web browser for using the web API

###Usage
Either 'mvn package' the project and run it via 'java -jar target/hellojersey.jar';
or run the project's main class (io.github.vyo.hellojersey.application.Launcher) in your IDE of choice.
In the latter case an embedded Tomcat 8 will be started and the application deployed therein.

The application exposes a web interface at localhost:8080 (or localhost:PORT, if the PORT environment variable has been specified).

You can then easily inspect and operate the application's REST methods via the Swagger UI.

###Presentation Slides
http://slides.com/manuelweidmann/jerseytesting

###Sample app
https://hellojersey.herokuapp.com
