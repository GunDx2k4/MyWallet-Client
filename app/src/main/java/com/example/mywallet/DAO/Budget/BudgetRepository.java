package com.example.mywallet.DAO.Budget;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.mywallet.Models.Account;
import com.example.mywallet.Models.Budget;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BudgetRepository {
    private final BudgetDAO budgetDAO;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private final LiveData<List<Budget>> allBudgets;

    public BudgetRepository(Application application) {
        BudgetDatabase db = BudgetDatabase.getDatabase(application);
        budgetDAO = db.budgetDAO();
        allBudgets = budgetDAO.getAllBudget();
    }

    public LiveData<List<Budget>> getAllBudgets() {
        return allBudgets;
    }
    public void insert(Budget budget) {
        executorService.execute(() -> budgetDAO.insert(budget));
    }

    public void update(Budget budget) {
        executorService.execute(() -> budgetDAO.update(budget));
    }

    public void delete(Budget budget) {
        executorService.execute(() -> budgetDAO.delete(budget));
    }

    public void deleteAll() {
        executorService.execute(budgetDAO::deleteAll);
    }
}
