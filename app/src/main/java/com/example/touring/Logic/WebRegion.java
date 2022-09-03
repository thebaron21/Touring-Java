package com.example.touring.Logic;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.recyclerview.widget.GridLayoutManager;

import com.example.touring.Adapter.RegionAdapter;
import com.example.touring.Adapter.StateAdapter;
import com.example.touring.Models.Region;
import com.example.touring.Views.RegionActivity;
import com.example.touring.databinding.ActivityRegionBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class WebRegion extends AsyncTask<String,Void,String> {
    Context mContext;
    ActivityRegionBinding regionBinding;
    ProgressDialog progress;

    public WebRegion(Context mContext, ActivityRegionBinding regionBinding, ProgressDialog progress) {
        this.mContext = mContext;
        this.regionBinding=regionBinding;
        this.progress=progress;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        ArrayList<Region> regions = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject ro = jsonArray.getJSONObject(i);
                Region region = new Region(
                        ro.getString("id"),
                        ro.getString("name"),
                        ro.getString("image"),
                        ro.getString("content"),
                        ro.getString("type")
                        );
                regions.add(region);
            }
            Log.d("regions",String.valueOf(regions.size()));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        RegionAdapter adapter = new RegionAdapter(regions,mContext);
        regionBinding.recyclerViewRegions.setLayoutManager(new GridLayoutManager(mContext,3));
        regionBinding.recyclerViewRegions.setAdapter(adapter);
        if( progress.isShowing() )
            progress.dismiss();
    }

    @Override
    protected String doInBackground(String... strings) {
        String data = null;
        HttpURLConnection http = null;
        try {
            http = (HttpURLConnection) new URL(strings[0]).openConnection();
            InputStream in = new BufferedInputStream(http.getInputStream());
            data = Stream2String(in);
            Log.d("D", data);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
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
