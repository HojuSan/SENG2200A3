/*
Title:              Assignment3 PA3.java
Course:             SENG2200
Author:             Juyong Kim
Student No:         c3244203
Date:               21/05/2019
Description:        
*/
import java.util.Random;
import java.util.List;
import java.util.LinkedList;

public class Stage
{
    private Item processItem;
    private double m;
    private double n;
    private Random r;
    private String name;

    private List<State> status;
    private State currentStatus;

    private List<Stage> leftStage;
    private List<Stage> rightStage;

    Stage()
    {
        this.name = "";
    }

    Stage(String newName, double mean, double range)
    {
        this.name = newName;
        this.m = mean;
        this.n = range;
        this.leftStage = new LinkedList<Stage>();
        this.rightStage = new LinkedList<Stage>();
    }

    //randomized production time for items
    private double generateProductionDuration()
    {
        // P = M + (N * (d - 0.5))
        // M - mean
        // N - range
        // d - random
        return m + (n * (r.nextDouble() - 0.5));
    }

    //setters
    public void setLeft(Stage left)
    {
        this.leftStage.add(left);
    }
    public void setRight(Stage right)
    {
        this.rightStage.add(right);
    }

    //getters
    public String getName()
    {
        return name;
    }
}