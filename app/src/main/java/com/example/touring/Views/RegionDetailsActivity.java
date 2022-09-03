package com.example.touring.Views;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;

import com.example.touring.Config.GetImageWeb;
import com.example.touring.MapsActivity;
import com.example.touring.Models.Region;
import com.example.touring.databinding.ActivityRegionDetailsBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;

public class RegionDetailsActivity extends AppCompatActivity  {
    String mapKey = "AIzaSyCUNDOup-AmMnKFZLFHlZOWI1MP8rAAgos";

    ActivityRegionDetailsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegionDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.button.setOnClickListener(
                view -> {
                    Intent intent = new Intent(this, MapsActivity.class);
                    startActivity(intent);
                });


        Intent intent = getIntent();
        HashMap hashMap = (HashMap) intent.getSerializableExtra("object");
        Region region = Region.toRegion(hashMap);
        binding.regionName.setText(region.getName());
        binding.regionContent.setText(region.getContent());
        Log.d("Content", intent.getSerializableExtra("object").toString());
        new GetImageWeb(binding.regionImg).execute(region.getImage());



    }



}









