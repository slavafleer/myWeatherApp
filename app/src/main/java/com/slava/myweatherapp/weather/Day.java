package com.slava.myweatherapp.weather;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Slava on 29/03/2015.
 */
public class Day {
    private long mTime;
    private String mTimezone;
    private double mMaxTemp;
    private double mMinTemp;
    private String mIcon;

    public long getTime() {
        return mTime;
    }

    public void setTime(long time) {
        mTime = time;
    }

    public String getTimezone() {
        return mTimezone;
    }

    public void setTimezone(String timezone) {
        mTimezone = timezone;
    }

    public int getMaxTemp() {
        return (int) Math.round(mMaxTemp);
    }

    public void setMaxTemp(double maxTemp) {
        mMaxTemp = maxTemp;
    }

    public int getMinTemp() {
        return (int) Math.round(mMinTemp);
    }

    public void setMinTemp(double minTemp) {
        mMinTemp = minTemp;
    }

    public String getIcon() {
        return mIcon;
    }

    public void setIcon(String icon) {
        mIcon = icon;
    }

    public String getDayOfTheWeek() {
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE"); // day of the week
        formatter.setTimeZone(TimeZone.getTimeZone(mTimezone));
        Date day = new Date(mTime * 1000); // in milliseconds
        return formatter.format(day);
    }
}
