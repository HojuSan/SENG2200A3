
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
    private String name;
    private String status;

    State()
    {
        this.duration = 0;
        this.name = "";
        this.status = "Empty";
    }

    State(String name)
    {
        this.duration = 0;
        this.name = name;
        this.status = "Empty";
    }

    State(String name, double time)
    {
        this.duration = time;
        this.name = name;
        this.status = "Empty";
    }

    //also needs a method of saving the times it changes
    public void setStatus(int num)
    {
        if(num = 0)
        {
            this.status = "Busy";               //0 is busy
        }
        else if(num = 1)
        {
            this.status = "Block";              //1 is block
        }
        else if(num = 2)
        {
            this.status = "Starve";             //2 is starve
        }
        else
        {
            this.status = "Empty";
        }
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

    public void setName(String name)
    {
        this.name = name;
    }

    //what was the point of override again
    @Override
    public String toString()
    {
        return this.name;
    }

}