package com.mamunsproject.awesome_e_commerceapp.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.mamunsproject.awesome_e_commerceapp.Fragment.ProductDescription_Fragment;
import com.mamunsproject.awesome_e_commerceapp.Fragment.ProductSpecification_Fragment;

import org.jetbrains.annotations.NotNull;

public class ProductDetailsAdapter extends FragmentPagerAdapter {

    private int totalTabs;

    public ProductDetailsAdapter(@NonNull @NotNull FragmentManager fm, int totalTabs) {
        super(fm);
        this.totalTabs = totalTabs;

    }

    @NonNull
    @NotNull
    @Override
    public Fragment getItem(int position) {

        switch (position) {

            case 0:

                ProductDescription_Fragment productDescription_fragment1 = new ProductDescription_Fragment();

                return productDescription_fragment1;
            case 1:
                ProductSpecification_Fragment productSpecification_fragment = new ProductSpecification_Fragment();
                return productSpecification_fragment;


            case 2:
                ProductDescription_Fragment productDescription_fragment2 = new ProductDescription_Fragment();
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
