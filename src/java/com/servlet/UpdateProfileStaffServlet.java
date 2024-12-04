package com.servlet;

import com.dao.StaffDAO;
import com.model.Staff;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/update-profile-staff")
public class UpdateProfileStaffServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve the form data
        int staffId = Integer.parseInt(request.getParameter("staffId"));
        String staffName = request.getParameter("staffName");
        String staffEmail = request.getParameter("staffEmail");
        String staffPhone = request.getParameter("staffPhone");
        String staffDOBStr = request.getParameter("staffDOB");
        String staffAddress = request.getParameter("staffAddress");
        String staffPassword = request.getParameter("password");

        // Parse the date of birth string to a Date object
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date staffDOB = null;
        if (staffDOBStr != null && !staffDOBStr.isEmpty()) {
            try {
                staffDOB = dateFormat.parse(staffDOBStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        // Create a new Staff object
        Staff staff = new Staff(staffId, staffPassword, staffName, staffEmail, staffPhone, staffAddress, staffDOB);

        // Update the staff record in the database
        StaffDAO staffDAO = new StaffDAO();
        staffDAO.updateStaff(staff);

        // Retrieve the updated staff information
        Staff updatedStaff = staffDAO.getStaffById(staffId);

        // Close the staffDAO
        staffDAO.close();

        // Set the updated staff information as an attribute in the session
        request.getSession().setAttribute("staff", updatedStaff);

        // Set the success message as a request attribute
        request.setAttribute("successMessage", "Profile updated successfully!");

        // Forward the request to the edit-profile-staff.jsp page
        request.getRequestDispatcher("edit-profile-staff.jsp").forward(request, response);
    }
}
