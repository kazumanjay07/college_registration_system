package com.dao;

import com.model.Staff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;


public class StaffDAOLogin {
    private Connection connection;

    public StaffDAOLogin(Connection connection) {
        this.connection = connection;
    }

    public Staff getStaffByIdAndPassword(String staffId, String password) throws SQLException {
        String query = "SELECT * FROM staff WHERE staff_id = ? AND staff_password = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, staffId);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("staff_id");
                    String name = rs.getString("staff_name");
                    String pass = rs.getString("staff_password");
                    String gender = rs.getString("staff_gender");
                    String email = rs.getString("staff_email");
                    String phone = rs.getString("staff_phone");
                    String address = rs.getString("staff_address");
                    Date dob = rs.getDate("staff_dob");

                    Staff staff = new Staff(id, pass, name, gender, email, phone, address, dob);
                    return staff;
                }
            }
        }

        return null;
    }
    
    public void addStaff(Staff staff) throws SQLException {
        String query = "INSERT INTO staff (staff_id, staff_password, staff_name, staff_gender, staff_email, staff_phone, staff_address, staff_dob) " +
                       "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, staff.getStaffId());
            pstmt.setString(2, staff.getStaffPassword());
            pstmt.setString(3, staff.getStaffName());
            pstmt.setString(4, staff.getStaffGender());
            pstmt.setString(5, staff.getStaffEmail());
            pstmt.setString(6, staff.getStaffPhone());
            pstmt.setString(7, staff.getStaffAddress());
            pstmt.setDate(8, new java.sql.Date(staff.getStaffDOB().getTime()));

            pstmt.executeUpdate();
        }
    }

    
    // Add other methods as needed
    
}