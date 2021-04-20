package com.mamunsproject.awesome_e_commerceapp.Fragment;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airbnb.lottie.LottieAnimationView;

import com.mamunsproject.awesome_e_commerceapp.Adapter.CategoryAdapter;
import com.mamunsproject.awesome_e_commerceapp.Adapter.HomePage_Adapter;
import com.mamunsproject.awesome_e_commerceapp.DB_Queries.DB_Queries;

import com.mamunsproject.awesome_e_commerceapp.Model.HomePage_Model;
import com.mamunsproject.awesome_e_commerceapp.Model.SliderModel;
import com.mamunsproject.awesome_e_commerceapp.R;

import java.util.ArrayList;
import java.util.List;

import static com.mamunsproject.awesome_e_commerceapp.DB_Queries.DB_Queries.categoryModelList;
import static com.mamunsproject.awesome_e_commerceapp.DB_Queries.DB_Queries.firebaseFirestore;
import static com.mamunsproject.awesome_e_commerceapp.DB_Queries.DB_Queries.lists;
import static com.mamunsproject.awesome_e_commerceapp.DB_Queries.DB_Queries.loadedCategoriesNames;


public class Home_Fragment extends Fragment {

    private RecyclerView categoryRecyclerview;
    private CategoryAdapter categoryAdapter;
    RecyclerView homePage_RecyclerView;

