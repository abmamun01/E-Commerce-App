package com.mamunsproject.awesome_e_commerceapp.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mamunsproject.awesome_e_commerceapp.Model.Cart_item_model;
import com.mamunsproject.awesome_e_commerceapp.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter {

    private List<Cart_item_model> cart_item_modelList;

    public CartAdapter(List<Cart_item_model> cart_item_modelList) {
        this.cart_item_modelList = cart_item_modelList;
    }


    @Override
    public int getItemViewType(int position) {
        switch (cart_item_modelList.get(position).getType()) {

            case 0:
                return Cart_item_model.CART_ITEM;
            case 1:
                return Cart_item_model.TOTAL_AMOUNT;

            default:
                return -1;

        }
    }


    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        switch (viewType) {
            case Cart_item_model.CART_ITEM:

                View cartItemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_layout, parent, false);
                return new CartItemViewHolder(cartItemview);


            case Cart_item_model.TOTAL_AMOUNT:

                View cartTotalView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_total_amount_layout, parent, false);
                return new CartTotalAmountViewHolder(cartTotalView);


            default:
                return null;

        }
    }


    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {

        switch (cart_item_modelList.get(position).getType()) {

            case Cart_item_model.CART_ITEM:

                int resouce = cart_item_modelList.get(position).getProductImage();
                String title = cart_item_modelList.get(position).getProductTitle();
                int freeCoupons = cart_item_modelList.get(position).getFreeCoupons();
                String productPrice = cart_item_modelList.get(position).getProductPrice();
                String cuttedPrice = cart_item_modelList.get(position).getCuttedPrice();
                int offersApplied = cart_item_modelList.get(position).getOffers_applied();

                ((CartItemViewHolder) holder).setItemDetails(resouce, title, freeCoupons, productPrice, cuttedPrice, offersApplied);

                break;


            case Cart_item_model.TOTAL_AMOUNT:

                String totalItems = cart_item_modelList.get(position).getTotalItems();
                String totalItemsPrice = cart_item_modelList.get(position).getTotalItemPrice();
                String deliveryPrice = cart_item_modelList.get(position).getDeliveryPrice();
                String totalAmount = cart_item_modelList.get(position).getTotalAmount();
                String savedAmount = cart_item_modelList.get(position).getSavedAmount();

                ((CartTotalAmountViewHolder) holder).setTotalAmount(totalItems, totalItemsPrice, deliveryPrice, totalAmount, savedAmount);


                break;


            default:
                return;

        }

    }


    @Override
    public int getItemCount() {
        return cart_item_modelList.size();
    }


    class CartItemViewHolder extends RecyclerView.ViewHolder {

        private ImageView productImage;
        private ImageView freeCouponsIcon;


        private TextView productTitle;
        private TextView freeCoupons;
        private TextView prouctPrice;
        private TextView cuttedPrice;
        private TextView offersApplied;
        private TextView couponsApplied;
        private TextView productQuantity;

        public CartItemViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            productImage = itemView.findViewById(R.id.product_image);
            freeCouponsIcon = itemView.findViewById(R.id.free_coupon_icon);
            productTitle = itemView.findViewById(R.id.product_title);
            freeCoupons = itemView.findViewById(R.id.tv_free_coupon);
            prouctPrice = itemView.findViewById(R.id.product_cart_price);
            cuttedPrice = itemView.findViewById(R.id.cutted_cart_price);
            offersApplied = itemView.findViewById(R.id.offers_applied);
            couponsApplied = itemView.findViewById(R.id.coupons_applied);
            productQuantity = itemView.findViewById(R.id.product_quantity_);
        }


        private void setItemDetails(int resource, String title, int freeCouponsNo, String productPriceText, String cuttedPriceText, int offersAppliedNo) {
            productImage.setImageResource(resource);
            productTitle.setText(title);
            if (freeCouponsNo > 0) {
                freeCouponsIcon.setVisibility(View.VISIBLE);
                freeCoupons.setVisibility(View.VISIBLE);
                if (freeCouponsNo == 1) {
                    freeCoupons.setText("free " + freeCouponsNo + " Coupon");
                } else {
                    freeCoupons.setText("free " + freeCouponsNo + " Coupons");

                }


            } else {
                freeCouponsIcon.setVisibility(View.INVISIBLE);
                freeCoupons.setVisibility(View.INVISIBLE);
            }

            prouctPrice.setText(productPriceText);
            cuttedPrice.setText(cuttedPriceText);
            if (offersAppliedNo > 0) {
                offersApplied.setVisibility(View.VISIBLE);
                offersApplied.setText(offersAppliedNo + " offers Applied");
            } else {
                offersApplied.setVisibility(View.INVISIBLE);
            }

//                        if (offersAppliedNo>0){
//                offersApplied.setVisibility(View.VISIBLE);
//                offersApplied.setText(offersAppliedNo+" offers Applied");
//            }else {
//                offersApplied.setVisibility(View.INVISIBLE);
//            }


            productQuantity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Dialog quantityDialog = new Dialog(itemView.getContext());
                    quantityDialog.setContentView(R.layout.quantitiy_dialuge);
                    quantityDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                    quantityDialog.setCancelable(false);
                    EditText quantityNo = quantityDialog.findViewById(R.id.quantity_no);
                    Button cacelButton = quantityDialog.findViewById(R.id.cancel_button);
                    Button okButton = quantityDialog.findViewById(R.id.ok_button);

                    cacelButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            quantityDialog.dismiss();
                        }
                    });

                    okButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            productQuantity.setText("Qty: " + quantityNo.getText());
                            quantityDialog.dismiss();

                        }
                    });


                    quantityDialog.show();
                }
            });

        }
    }


    private class CartTotalAmountViewHolder extends RecyclerView.ViewHolder {

        private TextView totalItems;
        private TextView totalprice;
        private TextView deleveryPrice;
        private TextView totalAmount;
        private TextView savedAmount;


        public CartTotalAmountViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);


            totalItems = itemView.findViewById(R.id.total_items);
            totalprice = itemView.findViewById(R.id.total_items_price);
            deleveryPrice = itemView.findViewById(R.id.delevery_price);
            totalAmount = itemView.findViewById(R.id.total_price);
            savedAmount = itemView.findViewById(R.id.saved_amount);
        }

        private void setTotalAmount(String totalItemText, String totalItemPriceText, String deleveryPriceText, String totalAmountText, String savedAmountText) {

            totalItems.setText(totalItemText);
            totalprice.setText(totalItemPriceText);
            deleveryPrice.setText(deleveryPriceText);
            totalAmount.setText(totalAmountText);
            savedAmount.setText(savedAmountText);


        }


    }


}

