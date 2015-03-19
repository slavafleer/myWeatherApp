package com.slava.myweatherapp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Slava on 16/03/2015.
 */
public class Current {
    private long mTime;
    private String mTimeZone;
    private String mSummary;
    private String mIcon;
    private double mPrecipProbability; // chance for rain
    private double mTemperature;
    private double mApparentTemperature; // feels like temp
    private double mHumidity;
    private double mTemperatureMin; // taken from daily
    private long mTemperatureMinTime; //    |
    private double mTemperatureMax; //      |
    private long mTemperatureMaxTime; //    |
    private String mWeeklySummary;  // taken from daily

    public String formatTime(long unixTime) {
        SimpleDateFormat formatter = new SimpleDateFormat("H:mm"); //24 hours format
        formatter.setTimeZone(TimeZone.getTimeZone(getTimeZone()));
        Date dateTime = new Date(unixTime * 1000); // Date uses milliseconds, while mTime in seconds.

        // Turning Date to String
        return formatter.format(dateTime);
    }

    public long getTime() {
        return mTime;
    }

    public void setTime(long time) {
        mTime = time;
    }

    public String getFormattedTime() {
        return formatTime(getTime());
    }

    public String getTimeZone() {
        return mTimeZone;
    }

    public void setTimeZone(String timeZone) {
        mTimeZone = timeZone;
    }

    public String getSummary() {
        return mSummary;
    }

    public void setSummary(String summary) {
        mSummary = summary;
    }

    public String getIcon() {
        return mIcon;
    }

    public void setIcon(String icon) {
        mIcon = icon;
    }

    public int getIconId() {
        // Default
        int iconId = R.drawable.clear_day;

        if(mIcon.equals("clear-day"))
            iconId = R.drawable.clear_day;
        else if(mIcon.equals("clear-night"))
            iconId = R.drawable.clear_night;
        else if(mIcon.equals("rain"))
            iconId = R.drawable.rain;
        else if(mIcon.equals("snow"))
            iconId = R.drawable.snow;
        else if(mIcon.equals("sleet"))
            iconId = R.drawable.sleet;
        else if(mIcon.equals("wind"))
            iconId = R.drawable.wind;
        else if(mIcon.equals("fog"))
            iconId = R.drawable.fog;
        else if(mIcon.equals("cloudy"))
            iconId = R.drawable.cloudy;
        else if(mIcon.equals("partly-cloudy-day"))
            iconId = R.drawable.partly_cloudy;
        else if(mIcon.equals("partly-cloudy-night"))
            iconId = R.drawable.cloudy_night;

        return iconId;
    }

    public int getPrecipProbability() {
        return (int) mPrecipProbability * 100;
    }

    public void setPrecipProbability(double precipProbability) {
        mPrecipProbability = precipProbability;
    }

    public int getTemperature() {
        return (int) Math.round(mTemperature);
    }

    public void setTemperature(double temperature) {
        mTemperature = temperature;
    }

    public int getApparentTemperature() {
        return (int) Math.round(mApparentTemperature);
    }

    public void setApparentTemperature(double apparentTemperature) {
        mApparentTemperature = apparentTemperature;
    }

    public double getHumidity() {
        return mHumidity;
    }

    public void setHumidity(double humidity) {
        mHumidity = humidity;
    }

    public int getTemperatureMin() {
        return (int) Math.round(mTemperatureMin);
    }

    public void setTemperatureMin(double temperatureMin) {
        mTemperatureMin = temperatureMin;
    }

    public long getTemperatureMinTime() {
        return mTemperatureMinTime;
    }

    public String getFormattedTemperatureMinTime() {
        return formatTime(getTemperatureMinTime());
    }

    public void setTemperatureMinTime(long temperatureMinTime) {
        mTemperatureMinTime = temperatureMinTime;
    }

    public int getTemperatureMax() {
        return (int) Math.round(mTemperatureMax);
    }

    public long getTemperatureMaxTime() {
        return mTemperatureMaxTime;
    }

    public String getFormattedTemperatureMaxTime() {
        return formatTime(getTemperatureMaxTime());
    }

    public void setTemperatureMaxTime(long temperatureMaxTime) {
        mTemperatureMaxTime = temperatureMaxTime;
    }

    public void setTemperatureMax(double temperatureMax) {
        mTemperatureMax = temperatureMax;
    }

    public String getWeeklySummary() {
        return mWeeklySummary;
    }

    public void setWeeklySummary(String weeklySummary) {
        mWeeklySummary = weeklySummary;
    }
}
