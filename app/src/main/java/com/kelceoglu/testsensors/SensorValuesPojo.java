package com.kelceoglu.testsensors;

/**
 * Created by caveman on 9/29/17.
 */

public class SensorValuesPojo {

    private String name;
    private String value;

    public SensorValuesPojo(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
