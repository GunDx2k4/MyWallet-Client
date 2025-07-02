package com.example.mywallet.DAO.Account;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mywallet.Models.Account;

@Database(entities = {Account.class}, version = 1, exportSchema = false)
public abstract class AccountDatabase extends RoomDatabase {
    public abstract AccountDAO accountDAO();
    private static volatile AccountDatabase INSTANCE;
    public static AccountDatabase getDatabase(final Context context){
        if (INSTANCE==null){
            synchronized (AccountDatabase.class){
                if (INSTANCE==null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AccountDatabase.class,"account_db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
