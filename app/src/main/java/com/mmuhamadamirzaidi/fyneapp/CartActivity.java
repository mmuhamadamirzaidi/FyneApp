package com.mmuhamadamirzaidi.fyneapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mmuhamadamirzaidi.fyneapp.Common.Common;
import com.mmuhamadamirzaidi.fyneapp.Database.Database;
import com.mmuhamadamirzaidi.fyneapp.Model.Order;
import com.mmuhamadamirzaidi.fyneapp.Model.OrderRequest;
import com.mmuhamadamirzaidi.fyneapp.ViewHolder.CartAdapter;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CartActivity extends AppCompatActivity {

    RecyclerView recycler_cart;
    RecyclerView.LayoutManager layoutManager;

    TextView cart_sub_total, cart_delivery_charge, cart_others_charge, cart_grand_total;

    Button cart_button_place_order, cart_button_order_detail;

    List<Order> cart = new ArrayList<>();

    CartAdapter cartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        Toolbar toolbar = findViewById(R.id.cart_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        // Load category
        recycler_cart = (RecyclerView) findViewById(R.id.recycler_cart);
        recycler_cart.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recycler_cart.setLayoutManager(layoutManager);

        cart_sub_total = (TextView) findViewById(R.id.cart_sub_total);
        cart_delivery_charge = (TextView) findViewById(R.id.cart_delivery_charge);
        cart_others_charge = (TextView) findViewById(R.id.cart_others_charge);
        cart_grand_total = (TextView) findViewById(R.id.cart_grand_total);

        cart_button_place_order = (Button) findViewById(R.id.cart_button_place_order);
        cart_button_order_detail = (Button) findViewById(R.id.cart_button_order_detail);

        cart_button_place_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent checkoutIntent = new Intent(CartActivity.this, CheckOutActivity.class);
                checkoutIntent.putExtra("cart_sub_total", Common.cart_sub_total_global);
                checkoutIntent.putExtra("cart_delivery_charge", Common.cart_delivery_charge_global);
                checkoutIntent.putExtra("cart_others_charge", Common.cart_others_charge_global);
                checkoutIntent.putExtra("cart_grand_total", Common.cart_grand_total_global);
                startActivity(checkoutIntent);
            }
        });

        cart_button_order_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent orderIntent = new Intent(CartActivity.this, OrderDetailActivity.class);
                startActivity(orderIntent);
            }
        });

        loadCartListProduct();
    }

    private void loadCartListProduct() {

        cart = new Database(this).getCart();
        cartAdapter = new CartAdapter(cart, this);

        recycler_cart.setAdapter(cartAdapter);

        //Calculate grand total
        int sub_total_initial = 0, grand_total_initial = 0, delivery_charge = 0, others_charge = 0, total_charge;

        for (Order order:cart) {
            delivery_charge = (Integer.parseInt("6"));
            others_charge = (Integer.parseInt("1"));
            total_charge = delivery_charge + others_charge;

            sub_total_initial += (Integer.parseInt(order.getPrice())) * (Integer.parseInt(order.getQuantity()));
            grand_total_initial = sub_total_initial + total_charge;
        }

        Locale locale = new Locale("en", "MY");
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);

        cart_sub_total.setText(fmt.format(sub_total_initial));
        cart_delivery_charge.setText(fmt.format(delivery_charge));
        cart_others_charge.setText(fmt.format(others_charge));
        cart_grand_total.setText(fmt.format(grand_total_initial));

        Common.cart_sub_total_global = (fmt.format(sub_total_initial));
        Common.cart_delivery_charge_global = (fmt.format(delivery_charge));
        Common.cart_others_charge_global = (fmt.format(others_charge));
        Common.cart_grand_total_global = (fmt.format(grand_total_initial));

    }
}
