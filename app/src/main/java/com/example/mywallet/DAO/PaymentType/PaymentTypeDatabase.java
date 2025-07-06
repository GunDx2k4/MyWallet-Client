package com.example.mywallet.DAO.PaymentType;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mywallet.Models.PaymentType;

@Database(entities = {PaymentType.class}, version = 1, exportSchema = false)
public abstract class PaymentTypeDatabase extends RoomDatabase {
    public abstract PaymentTypeDAO paymentTypeDAO();

    private static volatile PaymentTypeDatabase INSTANCE;

    public static PaymentTypeDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (PaymentTypeDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    PaymentTypeDatabase.class, "payment_type_db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
