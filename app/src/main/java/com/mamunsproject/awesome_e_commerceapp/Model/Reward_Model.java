package com.mamunsproject.awesome_e_commerceapp.Model;

public class Reward_Model {

    private String title;
    private String expiryDate;
    private String coupon_body;


    public Reward_Model(String title, String expiryDate, String coupon_body) {
        this.title = title;
        this.expiryDate = expiryDate;
        this.coupon_body = coupon_body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCoupon_body() {
        return coupon_body;
    }

    public void setCoupon_body(String coupon_body) {
        this.coupon_body = coupon_body;
    }
}
