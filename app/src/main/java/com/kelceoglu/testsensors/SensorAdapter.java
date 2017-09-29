package com.kelceoglu.testsensors;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by caveman on 9/27/17.
 */

public class SensorAdapter  extends ArrayAdapter<Sensor> {

    private Context context;
    private List<Sensor> sensorList;

    //constructor, call on creation
    public SensorAdapter(Context context, int resource, List<Sensor> objects) {
        super(context, resource, objects);

        this.context = context;
        this.sensorList = objects;
    }

    //called when rendering the list
    @RequiresApi(api = Build.VERSION_CODES.N)
    public View getView(int position, View convertView, ViewGroup parent) {

        //get the Sensor we are displaying
        Sensor sensor = sensorList.get(position);

        //get the inflater and inflate the XML layout for each item
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.testlayout, null);

        TextView t1 = (TextView) view.findViewById(R.id.sensorname);
        t1.setText(sensor.getName());
        TextView resol = (TextView) view.findViewById(R.id.resolution);
        resol.setText(String.valueOf(sensor.getResolution()));
        TextView r = (TextView) view.findViewById(R.id.range);
        r.setText(String.valueOf(sensor.getMaximumRange()));
        return view;
    }

}
