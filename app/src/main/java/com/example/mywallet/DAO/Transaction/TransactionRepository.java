package com.example.mywallet.DAO.Transaction;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.mywallet.Models.Transaction;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TransactionRepository {
    private final TransactionDAO transactionDAO;
    private final LiveData<List<Transaction>> allTransactions;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public TransactionRepository(Application application) {
        TransactionDatabase db = TransactionDatabase.getDatabase(application);
        transactionDAO = db.transactionDAO();
        allTransactions = transactionDAO.getAllTransactions();
    }

    public LiveData<List<Transaction>> getAllTransactions() {
        return allTransactions;
    }

    public void insert(Transaction transaction) {
        executorService.execute(() -> transactionDAO.insert(transaction));
    }

    public void update(Transaction transaction) {
        executorService.execute(() -> transactionDAO.update(transaction));
    }

    public void delete(Transaction transaction) {
        executorService.execute(() -> transactionDAO.delete(transaction));
    }

    public void deleteAll() {
        executorService.execute(transactionDAO::deleteAll);
    }
}