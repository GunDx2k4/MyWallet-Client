package com.example.mywallet.DAO.PaymentType;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.mywallet.Models.PaymentType;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PaymentTypeRepository {
    private final PaymentTypeDAO paymentTypeDAO;
    private final LiveData<List<PaymentType>> allPaymentTypes;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public PaymentTypeRepository(Application application) {
        PaymentTypeDatabase db = PaymentTypeDatabase.getDatabase(application);
        paymentTypeDAO = db.paymentTypeDAO();
        allPaymentTypes = paymentTypeDAO.getAllPaymentTypes();
    }

    public LiveData<List<PaymentType>> getAllPaymentTypes() {
        return allPaymentTypes;
    }

    public void insert(PaymentType paymentType) {
        executorService.execute(() -> paymentTypeDAO.insert(paymentType));
    }

    public void update(PaymentType paymentType) {
        executorService.execute(() -> paymentTypeDAO.update(paymentType));
    }

    public void delete(PaymentType paymentType) {
        executorService.execute(() -> paymentTypeDAO.delete(paymentType));
    }

    public void deleteAll() {
        executorService.execute(paymentTypeDAO::deleteAll);
    }
}
