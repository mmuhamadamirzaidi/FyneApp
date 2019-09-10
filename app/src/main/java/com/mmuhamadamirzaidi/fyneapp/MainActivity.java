package com.mmuhamadamirzaidi.fyneapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mmuhamadamirzaidi.fyneapp.Common.Common;
import com.mmuhamadamirzaidi.fyneapp.Interface.ItemClickListener;
import com.mmuhamadamirzaidi.fyneapp.Model.Category;
import com.mmuhamadamirzaidi.fyneapp.ViewHolder.CategoryViewHolder;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    FirebaseDatabase database;
    DatabaseReference category;

    ImageView header_profile_image;

    TextView header_fullname, header_identity_card;

    RecyclerView recycler_category;
    RecyclerView.LayoutManager layoutManager;

    FirebaseRecyclerAdapter<Category, CategoryViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.category_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        // Init Firebase
        database = FirebaseDatabase.getInstance();
        category = database.getReference("Category");

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cartIntent = new Intent(MainActivity.this, CartActivity.class);
                startActivity(cartIntent);
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));

        // Set user informations
        View headerView = navigationView.getHeaderView(0);
        header_fullname = (TextView) headerView.findViewById(R.id.header_fullname);
        header_identity_card = (TextView) headerView.findViewById(R.id.header_identity_card);
        header_profile_image = (ImageView) headerView.findViewById(R.id.header_profile_image);

        header_fullname.setText(Common.currentUser.getUserName());
        header_identity_card.setText(Common.currentUser.getUserIdentityCard());

        // Set image
        Picasso.with(getBaseContext()).load(Common.currentUser.getUserImage()).into(header_profile_image);

        // Load category
        recycler_category = (RecyclerView) findViewById(R.id.recycler_category);
        recycler_category.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recycler_category.setLayoutManager(layoutManager);

        loadCategory();
    }

    private void loadCategory() {

        adapter = new FirebaseRecyclerAdapter<Category, CategoryViewHolder>(Category.class, R.layout.item_category, CategoryViewHolder.class, category) {
            @Override
            protected void populateViewHolder(CategoryViewHolder viewHolder, Category model, int position) {
                viewHolder.category_name.setText(model.getName());

                Picasso.with(getBaseContext()).load(model.getImage()).into(viewHolder.category_image);

                final Category clickItem = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {

                        // Get CategoryId and send to new activity
                        Intent product_id = new Intent(MainActivity.this, ProductListActivity.class);
                        product_id.putExtra("categoryId", adapter.getRef(position).getKey());
                        startActivity(product_id);
                    }
                });
            }
        };
        recycler_category.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_products) {
            // Handle the camera action
        } else if (id == R.id.nav_holders) {

        } else if (id == R.id.nav_cart) {

        } else if (id == R.id.nav_order_status) {

        }else if (id == R.id.nav_wishlist) {

        }else if (id == R.id.banned_products) {

        }else if (id == R.id.news) {

        }else if (id == R.id.nav_account) {

        }else if (id == R.id.nav_settings) {

        }else if (id == R.id.nav_sign_out) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
