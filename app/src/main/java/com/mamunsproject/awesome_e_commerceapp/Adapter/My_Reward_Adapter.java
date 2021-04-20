package com.mamunsproject.awesome_e_commerceapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mamunsproject.awesome_e_commerceapp.Activity.Product_Details_Activity;
import com.mamunsproject.awesome_e_commerceapp.Model.Reward_Model;
import com.mamunsproject.awesome_e_commerceapp.R;

import java.util.List;


public class My_Reward_Adapter extends RecyclerView.Adapter<My_Reward_Adapter.ViewHolder> {

    private List<Reward_Model> reward_modelList;
    private Boolean useminiLayout = false;


    public My_Reward_Adapter(List<Reward_Model> reward_modelList, Boolean useminiLayout) {
        this.reward_modelList = reward_modelList;
        this.useminiLayout = useminiLayout;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        if (useminiLayout) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mini_rewards_item_layout, parent, false);
            return new ViewHolder(view);
        } else {

            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rewards_item_layout, parent, false);
            return new ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String title = reward_modelList.get(position).getTitle();
        String date = reward_modelList.get(position).getExpiryDate();
        String body = reward_modelList.get(position).getCoupon_body();

        holder.setData(title, date, body);
    }

    @Override
    public int getItemCount() {
        return reward_modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView couponTitle;
        private TextView couponExpiryDate;
        private TextView couponBody;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            couponTitle = itemView.findViewById(R.id.coupon_title);
            couponExpiryDate = itemView.findViewById(R.id.coupon_validity);
            couponBody = itemView.findViewById(R.id.coupon_body);


        }

        private void setData(String title, String date, String body) {
            couponTitle.setText(title);
            couponExpiryDate.setText(date);
            couponBody.setText(body);

            if (useminiLayout) {
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Product_Details_Activity.couponTitle.setText(title);
                        Product_Details_Activity.couponExpriyDate.setText(date);
                        Product_Details_Activity.couponBody.setText(body);
                        Product_Details_Activity.showDialogReclerView();

                    }
                });
            }

        }
    }
}
