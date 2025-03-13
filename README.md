**InterviewAssignment**


****Blog API (Spring Boot + MySQL + OpenAI)****



A simple Spring Boot REST API for managing blogs with CRUD operations and AI-powered summarization.



**Features**


CRUD operations – Create, Read, Update, Delete Blogs

AI-powered summarization – Uses OpenAI API

Database – MySQL Integration

Dockerized – Ready for deployment

Hosted on AWS EC2 – Scalable & secure


**Prerequisites**

Ensure you have installed:


Java 17+


Spring Boot


MySQL


Docker & Docker Compose


Postman (for API testing)


**Clone Repository**

git clone https://github.com/your-username/blog-api.git
cd blog-api




**Configure MySQL Database**

Modify application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/blog_db
spring.datasource.username=root
spring.datasource.password=yourpassword

application-example.properties


# Database Configuration  
spring.datasource.url=jdbc:mysql://localhost:3306/blog_db  
spring.datasource.username=root  
spring.datasource.password=yourpassword  

# JPA Properties  
spring.jpa.hibernate.ddl-auto=update  
spring.jpa.show-sql=true  
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect  

# OpenAI API Configuration (if used)  
openai.api.key=your-api-key  

# Server Configuration  
server.port=8080  





**Create the database:**

CREATE DATABASE blog_db;



**Run Locally**

mvn clean install


mvn spring-boot:run









**Method       Endpoint	               Description	           Body (JSON)**

GET	       /api/blogs      	       Get all blogs	        None

GET	       /api/blogs/{id}	       Get a blog by ID	      None

POST	     /api/blogs         	   Create a blog	       { "title": "My Blog", "content": "Blog content" }

PUT	       /api/blogs/{id}	       Update a blog	       { "title": "Updated Title", "content": "Updated content" }

DELETE	   /api/blogs/{id}	       Delete a blog	        None

POST	     /api/blogs/summarize	   Summarize content	   { "content": "Long text to summarize" }



**Technologies Used**


Spring Boot – Backend API


MySQL – Database


OpenAI API – AI-powered summarization


Docker & Docker Compose – Containerization


