/*
Title:              Assignment3 PA3.java
Course:             SENG2200
Author:             Juyong Kim
Student No:         c3244203
Date:               21/05/2019
Description:        
*/
public class Stage
{
    private Item processItem;
    private double mean;
    private double range;
    private Random r;

    private List<State> status;

    private String name;

    Stage()
    {
        
    }

    Stage(String newName)
    {
        this.name = newName;
    }

    //getters
    public String getName()
    {
        return name;
    }
}