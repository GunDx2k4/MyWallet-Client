package com.example.mywallet.Views.Activities;


import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
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
    private ImageView imageView;
    private MyWebSocket myWebSocket;

    private static final String CHANNEL_ID = "websocket";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
        initViewModel();
        observeData();
        loadEvents();

        requestNotificationPermission();
        createNotificationChannel();
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
    }

    private void initUI() {
        imageView = findViewById(R.id.imageView);
    }


    private void observeData() {

    }

    private void loadEvents() {
        imageView.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this,"Tạo mới giao dịch",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(MainActivity.this, TransactionActivity.class);
            startActivity(intent);
        });
    }
}