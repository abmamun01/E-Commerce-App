package com.mamunsproject.awesome_e_commerceapp.Model;

public class Cart_item_model {

    public static final int CART_ITEM = 0;
    public static final int TOTAL_AMOUNT = 1;


    private int type;

    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
//============== CART ITEM===============


    private int productImage;
    private String productTitle;
    private int freeCoupons;
    private String productPrice;
    private String cuttedPrice;
    private int product_quantity;
    private int offers_applied;
    private int coupons_applied;

    public Cart_item_model(int type, int productImage, String productTitle, int freeCoupons, String productPrice, String cuttedPrice, int product_quantity, int offers_applied, int coupons_applied) {
        this.type = type;
        this.productImage = productImage;
        this.productTitle = productTitle;
        this.freeCoupons = freeCoupons;
        this.productPrice = productPrice;
        this.cuttedPrice = cuttedPrice;
        this.product_quantity = product_quantity;
        this.offers_applied = offers_applied;
        this.coupons_applied = coupons_applied;
    }

    public int getProductImage() {
        return productImage;
    }

    public void setProductImage(int productImage) {
        this.productImage = productImage;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public int getFreeCoupons() {
        return freeCoupons;
    }

    public void setFreeCoupons(int freeCoupons) {
        this.freeCoupons = freeCoupons;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getCuttedPrice() {
        return cuttedPrice;
    }

    public void setCuttedPrice(String cuttedPrice) {
        this.cuttedPrice = cuttedPrice;
    }

    public int getProduct_quantity() {
        return product_quantity;
    }

    public void setProduct_quantity(int product_quantity) {
        this.product_quantity = product_quantity;
    }

    public int getOffers_applied() {
        return offers_applied;
    }

    public void setOffers_applied(int offers_applied) {
        this.offers_applied = offers_applied;
    }

    public int getCoupons_applied() {
        return coupons_applied;
    }

    public void setCoupons_applied(int coupons_applied) {
        this.coupons_applied = coupons_applied;
    }


    //============== CART ITEM===============






    //============== CART TOTAL===============

    private String totalItems;
    private String totalItemPrice;
    private String deliveryPrice;
    private String savedAmount;
    private String totalAmount;

    public Cart_item_model(int type, String totalItems, String totalItemPrice, String deliveryPrice, String savedAmount, String totalAmount) {
        this.type = type;
        this.totalItems = totalItems;
        this.totalItemPrice = totalItemPrice;
        this.deliveryPrice = deliveryPrice;
        this.savedAmount = savedAmount;
        this.totalAmount = totalAmount;
    }

    public String getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(String totalItems) {
        this.totalItems = totalItems;
    }

    public String getTotalItemPrice() {
        return totalItemPrice;
    }

    public void setTotalItemPrice(String totalItemPrice) {
        this.totalItemPrice = totalItemPrice;
    }

    public String getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(String deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    public String getSavedAmount() {
        return savedAmount;
    }

    public void setSavedAmount(String savedAmount) {
        this.savedAmount = savedAmount;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    //============== CART TOTAL===============


}
