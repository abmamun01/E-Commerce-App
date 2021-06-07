package com.mamunsproject.awesome_e_commerceapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mamunsproject.awesome_e_commerceapp.Adapter.My_Reward_Adapter;
import com.mamunsproject.awesome_e_commerceapp.Adapter.ProductDetailsAdapter;
import com.mamunsproject.awesome_e_commerceapp.Adapter.Product_Images_Adapter;

import com.mamunsproject.awesome_e_commerceapp.DB_Queries.DB_Queries;
import com.mamunsproject.awesome_e_commerceapp.Fragment.Sign_In_Fragment;
import com.mamunsproject.awesome_e_commerceapp.Fragment.Sign_Up_Fragment;
import com.mamunsproject.awesome_e_commerceapp.MainActivity;
import com.mamunsproject.awesome_e_commerceapp.Model.ProductSpecificationModel;
import com.mamunsproject.awesome_e_commerceapp.Model.Reward_Model;
import com.mamunsproject.awesome_e_commerceapp.Model.Wishlist_Model;
import com.mamunsproject.awesome_e_commerceapp.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mamunsproject.awesome_e_commerceapp.Activity.Register_Activity.setSignUpFragment;
import static com.mamunsproject.awesome_e_commerceapp.MainActivity.showCart;

public class Product_Details_Activity extends AppCompatActivity {

    private ViewPager productImagesViewPager;
    private TextView productTitle;
    private TextView averageRatingMiniView;
    private TextView totalRatingMiniView;
    private TextView productPrice;
    private TextView cuttedPrice;
    private ImageView codIndicator;
    private TextView tvCodIndicator;
    public static FloatingActionButton addToWishListButton;
    public static boolean ALREADY_ADDED_TO_WISH_LIST = false;
    private Button coupenRedeemButton;
    private TabLayout productDetailsTabLayout;
    private TextView rewardTitle;
    private TextView rewardBody;
    private String productDescription;
    private String productOtherDetails;
    private final List<ProductSpecificationModel> productSpecificationModelsList = new ArrayList<>();
    private TextView productOnlyDescriptionBody;
    private TextView totalRatingsFigure;
    private TextView averageRating;
    private LinearLayout addToCartButton;
    private LinearLayout couponRedeemptionLayout;
    private FirebaseUser currentUser;
    private String productID;
    private DocumentSnapshot documentSnapshot;


    //======================= PRODUCT DESCRIPTION ================

    private ConstraintLayout productDetailsOnlyLayoutContainer;
    private ConstraintLayout productDetailsTabsContainer;

    private ViewPager productDetailsViewpager;
    private TabLayout viewpagerIndicator;


    //===============RATING LAYOUT=========================


    private LinearLayout rateNowContainer;
    private TextView totalRatings;
    private LinearLayout ratingsNoContainer;
    private LinearLayout ratingsProgressBarContainer;


    //===============RATING LAYOUT=========================

    private Button buyNowButton;
    //===============COUPEN DIALOG=========================

    public static TextView couponTitle;
    public static TextView couponExpriyDate;
    public static TextView couponBody;
    private static RecyclerView couponsRecyclerView;
    private static LinearLayout selectedCoupons;

    //===============COUPEN DIALOG=========================

    private Dialog signInDialog;
    private Dialog loadingDialog;


