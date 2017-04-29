package com.sid.ithakademo.pojo.lists;

/**
 * Created by sid on 29/4/17.
 */

public class Trips {

    private String totalCost;

    private String totalDuration;

    private Routes[] routes;

    private String type;

    public String getTotalCost ()
    {
        return totalCost;
    }

    public void setTotalCost (String totalCost)
    {
        this.totalCost = totalCost;
    }

    public String getTotalDuration ()
    {
        return totalDuration;
    }

    public void setTotalDuration (String totalDuration)
    {
        this.totalDuration = totalDuration;
    }

    public Routes[] getRoutes ()
    {
        return routes;
    }

    public void setRoutes (Routes[] routes)
    {
        this.routes = routes;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [totalCost = "+totalCost+", totalDuration = "+totalDuration+", routes = "+routes+", type = "+type+"]";
    }

}
