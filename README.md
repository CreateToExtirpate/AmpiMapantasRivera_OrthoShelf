AmpiMapantasRivera_OrthoShelf  
**Finals Submission for Object-Oriented Programming**

## How to Run the Application

### Prerequisites
- **Java Development Kit (JDK) 21** installed and added to your system's environment variables
- **XAMPP** installed and running (for MySQL and phpMyAdmin)
- **Download_These** folder containing:
  - `Orthoshelf-0.0.1-SNAPSHOT.jar`
  - `OrthoShelf_DDL_WithDummyData.sql`

### Setup Instructions

1. **Start XAMPP**
   - Launch XAMPP and start the **MySQL** and **Apache** modules.
   - Open **phpMyAdmin** in your browser (`http://localhost/phpmyadmin`).

2. **Import the Database**
   - In phpMyAdmin, go to the **Import** tab.
   - Select and import `OrthoShelf_DDL_WithDummyData.sql`.
   - Confirm that a database named `orthoshelf_db` is created.

3. **Run the Application**
   - Open a terminal and navigate to the folder containing `Orthoshelf-0.0.1-SNAPSHOT.jar`.
   - Run the application using:
     ```
     java -jar Orthoshelf-0.0.1-SNAPSHOT.jar
     ```

4. **Access the Web App**
   - Open your browser and go to:  
     [http://localhost:8080/](http://localhost:8080/)

### Notes
- Ensure MySQL is running while the application is active.
- If you encounter port conflicts, check that no other services are using port `8080`.

