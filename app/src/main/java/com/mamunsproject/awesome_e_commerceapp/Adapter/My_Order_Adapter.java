package com.mamunsproject.awesome_e_commerceapp.Adapter;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mamunsproject.awesome_e_commerceapp.Activity.Order_details_Activity;
import com.mamunsproject.awesome_e_commerceapp.Model.My_Order_Item_Model;
import com.mamunsproject.awesome_e_commerceapp.R;

import java.util.List;

public class My_Order_Adapter extends RecyclerView.Adapter<My_Order_Adapter.MyOrderHoder> {

    List<My_Order_Item_Model> my_order_item_modelList;

    public My_Order_Adapter(List<My_Order_Item_Model> my_order_item_modelList) {
        this.my_order_item_modelList = my_order_item_modelList;
    }

    @NonNull
    @Override
    public MyOrderHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_order_item_layout, parent, false);
        return new MyOrderHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyOrderHoder holder, int position) {

        int resource = my_order_item_modelList.get(position).getProductImage();
        int rating = my_order_item_modelList.get(position).getRating();

        String title = my_order_item_modelList.get(position).getProductTitle();
        String deliveredDate = my_order_item_modelList.get(position).getDeliveryStatus();

        holder.setData(resource,title,deliveredDate,rating);
    }

    @Override
    public int getItemCount() {
        return my_order_item_modelList.size();
    }

    public class MyOrderHoder extends RecyclerView.ViewHolder {

        private ImageView productImage;
        private ImageView order_indicatior;
        private TextView productTitle;
        private TextView deliveryStatus;
        private LinearLayout rateNowContainer;


        public MyOrderHoder(@NonNull View itemView) {
            super(itemView);

            productImage = itemView.findViewById(R.id.product_ordered_image);
            order_indicatior = itemView.findViewById(R.id.order_indicatior);
            productTitle = itemView.findViewById(R.id.product_ordered_title);
            deliveryStatus = itemView.findViewById(R.id.order_delivered_date);
            rateNowContainer = itemView.findViewById(R.id.rate_now_container);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent orderDetailsIntent = new Intent(itemView.getContext(), Order_details_Activity.class);
                    itemView.getContext().startActivity(orderDetailsIntent);

                }
            });

        }

        private void setData(int resource, String title, String deliveredDate, int rating) {
            productImage.setImageResource(resource);
            productTitle.setText(title);

            if (deliveredDate.equals("Cancelled")) {
                order_indicatior.setImageTintList(ColorStateList.
                        valueOf(itemView.getContext().getResources().getColor(R.color.red)));
            } else {
                order_indicatior.setImageTintList(ColorStateList.
                        valueOf(itemView.getContext().getResources().getColor(R.color.green_800)));
            }

            deliveryStatus.setText(deliveredDate);

            //===============RATING LAYOUT=========================


            setRating(rating);

            for (int x = 0; x < rateNowContainer.getChildCount(); x++) {
                final int starPosition = x;
                rateNowContainer.getChildAt(x).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setRating(starPosition);
                    }
                });
            }

            //===============RATING LAYOUT=========================
        }

        private void setRating(int starPosition) {

            for (int x = 0; x < rateNowContainer.getChildCount(); x++) {

                ImageView starButton = (ImageView) rateNowContainer.getChildAt(x);
                starButton.setImageTintList(ColorStateList.valueOf(Color.parseColor("#bebebe")));
                if (x <= starPosition) {
                    starButton.setImageTintList(ColorStateList.valueOf(Color.parseColor("#ffbb00")));
                }
            }
        }


    }
}
