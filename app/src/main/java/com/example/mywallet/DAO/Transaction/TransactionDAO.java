package com.example.mywallet.DAO.Transaction;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mywallet.Models.Transaction;

import java.util.List;

@Dao
public interface TransactionDAO {
    @Insert
    void insert(Transaction transaction);

    @Update
    void update(Transaction transaction);

    @Delete
    void delete(Transaction transaction);

    @Query("DELETE FROM tblGiaoDich")
    void deleteAll();

    @Query("SELECT * FROM tblGiaoDich ORDER BY date DESC")
    LiveData<List<Transaction>> getAllTransactions();
}