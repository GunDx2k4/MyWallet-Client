package com.example.mywallet.Models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tblDanhMuc")
public class Category {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int userId;
    private String name;
    private boolean isIncome;

    public Category(int id, int userId, String name, boolean isIncome) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.isIncome = isIncome;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIncome() {
        return isIncome;
    }

    public void setIncome(boolean isIncome) {
        this.isIncome = isIncome;
    }
}
