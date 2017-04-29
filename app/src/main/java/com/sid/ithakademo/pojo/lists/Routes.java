package com.sid.ithakademo.pojo.lists;

/**
 * Created by sid on 29/4/17.
 */

public class Routes {

    private String to;

    private String duration;

    private String time;

    private String _id;

    private String __v;

    private String from;

    private String cost;

    private String mode;

    public String getTo ()
    {
        return to;
    }

    public void setTo (String to)
    {
        this.to = to;
    }

    public String getDuration ()
    {
        return duration;
    }

    public void setDuration (String duration)
    {
        this.duration = duration;
    }

    public String getTime ()
    {
        return time;
    }

    public void setTime (String time)
    {
        this.time = time;
    }

    public String get_id ()
    {
        return _id;
    }

    public void set_id (String _id)
    {
        this._id = _id;
    }

    public String get__v ()
    {
        return __v;
    }

    public void set__v (String __v)
    {
        this.__v = __v;
    }

    public String getFrom ()
    {
        return from;
    }

    public void setFrom (String from)
    {
        this.from = from;
    }

    public String getCost ()
    {
        return cost;
    }

    public void setCost (String cost)
    {
        this.cost = cost;
    }

    public String getMode ()
    {
        return mode;
    }

    public void setMode (String mode)
    {
        this.mode = mode;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [to = "+to+", duration = "+duration+", time = "+time+", _id = "+_id+", __v = "+__v+", from = "+from+", cost = "+cost+", mode = "+mode+"]";
    }

}
