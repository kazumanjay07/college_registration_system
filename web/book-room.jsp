<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
    <h1>Welcome, Student ID: ${studentId}</h1>
    
    <h2>Allocation Details:</h2>
    <c:if test="${allocation ne null}">
        <p>Room Number: ${allocation.room.roomNumber}</p>
        <p>Check-In Date: ${allocation.checkInDate}</p>
    </c:if>
    
    <a href="logout">Logout</a>
</body>
</html>
