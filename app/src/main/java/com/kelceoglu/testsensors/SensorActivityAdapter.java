package com.kelceoglu.testsensors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by caveman on 9/28/17.
 */

public class SensorActivityAdapter extends ArrayAdapter<SensorValuesPojo> {
    private final Context context;
    private final List<SensorValuesPojo> sensorList;
    private SensorValuesPojo sensor;

    public SensorActivityAdapter(Context context, int resource, List<SensorValuesPojo> objects) {
        super(context, resource, objects);
        this.context = context;
        this.sensorList = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        sensor = sensorList.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View inflate = inflater.inflate(R.layout.sensordisplay, null);

        TextView sensorName = (TextView) inflate.findViewById(R.id.sensorNameText);
        sensorName.setText(sensor.getName());
        TextView currVal = (TextView) inflate.findViewById(R.id.currentValues);
        currVal.setText(sensor.getValue());
        return inflate;
    }
}
