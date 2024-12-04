package com.servlet;

import com.dao.StudentDAO;
import com.model.Student;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet; // Add this import statement
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/update-profile-student")
public class UpdateProfileStudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve the form data
        int studentId = Integer.parseInt(request.getParameter("studentId"));
        String studentName = request.getParameter("studentName");
        String studentEmail = request.getParameter("studentEmail");
        String studentPhone = request.getParameter("studentPhone");
        String studentDOBStr = request.getParameter("dob");
        String studentAddress = request.getParameter("studentAddress");
        String studentPassword = request.getParameter("password");

        // Parse the date of birth string to a Date object
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date studentDOB = null;
        try {
            studentDOB = dateFormat.parse(studentDOBStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Create a new Student object
        Student student = new Student(studentId, studentName, studentPhone, studentEmail, studentDOB, studentAddress,
                studentPassword);

        // Update the student record in the database
        StudentDAO studentDAO = new StudentDAO();
        studentDAO.updateStudent(student);
        studentDAO.close();

        // Set the success message as a request attribute
        request.setAttribute("successMessage", "Profile updated successfully!");

        // Forward the request to the edit-profile.jsp page
        request.getRequestDispatcher("edit-profile-student.jsp").forward(request, response);
    }
}
