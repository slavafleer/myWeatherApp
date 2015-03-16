package com.slava.myweatherapp;

/**
 * Created by Slava on 16/03/2015.
 */
public class Current {
    private long mTime;
    private String mSummary;
    private String mIcon;
    private double mPrecipProbability; // chance for rain
    private double mTemperature;
    private double mApparentTemperature; // feels like temp
    private double mMinTemperature; // taken from daily
    private double mMaxTemperature;
    private String mWeeklySummary;

    public long getTime() {
        return mTime;
    }

    public void setTime(long time) {
        mTime = time;
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

    public double getMinTemperature() {
        return mMinTemperature;
    }

    public void setMinTemperature(double minTemperature) {
        mMinTemperature = minTemperature;
    }

    public double getMaxTemperature() {
        return mMaxTemperature;
    }

    public void setMaxTemperature(double maxTemperature) {
        mMaxTemperature = maxTemperature;
    }

    public String getWeeklySummary() {
        return mWeeklySummary;
    }

    public void setWeeklySummary(String weeklySummary) {
        mWeeklySummary = weeklySummary;
    }
}
