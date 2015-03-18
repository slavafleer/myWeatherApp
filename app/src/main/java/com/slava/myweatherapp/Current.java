package com.slava.myweatherapp;

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
    private double mTemperatureMin; // taken from daily
    private long mTemperatureMinTime; //    |
    private double mTemperatureMax; //      |
    private long mTemperatureMaxTime; //    |
    private String mWeeklySummary;  // taken from daily

    public long getTime() {
        return mTime;
    }

    public void setTime(long time) {
        mTime = time;
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

    public double getPrecipProbability() {
        return mPrecipProbability;
    }

    public void setPrecipProbability(double precipProbability) {
        mPrecipProbability = precipProbability;
    }

    public double getTemperature() {
        return mTemperature;
    }

    public void setTemperature(double temperature) {
        mTemperature = temperature;
    }

    public double getApparentTemperature() {
        return mApparentTemperature;
    }

    public void setApparentTemperature(double apparentTemperature) {
        mApparentTemperature = apparentTemperature;
    }

    public double getTemperatureMin() {
        return mTemperatureMin;
    }

    public void setTemperatureMin(double temperatureMin) {
        mTemperatureMin = temperatureMin;
    }

    public long getTemperatureMinTime() {
        return mTemperatureMinTime;
    }

    public void setTemperatureMinTime(long temperatureMinTime) {
        mTemperatureMinTime = temperatureMinTime;
    }

    public double getTemperatureMax() {
        return mTemperatureMax;
    }

    public long getTemperatureMaxTime() {
        return mTemperatureMaxTime;
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
