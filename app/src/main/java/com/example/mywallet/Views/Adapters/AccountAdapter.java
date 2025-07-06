package com.example.mywallet.Views.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mywallet.Models.Account;
import com.example.mywallet.R;
import com.example.mywallet.ViewModels.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class AccountAdapter  extends RecyclerView.Adapter<AccountAdapter.AccountViewHolder> {
    private List<Account> accountList = new ArrayList<>();
    private final Context context;
    private final MainViewModel viewModel;

    public AccountAdapter(Context context, MainViewModel viewModel) {
        this.context = context;
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public AccountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_account, parent, false);
        return new AccountViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AccountViewHolder holder, int position) {
        Account account = accountList.get(position);
        holder.txtAccountName.setText(account.getName());

        // Format số dư
        holder.txtBalance.setText(String.format("%,.0f VND", account.getBalance()));

        // Xử lý sự kiện xóa
        holder.btnDelete.setOnClickListener(v -> showDeleteConfirmationDialog(account, position));

    }

    @Override
    public int getItemCount() {
        return accountList.size();
    }

    public void setAccounts(List<Account> accountList) {
        this.accountList = accountList;
        notifyDataSetChanged();
    }

    private void showDeleteConfirmationDialog(Account account, int position) {
        new AlertDialog.Builder(context)
                .setTitle("Xác nhận xóa")
                .setMessage("Bạn có chắc chắn muốn xóa tài khoản \"" + account.getName() + "\" không?")
                .setPositiveButton("Xóa", (dialog, which) -> deleteAccount(account, position))
                .setNegativeButton("Hủy", null)
                .show();
    }

    private void deleteAccount(Account account, int position) {
        try {
            viewModel.delete(account);
            notifyItemRemoved(position);
            Toast.makeText(context, "Xóa tài khoản thành công!", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e) {
            Toast.makeText(context, "Xóa tài khoản thất bại!", Toast.LENGTH_SHORT).show();

        }
    }

    public static class AccountViewHolder extends RecyclerView.ViewHolder {
        TextView txtAccountName, txtBalance;
        ImageButton btnDelete;

        public AccountViewHolder(@NonNull View itemView) {
            super(itemView);
            txtAccountName = itemView.findViewById(R.id.txtAccountName);
            txtBalance = itemView.findViewById(R.id.txtBalance);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
