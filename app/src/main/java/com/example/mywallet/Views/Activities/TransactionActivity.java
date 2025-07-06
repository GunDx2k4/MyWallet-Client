package com.example.mywallet.Views.Activities;

import android.content.Intent;
import android.os.Bundle;

import com.example.mywallet.R;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.mywallet.databinding.ActivityTransactionBinding;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TransactionActivity extends AppCompatActivity {
    private Spinner spinnerAccount, spinnerCategory;
    private EditText edtAmount, edtDueDate, edtNote;
    private TextView txtDate, txtSelectedCategory;
    private Button btnAddTransaction;
    private ImageButton btnClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        initUI();
        initViewModel();
        observeData();
        loadEvents();
    }

    private void initViewModel() {
    }

    private void initUI() {
        spinnerAccount = findViewById(R.id.spinnerAccount);
        edtAmount = findViewById(R.id.edtAmount);
        txtDate = findViewById(R.id.txtDate);
        edtDueDate = findViewById(R.id.edtDueDate);
        edtNote = findViewById(R.id.edtNote);
        btnAddTransaction = findViewById(R.id.btnAddTransaction);
        btnClose = findViewById(R.id.btnBackToProfile);
        txtSelectedCategory = findViewById(R.id.txtSelectedCategory);

        String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        txtDate.setText("Ngày: " + currentDate);

        btnClose.setOnClickListener(v -> finish());
    }


    private void observeData() {

    }

    private void loadEvents() {

    }
}