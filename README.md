**INTRODUCTION**
This project aims to create a multi-tenant ecommerce website using Java Spring Boot, Lombok, Spring Data JPA, and MySQL. The system will be designed as a collection of microservices, each responsible for specific functionalities of the ecommerce platform. The use of microservices architecture enables modularity, scalability, and easier maintenance of the system.

**FEATURES**
Multi-Tenancy: The platform will support multiple tenants, each with its own separate database schema. This allows for isolation of data and configurations between different tenants.

1. User Management: The system will provide user authentication and authorization functionalities, allowing users to register, log in, and manage their accounts.

2. Product Catalog: Each tenant will have its own product catalog, allowing them to add and update products. Customers can browse through the catalog, view product details, and make purchases.

3. Category Catalog: Each tenant will have its own category catalog, allowing them to add and update catalog. Customers can browse through the catalog, view catalog details, and add products to category..

4. Transaction Management: The platform will support transaction management functionalities, allowing users to sell their products, track status by transactionDate,product item and batch number.

**TECHNOLOGIES USED**
1. Java Spring Boot: For building microservices and handling HTTP requests.
2. Lombok: To reduce boilerplate code and improve code readability.
3. Spring Data JPA: For interacting with the MySQL database and performing CRUD operations.
4. MySQL: As the relational database management system for storing data.
5. Spring Security: For user authentication and authorization.
6. Thymeleaf: For server-side templating to generate dynamic HTML pages.
7. RESTful APIs: For communication between microservices and external clients.


**Postman Collection:** https://grey-robot-178198.postman.co/workspace/My-Workspace~ed0d19f7-5398-4b7d-81fe-206eca557a6c/folder/27808364-a7a68ed9-041c-44d8-ad6b-3e5af26c1468?action=share&creator=27808364&ctx=documentation

**SETUP INSTRUCTION**
Clone the repository to your local machine.
Ensure that Java and Maven are installed on your system.
Set up MySQL and create a database for the ecommerce platform.
Configure the database connection properties in the application.properties file.
Build and run each microservice using Maven or your preferred IDE.

**USAGE**
Access the web interface through the provided URL.
Register an account or log in if you already have one.
Browse through the product catalog, add items to your shopping cart, and proceed to checkout.
Make payments securely using integrated payment gateways.
Administrators can access the admin dashboard to manage tenant accounts, view analytics, and perform administrative tasks.


**Acknowledgements**
Special thanks to the developers of Java Spring Boot, Lombok, Spring Data JPA, and MySQL for providing the tools and technologies necessary to build this ecommerce platform.

**Team Members**
Zikrulahi Atobatele - (Team Lead),
Uzoma Nwachukwu, 
Tokunbo Ogunjobi, 
Victor Otubure, 
Samuel Onyedikachi - (PM)


