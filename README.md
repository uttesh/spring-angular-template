# spring-angular-template
Simple Spring 4 + Jersey + MongoDB + AngularJS + Gulp + Maven starter template application 

Basic template for web application with front end angularJS framework and REST as backend with JERSEY , With Bootstrap 3.x css and fontAwesome

<h4>Prerequisites</h4>
Need node.js installed in system to build the js part of the application by using the gulp.

NODE.JS : https://nodejs.org/en/download/

<h4>Technologies</h4>
---------------------
1.Java 1.7 <br/>
2.Spring 4.2.x <br/>
3.Spring Security 3.x <br/>
4.Jersey REST <br/>
5.Json <br/>
6.Angularjs 1.4.x<br/>
7.Jquery <br/>
8.Bootstrap 3.x css <br/>
9.FontAwesome <br/>
10.Maven <br/>
11.MongoDB <br/>
12.Gulp </br/>

<h4>Building</h4>

execute the build.bat file which will setup node+bower+gulp along with required files. 
After the setup run the following commands for js building, minifying and deploying to static folder under web-app folder.

run "gulp" on parent folder and "gulp deploy", after the execution make sure that the minified file under web-app/static folder.

after building the UI run the maven commands.

"mvn clean install"

<h4>Deploy</h4>

deploy the "spring-angular-template.war" on tomcat/wildfly server and access the following url

demo url : http://localhost:8080/spring-angular-template

 


