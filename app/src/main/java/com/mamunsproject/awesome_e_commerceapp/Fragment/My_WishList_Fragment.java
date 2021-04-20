package com.mamunsproject.awesome_e_commerceapp.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mamunsproject.awesome_e_commerceapp.Adapter.Wishlist_Adapter;
import com.mamunsproject.awesome_e_commerceapp.Model.Wishlist_Model;
import com.mamunsproject.awesome_e_commerceapp.R;

import java.util.ArrayList;
import java.util.List;


public class My_WishList_Fragment extends Fragment {


    private RecyclerView wishListRecyclerview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_my__wish_list_, container, false);


        wishListRecyclerview =view.findViewById(R.id.my_wishList_Recyclerview);

        wishListRecyclerview.setHasFixedSize(true);
        wishListRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));

        List<Wishlist_Model> wishlist_modelsList=new ArrayList<>();


        Wishlist_Adapter wishlist_adapter=new Wishlist_Adapter(wishlist_modelsList,true);
        wishListRecyclerview.setAdapter(wishlist_adapter);
        wishlist_adapter.notifyDataSetChanged();






        return view;


    }
}