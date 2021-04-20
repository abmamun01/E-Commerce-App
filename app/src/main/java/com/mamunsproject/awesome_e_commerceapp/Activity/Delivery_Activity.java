package com.mamunsproject.awesome_e_commerceapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.mamunsproject.awesome_e_commerceapp.Adapter.CartAdapter;
import com.mamunsproject.awesome_e_commerceapp.Fragment.My_cart_Fragment;
import com.mamunsproject.awesome_e_commerceapp.Model.Cart_item_model;
import com.mamunsproject.awesome_e_commerceapp.R;

import java.util.ArrayList;
import java.util.List;

public class Delivery_Activity extends AppCompatActivity {

    Toolbar toolbar;
    private RecyclerView deliveryRecyclerView;
    private Button change_or_add_new_address_Button;
    public static final int SELECT_ADDRESS = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Delivery");


        deliveryRecyclerView = findViewById(R.id.delivery_recyclerVIew);
        change_or_add_new_address_Button = findViewById(R.id.change_or_add_addres_Button);


        deliveryRecyclerView.setHasFixedSize(true);
        deliveryRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        List<Cart_item_model> cart_item_modelList = new ArrayList<>();

        cart_item_modelList.add(new Cart_item_model(0, R.drawable.iphone, "I Phone", 2, "Rs.4999/=", "Rs.999/-", 1, 0, 0));
        cart_item_modelList.add(new Cart_item_model(0, R.drawable.iphone, "I Phone", 0, "Rs.4999/=", "Rs.999/-", 1, 1, 0));
        cart_item_modelList.add(new Cart_item_model(0, R.drawable.iphone, "I Phone", 2, "Rs.4999/=", "Rs.999/-", 1, 2, 0));

        cart_item_modelList.add(new Cart_item_model(1, "Price (3items)", "Rs. 16999/-", "Free", "Rs.199/-", "Rs.3000/-"));


        CartAdapter cartAdapter = new CartAdapter(cart_item_modelList);
        deliveryRecyclerView.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();

        change_or_add_new_address_Button.setVisibility(View.VISIBLE);
        change_or_add_new_address_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myAddressIntent = new Intent(getApplicationContext(), My_Adresses_Activity.class);

                myAddressIntent.putExtra("MODE", SELECT_ADDRESS);
                startActivity(myAddressIntent);
            }
        });


    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();


        if (id == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}