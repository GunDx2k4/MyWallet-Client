package com.example.mywallet.Models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tblGiaoDich")
public class Transaction {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int userId;
    private int paymentTypeId;
    private int categoryId;
    private double amount;
    private String date;
    private String note;

    public Transaction(int id, int userId, int paymentTypeId, int categoryId, double amount, String date, String note) {
        this.id = id;
        this.userId = userId;
        this.paymentTypeId = paymentTypeId;
        this.categoryId = categoryId;
        this.amount = amount;
        this.date = date;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(int paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