    HomePage_Adapter adapter;
    private LottieAnimationView no_internet_connection;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_, container, false);

        no_internet_connection=view.findViewById(R.id.no_internet_connection);
        ConnectivityManager connectivityManager= (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();








        if (networkInfo != null && networkInfo.isConnected()==true) {
            no_internet_connection.setVisibility(View.GONE);




            categoryRecyclerview = view.findViewById(R.id.category_recyclerview);
            categoryRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
            categoryRecyclerview.setHasFixedSize(true);
            categoryAdapter = new CategoryAdapter(categoryModelList);
            categoryRecyclerview.setAdapter(categoryAdapter);


            //NULL MANE AGE KOKHONO APP TA CHALU HOYNAI
            if (categoryModelList.size()==0) {

                DB_Queries.loadCategories(categoryAdapter, getContext());

                //AR MANE AGE APP CHALU HOICHE AKHON LOAD NA KORE REFRESH KORBO
            } else {
                categoryAdapter.notifyDataSetChanged();
            }


/////============================ Banner Slider==============================

            List<SliderModel> sliderModelsList = new ArrayList<>();


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
//
//        sliderModelsList.add(new SliderModel(R.drawable.ic_attention, "#FF5733"));
//        sliderModelsList.add(new SliderModel(R.drawable.banner_sample, "#FF5733"));
//        sliderModelsList.add(new SliderModel(R.drawable.ic_baseline_email_24, "#FF5733"));


            ////====================================== Banner Slider========================================


            /////============================Strip Ad==============================


/////============================Strip Ad==============================


            /////============================HORIZONTAL PRODUCT LAYOUT==============================
//
//
//        List<Horizontal_Product_scrollModel> horizontal_product_scrollModelList = new ArrayList<>();
//
//        horizontal_product_scrollModelList.add(new Horizontal_Product_scrollModel(R.drawable.light, "You and me X", "A13 Bionic", "999$"));
//        horizontal_product_scrollModelList.add(new Horizontal_Product_scrollModel(R.drawable.cycler, "I Phone 11", "A13 Bionic", "999$"));
//        horizontal_product_scrollModelList.add(new Horizontal_Product_scrollModel(R.drawable.headphone, "Samsung X", "A13 Bionic", "999$"));
//        horizontal_product_scrollModelList.add(new Horizontal_Product_scrollModel(R.drawable.t_shirt1, "Xiomi", "A13 Bionic", "999$"));
//        horizontal_product_scrollModelList.add(new Horizontal_Product_scrollModel(R.drawable.tshirt2, "Oppo X", "A13 Bionic", "999$"));
//        horizontal_product_scrollModelList.add(new Horizontal_Product_scrollModel(R.drawable.tshirt3, "Vivo X", "A13 Bionic", "999$"));
//        horizontal_product_scrollModelList.add(new Horizontal_Product_scrollModel(R.drawable.cycler, "POCO F1", "A13 Bionic", "999$"));
//        horizontal_product_scrollModelList.add(new Horizontal_Product_scrollModel(R.drawable.headphone, "NOKIA", "A13 Bionic", "999$"));
//        horizontal_product_scrollModelList.add(new Horizontal_Product_scrollModel(R.drawable.cycler, "I Phone 11", "A13 Bionic", "999$"));
//        horizontal_product_scrollModelList.add(new Horizontal_Product_scrollModel(R.drawable.headphone, "Samsung X", "A13 Bionic", "999$"));
//        horizontal_product_scrollModelList.add(new Horizontal_Product_scrollModel(R.drawable.t_shirt1, "Xiomi", "A13 Bionic", "999$"));
//        horizontal_product_scrollModelList.add(new Horizontal_Product_scrollModel(R.drawable.tshirt2, "Oppo X", "A13 Bionic", "999$"));
//        horizontal_product_scrollModelList.add(new Horizontal_Product_scrollModel(R.drawable.tshirt3, "Vivo X", "A13 Bionic", "999$"));
//        horizontal_product_scrollModelList.add(new Horizontal_Product_scrollModel(R.drawable.cycler, "POCO F1", "A13 Bionic", "999$"));
//        horizontal_product_scrollModelList.add(new Horizontal_Product_scrollModel(R.drawable.headphone, "NOKIA", "A13 Bionic", "999$"));
//

            /////============================HORIZONTAL PRODUCT LAYOUT==============================


/////============================GRID PRODUCT LAYOUT==============================


/////============================GRID PRODUCT LAYOUT==============================


            //////////////////////////////////////////////////////////////////////////////////
            homePage_RecyclerView = view.findViewById(R.id.home_page_recyclerview);
            homePage_RecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

            //NULL MANE AGE KOKHONO APP TA CHALU HOYNAI
            //For Better Understanding watch 54 No video
            if (lists.size()==0) {
                loadedCategoriesNames.add("Home");
                lists.add(new ArrayList<HomePage_Model>());
                adapter = new HomePage_Adapter(lists.get(0));
                DB_Queries.loadFragmentData(adapter, getContext(),0,"Home");

                //AR MANE AGE APP CHALU HOICHE AKHON LOAD NA KORE REFRESH KORBO
            } else {
                adapter = new HomePage_Adapter(lists.get(0));
                categoryAdapter.notifyDataSetChanged();
            }
//        homePage_modelList.add(new HomePage_Model(0, sliderModelsList));
//        homePage_modelList.add(new HomePage_Model(1, R.drawable.iphone, "#000000"));
//        homePage_modelList.add(new HomePage_Model(2, "Deals Of The Day", horizontal_product_scrollModelList));
//        homePage_modelList.add(new HomePage_Model(3, "Deals Of The Day", horizontal_product_scrollModelList));
//        homePage_modelList.add(new HomePage_Model(1, R.drawable.iphone2, "#F50057"));
//        homePage_modelList.add(new HomePage_Model(2, "Deals Of The Day", horizontal_product_scrollModelList));
//        homePage_modelList.add(new HomePage_Model(3, "Deals Of The Day", horizontal_product_scrollModelList));
//        homePage_modelList.add(new HomePage_Model(1, R.drawable.car, "#F9A825"));
//
//        homePage_modelList.add(new HomePage_Model(1, R.drawable.iphone, "#000000"));
//        homePage_modelList.add(new HomePage_Model(2, "Deals Of The Day", horizontal_product_scrollModelList));
//        homePage_modelList.add(new HomePage_Model(3, "Deals Of The Day", horizontal_product_scrollModelList));
//        homePage_modelList.add(new HomePage_Model(1, R.drawable.iphone2, "#F50057"));
//        homePage_modelList.add(new HomePage_Model(2, "Deals Of The Day", horizontal_product_scrollModelList));
//        homePage_modelList.add(new HomePage_Model(3, "Deals Of The Day", horizontal_product_scrollModelList));
//        homePage_modelList.add(new HomePage_Model(1, R.drawable.car, "#F9A825"));


            ////////////////////////////////////////////////////////////////////////////////

            homePage_RecyclerView.setAdapter(adapter);

        } else {
            no_internet_connection.setVisibility(View.VISIBLE);
        }




        return view;
    }


}