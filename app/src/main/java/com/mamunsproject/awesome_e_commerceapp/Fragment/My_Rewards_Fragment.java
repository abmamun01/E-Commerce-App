package com.mamunsproject.awesome_e_commerceapp.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mamunsproject.awesome_e_commerceapp.Adapter.My_Reward_Adapter;
import com.mamunsproject.awesome_e_commerceapp.Model.Reward_Model;
import com.mamunsproject.awesome_e_commerceapp.R;

import java.util.ArrayList;
import java.util.List;


public class My_Rewards_Fragment extends Fragment {

    private RecyclerView rewardsRecyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my__rewards_, container, false);


        rewardsRecyclerView = view.findViewById(R.id.my_rewards_recyclerview);
        rewardsRecyclerView.setHasFixedSize(true);
        rewardsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        List<Reward_Model> reward_modelList = new ArrayList<>();
        reward_modelList.add(new Reward_Model("Cashback", "till 2nd , June 2021", "Get 20 % CASHBAK on any product and so mony offer to get enter the app daily"));
        reward_modelList.add(new Reward_Model("Discount", "till 2nd , June 2021", "Get 20 % CASHBAK on any product and so mony offer to get enter the app daily"));
        reward_modelList.add(new Reward_Model("Buy 1 get 1 free", "till 2nd , June 2021", "Get 20 % CASHBAK on any product and so mony offer to get enter the app daily"));
        reward_modelList.add(new Reward_Model("Cashback", "till 2nd , June 2021", "Get 20 % CASHBAK on any product and so mony offer to get enter the app daily"));
        reward_modelList.add(new Reward_Model("Discount", "till 2nd , June 2021", "Get 20 % CASHBAK on any product and so mony offer to get enter the app daily"));
        reward_modelList.add(new Reward_Model("Buy 1 get 1 free", "till 2nd , June 2021", "Get 20 % CASHBAK on any product and so mony offer to get enter the app daily"));
        reward_modelList.add(new Reward_Model("Cashback", "till 2nd , June 2021", "Get 20 % CASHBAK on any product and so mony offer to get enter the app daily"));
        reward_modelList.add(new Reward_Model("Discount", "till 2nd , June 2021", "Get 20 % CASHBAK on any product and so mony offer to get enter the app daily"));
        reward_modelList.add(new Reward_Model("Buy 1 get 1 free", "till 2nd , June 2021", "Get 20 % CASHBAK on any product and so mony offer to get enter the app daily"));
        reward_modelList.add(new Reward_Model("Cashback", "till 2nd , June 2021", "Get 20 % CASHBAK on any product and so mony offer to get enter the app daily"));
        reward_modelList.add(new Reward_Model("Discount", "till 2nd , June 2021", "Get 20 % CASHBAK on any product and so mony offer to get enter the app daily"));
        reward_modelList.add(new Reward_Model("Buy 1 get 1 free", "till 2nd , June 2021", "Get 20 % CASHBAK on any product and so mony offer to get enter the app daily"));


        My_Reward_Adapter my_reward_adapter=new My_Reward_Adapter(reward_modelList,false);
        rewardsRecyclerView.setAdapter(my_reward_adapter);
        my_reward_adapter.notifyDataSetChanged();

        return view;

    }
}