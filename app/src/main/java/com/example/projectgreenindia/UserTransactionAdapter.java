package com.example.projectgreenindia;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.payumoney.core.response.TransactionResponse;

import java.util.List;

public class UserTransactionAdapter extends RecyclerView.Adapter<UserTransactionAdapter.MyViewHolder>
{
    private List<UserTransaction> transactionList;

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        public TextView date,amount;

        public MyViewHolder(View view)
        {
            super(view);
            date = view.findViewById(R.id.tv_trans_Date);
            amount = view.findViewById(R.id.tv_trans_Amount);
        }
    }


    public UserTransactionAdapter(List<UserTransaction> transactionList)
    {
        this.transactionList = transactionList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.transactions_row_view, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position)
    {
        UserTransaction transaction = transactionList.get(position);
        holder.date.setText(transaction.getDate());
        holder.amount.setText(transaction.getAmount());
    }

    @Override
    public int getItemCount()
    {
        return transactionList.size();
    }

}
