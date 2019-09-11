package com.mmuhamadamirzaidi.fyneapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mmuhamadamirzaidi.fyneapp.Common.Common;
import com.mmuhamadamirzaidi.fyneapp.Model.OrderRequest;
import com.mmuhamadamirzaidi.fyneapp.ViewHolder.OrderViewHolder;

public class OrderStatusActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference orderrequest;

    RecyclerView recycler_order;
    RecyclerView.LayoutManager layoutManager;

    FirebaseRecyclerAdapter<OrderRequest, OrderViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_status);

        Toolbar toolbar = findViewById(R.id.order_list_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        // Init Firebase
        database = FirebaseDatabase.getInstance();
        orderrequest = database.getReference("OrderRequest");

        // Load category
        recycler_order = (RecyclerView) findViewById(R.id.recycler_order);
        recycler_order.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recycler_order.setLayoutManager(layoutManager);

        loadOrder(Common.currentUser.getUserPhone());
    }

    private void loadOrder(String userPhone) {

        adapter = new FirebaseRecyclerAdapter<OrderRequest, OrderViewHolder>(OrderRequest.class, R.layout.item_order, OrderViewHolder.class, orderrequest.orderByChild("userPhone")) {
            @Override
            protected void populateViewHolder(OrderViewHolder viewHolder, OrderRequest model, int position) {

                viewHolder.item_order_id.setText(adapter.getRef(position).getKey());
//                viewHolder.item_order_date.setText(model.);
                viewHolder.item_order_price.setText(model.getGrandTotal());
                viewHolder.item_order_status.setText(convertCodeToStatus(model.getStatus()));

            }
        };
        recycler_order.setAdapter(adapter);
    }

    private String convertCodeToStatus(String status) {

        if (status.equals("0"))
            return "Processing";
        else if (status.equals("1"))
            return "On the way";
        else if (status.equals("2"))
            return "Delivered";
        else
            return "Cancelled";
    }
}
