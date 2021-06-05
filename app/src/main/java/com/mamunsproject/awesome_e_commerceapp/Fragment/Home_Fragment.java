package com.mamunsproject.awesome_e_commerceapp.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import com.mamunsproject.awesome_e_commerceapp.Adapter.CategoryAdapter;
import com.mamunsproject.awesome_e_commerceapp.Adapter.HomePage_Adapter;
import com.mamunsproject.awesome_e_commerceapp.DB_Queries.DB_Queries;

import com.mamunsproject.awesome_e_commerceapp.MainActivity;
import com.mamunsproject.awesome_e_commerceapp.Model.CategoryModel;
import com.mamunsproject.awesome_e_commerceapp.Model.HomePage_Model;
import com.mamunsproject.awesome_e_commerceapp.Model.Horizontal_Product_scrollModel;
import com.mamunsproject.awesome_e_commerceapp.Model.SliderModel;
import com.mamunsproject.awesome_e_commerceapp.Model.Wishlist_Model;
import com.mamunsproject.awesome_e_commerceapp.R;

import java.util.ArrayList;
import java.util.List;

import static com.mamunsproject.awesome_e_commerceapp.DB_Queries.DB_Queries.categoryModelList;
import static com.mamunsproject.awesome_e_commerceapp.DB_Queries.DB_Queries.lists;
import static com.mamunsproject.awesome_e_commerceapp.DB_Queries.DB_Queries.loadedCategoriesNames;


public class Home_Fragment extends Fragment {

    private RecyclerView categoryRecyclerview;
    private CategoryAdapter categoryAdapter;
    private RecyclerView homePage_RecyclerView;
    public static SwipeRefreshLayout swipeRefreshLayout;

    private HomePage_Adapter adapter;
    private LottieAnimationView no_internet_connection;
    private ConnectivityManager connectivityManager;
    private NetworkInfo networkInfo;
    private List<CategoryModel> categoryModelFakeList = new ArrayList<>();
    private List<HomePage_Model> homePageModelFakeList = new ArrayList<>();
    private Button retryBtn;

    @SuppressLint("WrongConstant")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_, container, false);

        no_internet_connection = view.findViewById(R.id.no_internet_connection);
        swipeRefreshLayout = view.findViewById(R.id.refresh_layout);
        homePage_RecyclerView = view.findViewById(R.id.home_page_recyclerview);
        retryBtn = view.findViewById(R.id.retry_button);


        swipeRefreshLayout.setColorSchemeColors(getContext().getResources().getColor(R.color.red_600),
                getContext().getResources().getColor(R.color.red_600), getContext().getResources().getColor(R.color.red_600));


        categoryRecyclerview = view.findViewById(R.id.category_recyclerview);
        categoryRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        categoryRecyclerview.setHasFixedSize(true);

// CATEGORIES FAKE LIST
        categoryModelFakeList.add(new CategoryModel("null", ""));
        categoryModelFakeList.add(new CategoryModel("", ""));
        categoryModelFakeList.add(new CategoryModel("", ""));
        categoryModelFakeList.add(new CategoryModel("", ""));
        categoryModelFakeList.add(new CategoryModel("", ""));
        categoryModelFakeList.add(new CategoryModel("", ""));
        categoryModelFakeList.add(new CategoryModel("", ""));
        categoryModelFakeList.add(new CategoryModel("", ""));
        categoryModelFakeList.add(new CategoryModel("", ""));
        categoryModelFakeList.add(new CategoryModel("", ""));

