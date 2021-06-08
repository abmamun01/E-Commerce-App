package com.mamunsproject.awesome_e_commerceapp.Fragment;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mamunsproject.awesome_e_commerceapp.Activity.Product_Details_Activity;
import com.mamunsproject.awesome_e_commerceapp.Adapter.Wishlist_Adapter;
import com.mamunsproject.awesome_e_commerceapp.DB_Queries.DB_Queries;
import com.mamunsproject.awesome_e_commerceapp.Model.Wishlist_Model;
import com.mamunsproject.awesome_e_commerceapp.R;

import java.util.ArrayList;
import java.util.List;


public class My_WishList_Fragment extends Fragment {


    private RecyclerView wishListRecyclerview;
    private Dialog loadingDialog;
    public static Wishlist_Adapter wishlist_adapter=new Wishlist_Adapter(DB_Queries.wishlist_modelList,true);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_my__wish_list_, container, false);

        ///---------Loading Dialog-------------


        loadingDialog = new Dialog(getContext());
        loadingDialog.setContentView(R.layout.loading_progress_dialog);
        loadingDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        loadingDialog.setCancelable(false);
        loadingDialog.getWindow().setBackgroundDrawable(getContext().getDrawable(R.drawable.slider_background));
        loadingDialog.show();
        ///---------Loading Dialog-------------



        wishListRecyclerview =view.findViewById(R.id.my_wishList_Recyclerview);

        wishListRecyclerview.setHasFixedSize(true);
        wishListRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));



        if (DB_Queries.wishlist_modelList.size()==0){
            DB_Queries.wishList.clear();
            DB_Queries.loadWishList(getContext(),loadingDialog,true);

        }else {
            loadingDialog.dismiss();
        }

     //   wishlist_adapter=new Wishlist_Adapter(DB_Queries.wishlist_modelList,true);
        wishListRecyclerview.setAdapter(wishlist_adapter);
        wishlist_adapter.notifyDataSetChanged();



        return view;


    }
}