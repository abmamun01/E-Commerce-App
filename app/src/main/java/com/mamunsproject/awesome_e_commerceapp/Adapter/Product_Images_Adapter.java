package com.mamunsproject.awesome_e_commerceapp.Adapter;

import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Product_Images_Adapter extends PagerAdapter {

    List<Integer> productImages;

    public Product_Images_Adapter(List<Integer> productImages) {
        this.productImages = productImages;
    }


    @NonNull
    @NotNull
    @Override
    public Object instantiateItem(@NonNull @NotNull ViewGroup container, int position) {

        ImageView productImage = new ImageView(container.getContext());
        productImage.setImageResource(productImages.get(position));
        container.addView(productImage, 0);
        return productImage;

    }

    @Override
    public void destroyItem(@NonNull @NotNull ViewGroup container, int position, @NonNull @NotNull Object object) {

        container.removeView((ImageView) object);
    }

    @Override
    public int getCount() {
        return productImages.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull @NotNull View view, @NonNull @NotNull Object object) {
        return view == object;
    }
}
