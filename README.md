# Specification
A Kazakh cuisine restaurant offers a unique dining experience that showcases the delicious and flavorful food of the Kazakh people. Kazakh cuisine is a fusion of various cooking traditions from Central Asia, Europe, and the Middle East, resulting in a rich and diverse menu. The user will have to be able to order food and book the seats in the restaurant, and all his orders will have to be stored in the database. The project built with Spring Boot, it leverages a relational database for data storage.
# Complex query
- Choose all orders that have dishes that cost more than 3 euro, but total cost of order is under 15 euro.

# Business logic
- If a client is deleted from the table, his reservation and orders will also be deleted due to uselessness.

# Prerequisites
To build and run this project, the following have to be installed:

- Java JDK (Version 17 or higher)

- Gradle (compatible with the version specified in your build.gradle)

- A relational database (PostgreSQL recommended, H2 for testing)

# Setup and Running
- Database Setup
Install and configure your chosen database.
Update src/main/resources/application.properties with the database connection details.

# Using the API
The API provides endpoints for managing menus and orders. Example requests:

Menu Management
Create a Menu

Method: POST
Endpoint: /menus
Body:

{
  "name": "New Menu",
  "price": 150
}
- Get a Menu

Method: GET
Endpoint: /menus/{menuId}


