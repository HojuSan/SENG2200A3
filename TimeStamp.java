/*
Title:              Assignment3
Course:             SENG2200
Author:             Juyong Kim
Student No:         c3244203
Date:               21/05/2019
Description:        alongside currentTime thats instantiated in stages with processItem
                    timestamps keep track of the time being updated as items are being processed
*/
public class TimeStamp
{
    //variables
    private String stageName;
    private double entryTime;                   //used to track the start of when an item is being processed                                                      
    private double exitTime;                    //used to track the end of when an item is being processed      

    //constructor
    TimeStamp()
    {
        this.stageName = "NoName";
        this.entryTime = 0;
        this.exitTime = 0;
    }
    TimeStamp(String name, double inputEntryTime, double inputExitTime)
    {
        this.stageName = name;
        this.entryTime = inputEntryTime;
        this.exitTime = inputExitTime;
    }

    //setters
    public void setEntryTime(double time)
    {
        this.entryTime = time;
    }
    public void setExitTime(double time)
    {
        this.exitTime = time;
    }

    //getters
    public String getStageName()
    {
        return this.stageName;
    }
    //the difference between exit and entry creates the duration
    public double getDuration()
    {
        return this.exitTime - this.entryTime;
    }
    public double getEntryTime()
    {
        return this.entryTime;
    }
    public double getExitTime()
    {
        return this.exitTime;
    }
}