package com.mamunsproject.awesome_e_commerceapp.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.mamunsproject.awesome_e_commerceapp.Fragment.ProductDescription_Fragment;
import com.mamunsproject.awesome_e_commerceapp.Fragment.ProductSpecification_Fragment;
import com.mamunsproject.awesome_e_commerceapp.Model.ProductSpecificationModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ProductDetailsAdapter extends FragmentPagerAdapter {

    private int totalTabs;
    private String productDescription;
    private String productOtherDetails;
    private List<ProductSpecificationModel> productSpecificationModelList;



    public ProductDetailsAdapter(@NonNull @NotNull FragmentManager fm, int totalTabs,String productDescription, String productOtherDetails, List<ProductSpecificationModel> productSpecificationModelList) {
        super(fm);
        this.productDescription = productDescription;
        this.productOtherDetails = productOtherDetails;
        this.productSpecificationModelList = productSpecificationModelList;
        this.totalTabs = totalTabs;
    }




    @NonNull
    @NotNull
    @Override
    public Fragment getItem(int position) {

        switch (position) {

            case 0:

                ProductDescription_Fragment productDescription_fragment1 = new ProductDescription_Fragment();
                productDescription_fragment1.body =productDescription;
                return productDescription_fragment1;
            case 1:
                ProductSpecification_Fragment productSpecification_fragment = new ProductSpecification_Fragment();
               productSpecification_fragment.productSpecificationModelsList=productSpecificationModelList;
                return productSpecification_fragment;


            case 2:
                ProductDescription_Fragment productDescription_fragment2 = new ProductDescription_Fragment();
               productDescription_fragment2.body=productOtherDetails;
                return productDescription_fragment2;

            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
