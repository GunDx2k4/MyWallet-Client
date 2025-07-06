package com.example.mywallet.Models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tblNganSach")
public class Budget {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String note;
    private double amountLimit;
    private String startDate;
    private String endDate;
    private int categoryId;
    private int userId;

    public Budget(int id, String name, String note, double amountLimit, String startDate, String endDate, int categoryId, int userId) {
        this.id = id;
        this.name = name;
        this.note = note;
        this.amountLimit = amountLimit;
        this.startDate = startDate;
        this.endDate = endDate;
        this.categoryId = categoryId;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public double getAmountLimit() {
        return amountLimit;
    }

    public void setAmountLimit(double amountLimit) {
        this.amountLimit = amountLimit;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
