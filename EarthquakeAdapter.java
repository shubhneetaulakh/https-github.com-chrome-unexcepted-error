package com.example.android.quakereport;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
/**
 * Created by sharma on 28-03-2017.
 */


public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    private static final String LOCATION_SEPARATOR = " of ";


    public EarthquakeAdapter(Activity context, ArrayList<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.earthquake_list_item, parent, false);
        }


        Earthquake currentEarthquake = getItem(position);

        TextView magnitude = (TextView) listItemView.findViewById(R.id.magnitude);
        //format the magnitude
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        String mag  = magnitudeFormat.format(currentEarthquake.getMagnitude());
        magnitude.setText(mag);

        TextView primary_location = (TextView) listItemView.findViewById(R.id.primary_location);
        TextView location_offset = (TextView) listItemView.findViewById(R.id.location_offset);
        //split the location

        if(currentEarthquake.getLocation().contains(LOCATION_SEPARATOR))
        {
              String[] parts = currentEarthquake.getLocation().split(LOCATION_SEPARATOR);
              parts[0] = parts[0].concat(LOCATION_SEPARATOR);
              primary_location.setText(parts[0]);
              location_offset.setText(parts[1]);
        }
        else
        {
            primary_location.setText("near The");
            location_offset.setText(currentEarthquake.getLocation());
        }

        TextView date = (TextView) listItemView.findViewById(R.id.date);
        date.setText(currentEarthquake.getDate());

        TextView time = (TextView) listItemView.findViewById(R.id.time);
        time.setText(currentEarthquake.getTime());



        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitude.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);


        return listItemView;
    }


    private int getMagnitudeColor(double magnitude)
    {
        int magResourceId;
        int magnitudeColorId = (int) Math.floor(magnitude);
        switch(magnitudeColorId)
        {
            case 0:
            case 1:
                magResourceId = R.color.magnitude1;
                break;
            case 2:
                magResourceId = R.color.magnitude2;
                break;
            case 3:
                magResourceId = R.color.magnitude3;
                break;
            case 4:
                magResourceId = R.color.magnitude4;
                break;
            case 5:
                magResourceId = R.color.magnitude5;
                break;
            case 6:
                magResourceId = R.color.magnitude6;
                break;
            case 7:
                magResourceId = R.color.magnitude7;
                break;
            case 8:
                magResourceId = R.color.magnitude8;
                break;
            case 9:
                magResourceId = R.color.magnitude9;
                break;
            default:
                magResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magResourceId);
    }
}

