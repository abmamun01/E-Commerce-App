package com.mamunsproject.awesome_e_commerceapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;

import com.mamunsproject.awesome_e_commerceapp.Adapter.GridProductVIew_Layout_Adapter;
import com.mamunsproject.awesome_e_commerceapp.Adapter.Wishlist_Adapter;
import com.mamunsproject.awesome_e_commerceapp.Model.Horizontal_Product_scrollModel;
import com.mamunsproject.awesome_e_commerceapp.Model.Wishlist_Model;
import com.mamunsproject.awesome_e_commerceapp.R;

import java.util.ArrayList;
import java.util.List;

public class View_All_Activity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private GridView gridView;
    public static List<Horizontal_Product_scrollModel> horizontal_product_scrollModelList;
    public static List<Wishlist_Model> wishlist_modelsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__all_);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(getIntent().getStringExtra("title"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        recyclerView = findViewById(R.id.recyclerview);
        gridView = findViewById(R.id.gridview);

        int layout_code = getIntent().getIntExtra("layout_code", -1);


        if (layout_code == 0) {


            recyclerView.setVisibility(View.VISIBLE);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));



            Wishlist_Adapter adapter = new Wishlist_Adapter(wishlist_modelsList, false);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();

        } else if (layout_code == 1) {


            gridView.setVisibility(View.VISIBLE);



//            horizontal_product_scrollModelList.add(new Horizontal_Product_scrollModel(R.drawable.light, "I Phone X", "A13 Bionic", "999$"));
//            horizontal_product_scrollModelList.add(new Horizontal_Product_scrollModel(R.drawable.cycler, "I Phone 11", "A13 Bionic", "999$"));
//            horizontal_product_scrollModelList.add(new Horizontal_Product_scrollModel(R.drawable.headphone, "Samsung X", "A13 Bionic", "999$"));
//            horizontal_product_scrollModelList.add(new Horizontal_Product_scrollModel(R.drawable.t_shirt1, "Xiomi", "A13 Bionic", "999$"));
//            horizontal_product_scrollModelList.add(new Horizontal_Product_scrollModel(R.drawable.tshirt2, "Oppo X", "A13 Bionic", "999$"));
//            horizontal_product_scrollModelList.add(new Horizontal_Product_scrollModel(R.drawable.tshirt3, "Vivo X", "A13 Bionic", "999$"));
//            horizontal_product_scrollModelList.add(new Horizontal_Product_scrollModel(R.drawable.cycler, "POCO F1", "A13 Bionic", "999$"));
//            horizontal_product_scrollModelList.add(new Horizontal_Product_scrollModel(R.drawable.headphone, "NOKIA", "A13 Bionic", "999$"));
//
//            horizontal_product_scrollModelList.add(new Horizontal_Product_scrollModel(R.drawable.light, "I Phone X", "A13 Bionic", "999$"));
//            horizontal_product_scrollModelList.add(new Horizontal_Product_scrollModel(R.drawable.cycler, "I Phone 11", "A13 Bionic", "999$"));
//            horizontal_product_scrollModelList.add(new Horizontal_Product_scrollModel(R.drawable.headphone, "Samsung X", "A13 Bionic", "999$"));
//            horizontal_product_scrollModelList.add(new Horizontal_Product_scrollModel(R.drawable.t_shirt1, "Xiomi", "A13 Bionic", "999$"));
//            horizontal_product_scrollModelList.add(new Horizontal_Product_scrollModel(R.drawable.tshirt2, "Oppo X", "A13 Bionic", "999$"));
//            horizontal_product_scrollModelList.add(new Horizontal_Product_scrollModel(R.drawable.tshirt3, "Vivo X", "A13 Bionic", "999$"));
//            horizontal_product_scrollModelList.add(new Horizontal_Product_scrollModel(R.drawable.cycler, "POCO F1", "A13 Bionic", "999$"));
//            horizontal_product_scrollModelList.add(new Horizontal_Product_scrollModel(R.drawable.headphone, "NOKIA", "A13 Bionic", "999$"));


            GridProductVIew_Layout_Adapter gridProductVIew_layout_adapter = new GridProductVIew_Layout_Adapter(horizontal_product_scrollModelList);
            gridView.setAdapter(gridProductVIew_layout_adapter);

        }

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home) {

        }
        return super.onOptionsItemSelected(item);
    }
}