
# Hostel Management System

This repository contains the code and documentation for the **Hostel Management System**, a web-based application designed to streamline the management of student hostels. It offers robust features for students and administrators, ensuring efficient handling of room allocations, fee payments, and user data.

## Project Overview

The Hostel Management System automates various administrative tasks related to hostel management. The system features separate user and admin interfaces for access control and user convenience. Key functionalities include room allocation, fee management, and real-time updates of hostel-related data.

## Features

### Student Features
- **Room Booking**: Students can book rooms based on availability.
- **Profile Management**: Update personal details and view booking history.
- **Fee Payment**: Make and track payments online.

### Admin Features
- **Room Management**: Add, edit, and delete room details.
- **Student Management**: View, update, and manage student information.
- **Payment Tracking**: Monitor payments and generate reports.
- **Staff Management**: Add, update, and manage staff details.

## Technologies Used

- **Frontend**: HTML, CSS, JavaScript
- **Backend**: Java (Servlets, JSP), Spring Framework
- **Database**: MySQL
- **Architecture**: Model-View-Controller (MVC)

## System Objectives

1. Ensure high-level data protection for students and staff.
2. Simplify the hostel booking process for students.
3. Assist in managing and tracking student fee payments.
4. Automate room allocation based on availability.

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/<your-username>/hostel-management-system.git
   ```
2. Import the project into your IDE (e.g., IntelliJ, Eclipse).
3. Configure the database connection in the `application.properties` file.
4. Run the application using your local server (e.g., Tomcat).

## Database Schema

The project uses the following tables:

- **Room**: Stores room details (e.g., ID, floor, capacity).
- **Student**: Stores student details (e.g., ID, name, email).
- **Staff**: Stores staff details (e.g., ID, role, contact).
- **Allocation**: Tracks room allocations for students.
- **Payment**: Manages payment records.

### Example SQL DDL
```sql
CREATE TABLE room (
    room_id INT PRIMARY KEY,
    room_number VARCHAR(50),
    floor INT,
    capacity INT,
    is_available BOOLEAN
);
```

## Screenshots

### Student Dashboard
![Student Dashboard](path/to/student-dashboard-screenshot.png)

### Admin Panel
![Admin Panel](path/to/admin-panel-screenshot.png)

## Future Enhancements

1. Integration with payment gateways for secure transactions.
2. Mobile-friendly design for better accessibility.
3. Enhanced analytics for hostel performance tracking.

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.

## Acknowledgments

- **Lecturer**: Gloria Jennis Tan
- **Team Members**:
  - Muhammad Firdaus Bin Rosli
  - Muhammad Aiman Bin Kamarudin
  - Mohamad Hairil Bin Anuar
  - Izzat Zarif Bin Mohd Zamri
