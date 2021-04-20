package com.mamunsproject.awesome_e_commerceapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.mamunsproject.awesome_e_commerceapp.Adapter.My_Reward_Adapter;
import com.mamunsproject.awesome_e_commerceapp.Adapter.ProductDetailsAdapter;
import com.mamunsproject.awesome_e_commerceapp.Adapter.Product_Images_Adapter;
import com.mamunsproject.awesome_e_commerceapp.MainActivity;
import com.mamunsproject.awesome_e_commerceapp.Model.Reward_Model;
import com.mamunsproject.awesome_e_commerceapp.R;

import java.util.ArrayList;
import java.util.List;

import static com.mamunsproject.awesome_e_commerceapp.MainActivity.showCart;

public class Product_Details_Activity extends AppCompatActivity {

    private ViewPager productImagesViewPager;
    private TabLayout viewpagerIndicator;
    private FloatingActionButton addToWishListButton;
    private static boolean ALREADY_ADDED_TO_WISH_LIST = false;
    private Button coupenRedeemButton;
    private ViewPager productDetailsViewpager;
    private TabLayout productDetailsTabLayout;

    //===============COUPEN DIALOG=========================

    public static TextView couponTitle;
    public static TextView couponExpriyDate;
    public static TextView couponBody;
    private static RecyclerView couponsRecyclerView;
    private static LinearLayout selectedCoupons;


    //===============COUPEN DIALOG=========================

    //===============RATING LAYOUT=========================


    private LinearLayout rateNowContainer;


    //===============RATING LAYOUT=========================

    private Button buyNowButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String title = getIntent().getStringExtra("categoryName");
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        productImagesViewPager = findViewById(R.id.product_image_view_Pager);
        viewpagerIndicator = findViewById(R.id.viewpager_indicator);
        addToWishListButton = findViewById(R.id.addto_wishLIst_button);

        productDetailsViewpager = findViewById(R.id.product_details_viewpager);
        productDetailsTabLayout = findViewById(R.id.product_detailcs_tablayout);
        buyNowButton = findViewById(R.id.buy_now_button);

        coupenRedeemButton = findViewById(R.id.coupon_redeemption_button);

        List<Integer> productImages = new ArrayList<>();
        productImages.add(R.drawable.cycler);
        productImages.add(R.drawable.car);
        productImages.add(R.drawable.headphone);
        productImages.add(R.drawable.iphone);

        Product_Images_Adapter product_images_adapter = new Product_Images_Adapter(productImages);
        productImagesViewPager.setAdapter(product_images_adapter);


        viewpagerIndicator.setupWithViewPager(productImagesViewPager, true);


        addToWishListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ALREADY_ADDED_TO_WISH_LIST) {
                    ALREADY_ADDED_TO_WISH_LIST = false;
                    addToWishListButton.setSupportImageTintList(ColorStateList.valueOf(Color.parseColor("#9e9e9e")));
                } else {
                    ALREADY_ADDED_TO_WISH_LIST = true;

                    addToWishListButton.setSupportImageTintList(getResources().getColorStateList(R.color.red_A400));

                }
            }
        });


        productDetailsViewpager.setAdapter(new ProductDetailsAdapter(getSupportFragmentManager(), productDetailsTabLayout.getTabCount()));

        productDetailsViewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(productDetailsTabLayout));

        productDetailsTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                productDetailsViewpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        //===============RATING LAYOUT=========================


        rateNowContainer = findViewById(R.id.rate_now_container);

        for (int x = 0; x < rateNowContainer.getChildCount(); x++) {
            final int starPosition = x;
            rateNowContainer.getChildAt(x).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setRating(starPosition);
                }
            });
        }

        //===============RATING LAYOUT=========================

        buyNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Product_Details_Activity.this, Delivery_Activity.class));

            }
        });

