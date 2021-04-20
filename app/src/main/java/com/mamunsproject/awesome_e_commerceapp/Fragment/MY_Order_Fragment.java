package com.mamunsproject.awesome_e_commerceapp.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mamunsproject.awesome_e_commerceapp.Adapter.My_Order_Adapter;
import com.mamunsproject.awesome_e_commerceapp.Model.My_Order_Item_Model;
import com.mamunsproject.awesome_e_commerceapp.R;

import java.util.ArrayList;
import java.util.List;

public class MY_Order_Fragment extends Fragment {

    private RecyclerView myOrderRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_m_y__order_, container, false);

        myOrderRecyclerView = view.findViewById(R.id.myOrders_recyclerView);
        myOrderRecyclerView.setHasFixedSize(true);
        myOrderRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));



        List<My_Order_Item_Model> my_order_item_modelList=new ArrayList<>();

        my_order_item_modelList.add(new My_Order_Item_Model(R.drawable.iphone,2,"I phone","Delivered On Monday 15th Jan 2021"));
        my_order_item_modelList.add(new My_Order_Item_Model(R.drawable.iphone2,3,"I Phone Xs Max","Delivered On Monday 15th Jan 2021"));
        my_order_item_modelList.add(new My_Order_Item_Model(R.drawable.iphone,4,"I phone","Cancelled"));
        my_order_item_modelList.add(new My_Order_Item_Model(R.drawable.iphone,1,"I phone","Delivered On Monday 15th Jan 2021"));


        My_Order_Adapter my_order_adapter=new My_Order_Adapter(my_order_item_modelList);
        myOrderRecyclerView.setAdapter(my_order_adapter);
        my_order_adapter.notifyDataSetChanged();


        return view;

    }
}