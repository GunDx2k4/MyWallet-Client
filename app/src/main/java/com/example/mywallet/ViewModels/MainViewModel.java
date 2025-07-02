package com.example.mywallet.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mywallet.DAO.Account.AccountRepository;
import com.example.mywallet.Models.Account;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private final AccountRepository repository;
    private final LiveData<List<Account>> allStudents;
    private final MutableLiveData<String> toastMessage = new MutableLiveData<>();

    public MainViewModel(@NonNull Application application) {
        super(application);
        repository = new AccountRepository(application);
        allStudents = repository.getAllAccounts();
    }

    public LiveData<List<Account>> getAllStudents() {
        return allStudents;
    }

    public LiveData<String> getToastMessage() {
        return toastMessage;
    }

    public void insert(String accountName, String balanceStr) {
        if (accountName.isEmpty() || balanceStr.isEmpty()) {
            toastMessage.setValue("Vui lòng nhập đầy đủ thông tin");
            return;
        }
        double balance = Double.parseDouble(balanceStr);
        Account newAccount = new Account(accountName, balance);
        repository.insert(newAccount);
    }

    public void update(Account account) {
        //TO-DO: Pre-validation ...
        repository.update(account);
    }

    public void delete(Account account){
        //TO-DO: Pre-validation ...
        repository.delete(account);
    }

    public void deleteAll(){
        repository.deleteAll();
    }



}
