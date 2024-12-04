<%@ page import="com.dao.RoomDAO" %>
<%@ page import="com.model.Room" %>

<%
    String roomNumber = request.getParameter("roomNumber");

    // Create an instance of RoomDAO
    RoomDAO roomDAO = new RoomDAO();

    // Get the room object
    Room room = roomDAO.getRoomByNumber(roomNumber);

    // Close the RoomDAO connection
    roomDAO.close();
%>

<!DOCTYPE html>
<html>
<head>
    <title>Delete Room Confirmation</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
    <h1>Delete Room Confirmation</h1>
    <p>Are you sure you want to delete Room number <%= roomNumber %>?</p>
    
    <!-- Add a confirmation modal -->
    <div class="modal fade" id="confirmationModal" tabindex="-1" role="dialog" aria-labelledby="confirmationModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="confirmationModalLabel">Confirmation</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <p>Are you sure you want to delete Room number <%= roomNumber %>?</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                    <button type="button" class="btn btn-danger" id="confirmDelete">Delete</button>
                </div>
            </div>
        </div>
    </div>
    
    <script>
        // Show the confirmation modal
        $(document).ready(function() {
            $('#confirmationModal').modal('show');
        });
        
        // Handle delete confirmation
        $('#confirmDelete').on('click', function() {
            // Make an AJAX request to delete the room
            $.ajax({
                url: "delete-room.jsp", // Replace with the URL of your server-side delete script
                method: "POST", // Use the appropriate HTTP method (e.g., POST, GET, etc.)
                data: { roomNumber: '<%= roomNumber %>' }, // Pass the room number as data
                success: function(response) {
                    // Handle the success response
                    console.log("Room deleted successfully");
                    
                    // Display success message
                    $('#confirmationModal').modal('hide');
                    $('#successMessage').text("Room successfully deleted.");
                    $('#successMessage').show();
                    
                    // Redirect to room-details.jsp after deletion
                    setTimeout(function() {
                        window.location.href = "room-details.jsp";
                    }, 2000); // Redirect after 2 seconds
                },
                error: function(xhr, status, error) {
                    // Handle the error response
                    console.log("Error deleting room: " + error);
                    
                    // Optionally, you can display an error message to the user
                }
            });
        });
    </script>
    
    <!-- Success message -->
    <div id="successMessage" class="alert alert-success" style="display: none;">
        Room successfully deleted.
    </div>
</body>
</html>
