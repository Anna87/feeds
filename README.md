# Feeds

The Goal of this application is to be able to distribute data from different feeds including images to clients.
This is Java application that polls a news feed in a freely configurable interval and stores the data in an SQL database.


## Prerequisites

* MySql database version 8.0.21 or higher.
* Java 9 or higher.
* Operating system: no specific requirements (tested on Windows and Linux).

## Setup

* Create a new database<br/>
    ``mysql> create database feeds; -- Creates the new database``
    ``mysql> create user 'admin' identified by 'admin'; -- Creates the user``
    ``mysql> mysql> grant select, insert, delete, update on feeds.* to 'admin'; -- Gives privileges for new user on the newly created database``
    
* Execute jar file<br/>
download jar file https://github.com/Anna87/feeds/blob/master/target/feeds-0.0.1-SNAPSHOT.jar and execute next command:
    ``java -jar feeds-0.0.1-SNAPSHOT.jar``
    
## How to use

Now application is running and you can use it by using postman or some similar tool. You have two HTTP endpoints that you can use:

``GET http://localhost:8080/api/articles?page=1``

``http://localhost:8080/api/images/{id}/download`` where "id" is Image ID from database Images