package com.mamunsproject.awesome_e_commerceapp.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mamunsproject.awesome_e_commerceapp.Activity.Add_Adress_Activity;
import com.mamunsproject.awesome_e_commerceapp.Activity.Delivery_Activity;
import com.mamunsproject.awesome_e_commerceapp.Adapter.CartAdapter;
import com.mamunsproject.awesome_e_commerceapp.Model.Cart_item_model;
import com.mamunsproject.awesome_e_commerceapp.R;

import java.util.ArrayList;
import java.util.List;


public class My_cart_Fragment extends Fragment {

    private RecyclerView cartItemsRecyclerview;
    private Button continueButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_cart_, container, false);

        cartItemsRecyclerview=view.findViewById(R.id.cart_item_recyclerview);
        continueButton=view.findViewById(R.id.cart_continue_button);



        cartItemsRecyclerview.setHasFixedSize(true);
        cartItemsRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));


        List<Cart_item_model> cart_item_modelList=new ArrayList<>();

        cart_item_modelList.add(new Cart_item_model(0,R.drawable.iphone,"I Phone",2,"Rs.4999/=","Rs.999/-",1,0,0));
        cart_item_modelList.add(new Cart_item_model(0,R.drawable.iphone,"I Phone",0,"Rs.4999/=","Rs.999/-",1,1,0));
        cart_item_modelList.add(new Cart_item_model(0,R.drawable.iphone,"I Phone",2,"Rs.4999/=","Rs.999/-",1,2,0));

        cart_item_modelList.add(new Cart_item_model(1,"Price (3items)","Rs. 16999/-","Free","Rs.199/-","Rs.3000/-"));


        CartAdapter cartAdapter=new CartAdapter(cart_item_modelList);
        cartItemsRecyclerview.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();


        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContext().startActivity(new Intent(getContext(), Add_Adress_Activity.class));
            }
        });
        return view;
    }
}