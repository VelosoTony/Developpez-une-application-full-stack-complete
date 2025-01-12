<p align="center">
  <img src="front\src\assets\logo_p6.png" title="MDD" width="50%" height="50%">
</p>

# MDD - Monde de Dév

![Java](https://img.shields.io/badge/Java-17.0.6-red) ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.0.7-green) ![Spring Security](https://img.shields.io/badge/Spring-Security-darkgreen) ![Maven](https://img.shields.io/badge/Apache%20Maven-3.8.7-blueviolet) ![MySQL](https://img.shields.io/badge/MySQL-8.0.x-orange) ![Angular](https://img.shields.io/badge/Angular-16.0.1-red) ![Javadoc](https://img.shields.io/badge/Javadoc-3.5.0-red) ![SpringDoc](https://img.shields.io/badge/SpringDoc-2.1.0-green)

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 16.0.1, Node version 18.14.0, Package Manager version npm 9.6.6.

## Prerequisites

- This project run on **Java 17**, download [here](https://www.oracle.com/fr/java/technologies/downloads/).

- **Maven** is needed to build the project, download [here](https://maven.apache.org/download.cgi).

- The database is manage on **MySQL**, download [here](https://dev.mysql.com/downloads/installer/).

## Getting Started

### 1. Clone the github repository

> git clone https://github.com/VelosoTony/Developpez-une-application-full-stack-complete.git

### 2. Create MySQL Database

Start MySQL, connect to MySQL Command Line or GUI tool.

Run the script below to create the **mdd** database.

> resources\sql\complete_orion_mdd.sql

## Back-End

### Set the credential of database access of API

declare system environment variables to set database access parameters :

> `MYSQL_HOST` : hostname of the MySQL Server (defaut: localhost).

> `MYSQL_USERNAME` : username account of MySQL Server.

> `MYSQL_PASSWORD` : password of MySQL Server user.

### Start the API using maven

> `mvn spring-boot:run`

_**&tritime; Warning : to run this command you should be in the directory "\back" containing the file "pom.xml". &tritime;**_

## Front-End

_**&tritime; Warning : to run following commands, you should be in the directory "\front". &tritime;**_

Install your node_modules :

> `npm install`

Launch :

> `ng serve`

Navigate to `http://localhost:4200/`

### Build

To build the project, run this command:

> `ng build`

The build artifacts will be stored in the `dist/` directory.

## Documentation

### Javadoc documentation is available in :

> back\target\site\apidocs\index.html

### Swagger Open API documentation is available (when the api is running) :

> http://localhost:8080/swagger-ui/index.html#/

## Resources

A postman collection could be use to test the API.

> resources\postman\MDD.postman_collection.json

A Mockoon configuration could be use to simulate the API.

> resources\mockoon\mdd_mockoon.json
