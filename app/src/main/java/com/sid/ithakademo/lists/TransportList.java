package com.sid.ithakademo.lists;

/**
 * Created by sid on 29/4/17.
 */

public class TransportList {

    private String cityA;
    private String cityB;
    private String time;
    private String duration;
    private String type;
    private String cost;
    private int size;

    public TransportList(String cityA, String cityB, String time,
                         String duration, String type, String cost,int size) {
        this.cityA = cityA;
        this.cityB = cityB;
        this.time = time;
        this.duration = duration;
        this.type = type;
        this.cost = cost;
        this.size=size;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getCityA() {
        return cityA;
    }

    public void setCityA(String cityA) {
        this.cityA = cityA;
    }

    public String getCityB() {
        return cityB;
    }

    public void setCityB(String cityB) {
        this.cityB = cityB;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
}