//======================================================COUPON DIALOG======================================================================
                        Dialog checkCoupenPriceDialog = new Dialog(Product_Details_Activity.this);
                checkCoupenPriceDialog.setContentView(R.layout.coupon_redeem_dialog);
                checkCoupenPriceDialog.setCancelable(true);
                checkCoupenPriceDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                ImageView toggle_recyclerview = checkCoupenPriceDialog.findViewById(R.id.toggle_recyclerview);
                couponsRecyclerView = checkCoupenPriceDialog.findViewById(R.id.coupen_recyclerview);
                selectedCoupons = checkCoupenPriceDialog.findViewById(R.id.seleted_coupen);


                couponTitle = checkCoupenPriceDialog.findViewById(R.id.coupon_title);
                couponExpriyDate = checkCoupenPriceDialog.findViewById(R.id.coupon_validity);
                couponBody = checkCoupenPriceDialog.findViewById(R.id.coupon_body);

                TextView originalPrice = checkCoupenPriceDialog.findViewById(R.id.orginal_price);
                TextView discountPrice = checkCoupenPriceDialog.findViewById(R.id.discounted_price);
                couponsRecyclerView.setLayoutManager(new LinearLayoutManager(Product_Details_Activity.this));


                List<Reward_Model> reward_modelList = new ArrayList<>();
                reward_modelList.add(new Reward_Model("Cashback", "till 2nd , June 2021", "Get 20 % CASHBAK on any product and so mony offer to get enter the app daily"));
                reward_modelList.add(new Reward_Model("Discount", "till 2nd , June 2021", "Get 20 % CASHBAK on any product and so mony offer to get enter the app daily"));
                reward_modelList.add(new Reward_Model("Buy 1 get 1 free", "till 2nd , June 2021", "Get 20 % CASHBAK on any product and so mony offer to get enter the app daily"));
                reward_modelList.add(new Reward_Model("Cashback", "till 2nd , June 2021", "Get 20 % CASHBAK on any product and so mony offer to get enter the app daily"));
                reward_modelList.add(new Reward_Model("Discount", "till 2nd , June 2021", "Get 20 % CASHBAK on any product and so mony offer to get enter the app daily"));
                reward_modelList.add(new Reward_Model("Buy 1 get 1 free", "till 2nd , June 2021", "Get 20 % CASHBAK on any product and so mony offer to get enter the app daily"));
                reward_modelList.add(new Reward_Model("Cashback", "till 2nd , June 2021", "Get 20 % CASHBAK on any product and so mony offer to get enter the app daily"));
                reward_modelList.add(new Reward_Model("Discount", "till 2nd , June 2021", "Get 20 % CASHBAK on any product and so mony offer to get enter the app daily"));
                reward_modelList.add(new Reward_Model("Buy 1 get 1 free", "till 2nd , June 2021", "Get 20 % CASHBAK on any product and so mony offer to get enter the app daily"));
                reward_modelList.add(new Reward_Model("Cashback", "till 2nd , June 2021", "Get 20 % CASHBAK on any product and so mony offer to get enter the app daily"));
                reward_modelList.add(new Reward_Model("Discount", "till 2nd , June 2021", "Get 20 % CASHBAK on any product and so mony offer to get enter the app daily"));
                reward_modelList.add(new Reward_Model("Buy 1 get 1 free", "till 2nd , June 2021", "Get 20 % CASHBAK on any product and so mony offer to get enter the app daily"));


                My_Reward_Adapter my_reward_adapter = new My_Reward_Adapter(reward_modelList, true);
                couponsRecyclerView.setAdapter(my_reward_adapter);
                my_reward_adapter.notifyDataSetChanged();

                toggle_recyclerview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        showDialogReclerView();

                    }
                });


//======================================================COUPON DIALOG======================================================================




        coupenRedeemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkCoupenPriceDialog.show();

            }
        });
    }

    
    private void setRating(int starPosition) {

        for (int x = 0; x < rateNowContainer.getChildCount(); x++) {

            ImageView starButton = (ImageView) rateNowContainer.getChildAt(x);
            starButton.setImageTintList(ColorStateList.valueOf(Color.parseColor("#bebebe")));
            if (x <= starPosition) {
                starButton.setImageTintList(ColorStateList.valueOf(Color.parseColor("#ffbb00")));
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_and_cart_icon, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.main_searchIcon) {
            //todo:search
            return true;
        } else if (id == R.id.mainCartIcon) {
            //todo:Cart
            Intent cartIntent = new Intent(Product_Details_Activity.this, MainActivity.class);
            showCart = true;
            startActivity(cartIntent);


        } else if (id == android.R.id.home) {
            finish();
            return true;

        }

        return super.onOptionsItemSelected(item);
    }

    public static void showDialogReclerView() {
        if (couponsRecyclerView.getVisibility() == View.GONE) {
            couponsRecyclerView.setVisibility(View.VISIBLE);
            selectedCoupons.setVisibility(View.GONE);
        } else {
            couponsRecyclerView.setVisibility(View.GONE);
            selectedCoupons.setVisibility(View.VISIBLE);

        }
    }

}