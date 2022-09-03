package com.example.touring;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.touring.Views.StatesActivity;
import com.example.touring.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    ProgressDialog mProgressDialog;
    ImageButton btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        Intent intent = new Intent(this, StatesActivity.class);

        binding.asBtn.setOnClickListener(
                view -> {
                    intent.putExtra("type" ,"as");
                    startActivity(intent);
                }
        );

        binding.syBtn.setOnClickListener(
                view -> {
                    intent.putExtra("type" ,"sy");
                    startActivity(intent);
                }
        );



    }




}








/*

HttpURLConnection urlConnection = (HttpURLConnection) new URL(strings[0]).openConnection();
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                String line;
                while((line=br.readLine())!=null){
                    dataPost+=line;
                }
                in.close();
 */

