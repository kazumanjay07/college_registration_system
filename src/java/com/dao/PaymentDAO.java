package com.dao;

import com.model.Payment;
import com.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PaymentDAO {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/hostelmanagement2";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "";

    public List<Payment> getAllPayments() {
        List<Payment> payments = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             Statement stmt = conn.createStatement()) {

            String query = "SELECT p.payment_id, p.amount, p.date, s.student_id, s.stud_name FROM payment p INNER JOIN student s ON p.student_id = s.student_id";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                int paymentId = rs.getInt("payment_id");
                double amount = rs.getDouble("amount");
                Date date = rs.getDate("date");

                int studentId = rs.getInt("student_id");
                String studentName = rs.getString("stud_name");

                Student student = new Student(studentId, studentName);
                Payment payment = new Payment(paymentId, amount, date, student);

                payments.add(payment);
            }

            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return payments;
    }

    public boolean deletePayment(int paymentId) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM payment WHERE payment_id = ?")) {

            stmt.setInt(1, paymentId);
            int rowsAffected = stmt.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    
    public Payment getPayment(int paymentId) {
        Payment payment = null;
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("SELECT p.payment_id, p.amount, p.date, s.student_id, s.stud_name FROM payment p INNER JOIN student s ON p.student_id = s.student_id WHERE p.payment_id = ?")) {

            stmt.setInt(1, paymentId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                double amount = rs.getDouble("amount");
                Date date = rs.getDate("date");

                int studentId = rs.getInt("student_id");
                String studentName = rs.getString("stud_name");

                Student student = new Student(studentId, studentName);
                payment = new Payment(paymentId, amount, date, student);
            }

            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return payment;
    }

    public boolean updatePayment(Payment payment) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("UPDATE payment SET amount = ?, date = ?, student_id = ? WHERE payment_id = ?")) {

            stmt.setDouble(1, payment.getAmount());
            stmt.setDate(2, new java.sql.Date(payment.getDate().getTime()));
            stmt.setInt(3, payment.getStudent().getStudentId());
            stmt.setInt(4, payment.getPaymentId());

            int rowsAffected = stmt.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
