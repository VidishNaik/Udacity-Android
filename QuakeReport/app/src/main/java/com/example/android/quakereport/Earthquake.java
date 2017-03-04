package com.example.android.quakereport;

import java.util.Date;

/**
 * Created by Vidish on 07-09-2016.
 */
public class Earthquake {
    private double mMagnitude;
    private String mLocation;
    private String mDate;
    private String mLink;

    public Earthquake(double Magnitude,String Location, String date,String Link)
    {
        mMagnitude=Magnitude;
        mLocation=Location;
        mDate=date;
        mLink=Link;
    }
    public double getMagnitude()
    {
        return mMagnitude;
    }
    public String getLocation(){
        return mLocation;
    }
    public String getDate(){
        return mDate;
    }
    public String getLink(){
        return mLink;
    }
}
