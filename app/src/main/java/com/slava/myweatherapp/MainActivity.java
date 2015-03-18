package com.slava.myweatherapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


public class MainActivity extends ActionBarActivity {

    // TAG for Logs
    public static final String TAG = MainActivity.class.getSimpleName();

    private Current mCurrent;

    // BeerSheva coordinates
    private double latitude = 31.2589;
    private double longitude = 34.7997;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String forecastUrl = createForecastUrl(latitude, longitude);
        getForecast(forecastUrl);

    }

    // Forecast URL
    private String createForecastUrl(double latitude, double longitude) {
        // Creation of Forecast URL
        String apiKey = "f330ede89d6b04c04dfed6f53bffeafb";

        return "https://api.forecast.io/forecast/"
                + apiKey + "/" + latitude + "," + longitude;
    }

    // Get request
    private void getForecast(String forecastUrl) {
        if (isNetworkAvailable()) {
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

                    try {
                        String jsonData = response.body().string();
                        Log.v(TAG, jsonData);
                        if(response.isSuccessful()) {
                            mCurrent = getCurrentDetailes(jsonData);
                        } else {
                            Log.v(TAG, "There is some problem with connection to Forecast.");
                            userForecastAlert();
                        }
                    } catch (IOException e) {
                        Log.e(TAG, "IO Exception caught: ", e);
                    } catch (JSONException e) {
                        Log.e(TAG, "JSON Exception caught: ", e);
                    } catch (Exception e) {
                        Log.e(TAG, "Generic Exception caught: ", e);
                    }
                }
            });
        } else {
            userNoInternetConnectionAlert();
        }
    }

    // Putting data from forecast string to Current class.
    private Current getCurrentDetailes(String jsonData) throws JSONException {
        JSONObject forecast = new JSONObject(jsonData);
        Current current = new Current();
        JSONObject currently = forecast.getJSONObject("currently");
        JSONObject daily = forecast.getJSONObject("daily");
        JSONArray dailyData = daily.getJSONArray("data");
        JSONObject currentDay = dailyData.getJSONObject(0);

        current.setTime(currently.getLong("time"));
        current.setTimeZone(forecast.getString("timezone"));
        current.setSummary(currently.getString("summary"));
        current.setIcon(currently.getString("icon"));
        current.setPrecipProbability(currently.getDouble("precipProbability"));
        current.setTemperature(currently.getDouble("temperature"));
        current.setApparentTemperature(currently.getDouble("apparentTemperature"));
        current.setHumidity(currently.getDouble("humidity"));
        current.setTemperatureMin(currentDay.getDouble("temperatureMin"));
        current.setTemperatureMinTime(currentDay.getLong("temperatureMinTime"));
        current.setTemperatureMax(currentDay.getDouble("temperatureMax"));
        current.setTemperatureMaxTime(currentDay.getLong("temperatureMaxTime"));
        current.setWeeklySummary(daily.getString("summary"));

        return current;
    }

    // Checkong internet status before Get request
    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if(networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        }

        return isAvailable;
    }

    // Errors messages. Using AlertDialogFragment class
    private void userForecastAlert() {
        AlertDialogFragment dialog = AlertDialogFragment.setMessage
                (getString(R.string.forecast_error_title),
                 getString(R.string.forecast_error_message));
        dialog.show(getFragmentManager(), "dialog");
    }

    private void userNoInternetConnectionAlert() {
        AlertDialogFragment dialog = AlertDialogFragment.setMessage
                (getString(R.string.forecast_error_title),
                 getString(R.string.no_internet_connection_message));
        dialog.show(getFragmentManager(), "dialog");
    }
}
