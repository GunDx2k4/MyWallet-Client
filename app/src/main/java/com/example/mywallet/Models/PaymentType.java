package com.example.mywallet.Models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tblPhuongThucThanhToan")
public class PaymentType {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int userId;
    private int accountId;
    private String name;
    private double balance;

    public PaymentType(int id, int userId, int accountId, String name, double balance) {
        this.id = id;
        this.userId = userId;
        this.accountId = accountId;
        this.name = name;
        this.balance = balance;
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

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
