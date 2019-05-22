
/*
Title:              Assignment3 PA3.java
Course:             SENG2200
Author:             Juyong Kim
Student No:         c3244203
Date:               21/05/2019
Description:        
*/
public class State
{
    private double duration;
    private String status;

    State()
    {
        this.duration = 0;
        this.status = "Empty";
    }

    State(double time, String newStatus)
    {
        this.duration = time;
        this.status = newStatus;
    }

    //also needs a method of saving the times it changes
    public void setStatus(String newStatus)
    {
        this.status = newStatus;
    }

    public String getStatus()
    {
        return status;
    }

    public void incrementDuration(double time)
    {
        this.duration += time;
    }

    public double getDuration()
    {
        return this.duration;
    }
}