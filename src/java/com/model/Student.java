package com.model;

import java.util.Date;

public class Student {
    private int studentId;
    private String studentPassword;
    private String studentName;
    private String gender;
    private String studentEmail;
    private String studentPhone;
    private Date studentDOB;
    private String studentAddress;
    private Room room;
    private Allocation allocation; // Add allocation field

    // Constructors, getters, and setters

    public Student(int studentId, String studentPassword, String studentName, String gender, String studentEmail,
                   String studentPhone, Date studentDOB, String studentAddress, Room room, Allocation allocation) {
        this.studentId = studentId;
        this.studentPassword = studentPassword;
        this.studentName = studentName;
        this.gender = gender;
        this.studentEmail = studentEmail;
        this.studentPhone = studentPhone;
        this.studentDOB = studentDOB;
        this.studentAddress = studentAddress;
        this.room = room;
        this.allocation = allocation;
    }

    public Student(int studentId, String studentPassword, String studentName, String gender, String studentEmail,
                   String studentPhone, Date studentDOB, String studentAddress, Room room) {
        this.studentId = studentId;
        this.studentPassword = studentPassword;
        this.studentName = studentName;
        this.gender = gender;
        this.studentEmail = studentEmail;
        this.studentPhone = studentPhone;
        this.studentDOB = studentDOB;
        this.studentAddress = studentAddress;
        this.room = room;
    }
    
    public Student(int studentId, String studentName, String studentPhone, String studentEmail,Room room) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentPhone = studentPhone;
        this.studentEmail = studentEmail;
        this.room = room;
    }

    public Student(int studentId, String studentName, String studentPhone, String studentEmail) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentPhone = studentPhone;
        this.studentEmail = studentEmail;

    }
    
    public Student(int studentId, String studentName, String studentPhone, String studentEmail, Date studentDOB, String studentAddress, String studentPassword ) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentPhone = studentPhone;
        this.studentEmail = studentEmail;
        this.studentDOB = studentDOB;
        this.studentAddress = studentAddress;
        this.studentPassword = studentPassword;
    }
    
    public Student(int studentId, String studentName, String studentPhone, String studentEmail, Date studentDOB, String studentAddress) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentPhone = studentPhone;
        this.studentEmail = studentEmail;
        this.studentDOB = studentDOB;
        this.studentAddress = studentAddress;
    }
    
    public Student(int studentId, String studentName) {
        this.studentId = studentId;
        this.studentName = studentName;

    }
    
    public Student(int studentId, String studentPassword, String studentName, String gender, String studentEmail,
                   String studentPhone, Date studentDOB, String studentAddress) {
        this.studentId = studentId;
        this.studentPassword = studentPassword;
        this.studentName = studentName;
        this.gender = gender;
        this.studentEmail = studentEmail;
        this.studentPhone = studentPhone;
        this.studentDOB = studentDOB;
        this.studentAddress = studentAddress;
    }

    
    // Getters and setters for the fields

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentPassword() {
        return studentPassword;
    }

    public void setStudentPassword(String studentPassword) {
        this.studentPassword = studentPassword;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getStudentPhone() {
        return studentPhone;
    }

    public void setStudentPhone(String studentPhone) {
        this.studentPhone = studentPhone;
    }

    public Date getStudentDOB() {
        return studentDOB;
    }

    public void setStudentDOB(Date studentDOB) {
        this.studentDOB = studentDOB;
    }

    public String getStudentAddress() {
        return studentAddress;
    }

    public void setStudentAddress(String studentAddress) {
        this.studentAddress = studentAddress;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
    
    public Allocation getAllocation() {
        return allocation;
    }

    public void setAllocation(Allocation allocation) {
        this.allocation = allocation;
    }
}