// CATEGORIES FAKE LIST


        // HOME PAGE FAKE LIST

        List<SliderModel> sliderModelFakeList = new ArrayList<>();

        sliderModelFakeList.add(new SliderModel("null", "#dfdfdf"));
        sliderModelFakeList.add(new SliderModel("null", "#dfdfdf"));
        sliderModelFakeList.add(new SliderModel("null", "#dfdfdf"));
        sliderModelFakeList.add(new SliderModel("null", "#dfdfdf"));
        sliderModelFakeList.add(new SliderModel("null", "#dfdfdf"));


        List<Horizontal_Product_scrollModel> horizontal_product_scrollModelFakeList = new ArrayList<>();

        horizontal_product_scrollModelFakeList.add(new Horizontal_Product_scrollModel("", "", "", "", ""));
        horizontal_product_scrollModelFakeList.add(new Horizontal_Product_scrollModel("", "", "", "", ""));
        horizontal_product_scrollModelFakeList.add(new Horizontal_Product_scrollModel("", "", "", "", ""));
        horizontal_product_scrollModelFakeList.add(new Horizontal_Product_scrollModel("", "", "", "", ""));
        horizontal_product_scrollModelFakeList.add(new Horizontal_Product_scrollModel("", "", "", "", ""));
        horizontal_product_scrollModelFakeList.add(new Horizontal_Product_scrollModel("", "", "", "", ""));
        horizontal_product_scrollModelFakeList.add(new Horizontal_Product_scrollModel("", "", "", "", ""));


        homePageModelFakeList.add(new HomePage_Model(0, sliderModelFakeList));
        homePageModelFakeList.add(new HomePage_Model(1, "", "#dfdfdf"));
        homePageModelFakeList.add(new HomePage_Model(2, "", "#dfdfdf", horizontal_product_scrollModelFakeList, new ArrayList<Wishlist_Model>()));
        homePageModelFakeList.add(new HomePage_Model(3, "", "#dfdfdf", horizontal_product_scrollModelFakeList));
        // HOME PAGE FAKE LIST

        categoryAdapter = new CategoryAdapter(categoryModelFakeList);
        adapter = new HomePage_Adapter(homePageModelFakeList);


        connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected() == true) {
            MainActivity.drawerLayout.setDrawerLockMode(0);
            no_internet_connection.setVisibility(View.GONE);
            retryBtn.setVisibility(View.GONE);
            categoryRecyclerview.setVisibility(View.VISIBLE);
            homePage_RecyclerView.setVisibility(View.VISIBLE);

            //NULL MANE AGE KOKHONO APP TA CHALU HOYNAI
            if (categoryModelList.size() == 0) {

                DB_Queries.loadCategories(categoryRecyclerview, getContext());

                //AR MANE AGE APP CHALU HOICHE AKHON LOAD NA KORE REFRESH KORBO
            } else {
                categoryAdapter = new CategoryAdapter(categoryModelList);
                categoryAdapter.notifyDataSetChanged();
            }
            categoryRecyclerview.setAdapter(categoryAdapter);

/////============================ Banner Slider==============================


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
            homePage_RecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

            //NULL MANE AGE KOKHONO APP TA CHALU HOYNAI
            //For Better Understanding watch 54 No video
            if (lists.size() == 0) {
                loadedCategoriesNames.add("Home");
                lists.add(new ArrayList<HomePage_Model>());
                DB_Queries.loadFragmentData(homePage_RecyclerView, getContext(), 0, "Home");

                //AR MANE AGE APP CHALU HOICHE AKHON LOAD NA KORE REFRESH KORBO
            } else {
                adapter = new HomePage_Adapter(lists.get(0));
                adapter.notifyDataSetChanged();
                //  categoryAdapter.notifyDataSetChanged();
            }
            homePage_RecyclerView.setAdapter(adapter);

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

        } else {
            MainActivity.drawerLayout.setDrawerLockMode(1);

            categoryRecyclerview.setVisibility(View.GONE);
            homePage_RecyclerView.setVisibility(View.GONE);
            no_internet_connection.setVisibility(View.VISIBLE);
            retryBtn.setVisibility(View.VISIBLE);

        }

//REFRESH LAYOUT
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                reloadPage();
            }
        });
//REFRESH LAYOUT

        retryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reloadPage();
            }
        });
        return view;
    }

    @SuppressLint("WrongConstant")
    private void reloadPage() {


        networkInfo = connectivityManager.getActiveNetworkInfo();
        categoryModelList.clear();
        lists.clear();
        loadedCategoriesNames.clear();


        if (networkInfo != null && networkInfo.isConnected() == true) {
            MainActivity.drawerLayout.setDrawerLockMode(0);

            no_internet_connection.setVisibility(View.GONE);
            retryBtn.setVisibility(View.GONE);

            categoryRecyclerview.setVisibility(View.VISIBLE);
            homePage_RecyclerView.setVisibility(View.VISIBLE);

            categoryAdapter = new CategoryAdapter(categoryModelFakeList);
            adapter = new HomePage_Adapter(homePageModelFakeList);
            categoryRecyclerview.setAdapter(categoryAdapter);
            homePage_RecyclerView.setAdapter(adapter);

            DB_Queries.loadCategories(categoryRecyclerview, getContext());
            loadedCategoriesNames.add("Home");
            lists.add(new ArrayList<HomePage_Model>());
            DB_Queries.loadFragmentData(homePage_RecyclerView, getContext(), 0, "Home");


        } else {
            MainActivity.drawerLayout.setDrawerLockMode(1);

            Toast.makeText(getContext(), "No Connection!", Toast.LENGTH_SHORT).show();
            categoryRecyclerview.setVisibility(View.GONE);
            homePage_RecyclerView.setVisibility(View.GONE);
            retryBtn.setVisibility(View.VISIBLE);

            categoryAdapter.notifyDataSetChanged();
            no_internet_connection.setVisibility(View.VISIBLE);
            swipeRefreshLayout.setRefreshing(false);

        }


    }

}