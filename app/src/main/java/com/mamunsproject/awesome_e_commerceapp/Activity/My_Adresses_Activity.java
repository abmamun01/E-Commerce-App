package com.mamunsproject.awesome_e_commerceapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.mamunsproject.awesome_e_commerceapp.Adapter.Addresses_Adapter;
import com.mamunsproject.awesome_e_commerceapp.Model.Adresses_Model;
import com.mamunsproject.awesome_e_commerceapp.R;

import java.util.ArrayList;
import java.util.List;

import static com.mamunsproject.awesome_e_commerceapp.Activity.Delivery_Activity.SELECT_ADDRESS;

public class My_Adresses_Activity extends AppCompatActivity {

    private RecyclerView myAddressesRecyclerview;
    private Button deliverHereButton;
    private static Addresses_Adapter addresses_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__adresses_);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("My Adresses");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        myAddressesRecyclerview = findViewById(R.id.addresses_recyclerview);
        deliverHereButton=findViewById(R.id.deliver_hare_button);

        myAddressesRecyclerview.setHasFixedSize(true);
        myAddressesRecyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        List<Adresses_Model> adresses_modelList = new ArrayList<>();
        adresses_modelList.add(new Adresses_Model("Abdullah Al Mamun", "Mukundi,Araihazar", "1350", true));
        adresses_modelList.add(new Adresses_Model("Abdullah Al Mamun", "Mukundi,Araihazar", "1350", false));
        adresses_modelList.add(new Adresses_Model("Abdullah Al Mamun", "Mukundi,Araihazar", "1350", false));
        adresses_modelList.add(new Adresses_Model("Abdullah Al Mamun", "Mukundi,Araihazar", "1350", false));
        adresses_modelList.add(new Adresses_Model("Abdullah Al Mamun", "Mukundi,Araihazar", "1350", false));
        adresses_modelList.add(new Adresses_Model("Abdullah Al Mamun", "Mukundi,Araihazar", "1350", false));
        adresses_modelList.add(new Adresses_Model("Abdullah Al Mamun", "Mukundi,Araihazar", "1350", false));
        adresses_modelList.add(new Adresses_Model("Abdullah Al Mamun", "Mukundi,Araihazar", "1350", false));

        int mode=getIntent().getIntExtra("MODE",-1);
        if (mode==SELECT_ADDRESS){
            deliverHereButton.setVisibility(View.VISIBLE);
        }else {
            deliverHereButton.setVisibility(View.GONE);

        }
        addresses_adapter = new Addresses_Adapter(adresses_modelList ,mode);
        myAddressesRecyclerview.setAdapter(addresses_adapter);

        //By Default Animation Desable r Jonno karon amra ripple effect add koreci item r vitore
        ((SimpleItemAnimator) myAddressesRecyclerview.getItemAnimator()).setSupportsChangeAnimations(false);
        addresses_adapter.notifyDataSetChanged();

    }

    //For Refreshing ITEMS
    public static void refreshItem(int deselect, int select) {

        //2 ta item refresh korte chai tai 2 ta niyechi
        addresses_adapter.notifyItemChanged(deselect);
        addresses_adapter.notifyItemChanged(select);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}