package com.example.mywallet.Views;


import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mywallet.Networks.MyWebSocket;
import com.example.mywallet.R;
import com.example.mywallet.ViewModels.MainViewModel;
import com.example.mywallet.Views.Adapters.AccountAdapter;


public class MainActivity extends AppCompatActivity {
    private MainViewModel viewModel;
    private EditText edtAccountName, edtBalance;
    private Button btnSaveAccount, btnSendRequest;
    private RecyclerView recyclerView;
    private AccountAdapter adapter;
    private MyWebSocket myWebSocket;

    private static final String CHANNEL_ID = "websocket";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();

        initViewModel();

        requestNotificationPermission();
        createNotificationChannel();

        myWebSocket = new MyWebSocket(viewModel);
        observeData();
        loadEvents();
    }

    public void showNotification( String title, String message) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground) // dùng icon chắc chắn có
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());

        if (Build.VERSION.SDK_INT < 33 ||
                ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.POST_NOTIFICATIONS)
                        == PackageManager.PERMISSION_GRANTED) {

            int notificationId = (int) System.currentTimeMillis();
            notificationManager.notify(notificationId, builder.build());
            Log.d("Notification", "Đã hiển thị thông báo");
        } else {
            Log.w("Notification", "Thiếu quyền POST_NOTIFICATIONS");
        }
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelName = "Thông báo giao dịch";
            String channelDescription = "Thông báo giao dịch mới";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, channelName, importance);
            channel.setDescription(channelDescription);

            NotificationManager notificationManager = getApplicationContext().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.POST_NOTIFICATIONS}, 1001);
            }
        }
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        adapter = new AccountAdapter(this, viewModel);
        recyclerView.setAdapter(adapter);
    }

    private void initUI() {
        edtAccountName = findViewById(R.id.edtAccountName);
        edtBalance = findViewById(R.id.edtBalance);
        btnSendRequest = findViewById(R.id.btnSendRequest);
        btnSaveAccount = findViewById(R.id.btnSaveAccount);
        recyclerView = findViewById(R.id.recyclerViewAccounts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    private void observeData() {
        viewModel.getToastMessage().observe(this, message -> Toast.makeText(this, message, Toast.LENGTH_SHORT).show());

        viewModel.getAllStudents().observe(this, accounts -> {
            adapter.setAccounts(accounts);
        });

        viewModel.getNotificationMessage().observe(this, msg -> showNotification("Thông báo giao dịch mới", msg));
    }

    private void loadEvents() {
        btnSaveAccount.setOnClickListener(v -> {
            String accountName = edtAccountName.getText().toString().trim();
            String balanceStr = edtBalance.getText().toString().trim();
            viewModel.insert(accountName, balanceStr);
        });

        btnSendRequest.setOnClickListener(view ->
                myWebSocket.send("/app/update-user", () -> {
                    Log.d("STOMP", "Đã gửi update-user");
                }));
    }
}