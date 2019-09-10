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
import com.mmuhamadamirzaidi.fyneapp.Database.Database;
import com.mmuhamadamirzaidi.fyneapp.Interface.ItemClickListener;
import com.mmuhamadamirzaidi.fyneapp.Model.Order;
import com.mmuhamadamirzaidi.fyneapp.Model.Product;
import com.mmuhamadamirzaidi.fyneapp.ViewHolder.ProductViewHolder;
import com.squareup.picasso.Picasso;

public class ProductDetailActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference product;

    ImageView detail_image;

    TextView detail_product_name, detail_product_notification, detail_product_price, detail_product_description;

    FloatingActionButton detail_product_fab_bookmark, detail_product_fab_cart;

    String productId="", quantity = "1";

    Product currentProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        Toolbar toolbar = findViewById(R.id.product_detail_toolbar);
        toolbar.setTitle("");
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

        detail_product_fab_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Database(getBaseContext()).addToCart(new Order(
                        productId,
                        currentProduct.getProductName(),
                        quantity,
                        currentProduct.getProductPrice(),
                        currentProduct.getProductDiscount()
                ));

                Toast.makeText(ProductDetailActivity.this, currentProduct.getProductName()+" added to cart!", Toast.LENGTH_SHORT).show();
            }
        });

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
                currentProduct = dataSnapshot.getValue(Product.class);

                // Set image
                Picasso.with(getBaseContext()).load(currentProduct.getProductImage()).into(detail_image);

                detail_product_name.setText(currentProduct.getProductName());
                detail_product_notification.setText(currentProduct.getNotificationNo());
                detail_product_price.setText(currentProduct.getProductPrice());
                detail_product_description.setText(currentProduct.getProductDescription());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
