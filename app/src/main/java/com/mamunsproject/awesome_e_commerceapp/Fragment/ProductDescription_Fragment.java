package com.mamunsproject.awesome_e_commerceapp.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.mamunsproject.awesome_e_commerceapp.R;
public class ProductDescription_Fragment extends Fragment {


    private TextView descriptionBody;
    public String body;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_product_description_, container, false);

        descriptionBody=view.findViewById(R.id.tv_product_description);
        descriptionBody.setText(body);



        return  view;
    }
}