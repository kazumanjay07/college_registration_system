package com.dao;

import com.model.Staff;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class StaffDAO {
    private final String url = "jdbc:mysql://localhost:3306/hostelmanagement2";
    private final String username = "root";
    private final String password = "";
    private Connection connection;

    public StaffDAO() {
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as per your requirement
        }
    }

    public List<Staff> getAllStaff() {
        List<Staff> staffList = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM staff");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int staffId = rs.getInt("staff_id");
                String staffName = rs.getString("staff_name");
                String staffGender = rs.getString("staff_gender");
                String staffEmail = rs.getString("staff_email");
                String staffPhone = rs.getString("staff_phone");
                String staffAddress = rs.getString("staff_address");

                Staff staff = new Staff(staffId, null, staffName, staffGender, staffEmail, staffPhone, staffAddress, null);
                staffList.add(staff);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return staffList;
    }

    public Staff getStaffById(int staffId) {
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM staff WHERE staff_id = ?")) {
            stmt.setInt(1, staffId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String staffName = rs.getString("staff_name");
                    String staffGender = rs.getString("staff_gender");
                    String staffEmail = rs.getString("staff_email");
                    String staffPhone = rs.getString("staff_phone");
                    String staffAddress = rs.getString("staff_address");

                    return new Staff(staffId, null, staffName, staffGender, staffEmail, staffPhone, staffAddress, null);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void addStaff(Staff staff) {
        try (PreparedStatement stmt = connection.prepareStatement(
                "INSERT INTO staff (staff_id, staff_password, staff_name, staff_gender, staff_email, staff_phone, staff_address, staff_dob) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {

            stmt.setInt(1, staff.getStaffId());
            stmt.setString(2, staff.getStaffPassword());
            stmt.setString(3, staff.getStaffName());
            stmt.setString(4, staff.getStaffGender());
            stmt.setString(5, staff.getStaffEmail());
            stmt.setString(6, staff.getStaffPhone());
            stmt.setString(7, staff.getStaffAddress());
            stmt.setDate(8, new java.sql.Date(staff.getStaffDOB().getTime()));

            stmt.executeUpdate();

            System.out.println("Staff added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Staff updateStaff(Staff staff) {
        try (PreparedStatement stmt = connection.prepareStatement(
                "UPDATE staff SET staff_name = ?, staff_email = ?, staff_phone = ?, staff_address = ? WHERE staff_id = ?")) {

            stmt.setString(1, staff.getStaffName());
            stmt.setString(2, staff.getStaffEmail());
            stmt.setString(3, staff.getStaffPhone());
            stmt.setString(4, staff.getStaffAddress());
            stmt.setInt(5, staff.getStaffId());

            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Staff updated successfully.");
                return staff;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Return null if the update was not successful
    }


    public void deleteStaff(int staffId) {
        try (PreparedStatement stmt = connection.prepareStatement("DELETE FROM staff WHERE staff_id = ?")) {
            stmt.setInt(1, staffId);
            stmt.executeUpdate();

            System.out.println("Staff deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Staff loginStaff(int staffID, String password) {
        Staff staff = null;
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM staff WHERE staff_id = ? AND staff_password = ?")) {
            stmt.setInt(1, staffID);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String staffName = rs.getString("staff_name");
                    String staffPassword = rs.getString("staff_password");
                    String staffPhone = rs.getString("staff_phone");
                    String staffEmail = rs.getString("staff_email");
                    String staffAddress = rs.getString("staff_address");
                    Date staffDOB = rs.getDate("staff_dob");

                    staff = new Staff(staffID, staffPassword, staffName, staffEmail, staffPhone, staffAddress, staffDOB);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return staff;
    }


    
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
