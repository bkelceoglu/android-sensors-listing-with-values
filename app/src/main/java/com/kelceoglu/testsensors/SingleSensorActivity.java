package com.kelceoglu.testsensors;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class SingleSensorActivity extends AppCompatActivity implements SensorEventListener {

    private String sensor_id;
    SensorManager sensorManager;
    Long lastUpdate = 0L;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        sensor_id = intent.getExtras().getString("SENSOR_ID");
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> sensors = (sensorManager.getSensorList(Sensor.TYPE_ALL));
        Sensor localSensor = sensors.get(Integer.valueOf(sensor_id));
        this.registerSensor(localSensor);
        setContentView(R.layout.activity_single_sensor);
        TextView t = (TextView) findViewById(R.id.singleSensorName);
        t.setText(localSensor.getName());
    }

    private void registerSensor (Sensor s) {
        sensorManager.registerListener(this, s, sensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        TextView t = (TextView) findViewById(R.id.singleSensorValues);
        Long readTime = event.timestamp;
        if (readTime - lastUpdate > 500000000*2) {
            t.setText(Arrays.toString(event.values));
            lastUpdate = readTime;
        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
