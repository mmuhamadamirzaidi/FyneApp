package com.mmuhamadamirzaidi.fyneapp;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mmuhamadamirzaidi.fyneapp.Interface.ItemClickListener;
import com.mmuhamadamirzaidi.fyneapp.Model.Product;
import com.mmuhamadamirzaidi.fyneapp.ViewHolder.ProductViewHolder;
import com.squareup.picasso.Picasso;

public class ProductDetailActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference product;

    ImageView detail_image;

    TextView detail_product_name, detail_product_notification, detail_product_price, detail_product_description;

    FloatingActionButton detail_product_fab_bookmark, detail_product_fab_cart;

    String productId="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        Toolbar toolbar = findViewById(R.id.product_detail_toolbar);
        toolbar.setTitle("Product's Details");
        setSupportActionBar(toolbar);

        // Init Firebase
        database = FirebaseDatabase.getInstance();
        product = database.getReference("Product");

        // Init view
        detail_image = (ImageView) findViewById(R.id.detail_image);

        detail_product_name = (TextView) findViewById(R.id.detail_product_name);
        detail_product_notification = (TextView) findViewById(R.id.detail_product_notification);
        detail_product_price = (TextView) findViewById(R.id.detail_product_price);
        detail_product_description = (TextView) findViewById(R.id.detail_product_description);
        detail_product_fab_bookmark = (FloatingActionButton) findViewById(R.id.detail_product_fab_bookmark);
        detail_product_fab_cart = (FloatingActionButton) findViewById(R.id.detail_product_fab_cart);

        // Get productId intent
        if (getIntent() != null){
            productId = getIntent().getStringExtra("productId");
        }
        if (!productId.isEmpty() && productId != null){
            loadProductDetail(productId);
        }
    }

    private void loadProductDetail(String productId) {
        product.child(productId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Product product = dataSnapshot.getValue(Product.class);

                // Set image
                Picasso.with(getBaseContext()).load(product.getProductImage()).into(detail_image);

                detail_product_name.setText(product.getProductName());
                detail_product_notification.setText(product.getNotificationNo());
                detail_product_price.setText(product.getProductPrice());
                detail_product_description.setText(product.getProductDescription());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
