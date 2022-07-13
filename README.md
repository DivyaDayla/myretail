# RetailStoreApp
Case Study to build an application for a Retail Store.
"myRetail" is a rapidly growing company with HQ in Richmond, VA and over 200 stores across the east coast. myRetail wants to make its internal data available to any number of client devices, from myRetail.com to native mobile apps. 
## Goal
The goal for this exercise is to create an end-to-end Proof-of-Concept for a products API, which will aggregate product data from multiple sources and return it as JSON to the caller. 
Build an application that performs the following actions: 
* Responds to an HTTP GET request at /products/{id} and delivers product data as JSON (where {id} will be a number. 
  Example product IDs: 13860428, 54456119, 13264003, 12954218) 
  Example response: {"id":13860428,"name":"The Big Lebowski (Blu-ray) (Widescreen)","current_price":{"value": 13.49,"currency_code":"USD"}}
*	Performs an HTTP GET to retrieve the product name from an external API. (For this exercise the data will come from redsky.target.com, but let’s just pretend this is an internal resource hosted by myRetail) 
  Example:https://redsky-uat.perf.target.com/redsky_aggregations/v1/redsky/case_study_v1?key=3yUxt7WltYG7MFKPp7uyELi1K40ad2ys&tcin=13860428
*	Reads pricing information from a NoSQL data store and combines it with the product id and name from the HTTP request into a single response. 
*	(BONUS): Accepts an HTTP PUT request at the same path (/products/{id}), containing a JSON request body similar to the GET response, and updates the product’s price in the data store. 

## Technologies Used
 * Java 11, Spring Boot, MongoDB Cloud (Mongo Db Atlas), Maven, IntelliJ
## Project Setup
 * Import the project using any IDE or Clone the project into a folder.
 * Add Maven Framework
 * Clean the project and then build.
 
## Test Data
 * Create an account in MongoDB Cloud
 * Create Database and Collection
 * Generate Test data using 'Mockaroo.com'
 * Install MongoDb Tools and Import test data file using the command "mongoimport --uri mongodb+srv://<<URI> --collection <COLLECTION> --type <FILETYPE> --file <FILENAME>"
    
## Postman Collection
* File - Import myretail_postman_collection.json file (src/test/resources) into Postman
* Link - https://www.postman.com/collections/8126059139a588ad83f4
 
## Other Tools Used
Mongo Shell, Mongo Tools, Mockaroo


