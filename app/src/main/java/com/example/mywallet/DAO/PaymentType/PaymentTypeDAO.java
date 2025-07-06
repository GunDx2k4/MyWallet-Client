package com.example.mywallet.DAO.PaymentType;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mywallet.Models.PaymentType;

import java.util.List;

@Dao
public interface PaymentTypeDAO {
    @Insert
    void insert(PaymentType paymentType);

    @Update
    void update(PaymentType paymentType);

    @Delete
    void delete(PaymentType paymentType);

    @Query("DELETE FROM tblPhuongThucThanhToan")
    void deleteAll();

    @Query("SELECT * FROM tblPhuongThucThanhToan ORDER BY name ASC")
    LiveData<List<PaymentType>> getAllPaymentTypes();
}
