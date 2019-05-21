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

    State()
    {
        this.duration = 0;
        this.name = "";
    }

    State(String name)
    {
        this.duration = 0;
        this.name = name;
    }

    State(String name, double time)
    {
        this.duration = time;
        this.name = name;
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