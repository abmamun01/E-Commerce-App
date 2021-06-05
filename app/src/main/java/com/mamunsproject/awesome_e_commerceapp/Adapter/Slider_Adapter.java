package com.mamunsproject.awesome_e_commerceapp.Adapter;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mamunsproject.awesome_e_commerceapp.Model.SliderModel;
import com.mamunsproject.awesome_e_commerceapp.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Slider_Adapter extends PagerAdapter {

    private List<SliderModel> sliderModelsList;

    public Slider_Adapter(List<SliderModel> sliderModelsList) {
        this.sliderModelsList = sliderModelsList;
    }


    @NonNull
    @NotNull
    @Override
    public Object instantiateItem(@NonNull @NotNull ViewGroup container, int position) {

        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.slider_layout_sample, container, false);

        ConstraintLayout bannerContainer = view.findViewById(R.id.bannerContainer);
        bannerContainer.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(sliderModelsList.get(position).getBackgroundColor())));

        ImageView banner = view.findViewById(R.id.banner_sliderId);

        Glide.with(container.getContext()).load(sliderModelsList.get(position).getBanner())
                .apply(new RequestOptions().placeholder(R.drawable.ic_placeholder_big)).into(banner);

        container.addView(view, 0);
        return view;
    }

    @Override
    public int getCount() {
        return sliderModelsList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull @NotNull View view, @NonNull @NotNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull @NotNull ViewGroup container, int position, @NonNull @NotNull Object object) {
        container.removeView((View) object);
    }


}
