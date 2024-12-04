package com.model;

import java.util.Date;

public class Payment {
    private int paymentId;
    private double amount;
    private Date date;
    private Student student;

    public Payment() {
    }

    public Payment(int paymentId, double amount, Date date, Student student) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.date = date;
        this.student = student;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
