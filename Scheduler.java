/*
Title:              Assignment3
Course:             SENG2200
Author:             Juyong Kim
Student No:         c3244203
Date:               21/05/2019
Description:        Core part of the code, it micro manages the production of items
                    it communicates with the stages to check on states and storage to see
                    if items should be processed, added or stored
*/
//libraries
import java.util.PriorityQueue;
import java.util.List;
import java.util.Queue;

public class Scheduler
{
    //variables
    private Queue<Production> productionQueue;
    private double curTime;

    // Constructor
    Scheduler(int maxQ)
    {
        this.productionQueue = new PriorityQueue<Production>(maxQ, new ProductionComparator());
        this.curTime = 0;
    }

    public void addNewProduction(double duration, Stage stage)
    {
        productionQueue.add(new Production(duration, stage));
    }

    public Stage performNextProduction()
    {
        Production nextProduction = this.productionQueue.poll();

        this.curTime += nextProduction.getRemainingDuration();


        for (Production p : this.productionQueue)
        {
            p.updateRemainingDuration(nextProduction.getRemainingDuration());
        }

        nextProduction.finishProduction(this.curTime);

        return nextProduction.getStage();
    }

    public double getCurrentTime()
    {
        return this.curTime;
    }

    public String getPrintFormat()
    {
        return "%-15s%-15s";
    }

    public String getContents()
    {
        StringBuilder sb = new StringBuilder();
        String name;
        double dur;

        for (Production j : this.productionQueue)
        {
            name = j.getStage().getName();
            dur = j.getRemainingDuration();
            sb.append(String.format(this.getPrintFormat(), name, dur));
            sb.append("\n");
        }
        return sb.toString();
    }

    public int getProductionCount()
    {
        return this.productionQueue.size();
    }
}