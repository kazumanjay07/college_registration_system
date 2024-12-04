<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.dao.PaymentDAO" %>
<%@ page import="com.model.Payment" %>
<%@ page import="com.model.Student" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.ParseException" %>
<%@ page import="com.dao.StudentDAO" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Payment</title>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>
<body>
<%
    // Get the form data
    int paymentId = Integer.parseInt(request.getParameter("paymentId"));
    double amount = Double.parseDouble(request.getParameter("amount"));
    String dateString = request.getParameter("date");
    Date date = null;
    try {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        date = dateFormat.parse(dateString);
    } catch (ParseException e) {
        e.printStackTrace();
        // Handle the exception as per your requirement
    }
    int studentId = Integer.parseInt(request.getParameter("student"));

    // Create an instance of the PaymentDAO
    PaymentDAO paymentDAO = new PaymentDAO();

    // Get the existing payment
    Payment payment = paymentDAO.getPayment(paymentId);

    // Check if the payment exists
    if (payment != null) {
        // Retrieve the student details based on the studentId
        StudentDAO studentDAO = new StudentDAO();
        Student student = studentDAO.getStudentById(studentId);

        if (student != null) {
            // Update the payment details
            payment.setAmount(amount);
            payment.setDate(date);
            payment.setStudent(student);

            // Call the updatePayment method in the PaymentDAO to update the payment
            boolean success = paymentDAO.updatePayment(payment);

            if (success) {
                // Payment updated successfully
%>
                <script>
                    swal({
                        title: "Success",
                        text: "Payment updated successfully",
                        icon: "success"
                    }).then(function() {
                        window.location.href = "payment-details.jsp";
                    });
                </script>
<%
            } else {
                // Failed to update payment
                String errorMessage = "Failed to update payment.";
                request.setAttribute("errorMessage", errorMessage);
                request.getRequestDispatcher("editPayment.jsp?paymentId=" + paymentId).forward(request, response);
            }
        } else {
            // Student not found
            String errorMessage = "Student not found.";
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("editPayment.jsp?paymentId=" + paymentId).forward(request, response);
        }
    } else {
        // Payment not found
        String errorMessage = "Payment not found.";
        request.setAttribute("errorMessage", errorMessage);
        request.getRequestDispatcher("editPayment.jsp?paymentId=" + paymentId).forward(request, response);
    }
%>
</body>
</html>
