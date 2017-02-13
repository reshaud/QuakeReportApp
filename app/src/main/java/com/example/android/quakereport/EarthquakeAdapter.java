package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static android.R.attr.cacheColorHint;
import static android.R.attr.resource;
import static android.support.v4.content.ContextCompat.getColor;

/**
 * Created by Reshaud Ally on 2/5/2017.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {
    public EarthquakeAdapter(Context context,ArrayList<Earthquake> earthquakesData) {
        super(context, 0,earthquakesData);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;

        // Check if the existing view is being reused, otherwise inflate the view
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_layout,parent,false);
        }

        //Get current object
        Earthquake currentEarthquake = getItem(position);

        //Find appropriate views
        TextView mag = (TextView) listItemView.findViewById(R.id.mag);
        TextView location = (TextView) listItemView.findViewById(R.id.location);
        TextView date = (TextView) listItemView.findViewById(R.id.date);
        TextView time = (TextView) listItemView.findViewById(R.id.time);
        TextView distance = (TextView) listItemView.findViewById(R.id.distance);

        //Format Mag as a decimal formatted as 0.0
        mag.setText(formatDecimal(currentEarthquake.getmMag()));

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) mag.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getmMag(),getContext());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        //Split location into 2 Strings distance and actual location
        if(currentEarthquake.getmLocation().contains("of")){
            int index = currentEarthquake.getmLocation().indexOf("of");

            distance.setText(currentEarthquake.getmLocation().substring(0,index + 2));
            location.setText(currentEarthquake.getmLocation().substring(index + 3));
        }else {
            distance.setText("Near the");
            location.setText(currentEarthquake.getmLocation());
        }

        //Date object with time in milliseconds
        Date dateObject = new Date(currentEarthquake.getmTime());

        //Set Date
        date.setText(formatDate(dateObject));

        //Set Time
        time.setText(formatTime(dateObject));

        return listItemView;
    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    private String formatDecimal(Double decimal){
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        return decimalFormat.format(decimal);
    }

    private int getMagnitudeColor(Double mag, Context context){
        int magnitude =  mag.intValue();
        switch (mag.intValue()){
            case 0:
            case 1:
                return ContextCompat.getColor(context,R.color.magnitude1);
            case 2:
                return ContextCompat.getColor(context,R.color.magnitude2);
            case 3:
                return ContextCompat.getColor(context,R.color.magnitude3);
            case 4:
                return ContextCompat.getColor(context,R.color.magnitude4);
            case  5:
                return ContextCompat.getColor(context,R.color.magnitude5);
            case 6:
                return ContextCompat.getColor(context,R.color.magnitude6);
            case 7:
                return ContextCompat.getColor(context,R.color.magnitude7);
            case 8:
                return ContextCompat.getColor(context,R.color.magnitude8);
            case 9:
                return ContextCompat.getColor(context,R.color.magnitude9);
            default:
                return ContextCompat.getColor(context,R.color.magnitude10plus);
        }
    }
}
