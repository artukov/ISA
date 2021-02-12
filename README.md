# ISA

## Contributors

GitHub account | Name and surname
------------ | -------------
@panta246 | Stefan Pantic
@gitSrdzaan | Srdjan Simic
@artukov | Nikola Artukov

## Pharmacy web application

**pharmacyapp BACK-END folder**

  * Spring Boot framework
  * Apache Maven
  * PostgreSQL DBMS

**front/pharmacy-app FRONT-END folder**
  
  * Node.js
  * React library
  * React-bootstrap
  * Bootstrap
  
**Other folder and files**

  * isa-podjela-rada.txt - description of what funcionalties each teammember got, defined by the given specification  
  * ISA-PROJEKAT-MODEL-20_21 - folder that contains class diagram of the project, tool used Sybase PowerDesigner15

## Starting up application

***pharmacy-app***

 * npm install
 * npm start
 
***pharmacyapp***

 - from console or bash
   - mvn clean compile install
   - move to pharmacyapp/target folder
   - java -jar pharmacyapp-0.0.1-SNAPSHOT
  
* run application from IDE

## Manual application

***pharmacy-app***
 *every input filed must be changed(if placeholder shows anything, You still must input something)
 *if the changes of some CRUD operations does not show immediately, You can reload the page
 *searching through list both fields for firstname and lastname must be inserted
 *when sorting You can click on the column name (example: patients list, column patient fisrtname)
 *when adding drugs to appointment or supply order, you must select drug than click on a button to add it to the list
 *when as a pharmacy admin You are generating a report for pharmacy's incomes, choose lower date limit and upper date limit, the result will be calculated based on the month and year of the limits
 *when a user registers, he/she must go to their mail and click on the registration link

***pharmacyapp***

 *create in PostgreSQL database a database with a name pharmacy, user's username is postgres and password is postgre. If you want to change it you must do it in the application.properties file
 *email account for sending emails is also defined in application.properties


