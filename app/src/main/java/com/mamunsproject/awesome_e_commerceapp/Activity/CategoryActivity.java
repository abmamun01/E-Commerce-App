package com.mamunsproject.awesome_e_commerceapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.mamunsproject.awesome_e_commerceapp.Adapter.CategoryAdapter;
import com.mamunsproject.awesome_e_commerceapp.Adapter.HomePage_Adapter;
import com.mamunsproject.awesome_e_commerceapp.DB_Queries.DB_Queries;
import com.mamunsproject.awesome_e_commerceapp.Model.CategoryModel;
import com.mamunsproject.awesome_e_commerceapp.Model.HomePage_Model;
import com.mamunsproject.awesome_e_commerceapp.Model.Horizontal_Product_scrollModel;
import com.mamunsproject.awesome_e_commerceapp.Model.SliderModel;
import com.mamunsproject.awesome_e_commerceapp.R;

import java.util.ArrayList;
import java.util.List;

import static com.mamunsproject.awesome_e_commerceapp.DB_Queries.DB_Queries.lists;
import static com.mamunsproject.awesome_e_commerceapp.DB_Queries.DB_Queries.loadedCategoriesNames;

public class CategoryActivity extends AppCompatActivity {

    private RecyclerView categoryRecyclerview;
    private HomePage_Adapter adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String title=getIntent().getStringExtra("categoryName");
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        categoryRecyclerview=findViewById(R.id.category_recyclerview_ID);




/////============================ Banner Slider==============================

        List<SliderModel> sliderModelsList = new ArrayList<>();

        sliderModelsList = new ArrayList<>();
//        sliderModelsList.add(new SliderModel(R.drawable.ic_baseline_verified_user_24, "#FF5733"));
//        sliderModelsList.add(new SliderModel(R.drawable.ic_attention, "#FF5733"));
//        sliderModelsList.add(new SliderModel(R.drawable.banner_sample, "#FF5733"));
//
//        sliderModelsList.add(new SliderModel(R.drawable.ic_baseline_email_24, "#FF5733"));
//        sliderModelsList.add(new SliderModel(R.drawable.ic_baseline_home_24, "#FF5733"));
//        sliderModelsList.add(new SliderModel(R.drawable.ic_baseline_add_shopping_cart_24, "#FF5733"));
//        sliderModelsList.add(new SliderModel(R.drawable.ic_baseline_add_shopping_cart_24, "#FF5733"));
//        sliderModelsList.add(new SliderModel(R.drawable.ic_baseline_verified_user_24, "#FF5733"));
//        sliderModelsList.add(new SliderModel(R.drawable.ic_baseline_verified_user_24, "#FF5733"));
//        sliderModelsList.add(new SliderModel(R.drawable.ic_baseline_verified_user_24, "#FF5733"));
//
//
//        sliderModelsList.add(new SliderModel(R.drawable.ic_attention, "#FF5733"));
//        sliderModelsList.add(new SliderModel(R.drawable.banner_sample, "#FF5733"));
//        sliderModelsList.add(new SliderModel(R.drawable.ic_baseline_email_24, "#FF5733"));
//

        ////============================ Banner Slider==============================


        /////============================Strip Ad==============================


/////============================Strip Ad==============================


        /////============================HORIZONTAL PRODUCT LAYOUT==============================



        List<Horizontal_Product_scrollModel> horizontal_product_scrollModelList = new ArrayList<>();

//        horizontal_product_scrollModelList.add(new Horizontal_Product_scrollModel(R.drawable.iphone, "I Phone X", "A13 Bionic", "999$"));
//        horizontal_product_scrollModelList.add(new Horizontal_Product_scrollModel(R.drawable.iphone2, "I Phone 11", "A13 Bionic", "999$"));
//        horizontal_product_scrollModelList.add(new Horizontal_Product_scrollModel(R.drawable.iphone, "Samsung X", "A13 Bionic", "999$"));
//        horizontal_product_scrollModelList.add(new Horizontal_Product_scrollModel(R.drawable.iphone2, "Xiomi", "A13 Bionic", "999$"));
//        horizontal_product_scrollModelList.add(new Horizontal_Product_scrollModel(R.drawable.iphone2, "Oppo X", "A13 Bionic", "999$"));
//        horizontal_product_scrollModelList.add(new Horizontal_Product_scrollModel(R.drawable.iphone, "Vivo X", "A13 Bionic", "999$"));
//        horizontal_product_scrollModelList.add(new Horizontal_Product_scrollModel(R.drawable.iphone, "POCO F1", "A13 Bionic", "999$"));
//        horizontal_product_scrollModelList.add(new Horizontal_Product_scrollModel(R.drawable.iphone2, "NOKIA", "A13 Bionic", "999$"));
//

        /////============================HORIZONTAL PRODUCT LAYOUT==============================


/////============================GRID PRODUCT LAYOUT==============================


/////============================GRID PRODUCT LAYOUT==============================


        //////////////////////////////////////////////////////////////////////////////////



        categoryRecyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        //        homePage_modelList.add(new HomePage_Model(0, sliderModelsList));
//        homePage_modelList.add(new HomePage_Model(1, R.drawable.iphone, "#000000"));
//        homePage_modelList.add(new HomePage_Model(2, "Deals Of The Day", horizontal_product_scrollModelList));
//        homePage_modelList.add(new HomePage_Model(3, "Deals Of The Day", horizontal_product_scrollModelList));
//        homePage_modelList.add(new HomePage_Model(1, R.drawable.iphone2, "#F50057"));
//        homePage_modelList.add(new HomePage_Model(2, "Deals Of The Day", horizontal_product_scrollModelList));
//        homePage_modelList.add(new HomePage_Model(3, "Deals Of The Day", horizontal_product_scrollModelList));
//        homePage_modelList.add(new HomePage_Model(1, R.drawable.iphone, "#F9A825"));
//

        int listPosition=0;
        for (int x =0;x<loadedCategoriesNames.size();x++){
            if (loadedCategoriesNames.get(x).equals(title.toUpperCase())){
                listPosition=x;

            }
        }

        if (listPosition==0){

            loadedCategoriesNames.add(title.toUpperCase());
            lists.add(new ArrayList<HomePage_Model>());
            adapter = new HomePage_Adapter(lists.get(loadedCategoriesNames.size()-1));
            DB_Queries.loadFragmentData(adapter, this,loadedCategoriesNames.size()-1,title);

        }else {
            adapter = new HomePage_Adapter(lists.get(listPosition));



        }


        categoryRecyclerview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        //////////////////////////////////////////////////////////////////////////////////




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_icon, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id=item.getItemId();

        if (id==R.id.main_searchIcon){
            //todo:search


            return true;
        }else if (id==android.R.id.home){
            finish();
            return true;

        }

        return super.onOptionsItemSelected(item);
    }


}