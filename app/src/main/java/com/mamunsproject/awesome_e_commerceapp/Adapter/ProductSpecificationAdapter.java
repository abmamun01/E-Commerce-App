package com.mamunsproject.awesome_e_commerceapp.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mamunsproject.awesome_e_commerceapp.Model.ProductSpecificationModel;
import com.mamunsproject.awesome_e_commerceapp.R;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.util.List;

public class ProductSpecificationAdapter extends RecyclerView.Adapter<ProductSpecificationAdapter.ProductViewHolder> {

    private List<ProductSpecificationModel> productSpecificationModelList;

    public ProductSpecificationAdapter(List<ProductSpecificationModel> productSpecificationModelList) {
        this.productSpecificationModelList = productSpecificationModelList;
    }

    @NonNull
    @NotNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        switch (viewType) {
            case ProductSpecificationModel.SPECIFICATION_TITLE:

                TextView title = new TextView(parent.getContext());
                title.setTypeface(null, Typeface.BOLD);
                title.setTextColor(Color.parseColor("#000000"));
                //============   For Margin ==============
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(setDp(16,parent.getContext()),setDp(16,parent.getContext()),
                        setDp(16,parent.getContext()),
                        setDp(8,parent.getContext()));

                title.setLayoutParams(layoutParams);
                return new ProductViewHolder(title);


            case ProductSpecificationModel.SPECIFICATION_BODY:

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_specification_item_layout, parent, false);
                return new ProductViewHolder(view);

            default:
                return null;
        }


    }


    @Override
    public int getItemViewType(int position) {

        switch (productSpecificationModelList.get(position).getType()) {
            case 0:
                return ProductSpecificationModel.SPECIFICATION_TITLE;

            case 1:
                return ProductSpecificationModel.SPECIFICATION_BODY;

            default:
                return -1;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ProductViewHolder holder, int position) {

        switch (productSpecificationModelList.get(position).getType()){

            case ProductSpecificationModel.SPECIFICATION_TITLE:
                holder.setTitle(productSpecificationModelList.get(position).getTitle());


                break;
            case ProductSpecificationModel.SPECIFICATION_BODY:

                String featureTitle = productSpecificationModelList.get(position).getFeatureName();
                String featureDetail = productSpecificationModelList.get(position).getFeatureValue();
                holder.setFeatures(featureTitle, featureDetail);
                break;

            default:
                return;
        }



    }

    @Override
    public int getItemCount() {
        return productSpecificationModelList.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {

        private TextView featureName;
        private TextView featureValue;
        private TextView title;


        public ProductViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);


        }

        private void setTitle(String titleText){
            //itemview howar karon hocce amra jei title return koreci seta ProductViewHolder
            title= (TextView) itemView;
            title.setText(titleText);
        }
        private void setFeatures(String featureTitle, String featureDetail) {
            featureName = itemView.findViewById(R.id.feature_name);
            featureValue = itemView.findViewById(R.id.feature_value);

            featureName.setText(featureTitle);
            featureValue.setText(featureDetail);
        }
    }


    //For converting Pixel to dp
    private int setDp(int dp , Context context){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,context.getResources().getDisplayMetrics());
    }
}
