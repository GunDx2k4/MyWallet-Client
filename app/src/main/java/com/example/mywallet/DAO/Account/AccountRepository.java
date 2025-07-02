package com.example.mywallet.DAO.Account;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.mywallet.Models.Account;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AccountRepository {
    private final AccountDAO accountDAO;
    private final LiveData<List<Account>> allAccounts;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public AccountRepository(Application application) {
        AccountDatabase db = AccountDatabase.getDatabase(application);
        accountDAO = db.accountDAO();
        allAccounts = accountDAO.getAllStudent();
    }


    public LiveData<List<Account>> getAllAccounts() {
        return allAccounts;
    }
    public void insert(Account account){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                accountDAO.insert(account);
            }
        });
    }

    public void update(Account account){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                accountDAO.update(account);
            }
        });
    }

    public void delete(Account account){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                accountDAO.delete(account);
            }
        });
    }

    public void deleteAll(){
        executorService.execute(accountDAO::deleteAll);
    }
}
