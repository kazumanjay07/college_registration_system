package com.model;

public class Hostel {
    private int hostelId;
    private String name;
    private String address;
    private String phone;

    public Hostel(int hostelId, String name, String address, String phone) {
        this.hostelId = hostelId;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public Hostel(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }
        
    public Hostel(String name) {
        this.name = name;
    }

    public int getHostelId() {
        return hostelId;
    }

    public void setHostelId(int hostelId) {
        this.hostelId = hostelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
