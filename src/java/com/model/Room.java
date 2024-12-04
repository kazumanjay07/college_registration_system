package com.model;

public class Room {
    private int roomId; // Added room id
    private String roomNumber;
    private int floor;
    private int capacity;
    private boolean isAvailable;
    private Hostel hostel;

    public Room(int roomId, String roomNumber, int floor, int capacity, boolean isAvailable, Hostel hostel) {
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.floor = floor;
        this.capacity = capacity;
        this.isAvailable = isAvailable;
        this.hostel = hostel;
    }
    
    public Room(String roomNumber) {
    this.roomNumber = roomNumber;
    }

    
    public Room(String roomNumber, int floor, int capacity, boolean isAvailable, Hostel hostel) {
    this.roomNumber = roomNumber;
    this.floor = floor;
    this.capacity = capacity;
    this.isAvailable = isAvailable;
    this.hostel = hostel;
    }

    public Room(int roomId, String roomNumber, int floor, int capacity, Hostel hostel) {
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.floor = floor;
        this.capacity = capacity;
        this.hostel = hostel;
    }
    
    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }

    public Hostel getHostel() {
        return hostel;
    }

    public void setHostel(Hostel hostel) {
        this.hostel = hostel;
    }
}
