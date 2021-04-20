package com.mamunsproject.awesome_e_commerceapp.Adapter;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mamunsproject.awesome_e_commerceapp.Activity.Product_Details_Activity;
import com.mamunsproject.awesome_e_commerceapp.Model.Horizontal_Product_scrollModel;
import com.mamunsproject.awesome_e_commerceapp.R;

import java.util.List;

public class GridProductVIew_Layout_Adapter extends BaseAdapter {

    List<Horizontal_Product_scrollModel> horizontal_product_scrollModelList;

    public GridProductVIew_Layout_Adapter(List<Horizontal_Product_scrollModel> horizontal_product_scrollModelList) {
        this.horizontal_product_scrollModelList = horizontal_product_scrollModelList;
    }

    @Override
    public int getCount() {
        return horizontal_product_scrollModelList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view;
        if (convertView == null){

            view=LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_scroll_item_layout, null);
             view.setElevation(0);
             view.setBackgroundColor(Color.parseColor("#ffffff"));

             view.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     Intent productDetailsIntent=new Intent(parent.getContext(), Product_Details_Activity.class);
                     parent.getContext().startActivity(productDetailsIntent);
                 }
             });

            ImageView productImage=view.findViewById(R.id.h_s_product_image);
            TextView productTitle=view.findViewById(R.id.h_s_product_title);
            TextView productDescription=view.findViewById(R.id.hs_product_description);
            TextView productPrice=view.findViewById(R.id.hs_product_price);


            Glide.with(parent.getContext()).load(horizontal_product_scrollModelList.get(position).getProductImage())
                    .apply(new RequestOptions().placeholder(R.drawable.ic_rounded_home)).into(productImage);
            productTitle.setText(horizontal_product_scrollModelList.get(position).getProductTitle());
            productDescription.setText(horizontal_product_scrollModelList.get(position).getProductDescription());
            productPrice.setText("Tk."+horizontal_product_scrollModelList.get(position).getProductPrice()+"/-");

        }else {
            view=convertView;
        }

        return view;
    }
}
