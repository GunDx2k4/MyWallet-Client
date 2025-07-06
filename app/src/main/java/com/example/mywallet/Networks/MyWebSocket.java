package com.example.mywallet.Networks;

import android.util.Log;

import com.example.mywallet.ViewModels.MainViewModel;

import java.util.Objects;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import ua.naiksoftware.stomp.Stomp;
import ua.naiksoftware.stomp.StompClient;

public class MyWebSocket {
    private final MainViewModel viewModel;
    private StompClient stompClient;

    private static final String WEBSOCKET_URL = "ws://192.168.0.102:8080/ws";
    public MyWebSocket(MainViewModel mainViewModel) {
        this.viewModel = mainViewModel;

        stompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, WEBSOCKET_URL);
        stompClient.connect();

        initWebSocket();
    }

    private void initWebSocket() {
        stompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, WEBSOCKET_URL);
        stompClient.connect();

        try {
            Disposable lifecycleDisposable = stompClient.lifecycle().subscribe(event -> {
                switch (event.getType()) {
                    case OPENED:
                        Log.d("STOMP", "Kết nối thành công");
                        subscribeToUserChannel();
                        break;
                    case ERROR:
                        Log.e("STOMP", "Lỗi kết nối", event.getException());
                        break;
                    case CLOSED:
                        Log.d("STOMP", "Đã đóng kết nối");
                        break;
                }
            });
            Log.d("STOMP", lifecycleDisposable.toString());
        }
        catch (Exception ex) {
            Log.d("STOMP", Objects.requireNonNull(ex.getMessage()));
        }
    }

    private void subscribeToUserChannel() {
        try {
            Disposable notifyDisposable = stompClient.topic("/user")
                    .subscribe(msg -> {
                        Log.d("STOMP", "Nhận: " + msg.getPayload());
                        viewModel.setMessage( msg.getPayload());
                    });
            Log.d("STOMP", notifyDisposable.toString());
        }
        catch (Exception ex) {
            Log.d("STOMP", Objects.requireNonNull(ex.getMessage()));
        }
    }

    public void send(String destination, Action action) {
        try {
            Disposable sendDisposable = stompClient.send(destination).subscribe(action);
            Log.d("STOMP", sendDisposable.toString());
        }
        catch (Exception ex) {
            Log.d("STOMP", Objects.requireNonNull(ex.getMessage()));
        }
    }
}