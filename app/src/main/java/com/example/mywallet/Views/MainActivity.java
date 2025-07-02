package com.example.mywallet.Views;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mywallet.R;
import com.example.mywallet.ViewModels.MainViewModel;
import com.example.mywallet.Views.Adapters.AccountAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MainViewModel viewModel;
    private EditText edtAccountName, edtBalance;
    private Button btnSaveAccount;
    private RecyclerView recyclerView;
    private AccountAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtAccountName = findViewById(R.id.edtAccountName);
        edtBalance = findViewById(R.id.edtBalance);
        btnSaveAccount = findViewById(R.id.btnSaveAccount);
        recyclerView = findViewById(R.id.recyclerViewAccounts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        adapter = new AccountAdapter(this, viewModel);
        recyclerView.setAdapter(adapter);

        observeData();
        loadEvents();
    }

    private void observeData() {
        viewModel.getToastMessage().observe(this, message -> {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        });

        viewModel.getAllStudents().observe(this, accounts -> {
            adapter.setAccounts(accounts);
        });
    }

    private void loadEvents() {
        btnSaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String accountName = edtAccountName.getText().toString().trim();
                String balanceStr = edtBalance.getText().toString().trim();
                viewModel.insert(accountName, balanceStr);
            }
        });
    }
}