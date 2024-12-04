package com.dao;

import com.model.Room;
import com.model.Student;
import com.model.Allocation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.Types;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class StudentDAO {
    private final String url = "jdbc:mysql://localhost:3306/hostelmanagement2";
    private final String username = "root";
    private final String password = "";
    private Connection connection;

    public StudentDAO() {
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as per your requirement
        }
    }

    public List<Student> getAllStudentsWithRooms() {
        List<Student> students = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(
                "SELECT s.student_id, s.stud_name, s.stud_phone, s.stud_email, r.room_number " +
                "FROM student s " +
                "INNER JOIN allocation a ON s.student_id = a.student_id " +
                "INNER JOIN room r ON a.room_id = r.room_id");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int studentId = rs.getInt("student_id");
                String studentName = rs.getString("stud_name");
                String studentPhone = rs.getString("stud_phone");
                String studentEmail = rs.getString("stud_email");
                String roomNumber = rs.getString("room_number");

                Room room = new Room(roomNumber);
                Student student = new Student(studentId, studentName, studentPhone, studentEmail, room);
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }

    public void deleteStudent(int studentId) {
        try (PreparedStatement stmt = connection.prepareStatement("DELETE FROM student WHERE student_id = ?");
             PreparedStatement allocationStmt = connection.prepareStatement("DELETE FROM allocation WHERE student_id = ?")) {

            // Set the student ID parameter
            stmt.setInt(1, studentId);

            // Delete the student record
            stmt.executeUpdate();

            // Delete the related allocation records
            allocationStmt.setInt(1, studentId);
            allocationStmt.executeUpdate();

            System.out.println("Student and related allocation deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM student");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int studentId = rs.getInt("student_id");
                String studentName = rs.getString("stud_name");
                String studentPhone = rs.getString("stud_phone");
                String studentEmail = rs.getString("stud_email");

                Student student = new Student(studentId, studentName, studentPhone, studentEmail);
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }

    public Student getStudentById(int studentId) {
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM student WHERE student_id = ?")) {
            stmt.setInt(1, studentId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String studentName = rs.getString("stud_name");
                    String studentPhone = rs.getString("stud_phone");
                    String studentEmail = rs.getString("stud_email");

                    return new Student(studentId, studentName, studentPhone, studentEmail);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void updateStudent(Student student) {
        try (PreparedStatement stmt = connection.prepareStatement(
                "UPDATE student SET stud_name = ?, stud_phone = ?, stud_email = ? WHERE student_id = ?")) {

            stmt.setString(1, student.getStudentName());
            stmt.setString(2, student.getStudentPhone());
            stmt.setString(3, student.getStudentEmail());
            stmt.setInt(7, student.getStudentId());

            stmt.executeUpdate();

            System.out.println("Student updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Student loginStudent(int studentId, String password) {
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM student WHERE student_id = ? AND stud_password = ?")) {
            stmt.setInt(1, studentId);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String studentName = rs.getString("stud_name");
                    String studentPhone = rs.getString("stud_phone");
                    String studentEmail = rs.getString("stud_email");
                    Date studentDOB = rs.getDate("stud_dob");
                    String studentAddress = rs.getString("stud_address");
                    String studentPassword = rs.getString("stud_password");

                    Student student = new Student(studentId, studentName, studentPhone, studentEmail, studentDOB, studentAddress, studentPassword);

                    // Retrieve the allocation information
                    PreparedStatement allocationStmt = connection.prepareStatement("SELECT r.room_number, a.check_in_date " +
                            "FROM allocation a " +
                            "INNER JOIN room r ON a.room_id = r.room_id " +
                            "WHERE a.student_id = ?");
                    allocationStmt.setInt(1, studentId);
                    ResultSet allocationResult = allocationStmt.executeQuery();

                    if (allocationResult.next()) {
                        String roomNumber = allocationResult.getString("room_number");
                        Date checkInDate = allocationResult.getDate("check_in_date");

                        Room room = new Room(roomNumber);
                        Allocation allocation = new Allocation(0, checkInDate, null, student, room);
                        student.setAllocation(allocation);
                    }

                    allocationStmt.close();

                    return student;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Allocation getAllocation(int studentId) {
        try (PreparedStatement stmt = connection.prepareStatement("SELECT r.room_number, a.check_in_date, p.student_id " +
                "FROM allocation a " +
                "INNER JOIN room r ON a.room_id = r.room_id " +
                "LEFT JOIN payment p ON a.student_id = p.student_id " +
                "WHERE a.student_id = ?")) {
            stmt.setInt(1, studentId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String roomNumber = rs.getString("room_number");
                    Date checkInDate = rs.getDate("check_in_date");

                    Student student = new Student(studentId, "", "", "", null, "", ""); // Replace the empty strings and null with actual student properties
                    Room room = new Room(roomNumber); // Replace the empty strings and null with actual room properties

                    Allocation allocation = new Allocation(0, checkInDate, null, student, room); // Replace the 0 and null with actual allocation properties
                    return allocation;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    
    public boolean registerStudent(Student student) {
        try {
            String query = "INSERT INTO student (student_id, stud_password, stud_name, stud_gender, stud_email, stud_phone, stud_dob, stud_address, allocation_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setInt(1, student.getStudentId());
                stmt.setString(2, student.getStudentPassword());
                stmt.setString(3, student.getStudentName());
                stmt.setString(4, student.getGender());
                stmt.setString(5, student.getStudentEmail());
                stmt.setString(6, student.getStudentPhone());
                if (student.getStudentDOB() != null) {
                    stmt.setDate(7, new java.sql.Date(student.getStudentDOB().getTime()));
                } else {
                    stmt.setNull(7, Types.DATE);
                }
                stmt.setString(8, student.getStudentAddress());
                stmt.setNull(9, Types.INTEGER); // Set allocation_id as null

                int rowsAffected = stmt.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any database-related errors here
            return false;
        }
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
