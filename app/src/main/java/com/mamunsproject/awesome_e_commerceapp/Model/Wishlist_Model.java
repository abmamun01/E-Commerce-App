package com.mamunsproject.awesome_e_commerceapp.Model;

public class Wishlist_Model {
    private String productId;
    private String productImage;
    private String productTitle;
    private long freeCoupon;
    private String rating;
    private long totalRatings;
    private String productPrice;
    private String cuttedPrice;
    private boolean cod;


    public Wishlist_Model(String productId,String productImage, String productTitle, long freeCoupon, String rating, long totalRatings, String productPrice, String cuttedPrice, boolean cod) {
        this.productId=productId;
        this.productImage = productImage;
        this.productTitle = productTitle;
        this.freeCoupon = freeCoupon;
        this.rating = rating;
        this.totalRatings = totalRatings;
        this.productPrice = productPrice;
        this.cuttedPrice = cuttedPrice;
        this.cod = cod;
    }


    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public long getFreeCoupon() {
        return freeCoupon;
    }

    public void setFreeCoupon(long freeCoupon) {
        this.freeCoupon = freeCoupon;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public long getTotalRatings() {
        return totalRatings;
    }

    public void setTotalRatings(long totalRatings) {
        this.totalRatings = totalRatings;
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

    public boolean isCod() {
        return cod;
    }

    public void setCod(boolean cod) {
        this.cod = cod;
    }
}
