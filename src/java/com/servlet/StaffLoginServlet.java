package com.servlet;

import com.dao.StaffDAO;
import com.model.Staff;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/staff-login")
public class StaffLoginServlet extends HttpServlet {

    private StaffDAO staffDAO;

    public void init() {
        staffDAO = new StaffDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int staffId = Integer.parseInt(request.getParameter("staff_id"));
        String password = request.getParameter("password");

        Staff staff = staffDAO.loginStaff(staffId, password);
        if (staff != null) {
            // Login successful, redirect to the staff dashboard page
            request.getSession().setAttribute("staff", staff); // Store the staff object in the session
            request.setAttribute("staffId", staff.getStaffId()); // Set the staff ID attribute in the request
            request.getRequestDispatcher("staff-dashboard.jsp").forward(request, response);
        } else {
            // Invalid staff ID or password, redirect back to the login form with an error message
            request.setAttribute("errorMessage", "Invalid staff ID or password. Please try again.");
            request.getRequestDispatcher("staff-login.jsp").forward(request, response);
        }
    }

    public void destroy() {
        staffDAO.close();
    }
}
