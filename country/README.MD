## Country App POC

A Simple App to demo the working the Spring Application using the following projects : 

== Spring Projects

The following Spring projects are used in this sample app:

* http://projects.spring.io/spring-boot/[Spring Boot]
* http://docs.spring.io/spring/docs/current/spring-framework-reference/html/mvc.html[Spring MVC]
* http://projects.spring.io/spring-security/[Spring Security]
* http://projects.spring.io/spring-security-oauth/[Spring Security OAuth]
* http://projects.spring.io/spring-data-jpa/[Spring Data JPA]

#### Prerequisites
- Java 7
- Maven 3
- STS IDE

### Build and run
- Open the `application.properties` file and set your own configurations.
- Run the import.sql and setup the database before running the application
- Then run the Program, From Terminal or IDE
- then Insert the provided dummy data for testing.

#### Usage From terminal

Go on the project's root folder, then type:

    $ mvn spring-boot:run

#### Usage From Eclipse (Spring Tool Suite)

Import as *Existing Maven Project* and run it as *Spring Boot App*.

### Usage

- Run the application and go on http://localhost:8080/
- Use the following urls to invoke controllers methods and see the interactions
  with the database:
    * `/country/add` [POST] Accept:application/json : Creates a new Country with the provided Languages, Same is used to update the country details or language details.
    Example :     
    http://localhost:8080/country/add [POST]
    application/json
    {
	    "name": "United States of America",
	    "code": "USA",
	    "description": "Lorem ipsum",
	    "population": 1580,
	    "capital": "Detroit",
	    "languages": [
	        {
	            "name": "English",
	            "description": "Sample"
	        },
	        {
	            "name": "Urdu",
	            "description": "Sample"
	        },
	        {
	            "name": "Arabic",
	            "description": "Sample"
	        }
	    ]
	}
    * `/countries?codes=[codes]&val=all`: Get countries details using comma seperated codes. If codes are not mentioned then it'll display all the countries.
	Example : 
	http://localhost:8080/countries?codes=IN
	
