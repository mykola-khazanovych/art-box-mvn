<h4> art-box web-site </h4>
<b>mvn edition</b>
<br>
<br>

<p>
To read how to deploy this project to server using maven, pls, read comments in ***INSTALL*** 
section.
</p>

<h2>Training grounds to learn java+web engineering.</h2>


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

Hello friend! :)

How to build this project with Maven?

Quite simple. I think you already have JDK, Tomcat and Maven installed and ready-to-work. If not - 
follow simple guide and succeed:
<br>
https://maven.apache.org/download.cgi
<br>
http://tomcat.apache.org/

Please update your "tomcat-users.txt" in %tomcat-home%\conf\tomcat-users.xml 
with the code from file docs/tomcat-users.txt

Then open console and input command "catalina.bat run" in %tomcat-home%\bin\ directory.
(exmpl: run console in the directory E:\installs\programmng\apache-tomcat-8.5.14\bin and input 
command: catalina.bat run)

To deploy this project on Tomcat run command "mvn tomcat7:deploy" from project's root
directory.
(exmpl: run console in the directory E:\Programming\Eclipse\art-box-mvn and input command: 
mvn tomcat7:deploy)

Output we looking for - "BUILD SUCCESS" - :)
	
<h5> BASE COMMANDS IN CONSOLE: </h5>

<h6> to stop server </h6>
		- shutdown.bat or shortcut "Ctrl+C" 
		
<h6> deploy with particular profile </h6>
		- mvn tomcat7:deploy -PMentor
		
<h6> delete all artefacts in the 'target' directory" </h6>
		- mvn clean
		
<h6> delete and create new target directory with new war </h6>
		- mvn clean install
		

