package com.kelceoglu.testsensors;


import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Sensor> sensors;
    SensorManager sensorManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.numberOfSensors();
    }


    public void gotoSensorsActivity(View v) {
        Intent intent = new Intent(this, SensorActivity.class);
        startActivity(intent);
    }

    public void numberOfSensors() {
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensors = (sensorManager.getSensorList(Sensor.TYPE_ALL));
        if (! sensors.isEmpty()) {
            TextView t = (TextView) findViewById(R.id.numberOfSensors);
            t.setText(String.valueOf(sensors.size()));
            displaySensors(sensors);
        }
    }

    public void displaySensors(List<Sensor> s) {
        SensorAdapter sensorAdapter = new SensorAdapter(this, R.layout.testlayout, s);
        ListView lv = (ListView) findViewById(R.id.sensorListView);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("CLICK", String.valueOf(id));
                Intent i = new Intent(MainActivity.this, SingleSensorActivity.class);
                i.putExtra("SENSOR_ID", String.valueOf(id));
                startActivity(i);
            }
        });
        lv.setAdapter(sensorAdapter);
    }

}
