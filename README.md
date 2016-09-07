Interview Repo for MFG

# To reply this interview please make a pull request on this repo

# Task to do
* Create a object Good with the following attributes that will be saved in mongoDB:
  * name: the name of the Good, this name must be unique, not null and no more than 50 characters
  * age: the age of the Good, must not be under zero
  * productionDate: the date the Good was product
* Create API to manage the Good object:
  * GET /api/good : get the list of 10 most recent Goods, then can access next one by /api/good?page=1&size=10 
  * POST /api/good: post by json in body a new Good to create it
  * PUT /api/good: put by json in body an existing Good to update it
  * DELETE /api/good/{id}: delete a Good by it's id
  * GET /api/good/{id}: get a good by it's id

# Run this application

This application is simple, for more work this should contain security, REST api specification and so.
Also the unit test is not done here, sorry for poor time.

## Prepare mongodb

You must install mongodb and create database before run this application, for test purpose the recommdation is using
docker. Mongodb 3.x is required.

## Compile

By default, this application uses port 80, you can change it in application.properties.

Run the follow command in the source root folder to compile:

	mvn clean compile package

## Run

For this application is developed by Spring boot, you can just run it from the jar:

	java -jar InterviewMFG-0.0.1-SNAPSHORT.jar

# Using swagger to test the REST api visually

Swagger is an excellent tool for REST api management. For more information about swagger, click [here](http://swagger
.io/). Also you can use curl to test the api, just choose what you like.


- In your browser, open the url: http://localhost/swagger-ui.html
- After page load finished, you wil see the good endpoint, that's it
- Every api operation is listed in the page, also they are easy to understand
