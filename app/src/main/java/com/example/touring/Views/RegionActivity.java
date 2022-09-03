package com.example.touring.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.touring.Config.EndPoint;
import com.example.touring.Logic.WebRegion;
import com.example.touring.R;
import com.example.touring.databinding.ActivityRegionBinding;

public class RegionActivity extends AppCompatActivity {
    ActivityRegionBinding regionBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        regionBinding = ActivityRegionBinding.inflate(getLayoutInflater());
        setContentView(regionBinding.getRoot());

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String type = intent.getStringExtra("type");

        ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("Loading ...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.show();
        // http://localhost/web/api/api.php?get=regions&type=as&state=8
        new WebRegion(this,regionBinding,progress).execute(EndPoint.regions+"&type="+ type+"&state="+id);
        Log.d("Localhost : " ,EndPoint.regions+"&type="+ type+"&state="+id);



    }


}