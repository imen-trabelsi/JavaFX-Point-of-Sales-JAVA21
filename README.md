
# JavaFX-Point-of-Sales-JAVA21
This project is based on [OriginalProject](https://github.com/original-owner/original-repo), created by Rafsanjani. 

## What’s New
- Updated to support Java 21.
- Fixed deprecated APIs and compatibility issues.
## How to Start the Project

### Prerequisites
1. **Install Java 21**
   - Download and install [Java 21](https://www.oracle.com/java/technologies/javase-downloads.html).
   - Ensure `JAVA_HOME` is set correctly.

2. **Install Maven**
   - Download and install [Maven](https://maven.apache.org/).

3. **Install MySQL**
   - Download and install [MySQL](https://www.mysql.com/).
   - Start the MySQL server.

---

### Steps to Set Up and Start the Project

1. **Set Up the MySQL Database**
   - Log in to MySQL:
     ```bash
     mysql -u root -p
     ```
   - Create the `inventory` database:
     ```sql
     CREATE DATABASE inventory;
     ```
   - Use the `inventory` database:
     ```sql
     USE inventory;
     ```

2. **Import the Database**
   - Navigate to your project directory in the terminal.
   - Run the following command to import the database file:
     ```bash
     mysql -u root -p inventory < inventory.sql
     ```
   - Verify the import:
     ```sql
     SHOW TABLES;
     ```

3. **Configure Hibernate**
   - Open `hibernate.cfg.xml` and set your MySQL username and password.

4. **Run the Application**
   - Use Maven to start the project:
     ```bash
     mvn javafx:run
     ```


## Licence
The MIT License (MIT)

Copyright (c) 2024 Rafsanjani
Copyright (c) 2025 Imen Trabelsi

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
