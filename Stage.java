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
    private double prevTime;

    private List<State> statusList;
    private State currentState;

    private List<Stage> leftStage;
    private List<Stage> rightStage;

    private Scheduler sch;
    //key component of time management
    //each class has its own override method
    abstract public double processItem(double currentTime);
    abstract public void finishItem(double currentTime);


    Stage(String newName, double mean, double range, Scheduler schd)
    {
        this.name = newName;
        this.m = mean;
        this.n = range;
        this.processItem = null;
        this.r = new Random();
        this.sch = schd;
        this.statusList = new LinkedList<State>();
        this.currentState = new State();
        this.leftStage = new LinkedList<Stage>();
        this.rightStage = new LinkedList<Stage>();
        this.prevTime = 0;
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

    // Increase current state's duration
    public void updateTime(double currentTime)
    {
        if(this.currentState.getStatus().equals("Starve"))
        {
            this.statusList.add(new State((currentTime - this.prevTime),"Starve"));
        }
        else if(this.currentState.getStatus().equals("Block"))
        {
            this.statusList.add(new State((currentTime - this.prevTime),"Block"));
        }
        else
        {
            this.statusList.add(new State((currentTime - this.prevTime),"Busy"));
        }

        this.prevTime = currentTime;
    }

    //getters
    public String getName()
    {
        return name;
    }
    public String getCurrentState()
    {
        return currentState.getStatus();
    }
    public double getTotalTime()
    {
        double total = 0;

        for (State sta : this.statusList)
        {
            total += sta.getDuration();
        }
        return total;
    }
    //returns a percentage
    public double getStageAvgerageProduction()
    {
        double totalDuration = 0;
        double busyDuration = 0;

        for (State sta : this.statusList)
        {
            totalDuration += sta.getDuration();

            if (sta.getStatus().equals("Busy"))
            {
                busyDuration += sta.getDuration();
            }
        }

        return (busyDuration / totalDuration) * 100;
        // return busyDuration;
    }

    //returns the total time
    public double getStageTotalBlocked()
    {
        double blockedDuration = 0;

        for (State sta : this.statusList)
        {
            if (sta.getStatus().equals("Block"))
            {
                blockedDuration += sta.getDuration();
            }
        }

        return blockedDuration;
    }


    //returns the total time 
    public double getStageTotalStarve()
    {
        double starveDuration = 0;

        for (State sta : this.statusList)
        {
            if (sta.getStatus().equals("Starve"))
            {
                starveDuration += sta.getDuration();
            }
        }

        return starveDuration;
    }

    //notifying? checking? updating next stage?
    //notifying? checking? updating next stage?    


    //setters
    public void setLeft(Stage left)
    {
        this.leftStage.add(left);
    }
    public void setRight(Stage right)
    {
        this.rightStage.add(right);
    }
}