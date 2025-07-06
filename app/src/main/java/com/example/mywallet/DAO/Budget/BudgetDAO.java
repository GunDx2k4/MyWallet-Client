package com.example.mywallet.DAO.Budget;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mywallet.Models.Budget;

import java.util.List;

@Dao
public interface BudgetDAO {
    @Insert
    void insert(Budget budget);

    @Update
    void update(Budget budget);

    @Delete
    void delete(Budget budget);

    @Query("DELETE FROM tblNganSach")
    void deleteAll();

    @Query("SELECT * FROM tblNganSach ORDER BY name ASC")
    LiveData<List<Budget>> getAllBudget();
}
