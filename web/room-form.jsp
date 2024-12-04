<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Hostel Management</title>
    <link rel="stylesheet" href="css/Add room.css">
    <style>
        .no-line {
            border-bottom: none;
        }
        .active,
         a:hover {
            background-color: #f5f5f5;
            transition: all 0.6s;
           
    }
    
/*    ado room number,floor,capacity ,availibility,hostelname*/
    </style>
</head>
<body>
<span class="bckg"></span>
<header>
    <h1>HOSTEL MANAGEMENT</h1>
    <link rel="stylesheet" href="css/Add room.css">
    <nav>
                <li class="active"><a href="staff-dashboards.jsp">DASHBOARD</a></li>
                <ul>
                    <br><br><br>
                    <li><a>FEATURES</a></li>
                    <li><a href="room-details.jsp">ALL ROOMS</a></li>
                    <li><a href="room-form.jsp">ADD ROOMS</a></li>
                    <li><a href="payment-details.jsp">ALL PAYMENTS</a></li>
                    <li><a href="students.jsp">ALL STUDENTS</a></li>
                    <li><a href="add-staff.jsp">ADD STAFF</a></li>
                    <span><br><br></span>
                    <li><a href="index.jsp">LOG OUT</a></li>
                </ul>
    </nav>
</header>
<main>
    <div class="title">
        <h2></h2>
        <a href="javascript:void(0);" class="no-line">Hello</a>
    </div>
    <body>
    <br>
    <h2>Add Room</h2>
    <br>
    <div class="form-container">
    <form action="room-form" method="POST">
    <label for="room-number">Room Number:</label>
    <input type="text" id="roomNumber" name="roomNumber" required>
    
    <label for="floor">Floor:</label>
    <input type="number" id="floor" name="floor" required>
    
    <label for="capacity">Capacity:</label>
    <input type="number" id="capacity" name="capacity" required><br>

    <label for="isAvailable">Is Available:</label>
    <input type="checkbox" id="isAvailable" name="isAvailable" value="true"><br>
    
    <label for="hostelId">Hostel:</label>
    <select id="hostelId" name="hostelId" required>
        <option value="">Select Hostel</option>
        <option value="1">Kasa</option>
        <option value="2">Sutera</option>
    </select><br>
    
    <input type="submit" value="Add">
  </form>
</div>
    </body>
</main>
</body>
</html>
