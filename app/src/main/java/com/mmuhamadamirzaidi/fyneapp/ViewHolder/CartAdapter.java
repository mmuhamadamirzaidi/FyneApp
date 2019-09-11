package com.mmuhamadamirzaidi.fyneapp.ViewHolder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.mmuhamadamirzaidi.fyneapp.Interface.ItemClickListener;
import com.mmuhamadamirzaidi.fyneapp.Model.Order;
import com.mmuhamadamirzaidi.fyneapp.R;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView item_cart_product_name, item_cart_price;

    public ImageView item_cart_product_image;

    public ElegantNumberButton item_cart_count;

    private ItemClickListener itemClickListener;

    public CartViewHolder(@NonNull View itemView, TextView item_cart_product_name) {
        super(itemView);
        this.item_cart_product_name = item_cart_product_name;
    }

    public CartViewHolder(@NonNull View itemView) {
        super(itemView);

        item_cart_product_name = (TextView) itemView.findViewById(R.id.item_cart_product_name);
        item_cart_price = (TextView) itemView.findViewById(R.id.item_cart_price);

        item_cart_product_image = (ImageView) itemView.findViewById(R.id.item_cart_product_image);

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

    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.item_cart, viewGroup, false);
        return new CartViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder cartViewHolder, final int i) {


//        cartViewHolder.item_cart_count.setTypeface(ResourcesCompat.getFont(context.getApplicationContext(), R.font.mr));

        cartViewHolder.item_cart_count.setNumber(listData.get(i).getQuantity());

        Locale locale = new Locale("en", "MY");
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);

        int price = (Integer.parseInt(listData.get(i).getPrice()))*(Integer.parseInt(listData.get(i).getQuantity()));

        cartViewHolder.item_cart_product_name.setText(listData.get(i).getProductName());
        cartViewHolder.item_cart_price.setText(fmt.format(price));

        Picasso.with(context.getApplicationContext())
                .load(listData.get(i).getProductImage())
                .into(cartViewHolder.item_cart_product_image);
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}
