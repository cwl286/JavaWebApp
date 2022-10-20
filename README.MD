## Java web application Hong Kong Public Holidays Data

**Language** Java <br/>

**Requirements Specification**
- Develop a Java web application (any framework is fine)
- Button to get Hong Kong Public Holidays Data from https://data.gov.hk/en-data/dataset/hk-effo-statistic-
cal (any language is fine), the data should be stored in a persistent database
- UI to show the list of holidays data retrieved from database
o Required attributes: uid, dtstart, dtend, summary
- Able to filter the holidays by date range
- Wireframe for your reference


**How to work** <br/>

***Preparation***
1. Install Java

2. Install Docker Engineer from docker.com [https://docs.docker.com/engine/install/](https://docs.docker.com/engine/install/)

3. Pull database image in terminal from docker.com [https://hub.docker.com/_/mysql](https://hub.docker.com/_/mysql)
```
docker pull mysql
```
4. In the terminal, start a MySQL instance
```
docker run -p 13306:3306 --name some-mysql -e MYSQL_ROOT_PASSWORD=Password -d mysql:latest
```
***Information about the MySQL intance:***
mysqlname: some-mysql <br/> 
Host:127.0.0.1 <br/>
Client port:13306 <br/>
containers port:3306 <br/>
Username: root <br/>
RootPassword: Password <br/>

5a. To create database in MySQL, use container shell  in terminal
``` 
docker exec -it some-mysql bash
mysql -uroot --password=Password -e "CREATE DATABASE TestDB"
// Enter password:Password
mysql -uroot --password=Password -e "USE TestDB"
// Enter password:Password
```

5b. (Or) Use any MySQL Client application to access the running MySQL instance

```
// Create database
CREATE DATABASE TestDB;
USE TestDB;

```

***To Run***
To run the Spring Boot application from the generated JAR file in command line:
1. Download the demo folder, change the current directory to "target" directory

2. In terminal, type:
```
java –jar demo-1.jar
```

3. Open web browser, go to [localhost:8080/](localhost:8080/)

**Diary**

Day 1:
- Assessed difficulties and what tasks to be done<br/>
    - Protential tasks to do :
    - Web server, html Javascript, ETL, database (a relational database or a simple json or csv file)
        - Decide first store in JSON, store in relational DB only if time allows
- Glanced through simple Java frameworks to use<br/>
    - Picked up [SpringApplication](https://spring.io/guides/gs/serving-web-content/) as it seemed easy to use
- Dealt with environment settings in Mac
- Searched for and created HTML template with js and css
    - [colorlib](https://colorlib.com/wp/template/fixed-header-table/)
- Read through the guide how to handle Form Submission 


Day 2:
- Fixed date picker
- Added error html page
- Worked on handling HTML Form submission
    - HTML Form would return JSON, and Jquery was used to update table
    - Also considered to do with ModelAttribute and Thymeleaf for Form sumission
- Parsed [Hong Kong Public Holidays for 2021-2023](https://www.1823.gov.hk/common/ical/en.json)
    - Stucked with the problem Mac Java Eclipse can't add Certificate for HTTPS for half day
        - "javax.net.ssl.SSLHandshakeException: PKIX path building failed: sun.security.provider.certpath.SunCertPathBuilderException: unable to find valid certification path to requested target"
        - I thought it was my fault with my JSON parser bug at first, but I found a reference about Eclipse HTTPs certification problem. ([Valid cerfitication https](https://stackoverflow.com/questions/21076179/pkix-path-building-failed-and-unable-to-find-valid-certification-path-to-requ))
        - Unfortunately, it was hard to address the problem on Mac and Mac's Eclipse
    - As a quick solution, Created a API as a middleman to process the Hong Kong Holiday data [https://www.1823.gov.hk/common/ical/en.json](https://www.1823.gov.hk/common/ical/en.json)
        - Example: [https://apicwldemo.herokuapp.com/api/v1/importjson?apikey=AbHTJFYu0QDsr9u6Ax0i&url=https://www.1823.gov.hk/common/ical/en.json]
        - My api required two query params: apikey and url 
        - apikey: AbHTJFYu0QDsr9u6Ax0i 
        - url:  API Strings generated from [https://data.gov.hk/en-data/dataset/hk-effo-statistic-cal](https://data.gov.hk/en-data/dataset/hk-effo-statistic-cal)
        - My api output: uid, dtstart(MM/DD/YYYY), dtednd(MM/DD/YYYY), summary
- Add Jquery to filter the holidays by range
 

Day 3
- Created and prepare Mysql docker image
- Connected Java web application to Mysql instead of returning json data on the fly
- Exported to jar by referencing here [https://www.codejava.net/frameworks/spring-boot/how-to-package-spring-boot-application-to-jar-and-war](https://www.codejava.net/frameworks/spring-boot/how-to-package-spring-boot-application-to-jar-and-war)
- Updated test classes from MockMvc to SpringBootTest
- Created a Wireframe
- Updated README about how to compile and run the application.
- Wanted to create docker-compose to provide an one-step application image but seemed not enough time





```
docker build -t  javawebppdemo:1.0 .
docker images

//  --net="jost", then the localhost in your docker container will point to your docker host.
docker run -it --add-host=host.docker.internal:host-gateway --name javawebppdemo javawebppdemo:1.0 bash
```

 java -jar demo-1.jar