/*
Title:              Assignment3
Course:             SENG2200
Author:             Juyong Kim
Student No:         c3244203
Date:               21/05/2019
Description:        Uses strings to show the current status of Stages
*/
public class State
{
    private double duration;
    private String status;

    //constructors
    State()
    {
        this.duration = 0;
        this.status = "Starve";
    }
    State(double d, String newStatus)
    {
        this.duration = d;
        this.status = newStatus;
    }

    //setters
    public void incrementDuration(double d)
    {
        this.duration += d;
    }
    public void setStatus(String newStatus)
    {
        this.status = newStatus;
    }

    //getters
    public double getDuration()
    {
        return this.duration;
    }
    public String getStatus()
    {
        return status;
    }

}