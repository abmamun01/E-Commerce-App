package com.mamunsproject.awesome_e_commerceapp.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mamunsproject.awesome_e_commerceapp.Adapter.ProductSpecificationAdapter;
import com.mamunsproject.awesome_e_commerceapp.Model.ProductSpecificationModel;
import com.mamunsproject.awesome_e_commerceapp.R;

import java.util.ArrayList;
import java.util.List;

public class  ProductSpecification_Fragment extends Fragment {

    private RecyclerView productSpecificationRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_product_specification_, container, false);


        productSpecificationRecyclerView=view.findViewById(R.id.product_specification_recyclerview);

        productSpecificationRecyclerView.setHasFixedSize(true);
        productSpecificationRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        List<ProductSpecificationModel> productSpecificationModelsList=new ArrayList<>();

        productSpecificationModelsList.add(new ProductSpecificationModel(0,"GENARAL"));
        productSpecificationModelsList.add(new ProductSpecificationModel(1,"RAM","4"));
        productSpecificationModelsList.add(new ProductSpecificationModel(1,"RAM","4"));
        productSpecificationModelsList.add(new ProductSpecificationModel(1,"RAM","4"));
        productSpecificationModelsList.add(new ProductSpecificationModel(1,"RAM","4"));
        productSpecificationModelsList.add(new ProductSpecificationModel(1,"RAM","4"));
        productSpecificationModelsList.add(new ProductSpecificationModel(0,"DISPLAY"));
        productSpecificationModelsList.add(new ProductSpecificationModel(1,"RAM","4"));
        productSpecificationModelsList.add(new ProductSpecificationModel(1,"RAM","4"));
        productSpecificationModelsList.add(new ProductSpecificationModel(1,"RAM","4"));
        productSpecificationModelsList.add(new ProductSpecificationModel(1,"RAM","4"));
        productSpecificationModelsList.add(new ProductSpecificationModel(1,"RAM","4"));

        productSpecificationModelsList.add(new ProductSpecificationModel(0,"GENARAL"));
        productSpecificationModelsList.add(new ProductSpecificationModel(1,"RAM","4"));
        productSpecificationModelsList.add(new ProductSpecificationModel(1,"RAM","4"));
        productSpecificationModelsList.add(new ProductSpecificationModel(1,"RAM","4"));
        productSpecificationModelsList.add(new ProductSpecificationModel(1,"RAM","4"));
        productSpecificationModelsList.add(new ProductSpecificationModel(1,"RAM","4"));
        productSpecificationModelsList.add(new ProductSpecificationModel(0,"DISPLAY"));
        productSpecificationModelsList.add(new ProductSpecificationModel(1,"RAM","4"));
        productSpecificationModelsList.add(new ProductSpecificationModel(1,"RAM","4"));
        productSpecificationModelsList.add(new ProductSpecificationModel(1,"RAM","4"));
        productSpecificationModelsList.add(new ProductSpecificationModel(1,"RAM","4"));
        productSpecificationModelsList.add(new ProductSpecificationModel(1,"RAM","4"));

        productSpecificationModelsList.add(new ProductSpecificationModel(0,"GENARAL"));
        productSpecificationModelsList.add(new ProductSpecificationModel(1,"RAM","4"));
        productSpecificationModelsList.add(new ProductSpecificationModel(1,"RAM","4"));
        productSpecificationModelsList.add(new ProductSpecificationModel(1,"RAM","4"));
        productSpecificationModelsList.add(new ProductSpecificationModel(1,"RAM","4"));
        productSpecificationModelsList.add(new ProductSpecificationModel(1,"RAM","4"));
        productSpecificationModelsList.add(new ProductSpecificationModel(0,"DISPLAY"));
        productSpecificationModelsList.add(new ProductSpecificationModel(1,"RAM","4"));
        productSpecificationModelsList.add(new ProductSpecificationModel(1,"RAM","4"));
        productSpecificationModelsList.add(new ProductSpecificationModel(1,"RAM","4"));
        productSpecificationModelsList.add(new ProductSpecificationModel(1,"RAM","4"));
        productSpecificationModelsList.add(new ProductSpecificationModel(1,"RAM","4"));




        ProductSpecificationAdapter productSpecificationAdapter=new ProductSpecificationAdapter(productSpecificationModelsList  );

        productSpecificationRecyclerView.setAdapter(productSpecificationAdapter);
        productSpecificationAdapter.notifyDataSetChanged();



        return view;
    }
}