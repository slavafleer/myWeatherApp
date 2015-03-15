package com.slava.myweatherapp;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Creation of Forecast URL
        String apiKey = "f330ede89d6b04c04dfed6f53bffeafb";
        // BeerSheva coordinates
        Double latitude = 31.2589;
        Double longitude = 34.7997;
        String forecastUrl = "https://api.forecast.io/forecast/"
                + "/" + latitude + "," + longitude;
    }
}
