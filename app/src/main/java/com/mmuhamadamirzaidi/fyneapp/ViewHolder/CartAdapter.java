package com.mmuhamadamirzaidi.fyneapp.ViewHolder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.mmuhamadamirzaidi.fyneapp.Interface.ItemClickListener;
import com.mmuhamadamirzaidi.fyneapp.Model.Order;
import com.mmuhamadamirzaidi.fyneapp.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView item_cart_product_name, item_cart_price;

    ElegantNumberButton item_cart_count;

    private ItemClickListener itemClickListener;

    public void setItem_cart_product_name(TextView item_cart_product_name) {
        this.item_cart_product_name = item_cart_product_name;
    }

    public CartViewHolder(@NonNull View itemView) {
        super(itemView);

        item_cart_product_name = (TextView) itemView.findViewById(R.id.item_cart_product_name);
        item_cart_price = (TextView) itemView.findViewById(R.id.item_cart_price);

        item_cart_count = (ElegantNumberButton) itemView.findViewById(R.id.item_cart_count);

    }

    @Override
    public void onClick(View v) {

    }
}

public class CartAdapter extends RecyclerView.Adapter<CartViewHolder>{

    private List<Order> listData = new ArrayList<>();
    private Context context;

    public CartAdapter(List<Order> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }

    //    String delivery_charge = "6", others_charge = "1";

    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.item_cart, viewGroup, false);
        return new CartViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder cartViewHolder, int i) {

        cartViewHolder.item_cart_count.setNumber(listData.get(i).getQuantity());

        Locale locale = new Locale("en", "MY");
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);

        int price = (Integer.parseInt(listData.get(i).getPrice()))*(Integer.parseInt(listData.get(i).getQuantity()));

        cartViewHolder.item_cart_product_name.setText(listData.get(i).getProductName());
        cartViewHolder.item_cart_price.setText(fmt.format(price));

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}
