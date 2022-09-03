package com.example.touring.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.touring.Config.GetImageWeb;
import com.example.touring.Models.StateModel;
import com.example.touring.Views.RegionActivity;
import com.example.touring.databinding.StatesCardBinding;

import java.util.ArrayList;

public class StateAdapter  extends RecyclerView.Adapter<StateAdapter.MyViewHolder> {
    ArrayList<StateModel> states;
    Context mContext;
    String type;

    public StateAdapter(ArrayList<StateModel> states, Context mContext, String type) {
        this.states = states;
        this.mContext = mContext;
        this.type=type;
    }

    @NonNull
    @Override
    public StateAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        StatesCardBinding binding = StatesCardBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull StateAdapter.MyViewHolder holder, int position) {
        StateModel state = states.get(position);
        holder.binding.titleState.setText(state.getName());
//        new ThreadImage(state.getUrlImage(),holder.binding.imageState).start();
        new GetImageWeb(holder.binding.imageState)
                .execute(state.getUrlImage());
        holder.itemView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(mContext, RegionActivity.class);
                        intent.putExtra("id",state.getId());
                        intent.putExtra("type", type);
                        mContext.startActivity(intent);
                    }
                }
        );

    }

    @Override
    public int getItemCount() {
        return states.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        StatesCardBinding binding;
        public MyViewHolder(@NonNull StatesCardBinding itemView) {
            super(itemView.getRoot());
            binding=itemView;
        }
    }
}
