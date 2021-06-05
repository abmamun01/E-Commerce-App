package com.mamunsproject.awesome_e_commerceapp.DB_Queries;

import android.app.Dialog;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.mamunsproject.awesome_e_commerceapp.Activity.Product_Details_Activity;
import com.mamunsproject.awesome_e_commerceapp.Adapter.CategoryAdapter;
import com.mamunsproject.awesome_e_commerceapp.Adapter.HomePage_Adapter;
import com.mamunsproject.awesome_e_commerceapp.Fragment.Home_Fragment;
import com.mamunsproject.awesome_e_commerceapp.Fragment.My_WishList_Fragment;
import com.mamunsproject.awesome_e_commerceapp.Model.CategoryModel;
import com.mamunsproject.awesome_e_commerceapp.Model.HomePage_Model;
import com.mamunsproject.awesome_e_commerceapp.Model.Horizontal_Product_scrollModel;
import com.mamunsproject.awesome_e_commerceapp.Model.SliderModel;
import com.mamunsproject.awesome_e_commerceapp.Model.Wishlist_Model;
import com.mamunsproject.awesome_e_commerceapp.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DB_Queries {

    public static FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    public static List<CategoryModel> categoryModelList = new ArrayList<>();

    //For MORE UNDERSTANDING WATCH 54 NO VIDEO OF TCODE
    public static List<List<HomePage_Model>> lists = new ArrayList<>();


    public static List<String> wishList = new ArrayList<>();
    public static List<Wishlist_Model> wishlist_modelList = new ArrayList<>();


    //ALREADY J LIST TA AMRA FIREBASE THEKE LOAD KORE FELECI SETAI REUSE KORAR JONNO AI METHOD TA
    public static List<String> loadedCategoriesNames = new ArrayList<>();


    public static void loadCategories(RecyclerView categoryRecyclerView, Context context) {

        firebaseFirestore.collection("CATEGORIES").orderBy("index").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                categoryModelList.add(new CategoryModel(documentSnapshot.get("icon").toString(),
                                        documentSnapshot.get("categoryName").toString()));
                            }
                            CategoryAdapter categoryAdapter = new CategoryAdapter(categoryModelList);
                            categoryRecyclerView.setAdapter(categoryAdapter);
                            categoryAdapter.notifyDataSetChanged();

                        } else {
                            String error = task.getException().getMessage();
                            Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    public static void loadFragmentData(RecyclerView homePageRecyclerView, Context context, final int index, String categoryName) {

        firebaseFirestore.collection("CATEGORIES").document(categoryName.toUpperCase())
                .collection("TOP_DEALS").orderBy("index")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {

                        if ((long) documentSnapshot.get("view_type") == 0) {

                            List<SliderModel> sliderModelsList = new ArrayList<>();
                            long numOfBanner = (long) documentSnapshot.get("no_of_banner");

                            for (long x = 1; x < numOfBanner + 1; x++) {
                                sliderModelsList.add(new SliderModel(documentSnapshot.get("banner_" + x).toString()
                                        , documentSnapshot.get("banner_" + x + "_bg").toString()));
                            }
                            lists.get(index).add(new HomePage_Model(0, sliderModelsList));


                        } else if ((long) documentSnapshot.get("view_type") == 1) {
                            lists.get(index).add(new HomePage_Model(1, documentSnapshot
                                    .get("strip_ad_banner").toString(),
                                    documentSnapshot.get("background").toString()));

                        } else if ((long) documentSnapshot.get("view_type") == 2) {

                            List<Wishlist_Model> viewAllProductList = new ArrayList<>();

                            List<Horizontal_Product_scrollModel> horizontal_product_scrollModelList = new ArrayList<>();
                            long numOfProducts = (long) documentSnapshot.get("no_of_products");

                            for (long x = 1; x < numOfProducts + 1; x++) {

                                horizontal_product_scrollModelList.add(new Horizontal_Product_scrollModel(
                                        documentSnapshot.get("product_ID_" + x).toString()
                                        , documentSnapshot.get("product_image_" + x).toString()
                                        , documentSnapshot.get("product_title_" + x).toString()
                                        , documentSnapshot.get("product_subtitle_" + x).toString()
                                        , documentSnapshot.get("product_price_" + x).toString()));


                                viewAllProductList.add(new Wishlist_Model(documentSnapshot.get("product_image_" + x).toString()
                                        , documentSnapshot.get("product_full_title_" + x).toString()
                                        , (long) documentSnapshot.get("free_coupens_" + x)
                                        , documentSnapshot.get("average_rating_" + x).toString()
                                        , (long) documentSnapshot.get("total_ratings_" + x)
                                        , documentSnapshot.get("product_price_" + x).toString()
                                        , documentSnapshot.get("cutted_price_" + x).toString()
                                        , (boolean) documentSnapshot.get("COD_" + x)));

                            }

                            lists.get(index).add(new HomePage_Model(2, documentSnapshot.get("layout_title").toString(),
                                    documentSnapshot.get("layout_background").toString(), horizontal_product_scrollModelList, viewAllProductList));

                        } else if ((long) documentSnapshot.get("view_type") == 3) {

                            List<Horizontal_Product_scrollModel> gridLayoutModelList = new ArrayList<>();

                            long numOfProducts = (long) documentSnapshot.get("no_of_products");

                            for (long x = 1; x < numOfProducts + 1; x++) {

                                gridLayoutModelList.add(new Horizontal_Product_scrollModel(
                                        documentSnapshot.get("product_ID_" + x).toString()
                                        , documentSnapshot.get("product_image_" + x).toString()
                                        , documentSnapshot.get("product_title_" + x).toString()
                                        , documentSnapshot.get("product_subtitle_" + x).toString()
                                        , documentSnapshot.get("product_price_" + x).toString()));

                            }
                            lists.get(index).add(new HomePage_Model(3, documentSnapshot.get("layout_title").toString(),
                                    documentSnapshot.get("layout_background").toString(), gridLayoutModelList));

                        }

                    }
                    HomePage_Adapter homePage_adapter = new HomePage_Adapter(lists.get(index));
                    homePageRecyclerView.setAdapter(homePage_adapter);
                    homePage_adapter.notifyDataSetChanged();
                    Home_Fragment.swipeRefreshLayout.setRefreshing(false);

                } else {
                    String error = task.getException().getMessage();
                    Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
                }
            }
        });


    }


    public static void loadWishList(final Context context, Dialog dialog, final boolean loadProductData) {

        firebaseFirestore.collection("USERS").document(FirebaseAuth.getInstance().getUid())
                .collection("USER_DATA").document("MY_WISHLIST")
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<DocumentSnapshot> task) {

                if (task.isSuccessful()) {

                    for (long x = 0; x < (long) task.getResult().get("list_size"); x++) {
                        wishList.add(task.getResult().get("product_ID_" + x).toString());

                        if (loadProductData) {

                            firebaseFirestore.collection("PRODUCTS").document(task.getResult().get("product_ID_" + x).toString())
                                    .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull @NotNull Task<DocumentSnapshot> task) {
                                    if (task.isSuccessful()) {

                                        wishlist_modelList.add(new Wishlist_Model(task.getResult().get("product_image_1").toString()
                                                , task.getResult().get("product_title").toString()
                                                , (long) task.getResult().get("free_coupens")
                                                , task.getResult().get("average_rating").toString()
                                                , (long) task.getResult().get("total_ratings")
                                                , task.getResult().get("product_price").toString()
                                                , task.getResult().get("cutted_price").toString()
                                                , (boolean) task.getResult().get("COD")));

                                        My_WishList_Fragment.wishlist_adapter.notifyDataSetChanged();


                                    } else {

                                        Toast.makeText(context, task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });
                        }
                    }
                } else {
                    String error = task.getException().getMessage();
                    Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
                }

                dialog.dismiss();
            }

        });
    }

    public static void removeFromWishList(int index,Context context){

        wishList.remove(index);
        Map<String, Object> updateWishList=new HashMap<>();

        for (int x =0;x<wishList.size();x++){
            updateWishList.put("product_ID_"+x,wishList.get(x));
        }
        updateWishList.put("list_size",(long)wishList.size());

        firebaseFirestore.collection("USERS").document(FirebaseAuth.getInstance().getUid())
                .collection("USER_DATA").document("MY_WISHLIST")
                .set(updateWishList).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<Void> task) {
                if (task.isSuccessful()){

                    if (wishlist_modelList.size()!=0){
                        wishlist_modelList.remove(index);
                        //For Refresh
                        My_WishList_Fragment.wishlist_adapter.notifyDataSetChanged();
                    }
                    Product_Details_Activity.ALREADY_ADDED_TO_WISH_LIST=false;
                    Toast.makeText(context, "Removed Successfully!", Toast.LENGTH_SHORT).show();
                }else {

                    Product_Details_Activity.addToWishListButton.setSupportImageTintList(context.getResources().getColorStateList(R.color.red_A400));
                    Toast.makeText(context, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
                Product_Details_Activity.addToWishListButton.setEnabled(true);

            }
        });
    }

}
