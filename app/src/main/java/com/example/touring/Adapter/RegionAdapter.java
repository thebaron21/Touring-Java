package com.example.touring.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.touring.Config.GetImageWeb;
import com.example.touring.Models.Region;
import com.example.touring.Views.RegionDetailsActivity;
import com.example.touring.databinding.StatesCardBinding;

import java.util.ArrayList;

public class RegionAdapter extends RecyclerView.Adapter<RegionAdapter.MyViewHolder> {

    ArrayList<Region> data;
    Context mContext;
    public RegionAdapter(ArrayList<Region> d,Context mContext){
        this.data=d;
        this.mContext = mContext;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        StatesCardBinding binding = StatesCardBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new RegionAdapter.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        Region region = data.get(position);
        holder.binding.titleState.setText(region.getName());
        new GetImageWeb(holder.binding.imageState)
                .execute(region.getImage());
        holder.itemView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(mContext, RegionDetailsActivity.class);
                        intent.putExtra("id",region.getId());
                        intent.putExtra("object", region.toJson());
                        mContext.startActivity(intent);
                    }
                }
        );


    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        StatesCardBinding binding;
        public MyViewHolder(@NonNull StatesCardBinding itemView) {
            super(itemView.getRoot());
            binding=itemView;
        }
    }
}
