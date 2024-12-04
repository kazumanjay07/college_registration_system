import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.model.Student;
import com.dao.StudentDAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/register")
public class RegisterStudentServlet extends HttpServlet {

    private StudentDAO studentDAO; // Initialize and inject the StudentDAO instance

    @Override
    public void init() throws ServletException {
        super.init();
        studentDAO = new StudentDAO(); // Instantiate the StudentDAO
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data
        int studentId = Integer.parseInt(request.getParameter("studentId"));
        String password = request.getParameter("password");
        String fullName = request.getParameter("fullName");
        String gender = request.getParameter("gender");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String dateOfBirthStr = request.getParameter("dateOfBirth");
        String address = request.getParameter("address");

        // Convert dateOfBirth string to Date object
        Date dateOfBirth = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateOfBirth = dateFormat.parse(dateOfBirthStr);
        } catch (ParseException e) {
            e.printStackTrace();
            // Handle any parsing errors here
            // You might want to redirect to the registration page with an error alert
            response.sendRedirect("register-student.jsp?error=true");
            return;
        }

        // Create a new student object
        Student student = new Student(studentId, password, fullName, gender, email, phoneNumber, dateOfBirth, address);

        // Register the student
        boolean registrationSuccess = studentDAO.registerStudent(student);

        if (registrationSuccess) {
            // Redirect to the login page with a success alert
            response.sendRedirect("student-login.jsp?success=true");
        } else {
            // Redirect to the registration page with an error alert
            response.sendRedirect("register-student.jsp?error=true");
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        studentDAO.close(); // Close the StudentDAO connection
    }
}
