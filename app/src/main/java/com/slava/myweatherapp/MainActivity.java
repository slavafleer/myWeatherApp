package com.slava.myweatherapp;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;


public class MainActivity extends ActionBarActivity {

    // TAG for Logs
    public static final String TAG = MainActivity.class.getSimpleName();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Creation of Forecast URL
        String apiKey = "f330ede89d6b04c04dfed6f53bffeafb";
        // BeerSheva coordinates
        Double latitude = 31.2589;
        Double longitude = 34.7997;
        String forecastUrl = "https://api.forecast.io/forecast/"
                + apiKey + "/" + latitude + "," + longitude;

        // Creation of OkHttp Web request
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(forecastUrl)
                .build();
        Call call = client.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {

                if(response.isSuccessful())
                    Log.v(TAG, response.body().string());
                else
                    Log.v(TAG, "There is some problem with connection to Forecast");
            }
        });
    }
}
