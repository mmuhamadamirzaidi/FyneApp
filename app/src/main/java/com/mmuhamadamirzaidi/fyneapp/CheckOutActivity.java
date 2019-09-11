package com.mmuhamadamirzaidi.fyneapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mmuhamadamirzaidi.fyneapp.Common.Common;
import com.mmuhamadamirzaidi.fyneapp.Database.Database;
import com.mmuhamadamirzaidi.fyneapp.Model.Order;
import com.mmuhamadamirzaidi.fyneapp.Model.OrderRequest;
import com.mmuhamadamirzaidi.fyneapp.ViewHolder.CartAdapter;

import java.util.ArrayList;
import java.util.List;

public class CheckOutActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference orderrequest;

    private EditText check_out_delivery_address;
    private Button button_check_out;

    List<Order> cart = new ArrayList<>();

    CartAdapter cartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);

        Toolbar toolbar = findViewById(R.id.check_out_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        check_out_delivery_address = findViewById(R.id.check_out_delivery_address);
        button_check_out = findViewById(R.id.button_check_out);

        check_out_delivery_address.getText().toString().trim();


        // Init Firebase
        database = FirebaseDatabase.getInstance();
        orderrequest = database.getReference("OrderRequest");



//        String cart_sub_total = getIntent().getStringExtra("cart_sub_total");
//        String cart_delivery_charge = getIntent().getStringExtra("cart_delivery_charge");
//        String cart_others_charge = getIntent().getStringExtra("cart_others_charge");
        final String cart_grand_total = getIntent().getStringExtra("cart_grand_total");

        cart = new Database(this).getCart();
        cartAdapter = new CartAdapter(cart, this);

        button_check_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OrderRequest orderRequest = new OrderRequest(
                        Common.currentUser.getUserPhone(),
                        Common.currentUser.getUserName(),
                        check_out_delivery_address.getText().toString().trim(),
                        cart_grand_total,
                        cart
                );

                //Submit to Firebase
                orderrequest.child(String.valueOf(System.currentTimeMillis()))
                        .setValue(orderRequest);

                //Clear cart item

                Common.cart_discount_global = "";
                Common.cart_sub_total_global = "";
                Common.cart_delivery_charge_global = "";
                Common.cart_others_charge_global = "";
                Common.cart_grand_total_global = "";

                new Database(getBaseContext()).clearCart();
                Toast.makeText(CheckOutActivity.this, "Thank you! All orders have been placed.", Toast.LENGTH_SHORT).show();
                SendUserToMainActivity();
            }
        });
    }

    private void SendUserToMainActivity() {
        Intent mainIntent = new Intent(CheckOutActivity.this, MainActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mainIntent);
        finish();
    }
}