    private FirebaseFirestore firebaseFirestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String title = getIntent().getStringExtra("categoryName");
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        productTitle = findViewById(R.id.product_title);
        averageRatingMiniView = findViewById(R.id.tv_product_rating_mini_view);
        totalRatingMiniView = findViewById(R.id.total_ratings_miniview);
        productPrice = findViewById(R.id.product_price);
        cuttedPrice = findViewById(R.id.cutted_price);
        tvCodIndicator = findViewById(R.id.tv_cod_indicator);
        codIndicator = findViewById(R.id.cod_indicator_image_view);
        productImagesViewPager = findViewById(R.id.product_image_view_Pager);
        viewpagerIndicator = findViewById(R.id.viewpager_indicator);
        addToWishListButton = findViewById(R.id.addto_wishLIst_button);
        productDetailsViewpager = findViewById(R.id.product_details_viewpager);
        productDetailsTabLayout = findViewById(R.id.product_detailcs_tablayout);
        buyNowButton = findViewById(R.id.buy_now_button);
        coupenRedeemButton = findViewById(R.id.coupon_redeemption_button);
        rewardTitle = findViewById(R.id.reward_title);
        rewardBody = findViewById(R.id.reward_body);
        productDetailsTabsContainer = findViewById(R.id.product_details_tabs_container);
        productDetailsOnlyLayoutContainer = findViewById(R.id.product_details_container);
        productOnlyDescriptionBody = findViewById(R.id.product_details_body);
        totalRatings = findViewById(R.id.total_ratings);
        ratingsNoContainer = findViewById(R.id.ratings_numbers_container);
        totalRatingsFigure = findViewById(R.id.total_ratings_figure);
        ratingsProgressBarContainer = findViewById(R.id.ratings_progressbar_container);
        averageRating = findViewById(R.id.average_rating);
        addToCartButton = findViewById(R.id.add_to_cart_button);
        couponRedeemptionLayout = findViewById(R.id.coupon_redeemption_layout);

        productID = getIntent().getStringExtra("PRODUCT_ID");

        ///---------Loading Dialog-------------


        loadingDialog = new Dialog(Product_Details_Activity.this);
        loadingDialog.setContentView(R.layout.loading_progress_dialog);
        loadingDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        loadingDialog.setCancelable(false);
        loadingDialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.slider_background));
        loadingDialog.show();
        ///---------Loading Dialog-------------

