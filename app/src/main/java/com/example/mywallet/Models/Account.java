package com.example.mywallet.Models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "tblTaiKhoan")
public class Account {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private double balance;

    public Account(String name, double balance) {
        this.name = name;
        this.balance = balance;
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

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
