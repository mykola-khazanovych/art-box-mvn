<h2> art-box web-site </h2>
<h2> mvn edition </h2>
<h2> eclipse ide project </h2>

To read how to deploy this project to server using maven, pls, read comments in ***INSTALL*** section

<h2>Training grounds to learn java+web engineering.</h2>
</p>

***API***

<h6>API request examples</h6>

CREATE ArtBox with theme, age and cost:<br>
	http://localhost:8080/art-box/add?theme=BEARS&age=3&cost=123.00<br>
FIND by theme:<br>
	http://localhost:8080/art-box/find?theme=BEARS<br>
DELETE by id:<br>
	http://localhost:8080/art-box/remove?id=1<br>
SHOW ALL ArtBoxes:<br>
	http://localhost:8080/art-box/list<br>

***INSTALL***

Hello friend!

How to build this project with Maven?

Quite simple. I think you already have JDK, Tomcat and Maven installed and ready-to-work. If not — follow simple guide and succeed:
https://maven.apache.org/download.cgi
http://tomcat.apache.org/

Please update your "tomcat-users.txt" in %tomcat-home%\conf\tomcat-users.xml 

with the following code:

<tomcat-users>

	<role rolename="manager-script" />
	<role rolename="manager-gui" />
	
	#you can deploy your project to server(tomcat) using this setting:
	<user username="deploy-api" password="deploy-api" roles="manager-script" />
	
	#you can enter the tomcat's manager's gui console as:
	<user username="manager" password="manager" roles="manager-gui" />

</tomcat-users>

Then open console and input command "catalina.bat run" in %tomcat-home%\bin\ directory.
(exmpl: run console in the directory E:\installs\programmng\apache-tomcat-8.5.14\bin and input command: catalina.bat run)

To deploy this project on Tomcat run command "mvn tomcat7:deploy" from project's root
directory.
(exmpl: run console in the directory E:\Programming\Eclipse\art-box-mvn and input command: mvn tomcat7:deploy

Should be output "BUILD SUCCESS"
	
BASE COMMANDS IN CONSOLE:

# to stop server
		- shutdown.bat or shortcut "Ctrl+C" 
# deploy with particular profile
		- mvn tomcat7:deploy -PMentor
		
# delete all artefacts in the 'target' directory"
		- mvn clean
		
# delete and create new target directory with new war
		- mvn clean install
		

