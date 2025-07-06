package com.example.mywallet.DAO.Category;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mywallet.Models.Category;

import java.util.List;

@Dao
public interface CategoryDAO {
    @Insert
    void insert(Category category);

    @Update
    void update(Category category);

    @Delete
    void delete(Category category);

    @Query("DELETE FROM tblDanhMuc")
    void deleteAll();

    @Query("SELECT * FROM tblDanhMuc ORDER BY name ASC")
    LiveData<List<Category>> getAllCategory();
}
