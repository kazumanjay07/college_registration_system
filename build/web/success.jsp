<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Room Inserted Successfully</title>
    <style>
        /* Style the modal background overlay */
        .modal-overlay {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.5);
            display: flex;
            justify-content: center;
            align-items: center;
            z-index: 9999; /* Ensure the modal appears on top */
        }

        /* Style the modal content */
        .modal-content {
            background-color: #f2f2f2;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
            text-align: center;
        }
    </style>
</head>
<body>
    <div class="modal-overlay">
        <div class="modal-content">
            <h1>Room Inserted Successfully</h1>

            <p>Room Number: ${room.roomNumber}</p>
            <p>Floor: ${room.floor}</p>
            <p>Capacity: ${room.capacity}</p>
            <p>Availability: ${room.available}</p>
            <p>Hostel ID: ${room.hostelId}</p>
        </div>
    </div>

    <script>
        // Show the modal popup after the page is loaded
        window.addEventListener('DOMContentLoaded', function() {
            var modalOverlay = document.querySelector('.modal-overlay');
            modalOverlay.style.display = 'flex';
        });
    </script>
</body>
</html>
