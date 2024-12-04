<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Hostel Management</title>
    <link rel="stylesheet" href="css/Allroom.css">
    <style>
        .no-line {
            border-bottom: none;
        }
        .active,
         a:hover {
            background-color: #f5f5f5;
            transition: all 0.6s;
           
             }
          .search
          {
             padding-left: 3%;
          }
         
/*    ado room number,floor,capacity ,availibility,hostelname*/

  
    </style>
</head>
<body>
<span class="bckg"></span>
<header>
    <h1>HOSTEL MANAGEMENT</h1>
    <link rel="stylesheet" href="css/Allroom.css">
    <nav>
               <li class="active"><a href="staff-dashboards.jsp">DASHBOARD</a></li>
                <ul>
                    <br><br><br>
                    <li><a>FEATURES</a></li>
                    <li><a href="room-details.jsp">ALL ROOMS</a></li>
                    <li><a href="room-form.jsp">ADD ROOMS</a></li>
                    <li><a href="payment-details.jsp">ALL PAYMENTS</a></li>
                    <li><a href="students.jsp">ALL STUDENTS</a></li>
                    <li><a href="add-staff.jsp">ADD STAFF</a></li>
                    <span><br><br></span>
                    <li><a href="index.jsp">LOG OUT</a></li>
                </ul>
</header>
<main>
    <div class="title">
        <h2></h2>
        <a href="javascript:void(0);" class="no-line">Hello</a>
    </div>
    <body>
    <br>
    <h2>ALL STUDENTS</h2>
    <div class="search">
        <form>
            <input type="text" name="search-bar" placeholder="">
            <button type="submit" id="search-button">search</button>
        </form>
        </div>
    <br>
    <div class="table-container">
        
    <table>
        <tr>
            <th>Student ID</th>
            <th>Student Name</th>
            <th>Gender</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Date of Birth</th>
            <th>Address</th>
        </tr>
        <% 
            com.dao.StudentDAO studentDAO = new com.dao.StudentDAO();
            java.util.List<com.model.Student> students = studentDAO.getAllStudents();
            for (com.model.Student student : students) {
        %>
        <tr>
            <td><%= student.getStudentId() %></td>
            <td><%= student.getStudentName() %></td>
            <td><%= student.getGender() %></td>
            <td><%= student.getStudentEmail() %></td>
            <td><%= student.getStudentPhone() %></td>
            <td><%= student.getStudentDOB() %></td>
            <td><%= student.getStudentAddress() %></td>
        </tr>
        <% } %>
    </table>
        </div>
    </body>
</main>
</body>
</html>
