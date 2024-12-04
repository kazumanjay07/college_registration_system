package com.dao;

import com.model.Hostel;
import com.model.Room;
import com.model.Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {
    private final String url = "jdbc:mysql://localhost:3306/hostelmanagement2";
    private final String username = "root";
    private final String password = "";
    private Connection connection;

    public RoomDAO() {
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as per your requirement
        }
    }

    public List<Room> getAllRooms() {
        List<Room> rooms = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT r.room_id, r.room_number, r.floor, r.capacity, r.is_available, r.hostel_id, h.name, h.address, h.phone FROM room r JOIN hostel h ON r.hostel_id = h.hostel_id");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int roomId = resultSet.getInt("room_id");
                String roomNumber = resultSet.getString("room_number");
                int floor = resultSet.getInt("floor");
                int capacity = resultSet.getInt("capacity");
                boolean isAvailable = resultSet.getBoolean("is_available");
                int hostelId = resultSet.getInt("hostel_id");
                String hostelName = resultSet.getString("name");
                String hostelAddress = resultSet.getString("address");
                String hostelPhone = resultSet.getString("phone");

                Hostel hostel = new Hostel(hostelId, hostelName, hostelAddress, hostelPhone);
                Room room = new Room(roomId, roomNumber, floor, capacity, isAvailable, hostel);
                rooms.add(room);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as per your requirement
        }

        return rooms;
    }

    public void deleteRoom(String roomNumber) {
        try (PreparedStatement stmt = connection.prepareStatement("DELETE FROM room WHERE room_number = ?");
             PreparedStatement allocationStmt = connection.prepareStatement("DELETE FROM allocation WHERE room_number = ?")) {

            // Set the room number parameter
            stmt.setString(1, roomNumber);

            // Delete the room record
            stmt.executeUpdate();

            // Delete the related allocation records
            allocationStmt.setString(1, roomNumber);
            allocationStmt.executeUpdate();

            System.out.println("Room and related allocation deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Room getRoomByNumber(String roomNumber) {
        Room room = null;

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT r.room_id, r.room_number, r.floor, r.capacity, r.is_available, r.hostel_id, h.name, h.address, h.phone FROM room r JOIN hostel h ON r.hostel_id = h.hostel_id WHERE r.room_number = ?");
            statement.setString(1, roomNumber);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int roomId = resultSet.getInt("room_id");
                int floor = resultSet.getInt("floor");
                int capacity = resultSet.getInt("capacity");
                boolean isAvailable = resultSet.getBoolean("is_available");
                int hostelId = resultSet.getInt("hostel_id");
                String hostelName = resultSet.getString("name");
                String hostelAddress = resultSet.getString("address");
                String hostelPhone = resultSet.getString("phone");

                Hostel hostel = new Hostel(hostelId, hostelName, hostelAddress, hostelPhone);
                room = new Room(roomId, roomNumber, floor, capacity, isAvailable, hostel);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as per your requirement
        }

        return room;
    }
    
    public void updateRoom(Room room) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE room SET floor = ?, capacity = ?, is_available = ? WHERE room_number = ?");
            statement.setInt(1, room.getFloor());
            statement.setInt(2, room.getCapacity());
            statement.setBoolean(3, room.isAvailable());
            statement.setString(4, room.getRoomNumber());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as per your requirement
        }
    }

    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as per your requirement
        }
    }
    
    public void addRoom(Room room) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO room (room_number, floor, capacity, is_available, hostel_id) VALUES (?, ?, ?, ?, ?)");
            statement.setString(1, room.getRoomNumber());
            statement.setInt(2, room.getFloor());
            statement.setInt(3, room.getCapacity());
            statement.setBoolean(4, room.isAvailable());
            statement.setInt(5, room.getHostel().getHostelId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as per your requirement
        }
    }
    
    public int getTotalRoomsCapacity() {
        int totalCapacity = 0;

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT SUM(capacity) AS total_capacity FROM room");
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                totalCapacity = resultSet.getInt("total_capacity");
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as per your requirement
        }

        return totalCapacity;
    }

    public int getBookedRoomsCount() {
        int bookedRoomsCount = 0;

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) AS booked_rooms FROM allocation");
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                bookedRoomsCount = resultSet.getInt("booked_rooms");
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as per your requirement
        }

        return bookedRoomsCount;
    }
    
    public List<Room> getAvailableRooms() {
    List<Room> availableRooms = new ArrayList<>();

    try {
        PreparedStatement statement = connection.prepareStatement("SELECT r.room_id, r.room_number, r.floor, r.capacity, r.is_available, r.hostel_id, h.name, h.address, h.phone FROM room r JOIN hostel h ON r.hostel_id = h.hostel_id WHERE r.is_available = 1");
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int roomId = resultSet.getInt("room_id");
            String roomNumber = resultSet.getString("room_number");
            int floor = resultSet.getInt("floor");
            int capacity = resultSet.getInt("capacity");
            boolean isAvailable = resultSet.getBoolean("is_available");
            int hostelId = resultSet.getInt("hostel_id");
            String hostelName = resultSet.getString("name");
            String hostelAddress = resultSet.getString("address");
            String hostelPhone = resultSet.getString("phone");

            Hostel hostel = new Hostel(hostelId, hostelName, hostelAddress, hostelPhone);
            Room room = new Room(roomId, roomNumber, floor, capacity, isAvailable, hostel);
            availableRooms.add(room);
        }

        resultSet.close();
        statement.close();
    } catch (SQLException e) {
        e.printStackTrace();
        // Handle the exception as per your requirement
    }

    return availableRooms;
    }

}
