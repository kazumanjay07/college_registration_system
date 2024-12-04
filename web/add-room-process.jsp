<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.model.Room" %>
<%@ page import="com.model.Hostel" %>
<%@ page import="com.dao.RoomDAO" %>
<%
    String roomNumber = request.getParameter("roomNumber");
    int floor = Integer.parseInt(request.getParameter("floor"));
    int capacity = Integer.parseInt(request.getParameter("capacity"));
    boolean isAvailable = Boolean.parseBoolean(request.getParameter("isAvailable"));
    int hostelId = Integer.parseInt(request.getParameter("hostelId"));

    Hostel hostel;
    if (hostelId == 1) {
        hostel = new Hostel(1, "Kasa", "Kasa Address", "Kasa Phone");
    } else if (hostelId == 2) {
        hostel = new Hostel(2, "Sutera", "Sutera Address", "Sutera Phone");
    } else {
        // Handle the case where an invalid hostel ID is received
        // You can throw an exception or handle it according to your application's logic
        // Here, we set a default hostel if the ID is invalid
        hostel = new Hostel(1, "Kasa", "Kasa Address", "Kasa Phone");
    }

    Room room = new Room(roomNumber, floor, capacity, isAvailable, hostel);

    RoomDAO roomDAO = new RoomDAO();
    roomDAO.addRoom(room);
    roomDAO.close();
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Room Process</title>
    <!-- Include SweetAlert CSS -->
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.0.17/dist/sweetalert2.min.css">
</head>
<body>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.0.17/dist/sweetalert2.all.min.js"></script>
    <script>
        Swal.fire({
            title: 'Success',
            text: 'Room added successfully',
            icon: 'success',
            confirmButtonText: 'OK'
        }).then(function() {
            window.location.href = 'room-details.jsp';
        });
    </script>
</body>
</html>
