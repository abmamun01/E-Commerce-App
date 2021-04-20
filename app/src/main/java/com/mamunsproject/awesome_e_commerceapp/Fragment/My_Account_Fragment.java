package com.mamunsproject.awesome_e_commerceapp.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mamunsproject.awesome_e_commerceapp.Activity.My_Adresses_Activity;
import com.mamunsproject.awesome_e_commerceapp.R;



public class My_Account_Fragment extends Fragment {

    public static final int MANAGE_ADDRESS=1;

    private Button viewAllAddressButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

       View view= inflater.inflate(R.layout.fragment_my__account_, container, false);


       viewAllAddressButton=view.findViewById(R.id.view_all_adresses_button);
       viewAllAddressButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent myAddressIntent=new Intent(getContext(), My_Adresses_Activity.class);

               myAddressIntent.putExtra("MODE",MANAGE_ADDRESS);
               startActivity(myAddressIntent);
           }
       });


       return view;
    }
}