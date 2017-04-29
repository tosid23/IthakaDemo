package com.sid.ithakademo.lists;

/**
 * Created by sid on 29/4/17.
 */

public class RoutesLists {

    private String cityA;
    private String cityB;
    private String type;
    private String time;
    private String cost;

    public RoutesLists(String cityA, String cityB, String type, String time, String cost) {
        this.cityA = cityA;
        this.cityB = cityB;
        this.type = type;
        this.time = time;
        this.cost = cost;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }


}
