package com.dao;

import java.sql.*;

public class StaffDashboardDAO {
    private Connection conn;

    public StaffDashboardDAO() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hostelmanagement2", "root", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getRegisteredStudentsCount() {
        int registeredStudentsCount = 0;
        try {
            Statement stmt = conn.createStatement();
            ResultSet registeredStudentsResult = stmt.executeQuery("SELECT COUNT(*) AS total_students FROM student");
            if (registeredStudentsResult.next()) {
                registeredStudentsCount = registeredStudentsResult.getInt("total_students");
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registeredStudentsCount;
    }

    public int getTotalRoomsCount() {
        int totalRoomsCount = 0;
        try {
            Statement stmt = conn.createStatement();
            ResultSet totalRoomsResult = stmt.executeQuery("SELECT COUNT(*) AS total_rooms FROM room");
            if (totalRoomsResult.next()) {
                totalRoomsCount = totalRoomsResult.getInt("total_rooms");
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalRoomsCount;
    }

    public int getBookedRoomsCount() {
        int bookedRoomsCount = 0;
        try {
            Statement stmt = conn.createStatement();
            ResultSet bookedRoomsResult = stmt.executeQuery("SELECT COUNT(*) AS booked_rooms FROM allocation");
            if (bookedRoomsResult.next()) {
                bookedRoomsCount = bookedRoomsResult.getInt("booked_rooms");
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookedRoomsCount;
    }

    public int getAvailableRoomsCount() {
        int totalRoomsCount = getTotalRoomsCount();
        int bookedRoomsCount = getBookedRoomsCount();
        return totalRoomsCount - bookedRoomsCount;
    }

    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
