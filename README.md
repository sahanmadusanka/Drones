# The Drones

## Prerequisite

* JDK 17
* Docker
* Docker Compose

## How to run the project
* Create DB using docker compose, Run below commands in project root
 ```
docker-compose -f docker-compose.yml up --no-start
docker-compose -f docker-compose.yml start
```

* Make sure **src/main/resources/application.properties** file having correct values
 ```
 server.servlet.context-path=/api  
spring.jpa.hibernate.ddl-auto=none  
spring.datasource.platform=postgres  
spring.datasource.url=jdbc:postgresql://localhost:5433/the_drones   # DB (in Docker/init.sql) 
spring.datasource.username=the_drone_user # DB user (in Docker/init.sql) 
spring.datasource.password=  
liquibase.change-log=classpath:liquibase-changeLog.xml  
drone.battery.loadingMinLevel=25 # Minimum battery percentage drone can load medication 
drone.battery.level.update.crone=* */5 * * * *   # This crone for Drones battery lookup interval
 ```
* To build  and start the project, Run below gradle command
 ```
./gradlew bootRun
 ``` 

To run the JUnit tests
```
./gradlew clean test
```

#### Notes:
- Service will start on port 8080
- Context path is "/api/", example API - http://localhost:8080/api/v1/drone/register
- Database will generate with the schema and the master data using liquibase.
- Run **git log** for view all commits




### API Schema
Please find Postman collection **The Drones.postman_collection.json** in the project root 