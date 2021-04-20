package com.mamunsproject.awesome_e_commerceapp.Model;

public class CategoryModel {

    private String categoryIconLink;
    private String categoryName;


    public CategoryModel(String categoryIconLink, String categoryName) {
        this.categoryIconLink = categoryIconLink;
        this.categoryName = categoryName;
    }

    public CategoryModel() {
    }


    public String getCategoryIconLink() {
        return categoryIconLink;
    }

    public void setCategoryIconLink(String categoryIconLink) {
        this.categoryIconLink = categoryIconLink;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
