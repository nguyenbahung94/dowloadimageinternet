package com.example.android.udemydowloadimage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView=(ImageView)findViewById(R.id.imageview);
        ImageDowload task=new ImageDowload();
        Bitmap myBitmap;
        try {
            myBitmap=task.execute("https://smilesandkittens.files.wordpress.com/2015/03/1.jpg").get();
            imageView.setImageBitmap(myBitmap);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
    public class ImageDowload extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... url)
        { Bitmap myBitmap = null;
            try {
                URL urll=new URL(url[0]);
                HttpURLConnection connection=(HttpURLConnection)urll.openConnection();
                connection.connect();
                InputStream inputStream=connection.getInputStream();
                 myBitmap= BitmapFactory.decodeStream(inputStream);

            }catch (Exception e){
                e.printStackTrace();
            }

            return myBitmap;

        }
    }
}
