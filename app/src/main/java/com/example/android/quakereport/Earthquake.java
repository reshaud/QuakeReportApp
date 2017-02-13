package com.example.android.quakereport;

import java.util.Date;

import static com.example.android.quakereport.R.id.date;

/**
 * Created by Reshaud Ally on 2/5/2017.
 */

public class Earthquake {
    private String mLocation;
    private long mTime;
    private Double mMag;
    private String mURL;

    Earthquake(String location, long time, Double mag,String url){
        this.mLocation = location;
        this.mTime = time;
        this.mMag = mag;
        this.mURL = url;
    }

    public String getmLocation() {
        return mLocation;
    }

    public long getmTime() {
        return mTime;
    }

    public Double getmMag() {
        return mMag;
    }

    public String getmURL() {
        return mURL;
    }
}
