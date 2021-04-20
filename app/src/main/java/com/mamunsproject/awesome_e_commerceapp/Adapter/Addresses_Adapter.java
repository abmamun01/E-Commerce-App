package com.mamunsproject.awesome_e_commerceapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mamunsproject.awesome_e_commerceapp.Model.Adresses_Model;
import com.mamunsproject.awesome_e_commerceapp.R;

import java.util.List;

import static com.mamunsproject.awesome_e_commerceapp.Activity.Delivery_Activity.SELECT_ADDRESS;
import static com.mamunsproject.awesome_e_commerceapp.Activity.My_Adresses_Activity.refreshItem;
import static com.mamunsproject.awesome_e_commerceapp.Fragment.My_Account_Fragment.MANAGE_ADDRESS;

public class Addresses_Adapter extends RecyclerView.Adapter<Addresses_Adapter.ViewHolder> {

    private List<Adresses_Model> adresses_modelList;
    private int MODE;
    private int preSelectedPosition = -1;


    public Addresses_Adapter(List<Adresses_Model> adresses_modelList, int MODE) {
        this.adresses_modelList = adresses_modelList;
        this.MODE = MODE;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adresses_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String name = adresses_modelList.get(position).getFullName();
        String address = adresses_modelList.get(position).getAddress();
        String pincode = adresses_modelList.get(position).getPincode();
        Boolean selected = adresses_modelList.get(position).getSelected();
        holder.setData(name, address, pincode, selected, position);

    }

    @Override
    public int getItemCount() {
        return adresses_modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView fullName;
        private TextView address;
        private TextView pincode;
        private ImageView icon;
        private LinearLayout optionContainer;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fullName = itemView.findViewById(R.id.name);
            address = itemView.findViewById(R.id.address);
            pincode = itemView.findViewById(R.id.pincode);
            icon = itemView.findViewById(R.id.iconView);
            optionContainer = itemView.findViewById(R.id.option_container);


        }

        private void setData(String username, String userAddress, String userPincode, Boolean selected, int position) {

            fullName.setText(username);
            address.setText(userAddress);
            pincode.setText(userPincode);

            if (MODE == SELECT_ADDRESS) {
                icon.setImageResource(R.drawable.ic_baseline_check_24);
                if (selected) {
                    icon.setVisibility(View.VISIBLE);
                    preSelectedPosition = position;

                } else {
                    icon.setVisibility(View.GONE);
                }
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //USER JETAY ACE SEI POSITION THEKE JODI BAR BAR CLICK KORE TAHOLE KAJ JATE NA HOY TAI IF DIYE BLOCK KORECI
                        if (preSelectedPosition != position) {
                            adresses_modelList.get(position).setSelected(true);
                            adresses_modelList.get(preSelectedPosition).setSelected(false);
                            refreshItem(preSelectedPosition, position);

                            preSelectedPosition = position;

                        }


                    }
                });


            } else if (MODE == MANAGE_ADDRESS) {

                optionContainer.setVisibility(View.GONE);
                icon.setImageResource(R.drawable.ic_baseline_more_vert_24);
                icon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        optionContainer.setVisibility(View.VISIBLE);
                        refreshItem(preSelectedPosition, preSelectedPosition);
                        preSelectedPosition = position;

                    }
                });
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //JATE KONO OPTION OPEN THAKLE REFRESH HOLE PROTHOM JEMON CHILO TEMON HOYE JABE ORTHAT GONE HOBE
                        refreshItem(preSelectedPosition, preSelectedPosition);
                        preSelectedPosition = -1;
                    }
                });

            }
        }
    }
}
