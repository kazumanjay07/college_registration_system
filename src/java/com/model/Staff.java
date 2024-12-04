package com.model;

import java.util.Date;

public class Staff {
    private int staffId;
    private String staffPassword;
    private String staffName;
    private String staffGender;
    private String staffEmail;
    private String staffPhone;
    private String staffAddress;
    private Date staffDOB;

    public Staff(int staffId, String staffPassword, String staffName, String staffGender, String staffEmail, String staffPhone, String staffAddress, Date staffDOB) {
        this.staffId = staffId;
        this.staffPassword = staffPassword;
        this.staffName = staffName;
        this.staffGender = staffGender;
        this.staffEmail = staffEmail;
        this.staffPhone = staffPhone;
        this.staffAddress = staffAddress;
        this.staffDOB = staffDOB;
    }
    
    public Staff(int staffId, String staffPassword, String staffName, String staffEmail, String staffPhone, String staffAddress, Date staffDOB) {
        this.staffId = staffId;
        this.staffPassword = staffPassword;
        this.staffName = staffName;
        this.staffEmail = staffEmail;
        this.staffPhone = staffPhone;
        this.staffAddress = staffAddress;
        this.staffDOB = staffDOB;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public String getStaffPassword() {
        return staffPassword;
    }

    public void setStaffPassword(String staffPassword) {
        this.staffPassword = staffPassword;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getStaffGender() {
        return staffGender;
    }

    public void setStaffGender(String staffGender) {
        this.staffGender = staffGender;
    }

    public String getStaffEmail() {
        return staffEmail;
    }

    public void setStaffEmail(String staffEmail) {
        this.staffEmail = staffEmail;
    }

    public String getStaffPhone() {
        return staffPhone;
    }

    public void setStaffPhone(String staffPhone) {
        this.staffPhone = staffPhone;
    }

    public String getStaffAddress() {
        return staffAddress;
    }

    public void setStaffAddress(String staffAddress) {
        this.staffAddress = staffAddress;
    }

    public Date getStaffDOB() {
        return staffDOB;
    }

    public void setStaffDOB(Date staffDOB) {
        this.staffDOB = staffDOB;
    }
}
