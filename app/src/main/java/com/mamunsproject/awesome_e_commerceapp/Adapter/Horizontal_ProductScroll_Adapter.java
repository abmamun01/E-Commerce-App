package com.mamunsproject.awesome_e_commerceapp.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mamunsproject.awesome_e_commerceapp.Activity.Product_Details_Activity;
import com.mamunsproject.awesome_e_commerceapp.Model.Horizontal_Product_scrollModel;
import com.mamunsproject.awesome_e_commerceapp.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Horizontal_ProductScroll_Adapter extends RecyclerView.Adapter<Horizontal_ProductScroll_Adapter.HorizontalViewHolder> {


    private List<Horizontal_Product_scrollModel> horizontal_product_scrollModelList;

    public Horizontal_ProductScroll_Adapter(List<Horizontal_Product_scrollModel> horizontal_product_scrollModelList) {
        this.horizontal_product_scrollModelList = horizontal_product_scrollModelList;
    }

    @NonNull
    @NotNull
    @Override
    public HorizontalViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_scroll_item_layout, parent, false);
        return new HorizontalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull HorizontalViewHolder holder, int position) {

        String resource= horizontal_product_scrollModelList.get(position).getProductImage();
        String title=horizontal_product_scrollModelList.get(position).getProductTitle();
        String description=horizontal_product_scrollModelList.get(position).getProductDescription();
        String price=horizontal_product_scrollModelList.get(position).getProductPrice();
        String productId=horizontal_product_scrollModelList.get(position).getProduct_ID();

        holder.setData(productId,resource,title,description,price);


    }

    @Override
    public int getItemCount() {

        if (horizontal_product_scrollModelList.size()>8){
            return 8;
        }else {
            return horizontal_product_scrollModelList.size();
        }
    }

    public class HorizontalViewHolder extends RecyclerView.ViewHolder {

        public ImageView productImage;
        public TextView productTitle;
        public TextView productDescription;
        public TextView productPrice;

        public HorizontalViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            productImage = itemView.findViewById(R.id.h_s_product_image);
            productTitle = itemView.findViewById(R.id.h_s_product_title);
            productDescription = itemView.findViewById(R.id.hs_product_description);
            productPrice = itemView.findViewById(R.id.hs_product_price);

        }

        private void setData(final String productId,String resource,String title,String description,String price) {
           Glide.with(itemView.getContext()).load(resource)
                   .apply(new RequestOptions().placeholder(R.drawable.ic_placeholder_big)).
                   into(productImage);

            productTitle.setText(title);
            productDescription.setText(description);
            productPrice.setText("Tk."+price+"/-");

            if (!title.equals("")){
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent productDetailsIntent=new Intent(itemView.getContext(), Product_Details_Activity.class );
                        productDetailsIntent.putExtra("PRODUCT_ID",productId);
                        itemView.getContext().startActivity(productDetailsIntent);
                    }
                });
            }


        }




    }
}
