<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.dao.StaffDAO" %>
<%@ page import="com.model.Staff" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%
    int staffId = Integer.parseInt(request.getParameter("staffId"));
    String staffName = request.getParameter("staffName");
    String staffGender = request.getParameter("staffGender");
    String staffEmail = request.getParameter("staffEmail");
    String staffPhone = request.getParameter("staffPhone");
    String staffAddress = request.getParameter("staffAddress");
    String staffDOB = request.getParameter("staffDOB");
    String staffPassword = request.getParameter("staffPassword");

    // Convert the staffDOB string to a java.util.Date object
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Date dob = sdf.parse(staffDOB);

    Staff staff = new Staff(staffId, staffPassword, staffName, staffGender, staffEmail, staffPhone, staffAddress, dob);
    StaffDAO staffDAO = new StaffDAO();
    staffDAO.addStaff(staff);
    staffDAO.close();

    response.sendRedirect("add-staff.jsp?success=true");
%>
