/*
Title:              Assignment3
Course:             SENG2200
Author:             Juyong Kim
Student No:         c3244203
Date:               21/05/2019
Description:        Items are the things that are being processed in the stages, utilizes
                    time stamps to keep track of pathing and record times
*/
import java.util.List;
import java.util.LinkedList;

public class Item
{
    //variables
    //list of times of each stage it is in, and to keep track of stage path
    private List<TimeStamp> timeStampList;             
    private double entryTime;
    private double exitTime;

    //constructor
    Item()
    {
        this.entryTime = 0;
        this.exitTime = 0;
        this.timeStampList = new LinkedList<TimeStamp>();
    }

    //functions
    public void stampTime(String name)
    {
        //reminder to Daniel, DON'T SET VALUES TO ZERO AND THEN CREATE THEM,
        //rip 3 hours of my life
        this.timeStampList.add(new TimeStamp(name, this.entryTime, this.exitTime));
        this.entryTime = 0;
        this.exitTime = 0;
    }
    public TimeStamp queueStampTime(String qName)
    {
        TimeStamp t = new TimeStamp(qName, this.entryTime, this.exitTime);
        this.entryTime = 0;
        this.exitTime = 0;
        return t;
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
    public String getPath()
    {
        //string builder, cause this funciton is really useful for assignments
        StringBuilder sb = new StringBuilder();

        //for loop the list of the name of the stage on the time stamps 
        for (int i = 0; i < this.timeStampList.size(); i++)
        {
            sb.append(this.timeStampList.get(i).getStageName());

            //add the arrow for better visibility 
            if (i < this.timeStampList.size() - 1)
            {
                sb.append("->");
            }
        }
        //return the string
        return sb.toString();
    }

}