# Cinema Reservation System (JSP & Servlet)

## Project Overview
The **Cinema Reservation System** is a 3-tier Java web application using **Servlets**, **JSP**, and **MySQL** for managing ticket reservations. Users can browse movies and make reservations, while admins manage listings and bookings. The system follows the **MVC** architecture and integrates with a **MySQL** database.

## Features
- Browse available movies
- Make reservations as a customer
- Admin section for managing movies and reservations
- User authentication (customer and admin)
- MySQL database integration

## Project Structure

- **src/main/java**: Contains Java source files, including:
  - `controller`: Handles various user actions via Servlets.
  - `Cinema`: Model classes representing entities such as `Movies`, `Reservations`, etc.
  
- **src/main/webapp**: Contains web resources:
  - JSP files for different pages (e.g., `index.jsp`, `reservationCustomer.jsp`, etc.)
  - CSS and JavaScript files

- **WEB-INF/lib**: Includes required libraries:
  - `mysql-connector-j`: MySQL JDBC driver for database connection
  - `jsp-api`, `servlet-api`: Required for JSP and Servlet functionality
  
- **Database**: The file `cinemaDB2.mwb` (MySQL Workbench format) contains the database schema.

## Prerequisites
To run this project, ensure you have:
- Java JDK 8 or higher
- Apache Tomcat 9 or higher
- MySQL database server
- IDE such as Eclipse (recommended) or IntelliJ
- MySQL Workbench (optional, for database schema)

## Setup Instructions

1. **Clone the Repository**:  
   Download or clone the project to your local machine.

2. **Set Up MySQL Database**:
   - Open `cinemaDB2.mwb` in MySQL Workbench.
   - Execute the SQL script to create the database schema.
   - Configure database connection settings in the project.

3. **Import the Project into Eclipse**:
   - Import the project into Eclipse using `File -> Import -> Existing Projects into Workspace`.
   - Ensure all required libraries are properly configured.

4. **Configure Apache Tomcat**:
   - Add the project to the Tomcat server in Eclipse.
   - Set the runtime environment to your Tomcat installation.

5. **Run the Application**:
   - Start Tomcat and access the app at `http://localhost:8080/java-jsp-servlet-example`.

## Admin Login
Set up the admin login credentials directly in the MySQL database during setup.
