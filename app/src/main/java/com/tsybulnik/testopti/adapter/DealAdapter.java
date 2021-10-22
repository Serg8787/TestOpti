package com.tsybulnik.testopti.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tsybulnik.testopti.R;
import com.tsybulnik.testopti.model.Deal;

import java.util.List;

public class DealAdapter extends RecyclerView.Adapter<DealAdapter.ViewHolder>{
    private LayoutInflater inflater;
    private List<Deal> dealList;
    public DealAdapter(Context context, List<Deal> dealList) {
        this.dealList = dealList;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.layout_item_deal, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Deal deal = dealList.get(position);
        if(deal.getAutoComplete().equals("Расход")){
            holder.tvItemSum.setTextColor(Color.DKGRAY);
            holder.ivIncomeExpense.setImageResource(R.drawable.icons8_expense);
        } else {
            holder.tvItemSum.setTextColor(Color.GREEN);
            holder.ivIncomeExpense.setImageResource(R.drawable.icons8_income);
        }
        holder.tvItemSum.setText(deal.getSum());
        holder.tvItemData.setText(deal.getDate());
        holder.tvnameTransaction.setText(deal.getNameTransaction());

    }

    @Override
    public int getItemCount() {
        return dealList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView tvItemSum;
        final TextView tvItemData;
        final TextView tvnameTransaction;
        final ImageView ivIncomeExpense;
        ViewHolder(View view){
            super(view);
            tvItemData = view.findViewById(R.id.tvItemData);
            tvItemSum = view.findViewById(R.id.tvItemSum);
            tvnameTransaction = view.findViewById(R.id.tvnameTransaction);
            ivIncomeExpense = view.findViewById(R.id.ivIncomeExpense);
        }
    }
}

