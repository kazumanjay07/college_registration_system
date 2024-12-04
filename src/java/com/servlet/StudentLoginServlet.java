package com.servlet;

import com.dao.StudentDAO;
import com.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class StudentLoginServlet extends HttpServlet {

    private StudentDAO studentDAO;

    public void init() {
        studentDAO = new StudentDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int studentId = Integer.parseInt(request.getParameter("studentId"));
        String password = request.getParameter("password");

        Student student = studentDAO.loginStudent(studentId, password);
        if (student != null) {
            // Login successful, redirect to the student dashboard page
            request.getSession().setAttribute("student", student); // Store the student object in the session
            request.setAttribute("studentId", student.getStudentId()); // Set the student ID attribute in the request
            request.getRequestDispatcher("book-room.jsp").forward(request, response);
        } else {
            // Invalid student ID or password, redirect back to the login form with an error message
            request.setAttribute("errorMessage", "Invalid student ID or password. Please try again.");
            request.getRequestDispatcher("student-login.jsp").forward(request, response);
        }
    }

    public void destroy() {
        studentDAO.close();
    }
}
