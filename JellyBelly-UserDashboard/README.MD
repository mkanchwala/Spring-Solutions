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
 
 6) Now go to the profiles/dev/config.propeties and socialConfig.properties and update your credentials and a VM folder path.
 
 7) After configuring this go to the base directory of Project, i.e. HOME/workspace/JellyBelly and run the following commands :

		mvn clean install -e
		mvn tomcat7:run-war or mvn tomcat7:run
 8) Go to the http://localhost:8080/jellybelly/ or http://localhost:8080/jellybelly/login for the Login View and Create a User Account or Login thru Facebook or Twitter.
 
 9) After Registration You will get an Email on your mentioned "email", Click on the sample verification link and activate your account and also check the confirmation email for same.
 
 
 
