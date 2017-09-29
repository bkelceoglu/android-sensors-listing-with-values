package com.kelceoglu.testsensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SensorActivity extends AppCompatActivity implements SensorEventListener{

    int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        this.createList("0.00");
        this.registerSensors();
    }

    private void registerSensors() {

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> sl = (sensorManager.getSensorList(Sensor.TYPE_ALL));
        for (Sensor s : sl) {
            Sensor sn = sensorManager.getDefaultSensor(s.getType());
            sensorManager.registerListener(this, sn, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    private void createList(String values) {
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> sl = (sensorManager.getSensorList(Sensor.TYPE_ALL));
        List<SensorValuesPojo> sensorValuesPojos = new ArrayList<>();
        for (Sensor s : sl) {
            sensorValuesPojos.add(new SensorValuesPojo(s.getName(), values));
        }
        ListView lv = (ListView) findViewById(R.id.listOfSensors);
        SensorActivityAdapter saa = new SensorActivityAdapter(this, R.layout.sensordisplay, sensorValuesPojos);
        lv.setAdapter(saa);
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        // TODO: correct this as single value
        this.createList(Arrays.toString(event.values));
        // Toast.makeText(this, "Sensor Changed"  + Arrays.toString(event.values), Toast.LENGTH_SHORT).show();
        Log.d(Arrays.toString(event.values), String.valueOf(i++));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}