//======================================= SETTING PRODUCTS IMAGES FROM FIREBASE =============================
        firebaseFirestore = FirebaseFirestore.getInstance();

        List<String> productImages = new ArrayList<>();


        Log.d("TAGddd", "onCreate: " + getIntent().getStringExtra("PRODUCT_ID"));
        firebaseFirestore.collection("PRODUCTS").document(getIntent().getStringExtra("PRODUCT_ID"))
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    documentSnapshot = task.getResult();

                    for (long x = 1; x < (long) documentSnapshot.get("no_of_product_images") + 1; x++) {
                        productImages.add(documentSnapshot.get("product_image_" + x).toString());

                    }

                    Product_Images_Adapter product_images_adapter = new Product_Images_Adapter(productImages);

                    productImagesViewPager.setAdapter(product_images_adapter);

                    productTitle.setText(documentSnapshot.get("product_title").toString());
                    averageRatingMiniView.setText(documentSnapshot.get("average_rating").toString());
                    totalRatingMiniView.setText("(" + (long) documentSnapshot.get("total_ratings") + ") ratings");
                    productPrice.setText("Tk." + documentSnapshot.get("product_price").toString() + "/-");
                    cuttedPrice.setText("Tk." + documentSnapshot.get("cutted_price").toString() + "/-");

                    if ((boolean) documentSnapshot.get("COD")) {
                        codIndicator.setVisibility(View.VISIBLE);
                        tvCodIndicator.setVisibility(View.VISIBLE);
                    } else {
                        codIndicator.setVisibility(View.INVISIBLE);
                        tvCodIndicator.setVisibility(View.INVISIBLE);
                    }

                    rewardTitle.setText((long) documentSnapshot.get("free_coupens") + documentSnapshot.get("free_coupen_title").toString());
                    rewardBody.setText(documentSnapshot.get("free_coupen_body").toString());

                    if ((boolean) documentSnapshot.get("used_tab_layout")) {

                        productDetailsTabsContainer.setVisibility(View.VISIBLE);
                        productDetailsOnlyLayoutContainer.setVisibility(View.GONE);
                        productDescription = documentSnapshot.get("product_description").toString();


                        productOtherDetails = documentSnapshot.get("product_other_details").toString();

                        for (long x = 1; x < (long) documentSnapshot.get("total_spec_titles") + 1; x++) {

                            productSpecificationModelsList.add(new ProductSpecificationModel(0, documentSnapshot.get("spec_title_" + x).toString()));
                            for (long y = 1; y < (long) documentSnapshot.get("spec_title_" + x + "_total_field") + 1; y++) {

                                productSpecificationModelsList.add(new ProductSpecificationModel(1, documentSnapshot.get("spec_title_" + x + "_field_" + y + "_name").toString(), documentSnapshot.get("spec_title_" + x + "_field_" + y + "_value").toString()));

                            }
                        }

                    } else {
                        productDetailsTabsContainer.setVisibility(View.GONE);
                        productDetailsOnlyLayoutContainer.setVisibility(View.VISIBLE);
                        productOnlyDescriptionBody.setText(documentSnapshot.get("product_description").toString());

                    }

                    //=============== RATINGS LAYOUT =========================
                    totalRatings.setText((long) documentSnapshot.get("total_ratings") + "ratings");
                    for (int x = 0; x < 5; x++) {

                        // Tricky Part Making Linear Layout To Progressbar
                        TextView rating = (TextView) ratingsNoContainer.getChildAt(x);
                        rating.setText(String.valueOf((long) documentSnapshot.get(5 - x + "_star")));

                        ProgressBar progressBar = (ProgressBar) ratingsProgressBarContainer.getChildAt(x);

                        int maxProgress = Integer.parseInt(String.valueOf((long) documentSnapshot.get("total_ratings")));
                        progressBar.setMax(maxProgress);
                        progressBar.setProgress(Integer.parseInt(String.valueOf((long) documentSnapshot.get((5 - x) + "_star"))));
                    }
                    totalRatingsFigure.setText(String.valueOf((long) documentSnapshot.get("total_ratings")));
                    averageRating.setText(documentSnapshot.get("average_rating").toString());
                    productDetailsViewpager.setAdapter(new ProductDetailsAdapter(getSupportFragmentManager(), productDetailsTabLayout.getTabCount(), productDescription, productOtherDetails, productSpecificationModelsList));


                    if (currentUser != null) {


                        if (DB_Queries.wishList.size() == 0) {
                            DB_Queries.loadWishList(Product_Details_Activity.this, loadingDialog, false);

                        } else {

                            loadingDialog.dismiss();
                        }
                    } else {
                        loadingDialog.dismiss();
                    }

                    if (DB_Queries.wishList.contains(productID)) {

                        ALREADY_ADDED_TO_WISH_LIST = true;
                        addToWishListButton.setSupportImageTintList(getResources().getColorStateList(R.color.red_A400));

                    } else {
                        ALREADY_ADDED_TO_WISH_LIST = false;
                        loadingDialog.dismiss();
                    }


                    //=============== RATINGS LAYOUT =========================


                } else {

                    Toast.makeText(Product_Details_Activity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });


