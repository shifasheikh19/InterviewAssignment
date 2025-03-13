# InterviewAssignment
#  Blog API (Spring Boot + MySQL + OpenAI)

A simple **Spring Boot REST API** for managing blogs with CRUD operations and AI-powered summarization.

---

##  Features
**CRUD operations** – Create, Read, Update, Delete Blogs  
**AI-powered summarization** – Uses OpenAI API  
**Database** – MySQL Integration  
**Dockerized** – Ready for deployment  
**Hosted on AWS EC2** – Scalable & secure  

### 1️⃣ Prerequisites  
Ensure you have installed:
- **Java 17+**
- **Spring Boot**
- **MySQL**
- **Docker & Docker Compose**
- **Postman** (for API testing)


Method       Endpoint	               Description	           Body (JSON)

  
GET	       /api/blogs      	       Get all blogs	        None


GET	       /api/blogs/{id}	       Get a blog by ID	      None


POST	     /api/blogs         	   Create a blog	       { "title": "My Blog", "content": "Blog content" }


PUT	       /api/blogs/{id}	       Update a blog	       { "title": "Updated Title", "content": "Updated content" }


DELETE	   /api/blogs/{id}	       Delete a blog	        None


POST	     /api/blogs/summarize	   Summarize content	   { "content": "Long text to summarize" }

Technologies Used
Spring Boot – Backend API
MySQL – Database
OpenAI API – AI-powered summarization
Docker & Docker Compose – Containerization
