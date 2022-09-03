package com.example.touring.Config;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class GetImageWeb extends AsyncTask<String,Void, Bitmap> {


        private ImageView setImageBitmap;

        public GetImageWeb(ImageView imageView){
            setImageBitmap = imageView;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            setImageBitmap.setImageBitmap(bitmap);
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap bitmap = null;
            try {
                InputStream url  = new URL(EndPoint.localhost+strings[0]).openStream();
                bitmap = BitmapFactory.decodeStream(url);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return bitmap;
        }

}
