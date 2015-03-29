package com.slava.myweatherapp.ui;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.slava.myweatherapp.weather.Current;
import com.slava.myweatherapp.R;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends ActionBarActivity {

    // TAG for Logs
    public static final String TAG = MainActivity.class.getSimpleName();

    public static final String degreeSign = "\u00B0";

    private Current mCurrent;

    @InjectView(R.id.mainLayout) RelativeLayout mMainLayout;

    @InjectView(R.id.locationLabel) TextView mLocation;
    @InjectView(R.id.timeLabel) TextView mTime;
    @InjectView(R.id.temperature) TextView mTemperature;
    @InjectView(R.id.todaysLabel) TextView mTodaysLabel;
    @InjectView(R.id.maximumLabel) TextView mMaximumLabel;
    @InjectView(R.id.maxTemperature) TextView mMaxTemperature;
    @InjectView(R.id.minimumLabel) TextView mMinLabel;
    @InjectView(R.id.minTemperature) TextView mMinTemperature;
    @InjectView(R.id.feelsLikeLabel) TextView mFeelsLikeLabel;
    @InjectView(R.id.feelsLikeTemperature) TextView mFeelsLikeTemperature;
    @InjectView(R.id.icon) ImageView mIcon;
    @InjectView(R.id.summary) TextView mSummary;
    @InjectView(R.id.hourlySummary) TextView mHourlySummary;
    @InjectView(R.id.weeklySummary) TextView mWeeklySummary;
    @InjectView(R.id.humidityLabel) TextView mHumidyLabel;
    @InjectView(R.id.humidityValue) TextView mHumidity;
    @InjectView(R.id.precipLabel) TextView mPrecipLabel;
    @InjectView(R.id.precipValue) TextView mPrecip;
    @InjectView(R.id.refreshButton) ImageView mRefreshButton;
    @InjectView(R.id.progressSpinner) ProgressBar mProgressSpinner;
    @InjectView(R.id.settingsButton) ImageView mSettingsButton;

    // BeerSheva coordinates
    private double latitude = 31.2589;
    private double longitude = 34.7997;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Use ButterKnife
        ButterKnife.inject(this);

        final String forecastUrl = createForecastUrl(latitude, longitude);

        toggleRefreshButton();

        getForecast(forecastUrl);

        mRefreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getForecast(forecastUrl);
            }
        });

        mSettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        Log.v(TAG, "Returned to Main Layout");
        updateSettings();
    }

    private void toggleRefreshButton() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(mProgressSpinner.getVisibility() == View.INVISIBLE) {
                    mProgressSpinner.setVisibility(View.VISIBLE);
                    mRefreshButton.setVisibility(View.INVISIBLE);
                } else {
                    mProgressSpinner.setVisibility(View.INVISIBLE);
                    mRefreshButton.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    // Forecast URL
    private String createForecastUrl(double latitude, double longitude) {
        // Creation of Forecast URL
        String apiKey = "f330ede89d6b04c04dfed6f53bffeafb";

        return "https://api.forecast.io/forecast/"
                + apiKey + "/" + latitude + "," + longitude
                + "?units=ca"; // data in celsius and kilometers
    }

    // Get request
    private void getForecast(String forecastUrl) {
        if (isNetworkAvailable()) {
            toggleRefreshButton();
            // Creation of OkHttp Web request
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(forecastUrl)
                    .build();
            Call call = client.newCall(request);

            call.enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    toggleRefreshButton();
                }

                @Override
                public void onResponse(Response response) throws IOException {
                    toggleRefreshButton();
                    try {
                        String jsonData = response.body().string();
                        Log.v(TAG, jsonData);
                        if(response.isSuccessful()) {
                            mCurrent = getCurrentDetailes(jsonData);
                            updateSettings();
                            updateDisplay();
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

    private void updateSettings() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // Layout design settings
                mMainLayout.setBackgroundColor(Settings.backgroudColor);
                int textColor = Settings.textColor;
                mLocation.setTextColor(textColor);
                mTime.setTextColor(textColor);
                mTemperature.setTextColor(textColor);
                mTodaysLabel.setTextColor(textColor);
                mMaximumLabel.setTextColor(textColor);
                mMaxTemperature.setTextColor(textColor);
                mMinLabel.setTextColor(textColor);
                mMinTemperature.setTextColor(textColor);
                mFeelsLikeLabel.setTextColor(textColor);
                mFeelsLikeTemperature.setTextColor(textColor);
                mSummary.setTextColor(textColor);
                mHourlySummary.setTextColor(textColor);
                mWeeklySummary.setTextColor(textColor);
                mHumidyLabel.setTextColor(textColor);
                mHumidity.setTextColor(textColor);
                mPrecipLabel.setTextColor(textColor);
                mPrecip.setTextColor(textColor);
            }
        });

    }

    // Updating main layout
    private void updateDisplay() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // mCurrent --> main layout
                mTime.setText("At " + mCurrent.getFormattedTime() + " it will be");
                mTemperature.setText(mCurrent.getTemperature() + "");
                mMaxTemperature.setText(mCurrent.getTemperatureMax() + degreeSign +
                        " at " + mCurrent.getFormattedTemperatureMaxTime());
                mMinTemperature.setText(mCurrent.getTemperatureMin() + degreeSign +
                        " at " + mCurrent.getFormattedTemperatureMinTime());
                mFeelsLikeTemperature.setText(mCurrent.getApparentTemperature() + degreeSign);
                mIcon.setImageResource(mCurrent.getIconId());
                mSummary.setText(mCurrent.getSummary());
                mHourlySummary.setText(mCurrent.getHourlySummary());
                mWeeklySummary.setText(mCurrent.getWeeklySummary());
                mHumidity.setText(mCurrent.getHumidity() + "");
                mPrecip.setText(mCurrent.getPrecipProbability() + "%");
            }
        } );
    }

    // Putting data from forecast string to Current class.
    private Current getCurrentDetailes(String jsonData) throws JSONException {
        JSONObject forecast = new JSONObject(jsonData);
        Current current = new Current();
        JSONObject currently = forecast.getJSONObject("currently");
        JSONObject daily = forecast.getJSONObject("daily");
        JSONArray dailyData = daily.getJSONArray("data");
        JSONObject currentDay = dailyData.getJSONObject(0);
        JSONObject hourly = forecast.getJSONObject("hourly");

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
        current.setHourlySummary(hourly.getString("summary"));
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
