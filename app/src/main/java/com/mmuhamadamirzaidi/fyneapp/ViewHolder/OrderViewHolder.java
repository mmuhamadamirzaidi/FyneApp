package com.mmuhamadamirzaidi.fyneapp.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mmuhamadamirzaidi.fyneapp.Interface.ItemClickListener;
import com.mmuhamadamirzaidi.fyneapp.R;

public class OrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView item_order_id, item_order_date, item_order_price, item_order_status;

    private ItemClickListener itemClickListener;

    public OrderViewHolder(@NonNull View itemView) {
        super(itemView);

        item_order_id = (TextView) itemView.findViewById(R.id.item_order_id);
        item_order_date = (TextView) itemView.findViewById(R.id.item_order_date);
        item_order_price = (TextView) itemView.findViewById(R.id.item_order_price);
        item_order_status = (TextView) itemView.findViewById(R.id.item_order_status);

        itemView.setOnClickListener(this);
    }

    public OrderViewHolder(@NonNull View itemView, ItemClickListener itemClickListener) {
        super(itemView);
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {

        itemClickListener.onClick(v, getAdapterPosition(), false);
    }
}
