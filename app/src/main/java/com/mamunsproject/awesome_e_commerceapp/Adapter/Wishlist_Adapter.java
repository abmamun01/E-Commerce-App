package com.mamunsproject.awesome_e_commerceapp.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mamunsproject.awesome_e_commerceapp.Activity.Product_Details_Activity;
import com.mamunsproject.awesome_e_commerceapp.DB_Queries.DB_Queries;
import com.mamunsproject.awesome_e_commerceapp.Model.Wishlist_Model;
import com.mamunsproject.awesome_e_commerceapp.R;

import java.util.List;

public class Wishlist_Adapter extends RecyclerView.Adapter<Wishlist_Adapter.ViewHolder> {


    List<Wishlist_Model> wishlist_modelList;
    //===== AK E ADAPTER 2 TA DIFFERENT LAYOUTE USE KORAR JONNO BOOLEAN LAGANO HOICE R ADAPTERE ATA VALUE FALSE R TRUE KORE DILE J ITEMTA AMI NITE CHACCI NA SETA ASBENA
    private Boolean wish_List;
    private int lastPosition=-1;


    public Wishlist_Adapter(List<Wishlist_Model> wishlist_modelList, Boolean wish_List) {
        this.wishlist_modelList = wishlist_modelList;
        this.wish_List = wish_List;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wish_list_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String resource = wishlist_modelList.get(position).getProductImage();
        String title = wishlist_modelList.get(position).getProductTitle();
        long freeCoupons = wishlist_modelList.get(position).getFreeCoupon();
        String rating = wishlist_modelList.get(position).getRating();
        long totalRating = wishlist_modelList.get(position).getTotalRatings();
        String productPrice = wishlist_modelList.get(position).getCuttedPrice();
        String cuttedPrice = wishlist_modelList.get(position).getCuttedPrice();
        boolean paymentMethod = wishlist_modelList.get(position).isCod();
        holder.setData(resource, title, freeCoupons, rating, totalRating, productPrice, cuttedPrice, paymentMethod,position);

        if (lastPosition < position) {
            Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.fade_in);
            holder.itemView.setAnimation(animation);
            lastPosition = position;

        }
    }

    @Override
    public int getItemCount() {
        return wishlist_modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView productImage;
        private TextView productTitle;
        private TextView freeCoupons;
        private ImageView couponsIcon;
        private TextView rating;
        private TextView totalRatings;
        private View priceCut;
        private TextView productPrice;
        private TextView cuttedPrice;
        private TextView paymentMethod;

        private ImageButton deleteButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productImage = itemView.findViewById(R.id.product_image);
            productTitle = itemView.findViewById(R.id.product_title);
            freeCoupons = itemView.findViewById(R.id.free_coupon);
            couponsIcon = itemView.findViewById(R.id.coupon_icon);
            rating = itemView.findViewById(R.id.tv_product_rating_mini_view);
            totalRatings = itemView.findViewById(R.id.total_ratings);
            priceCut = itemView.findViewById(R.id.price_cut);
            productPrice = itemView.findViewById(R.id.product_price);
            cuttedPrice = itemView.findViewById(R.id.cutted_price);
            paymentMethod = itemView.findViewById(R.id.payment_method);
            deleteButton = itemView.findViewById(R.id.delete_btn);


        }

        private void setData(String resouce, String title, long freeCouponsNo, String averageRate, long totalRatingsNo, String price, String cuttedPriceValue, boolean cod,int index) {
            Glide.with(itemView.getContext()).load(resouce)
                    .apply(new RequestOptions().placeholder(R.drawable.ic_placeholder_big))
                    .into(productImage);
            productTitle.setText(title);

            if (freeCouponsNo != 0) {
                couponsIcon.setVisibility(View.VISIBLE);
                if (freeCouponsNo == 1) {
                    freeCoupons.setText("free " + freeCouponsNo + " coupon");

                } else {
                    freeCoupons.setText("free " + freeCouponsNo + " coupons");

                }
            } else {
                couponsIcon.setVisibility(View.INVISIBLE);
                freeCoupons.setVisibility(View.INVISIBLE);
            }


            rating.setText(averageRate);
            totalRatings.setText("("+totalRatingsNo +" )"+"ratings");

            productPrice.setText("৳"+price+"/-");
            cuttedPrice.setText("৳"+cuttedPriceValue+"/-");
            if (cod){
                paymentMethod.setVisibility(View.VISIBLE);

            }else {
                paymentMethod.setVisibility(View.INVISIBLE);
            }

            if (wish_List) {

                deleteButton.setVisibility(View.VISIBLE);
            } else {
                deleteButton.setVisibility(View.GONE);
            }

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    deleteButton.setEnabled(false);
                    DB_Queries.removeFromWishList(index,itemView.getContext());
                }
            });


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent productDetailsIntent = new Intent(itemView.getContext(), Product_Details_Activity.class);
                    itemView.getContext().startActivity(productDetailsIntent);
                }
            });
        }
    }


}
