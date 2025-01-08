
# JavaFX-Point-of-Sales-JAVA21
This project is based on [OriginalProject](https://github.com/original-owner/original-repo), created by Rafsanjani. 

## What’s New
- Updated to support Java 21.
- Fixed deprecated APIs and compatibility issues.

## How to Start the Project
- Make sure you have [Java 21](https://www.oracle.com/java/technologies/javase-downloads.html)
- Install [Maven](https://maven.apache.org/) 
- Install [MySQL](https://www.mysql.com/)
- Start MySQL server
- Log in to MySQL: mysql -u root -p
- create inventory database: CREATE DATABASE inventory;
- Use the inventory Database: USE inventory;
- Navigate to your project directory in the terminal
- Run the following command to import the database file: mysql -u root -p inventory < inventory.sql
- Verify the Import: Log in to MySQL and check the tables: SHOW TABLES;
- setup you password in hibernate.cfg.xml
- Run your application: mvn javafx:run


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
