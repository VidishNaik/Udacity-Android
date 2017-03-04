package com.example.android.quakereport;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.content.pm.PackageManager;

import java.util.ArrayList;
import java.util.Calendar;


import static android.support.v4.app.ActivityCompat.startActivity;
import static android.support.v4.app.ActivityCompat.startActivityForResult;

/**
 * Created by Vidish on 07-09-2016.
 */
public class EarthquakeAdapter extends ArrayAdapter<Earthquake>
{
    public EarthquakeAdapter(Context context, ArrayList earthquake)
    {
        super(context,0,earthquake);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView= convertView;
        if(listItemView==null)
        {
            listItemView= LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }
        final Earthquake currentEarthQuake=getItem(position);
        TextView magnitude=(TextView) listItemView.findViewById(R.id.Magnitude);
        magnitude.setText(""+currentEarthQuake.getMagnitude());
        if(currentEarthQuake.getLocation().contains("of")) {
            String s[]=currentEarthQuake.getLocation().split("of");
            TextView location = (TextView) listItemView.findViewById(R.id.Location);
            location.setText(s[0]+" of");
            location=(TextView) listItemView.findViewById(R.id.Location2);
            location.setText(s[1]);
        }
        else
        {
            TextView location = (TextView) listItemView.findViewById(R.id.Location);
            location.setText("Near the");
            location=(TextView) listItemView.findViewById(R.id.Location2);
            location.setText(currentEarthQuake.getLocation());
        }
        Calendar cl = Calendar.getInstance();
        cl.setTimeInMillis(Long.parseLong(currentEarthQuake.getDate()));
        String date = "" + cl.get(Calendar.DAY_OF_MONTH) + "/" + (1+cl.get(Calendar.MONTH)) + "/" + cl.get(Calendar.YEAR);
        String time = "" + cl.get(Calendar.HOUR_OF_DAY) + ":" + cl.get(Calendar.MINUTE);
        TextView dt=(TextView) listItemView.findViewById(R.id.Date);
        dt.setText(date);
        dt = (TextView) listItemView.findViewById(R.id.Time);
        dt.setText(time);
        return listItemView;
    }
}
