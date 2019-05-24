/*
Title:              Assignment3
Course:             SENG2200
Author:             Juyong Kim
Student No:         c3244203
Date:               21/05/2019
Description:        
*/
//libraries
import java.util.Random;
import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Queue;

public abstract class Stage
{
    // Add flags for setting the state of the production stage
    // implement prod stage as state machine pattern

    //private variables are 
    protected Item processItem;
    protected double mean;
    protected double range;
    protected Random r;

    protected List<State> events;
    protected State currentState;
    protected double prevTime;

    protected String name;

    // For StartStage use only
    protected int itemCount;

    protected List<Stage> leftProd;
    protected List<Stage> rightProd;

    protected Scheduler scheduler;

    abstract public double processItem(double currentTime);
    abstract public void finishItem(double currentTime);

    //constructor
    Stage()
    {
        this.mean = 0;
        this.range = 0;
        this.processItem = null;
        this.r = new Random();
        this.events = new LinkedList<State>();
        this.currentState = new State();
        this.prevTime = 0;
        this.name = "";
        this.itemCount = 0;
        this.scheduler = null;
        this.leftProd = new LinkedList<Stage>();
        this.rightProd = new LinkedList<Stage>();
    }
    Stage(String newName, double mean, double range, Scheduler sched)
    {
        this.mean = mean;
        this.range = range;
        this.processItem = null;
        this.r = new Random();
        this.events = new LinkedList<State>();
        this.currentState = new State();
        this.prevTime = 0;
        this.name = newName;
        this.itemCount = 0;
        this.scheduler = sched;
        this.leftProd = new LinkedList<Stage>();
        this.rightProd = new LinkedList<Stage>();
    }

    protected double calculateProdDuration()
    {
        // P = M + (N * (d - 0.5))
        return mean + (range * (r.nextDouble() - 0.5));
    }

    public void incStateDur(double currentTime)
    {
        if (this.currentState.getStatus().equals("Starve"))
        {
            this.events.add(new State((currentTime - this.prevTime),"Starve"));
        }
        else if (this.currentState.getStatus().equals("Block"))
        {
            this.events.add(new State((currentTime - this.prevTime),"Block"));
        }
        else
        {
            this.events.add(new State((currentTime - this.prevTime),"Busy"));
        }
        this.prevTime = currentTime;
    }

    public void setLeftProd(Stage left)
    {
        this.leftProd.add(left);
    }

    public void setRightProd(Stage right)
    {
        this.rightProd.add(right);
    }

    protected void notifyLeftProd(double currentTime)
    {
        for (Stage s : this.leftProd)
        {
            s.processItem(currentTime);
        }
    }

    protected void notifyRightProd(double currentTime)
    {
        for (Stage s : this.rightProd)
        {
            s.processItem(currentTime);
        }
    }

    //getters
    public int getTotalItemsCreated()
    {
        return this.itemCount;
    }
    public String getName()
    {
        return this.name;
    }
    public String getState()
    {
        return this.currentState.getStatus();
    }
    public double getTotalTimeOperation()
    {
        double total = 0;
        for (State s : this.events)
        {
            total += s.getDuration();
        }
        return total;
    }
    //uses forloops to add up the total time and returns it as a double
    public double getStageTotalBlocked()
    {
        double totalDuration = 0;
        double blockedDuration = 0;

        for (State s : this.events)
        {
            totalDuration += s.getDuration();
            if (s.getStatus().equals("Block"))
            {
                blockedDuration += s.getDuration();
            }
        }
        return blockedDuration;
    }
    //uses forloops to add up the total time and returns it as a percentage
    public double getStageAvgProduction()
    {
        double totalDuration = 0;
        double busyDuration = 0;

        for (State s : this.events)
        {
            totalDuration += s.getDuration();
            if (s.getStatus().equals("Busy"))
            {
                busyDuration += s.getDuration();
            }
        }
        return (totalDuration == 0)?0:(busyDuration / totalDuration) * 100;
        // return busyDuration;
    }
    //uses forloops to add up the total time and returns it as a double
    public double getStageTotalStarve()
    {
        double totalDuration = 0;
        double starveDuration = 0;

        for (State s : this.events)
        {
            totalDuration += s.getDuration();
            if (s.getStatus().equals("Starve"))
            {
                starveDuration += s.getDuration();
            }
        }
        return starveDuration;
    }
}