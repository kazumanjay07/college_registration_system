package com.model;

import java.util.Date;

public class Allocation {
    private int allocationId;
    private Date checkInDate;
    private Date checkOutDate;
    private Student student;
    private Room room;

    public Allocation(int allocationId, Date checkInDate, Date checkOutDate, Student student, Room room) {
        this.allocationId = allocationId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.student = student;
        this.room = room;
    }
    
    public Allocation(Room room,Date checkInDate,Student student) {
        this.room = room;
        this.checkInDate = checkInDate;
        this.student = student;
        
    }

    public int getAllocationId() {
        return allocationId;
    }

    public void setAllocationId(int allocationId) {
        this.allocationId = allocationId;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
