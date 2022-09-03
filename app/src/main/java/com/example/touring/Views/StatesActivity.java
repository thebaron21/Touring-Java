package com.example.touring.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import com.example.touring.Config.EndPoint;
import com.example.touring.Logic.WebState;
import com.example.touring.databinding.ActivityStatesBinding;

public class StatesActivity extends AppCompatActivity {

    ActivityStatesBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStatesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.reyStates.setLayoutManager(new GridLayoutManager(this,3));

        Intent intent = getIntent();
        String type = intent.getStringExtra("type");


        ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("Loading ...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.show();


        new WebState(EndPoint.states,this,binding,progress,type).start();



    }
}