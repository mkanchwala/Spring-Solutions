#Steps to Configure the JellyBelly Project : 

1) First configure Apache Maven 3+ and Apache Tomcat 7+ on your system.
2) After configuration go to MAVEN_HOME Installation directory inside "conf" directory there is a "settings.xml" file 

So Add this config in Servers tab : 

  <servers>
 		<server>
			<id>TomcatServer</id>
			<username>mkanchwala</username>
			<password>jellybelly1</password>
		</server>
  </servers>
  
 3) Now go to the CATALINA_HOME, Tomcat's Installation directory inside "conf" you'll find "tomcat-users.xml"
 
 And Add this Roles with mkanchwala user : 
 
 	<role rolename="manager-script"/>
	<role rolename="manager-jmx"/>
	<user username="mkanchwala" password="jellybelly1" roles="manager-script,manager-jmx" />
	
 4) You may also need RPM module for your operating system : 
 
 	For Linux : sudo apt-get install rpm /sudo yum install rpm
	
 5) Run DB Script : 1 - CreateDBwithTable.sql
 
 6) After configuring this go to the base directory of Project, i.e. HOME/workspace/JellyBelly and run the following commands :

		mvn clean install -e
		mvn tomcat7:run-war or mvn tomcat7:run
 7) Try http://localhost:8080/jellybelly/user/register
 POST
 {
    "username": "test",
    "password": "test",
    "firstName": "test1",
    "lastName": "test2",
    "email": "test@gmail.com",
    "provider": "Normal",
    "isEnabled":1,
    "isVerified":0,
    "smsCode": 0
}
		
 1. add spring security dependency (50 min)
1. http://mvnrepository.com/artifact/org.springframework.security/spring-security-core
2. http://mvnrepository.com/artifact/org.springframework.security/spring-security-web
3. create UserEntity class and implement UserDetails interface (username, password,
enabled)
4. create UserDAO with next methods - saveUser, getUserById, loadUserByUsername
5. configure spring security - create security-config.xml, secure all inner urls, set
authentication manager (authentication provider, password encoder)
2. add apache tiles and configure it (40 min)
1. http://mvnrepository.com/artifact/org.apache.tiles/tiles-core
2. http://mvnrepository.com/artifact/org.apache.tiles/tiles-jsp
3. http://mvnrepository.com/artifact/org.apache.tiles/tiles-template
4. create tiles-config.xml, add viewResolver and tilesConfigurer beans, set up tiles-
layouts.xml
5. creata core tiles definition with header, footer and body
3. create login.jsp (pic. 1 from task 1) and modify login.jsp with two banners, first one
100% width at the top, second one is on left side, login page aligned to right, create
register.jsp (20 min)
1. implement reCaptcha service (30 min)
1. https://developers.google.com/recaptcha/old/docs/java
4. implement register user feature1. implement UserRegistrationController - simple register controller without any
validations and other time-sensitive tasks, check if there are no user with such
email, generate confirmation key and save user to database, send email with
confirmation link (30 min)
2. implement mailing service - link java mail sender to some gmail account for now,
implement sendEmail method with email, template name and map parameters (30
min)
3. add apache velocity dependency - http://mvnrepository.com/artifact/
org.apache.velocity/velocity/1.7 (5 min)
4. create user was registered email template (some mock that can be changed later)
(15 min)
5. implement findUserByKey in UserDAO (10 min)
6. implement UserConfirmationController (20 min)
7. create user was confirmed email template (some mock that can be changed later)
(15 min)
8. create SpringMobileResolver to return device-related pages (15 min)
1. create mockups for tablet and phone views just to show that spring mobile works
correctly (15 min)
9. implement sms service (40+ mins)
1. http://springedge.com/developersapi.html or http://exotel.in
10. in any case - implement sms.properties in resources folder and
PropertyPlaceholderConfigurer to be free to change credentials in easy way (20
min)
11. create registration via sms tab on registration page (10 min)
12. implement UserRegistrationController via sms
1. send sms to user, save smsCode into database (15 min)
2. verify user via sms only if user send valid sms code (10 min)
13. dashboard
1. create dashboard controller which will be available via /dashboard (15 min)
2. add security to it (5 min)
3. add redirect url to spring security login bean (10 min)
4. create dashboard.jsp (20 min)
14. create header.jsp (15 min) and login\logout buttons (10 min