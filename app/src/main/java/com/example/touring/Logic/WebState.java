package com.example.touring.Logic;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.util.Log;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.touring.Adapter.StateAdapter;
import com.example.touring.Models.StateModel;
import com.example.touring.databinding.ActivityStatesBinding;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.URL;
import java.util.ArrayList;

public class WebState extends Thread{
    String url;
    Context mContext;
    ActivityStatesBinding binding;
    ArrayList<StateModel> states;
    ProgressDialog progress;
    String type;
    public WebState(String url, Context mContext, ActivityStatesBinding binding, ProgressDialog progress, String type) {
        this.url = url;
        this.mContext = mContext;
        this.binding = binding;
        states = new ArrayList<>();
        this.progress=progress;
        this.type = type;
    }
    Handler mainHandler = new Handler();
    String data;


    @Override
    public void run() {
        super.run();
        try {
            URL uri = new URL(this.url);
            HttpURLConnection urlConnection = (HttpURLConnection) uri.openConnection();
            InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
            data = Stream2String(inputStream);
            JSONObject objectJson = new JSONObject(data);
            JSONArray jsonArray = objectJson.getJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                states.add(
                        new StateModel(
                                object.getString("id"),
                                object.getString("name"),
                                object.getString("image")
                                )
                );
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                StateAdapter adapter = new StateAdapter(states,mContext,type);
                binding.reyStates.setLayoutManager(new GridLayoutManager(mContext,3));
                binding.reyStates.setAdapter(adapter);
                if( progress.isShowing() )
                    progress.dismiss();
            }
        });


    }

    private String Stream2String(InputStream in){
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
        String line;
        String Text = "";
        try{
            while ((line=bufferedReader.readLine())!=null){
                Text=Text+line;
            }
            in.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return Text;
    }

}
