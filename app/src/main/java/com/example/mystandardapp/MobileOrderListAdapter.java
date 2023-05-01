package com.example.mystandardapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MobileOrderListAdapter extends RecyclerView.Adapter<MobileOrderListAdapter.MobileOrderViewHolder> {

    private final LayoutInflater inflater;
    private List<MobileOrder> mobileOrders;

    MobileOrderListAdapter(Context context) {inflater = LayoutInflater.from(context);}



    @NonNull
    @Override
    public MobileOrderListAdapter.MobileOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.recyclerview_item, parent, false);
    }

    @Override
    public void onBindViewHolder(@NonNull MobileOrderListAdapter.MobileOrderViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MobileOrderViewHolder extends RecyclerView.ViewHolder {
        public MobileOrderViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
