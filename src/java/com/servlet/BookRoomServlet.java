// Assuming you have a servlet named BookRoomServlet

import com.dao.RoomDAO;
import com.model.Room;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BookRoomServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Instantiate the RoomDAO
        RoomDAO roomDAO = new RoomDAO();

        // Get the list of available rooms
        List<Room> availableRooms = roomDAO.getAvailableRooms();

        // Set the availableRooms attribute
        request.setAttribute("availableRooms", availableRooms);

        // Forward the request to the book-room.jsp page
        request.getRequestDispatcher("book-room.jsp").forward(request, response);
    }
    
    // ... Other methods if needed
    
}
