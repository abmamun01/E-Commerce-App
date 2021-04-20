package com.mamunsproject.awesome_e_commerceapp.DB_Queries;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.mamunsproject.awesome_e_commerceapp.Adapter.CategoryAdapter;
import com.mamunsproject.awesome_e_commerceapp.Adapter.HomePage_Adapter;
import com.mamunsproject.awesome_e_commerceapp.Model.CategoryModel;
import com.mamunsproject.awesome_e_commerceapp.Model.HomePage_Model;
import com.mamunsproject.awesome_e_commerceapp.Model.Horizontal_Product_scrollModel;
import com.mamunsproject.awesome_e_commerceapp.Model.SliderModel;
import com.mamunsproject.awesome_e_commerceapp.Model.Wishlist_Model;

import java.util.ArrayList;
import java.util.List;

public class DB_Queries {

    public static FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    public static List<CategoryModel> categoryModelList = new ArrayList<>();


    //For MORE UNDERSTANDING WATCH 54 NO VIDEO OF TCODE
    public static List<List<HomePage_Model>> lists=new ArrayList<>();

    //ALREADY J LIST TA AMRA FIREBASE THEKE LOAD KORE FELECI SETAI REUSE KORAR JONNO AI METHOD TA
    public static List<String> loadedCategoriesNames = new ArrayList<>();



    public static void loadCategories(CategoryAdapter categoryAdapter, Context context) {

        firebaseFirestore.collection("CATEGORIES").orderBy("index").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                categoryModelList.add(new CategoryModel(documentSnapshot.get("icon").toString(),
                                        documentSnapshot.get("categoryName").toString()));
                            }
                            categoryAdapter.notifyDataSetChanged();

                        } else {
                            String error = task.getException().getMessage();
                            Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }


    public static void loadFragmentData(HomePage_Adapter adapter , Context context,final int index, String categoryName) {

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

                           List<Wishlist_Model> viewAllProductList=new ArrayList<>();

                            List<Horizontal_Product_scrollModel> horizontal_product_scrollModelList = new ArrayList<>();
                            long numOfProducts = (long) documentSnapshot.get("no_of_products");

                            for (long x = 1; x < numOfProducts + 1; x++) {

                                horizontal_product_scrollModelList.add(new Horizontal_Product_scrollModel(
                                        documentSnapshot.get("product_ID_" + x).toString()
                                        , documentSnapshot.get("product_image_" + x).toString()
                                        , documentSnapshot.get("product_title_" + x).toString()
                                        , documentSnapshot.get("product_subtitle_" + x).toString()
                                        , documentSnapshot.get("product_price_" + x).toString()));


                                viewAllProductList.add(new Wishlist_Model(documentSnapshot.get("product_image_"+x).toString()
                                ,documentSnapshot.get("product_full_title_" + x).toString()
                                ,(long)documentSnapshot.get("free_coupens_" + x)
                                ,documentSnapshot.get("average_rating_" + x).toString()
                                ,(long)documentSnapshot.get("total_ratings_" + x)
                                ,documentSnapshot.get("product_price_" + x).toString()
                                ,documentSnapshot.get("cutted_price_" + x).toString()
                                ,(boolean)documentSnapshot.get("COD_" + x)));

                            }


                            lists.get(index).add(new HomePage_Model(2, documentSnapshot.get("layout_title").toString(),
                                    documentSnapshot.get("layout_background").toString(), horizontal_product_scrollModelList,viewAllProductList));

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
                    adapter.notifyDataSetChanged();

                } else {
                    String error = task.getException().getMessage();
                    Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

}