//======================================= SETTING PRODUCTS IMAGES FROM FIREBASE =============================


        viewpagerIndicator.setupWithViewPager(productImagesViewPager, true);


        addToWishListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (currentUser == null) {
                    signInDialog.show();
                } else {

                    addToWishListButton.setEnabled(false);

                    if (ALREADY_ADDED_TO_WISH_LIST) {
                        int index = DB_Queries.wishList.indexOf(productID);
                        DB_Queries.removeFromWishList(index, Product_Details_Activity.this);

                        //ALREADY_ADDED_TO_WISH_LIST = false;
                        addToWishListButton.setSupportImageTintList(ColorStateList.valueOf(Color.parseColor("#9e9e9e")));
                    } else {

                        addToWishListButton.setSupportImageTintList(getResources().getColorStateList(R.color.red));
                        Map<String, Object> addProduct = new HashMap<>();
                        addProduct.put("product_ID_" + String.valueOf(DB_Queries.wishList.size()), productID);


                        firebaseFirestore.collection("USERS").document(currentUser.getUid()).collection("USER_DATA")
                                .document("MY_WISHLIST").set(addProduct).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull @NotNull Task<Void> task) {
                                if (task.isSuccessful()) {

                                    Map<String, Object> updateListSize = new HashMap<>();
                                    updateListSize.put("list_size", (long) DB_Queries.wishList.size() + 1);


                                    firebaseFirestore.collection("USERS").document(currentUser.getUid()).collection("USER_DATA")
                                            .document("MY_WISHLIST").update(updateListSize).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull @NotNull Task<Void> task) {
                                            if (task.isSuccessful()) {

                                                DB_Queries.wishlist_modelList.add(new Wishlist_Model(documentSnapshot.get("product_image_1").toString()
                                                        , documentSnapshot.get("product_title").toString()
                                                        , (long) documentSnapshot.get("free_coupens")
                                                        , documentSnapshot.get("average_rating").toString()
                                                        , (long) documentSnapshot.get("total_ratings")
                                                        , documentSnapshot.get("product_price").toString()
                                                        , documentSnapshot.get("cutted_price").toString()
                                                        , (boolean) documentSnapshot.get("COD")));


                                                ALREADY_ADDED_TO_WISH_LIST = true;
                                                addToWishListButton.setSupportImageTintList(getResources().getColorStateList(R.color.red_A400));
                                                DB_Queries.wishList.add(productID);
                                                Toast.makeText(Product_Details_Activity.this, "Added Successfully!", Toast.LENGTH_SHORT).show();

                                                addToWishListButton.setEnabled(true);

                                            } else {
                                                addToWishListButton.setEnabled(true);
                                                addToWishListButton.setSupportImageTintList(ColorStateList.valueOf(Color.parseColor("#9e9e9e")));
                                                Toast.makeText(Product_Details_Activity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                            //  addToWishListButton.setEnabled(false);
                                        }
                                    });

                                } else {

                                    addToWishListButton.setEnabled(true);
                                    Toast.makeText(Product_Details_Activity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                    }

                }
            }
        });


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

                    if (currentUser == null) {
                        signInDialog.show();
                    } else {
                        setRating(starPosition);
                    }
                }
            });
        }

        //===============RATING LAYOUT=========================

        buyNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (currentUser == null) {
                    signInDialog.show();
                } else {
                    startActivity(new Intent(Product_Details_Activity.this, Delivery_Activity.class));
                }

            }
        });

        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentUser == null) {
                    signInDialog.show();
                } else {

                    ////todo:add to cart
                }
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

        //---------- Sign In Dialog ------------------

        signInDialog = new Dialog(Product_Details_Activity.this);
        signInDialog.setContentView(R.layout.sign_in_dialauge);
        signInDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        signInDialog.setCancelable(true);
        Button dialogSignInButton = signInDialog.findViewById(R.id.signInButton);
        Button dialogSignUpButton = signInDialog.findViewById(R.id.signUpButton);

        signInDialog.setCancelable(true);

        Intent registerIntent = new Intent(Product_Details_Activity.this, Register_Activity.class);


        dialogSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Sign_In_Fragment.disableCloseBtn = true;
                Sign_Up_Fragment.disableCloseButton = true;

                signInDialog.dismiss();
                setSignUpFragment = false;
                startActivity(registerIntent);

            }
        });

        dialogSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sign_In_Fragment.disableCloseBtn = true;
                Sign_Up_Fragment.disableCloseButton = true;

                signInDialog.dismiss();
                setSignUpFragment = true;
                startActivity(registerIntent);

            }
        });


    }


    @Override
    protected void onStart() {
        super.onStart();

        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser == null) {
            couponRedeemptionLayout.setVisibility(View.GONE);

        } else {
            couponRedeemptionLayout.setVisibility(View.VISIBLE);

        }

        if (currentUser != null) {
            if (DB_Queries.wishList.size() == 0) {
                DB_Queries.loadWishList(Product_Details_Activity.this, loadingDialog, false);
            } else {
                loadingDialog.dismiss();
            }
        } else {
            loadingDialog.dismiss();
        }

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
            if (currentUser == null) {
                signInDialog.show();
            } else {
                Intent cartIntent = new Intent(Product_Details_Activity.this, MainActivity.class);
                showCart = true;
                startActivity(cartIntent);
            }

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