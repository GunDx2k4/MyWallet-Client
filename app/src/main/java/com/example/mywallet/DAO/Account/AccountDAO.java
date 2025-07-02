package com.example.mywallet.DAO.Account;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mywallet.Models.Account;

import java.util.List;

@Dao
public interface AccountDAO {
    @Insert
    void insert(Account account);

    @Update
    void update(Account account);

    @Delete
    void delete(Account account);

    @Query("DELETE FROM tblTaiKhoan")
    void deleteAll();

    @Query("SELECT * FROM tblTaiKhoan ORDER BY name ASC")
    LiveData<List<Account>> getAllStudent();
}
