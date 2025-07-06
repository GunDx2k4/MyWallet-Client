package com.example.mywallet.DAO.Budget;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mywallet.Models.Budget;

@Database(entities = {Budget.class}, version = 1, exportSchema = false)
public abstract class BudgetDatabase extends RoomDatabase {
    public abstract BudgetDAO budgetDAO();
    private static volatile BudgetDatabase INSTANCE;
    public static BudgetDatabase getDatabase(final Context context){
        if (INSTANCE==null){
            synchronized (BudgetDatabase.class){
                if (INSTANCE==null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    BudgetDatabase.class,"budget_db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
