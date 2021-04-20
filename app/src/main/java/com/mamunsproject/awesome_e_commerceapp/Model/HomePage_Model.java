package com.mamunsproject.awesome_e_commerceapp.Model;

import android.graphics.Color;

import com.mamunsproject.awesome_e_commerceapp.R;

import java.util.List;

public class HomePage_Model {

    public static final int BANNER_SLIDER = 0;
    public static final int STRIP_AD_BANNER = 1;
    public static final int HORIZONTAL_PRODUCT_VIEW = 2;
    public static final int GRID_PRODUCT_VIEW = 3;

    private int type;
    private String backgroundColor;



    /////============================ Banner Slider==============================
    private List<SliderModel> sliderModelsList;

    public HomePage_Model() {
    }

    public HomePage_Model(int type, List<SliderModel> sliderModelsList) {
        this.type = type;
        this.sliderModelsList = sliderModelsList;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<SliderModel> getSliderModelsList() {
        return sliderModelsList;
    }

    public void setSliderModelsList(List<SliderModel> sliderModelsList) {
        this.sliderModelsList = sliderModelsList;
    }
    /////============================ Banner Slider==============================


    /////============================Strip Ad==============================
    private String resource;

    public HomePage_Model(int type, String resource, String backgroundColor) {
        this.type = type;
        this.resource = resource;
        this.backgroundColor = backgroundColor;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }


    /////============================Strip Ad==============================





    private String title;
    private List<Horizontal_Product_scrollModel> horizontal_product_scrollModelList;


    /////============================HORIZONTAL PRODUCT LAYOUT ==============================

    private List<Wishlist_Model> viewAllProductList;

    public HomePage_Model(int type, String title,String backgroundColor, List<Horizontal_Product_scrollModel> horizontal_product_scrollModelList,List<Wishlist_Model> viewAllProductList) {
        this.type = type;
        this.title = title;
        this.backgroundColor = backgroundColor;
        this.horizontal_product_scrollModelList = horizontal_product_scrollModelList;
        this.viewAllProductList=viewAllProductList;
    }

    public List<Wishlist_Model> getViewAllProductList() {
        return viewAllProductList;
    }

    public void setViewAllProductList(List<Wishlist_Model> viewAllProductList) {
        this.viewAllProductList = viewAllProductList;
    }
    /////============================HORIZONTAL PRODUCT LAYOUT ==============================




    /////============================GRID PRODUCT LAYOUT ==============================

    public HomePage_Model(int type, String title, String backgroundColor, List<Horizontal_Product_scrollModel> horizontal_product_scrollModelList) {
        this.type = type;
        this.title = title;
        this.backgroundColor = backgroundColor;
        this.horizontal_product_scrollModelList = horizontal_product_scrollModelList;
    }


    public List<Horizontal_Product_scrollModel> getHorizontal_product_scrollModelList() {
        return horizontal_product_scrollModelList;
    }

    public void setHorizontal_product_scrollModelList(List<Horizontal_Product_scrollModel> horizontal_product_scrollModelList) {
        this.horizontal_product_scrollModelList = horizontal_product_scrollModelList;
    }
    /////============================GRID PRODUCT LAYOUT ==============================

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



}
