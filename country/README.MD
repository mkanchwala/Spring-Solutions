## Country App POC

A Simple App to demo the working the Spring Application using the following projects : 

== Spring Projects

The following Spring projects are used in this sample app:

* http://projects.spring.io/spring-boot/[Spring Boot]
* http://docs.spring.io/spring/docs/current/spring-framework-reference/html/mvc.html[Spring MVC]
* http://projects.spring.io/spring-security/[Spring Security]
* http://projects.spring.io/spring-security-oauth/[Spring Security OAuth]
* http://projects.spring.io/spring-data-jpa/[Spring Data JPA]
* http://www.mysql.com/[MYSQL]

#### Prerequisites
- Java 7
- Maven 3
- STS IDE
- Curl Package or Some REST Client

### Build and run
- Open the `application.properties` file and set your own configurations.
- Run the world.sql in SQLScripts folder and setup the database before running the application (Database contains some data too..)
 		mysql -uroot -proot < world.sql
- Then run the Program, From Terminal or IDE

#### Usage From terminal

Go on the project's root folder, then type:

    $ mvn spring-boot:run

#### Usage From Eclipse (Spring Tool Suite)

Import as *Existing Maven Project* and run it as *Spring Boot App*.

### Usage

- Run the application thru one of the above method
- Use the following urls to invoke controllers methods and see the interactions, Webapp will be deployed on port 8080
  with the database:
    * `http://localhost:8080/oauth/token` : Creates a new Oauth2 Token
     Example : curl -X POST -k -vu MKClient:sm@rty123 http://localhost:8080/oauth/token -H "Accept: application/json" -d "client_id=MKClient&client_secret=sm@rty123&username=mkanchwala&password=spring123&grant_type=password&scope=read%20write"
     
    * `/country/add` [POST] Accept:application/json : Creates a new Country with the provided Languages, Same is used to update the country details or language details.
    Example :     
    curl -v -X POST \
	   -H "Content-Type: application/json" \
	   -H "Authorization: Bearer <ACCESS_TOKEN>" \
	   -d '{
		    "worldName": "United Arab Emirates",
		    "code": "UAE",
		    "population": 120,
		    "translations": [
			{
			    "name": "English",
			    "description": "World Official Language",
			    "localName": "Emirates",
			    "capital": "Abu Dhabi",
			    "details": "This country is a federation of seven emirates"
			},
			{
			    "name": "Arabic",
			    "localName": "الإمارات العربية المتحدة",
			    "capital": "ابوظبي",
			    "details": "الإمَارَات العَرَبيّة المُتّحِدة"
			},
			{
			    "name": "اردو",
			    "localName": "متحدہ عرب امارات",
			    "capital": "ابو_ظہبی",
			    "details": " جزیرہ نمائے عرب کے جنوب مشرقی ساحلوں پر واقع ایک ملک ہے جو 7 امارات: ابوظہبی"
			}
		    ]
		}' \
	   'http://localhost:8080/country/add'
	   
    * `/countries?codes=[codes]&val=all`: Get countries details using comma seperated codes. If codes are not mentioned then it'll display all the countries. "codes" is used to provide the countries codes(Unqiue) and "val" (ALL\NONE) is the parameter to return without user's preferred language.
	Example : 
	curl -v 'http://localhost:8080/countries?codes=IN&val=All' -H "Authorization: Bearer <ACCESS_TOKEN>"
	