package com.mamunsproject.awesome_e_commerceapp.Model;

public class Horizontal_Product_scrollModel {

    private String product_ID;
    private String productImage;
    private String productTitle;
    private String productDescription;
    private String productPrice;


    public Horizontal_Product_scrollModel() {
    }

    public Horizontal_Product_scrollModel(String product_ID,String productImage, String productTitle, String productDescription, String productPrice) {
        this.product_ID = product_ID;
        this.productImage = productImage;
        this.productTitle = productTitle;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
    }


    public String getProduct_ID() {
        return product_ID;
    }

    public void setProduct_ID(String product_ID) {
        this.product_ID = product_ID;
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

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }
}
