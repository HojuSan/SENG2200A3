/*
Title:              Assignment3
Course:             SENG2200
Author:             Juyong Kim
Student No:         c3244203
Date:               21/05/2019
Description:        
*/
import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Map;
import java.util.Scanner;
import java.lang.Thread;

public class ProductionManager
{
    private double mean;
    private double range;
    private int maxQ;

    private ArrayList<Stage> stageList;
    private ArrayList<InterStageStorage> storageList;

    private Scheduler scheduler;

    private Inventory prodStat;

    private final double MAX_TIME = 10000000;

    private boolean IS_DEBUG = false;

    private Scanner reader;

    private InterStageStorage q01, q02, q03, q04, q05;
    private Stage s0, s1, s2a, s2b, s3, s4a, s4b, s5;

    // Constructor
    ProductionManager()
    {
        this.mean = 0;
        this.range = 0;
        this.maxQ = 0;
    }

    ProductionManager(double mean, double range, int maxQLength)
    {
        this.mean = mean;
        this.range = range;
        this.maxQ = maxQLength;
        this.stageList = new ArrayList<Stage>();
        this.storageList = new ArrayList<InterStageStorage>();

        this.prodStat = new Inventory(this.stageList);

        // Production priority queue should only have length
        // same as the number of Production stages
        // TODO: Hard coded Production queue length!
        this.scheduler = new Scheduler(8);

    }

    public void begin()
    {

        Stage stageFinished;
        // create the structure of the production line
        this.generateProductionInfastructure();

        // Main simulation loop
        while (this.scheduler.getCurrentTime() < this.MAX_TIME)
        {
            // Process Phase/Unblock Phase/Starve Phase
            this.processPhase();

            // Finish Phase
            stageFinished = this.scheduler.performNextProduction();

            // Update stage state durations
            for (Stage p : this.stageList)
            {
                if (p != stageFinished)
                    p.incStateDur(this.scheduler.getCurrentTime());
            }

            this.stampAvgItems();

        }

        this.printStatus();

        if (reader != null)
            reader.close();
    }

    private void processPhase()
    {

        for (Stage s : this.stageList)
        {
            s.processItem(this.scheduler.getCurrentTime());
        }
    }

    private void generateProductionInfastructure()
    {
        // Stes up all production stages and connections

        // ISQ's
        q01 = new InterStageStorage("Q1", this.maxQ);
        q02 = new InterStageStorage("Q2", this.maxQ);
        q03 = new InterStageStorage("Q3", this.maxQ);
        q04 = new InterStageStorage("Q4", this.maxQ);
        q05 = new InterStageStorage("Q5", this.maxQ);

        this.storageList.add(q01);
        this.storageList.add(q02);
        this.storageList.add(q03);
        this.storageList.add(q04);
        this.storageList.add(q05);

        // For now, create the structure statically
        s0 = new StartStage("S0", q01, this.mean, this.range, this.scheduler);
        s1 = new MiddleStage("S1", q01, q02, this.mean, this.range, this.scheduler);
        s2a = new MiddleStage("S2a", q02, q03, this.mean * 2, this.range * 2, this.scheduler);
        s2b = new MiddleStage("S2b", q02, q03, this.mean , this.range * 0.5, this.scheduler);
        s3 = new MiddleStage("S3", q03, q04, this.mean, this.range, this.scheduler);
        s4a = new MiddleStage("S4a", q04, q05, this.mean, this.range * 0.5, this.scheduler);
        s4b = new MiddleStage("S4b", q04, q05, this.mean * 2, this.range * 2, this.scheduler);
        s5 = new FinishStage("S5", q05, this.mean, this.range, this.prodStat, this.scheduler);

        // Set the MiddleStages' left and right Prod for unblocking/unstarving
        s0.setRightProd(s1);

        s1.setLeftProd(s0);
        s1.setRightProd(s2a);
        s1.setRightProd(s2b);

        s2a.setLeftProd(s1);
        s2a.setRightProd(s3);

        s2b.setLeftProd(s1);
        s2b.setRightProd(s3);

        s3.setLeftProd(s2a);
        s3.setLeftProd(s2b);

        s3.setRightProd(s4a);
        s3.setRightProd(s4b);

        s4a.setLeftProd(s3);
        s4a.setRightProd(s5);

        s4b.setLeftProd(s3);
        s4b.setRightProd(s5);

        s5.setLeftProd(s4a);
        s5.setLeftProd(s4b);

        // Add the ProdStages to the list
        this.stageList.add(s0);
        this.stageList.add(s1);
        this.stageList.add(s2a);
        this.stageList.add(s2b);
        this.stageList.add(s3);
        this.stageList.add(s4a);
        this.stageList.add(s4b);
        this.stageList.add(s5);

    }

    private void printStats()
    {
        StringBuilder sb1 = new StringBuilder();

        System.out.println("\n\nProduction Stations:");
        System.out.println("----------------------------------------------------------");
        String mainFormat1 = "%-15s%-15s%-15s%-15s";
        String prod, starve, block;

        System.out.println(String.format(mainFormat1,"Stage:","Work[%]:","Starve[t]:","Block[t]:"));

        for (Stage s : this.stageList)
        {
            prod = String.format("%.2f",s.getStageAvgProduction()) + "%";
            starve = String.format("%.2f", s.getStageTotalStarve());
            block = String.format("%.2f", s.getStageTotalBlocked());

            sb1.append(String.format(mainFormat1,s.getName(),prod,starve,block,String.format("%.2f",s.getTotalTimeOperation())));
            sb1.append("\n");
        }
        System.out.println(sb1.toString());

    }

    private void printIsqTime()
    {
        StringBuilder sb2 = new StringBuilder();

        System.out.println("Storage Queues:");
        System.out.println("---------------------------------------");
        String mainFormat2 = "%-15s%-15s%-15s";
        String itemFormat2 = "%6.3f";

        String name, aveTime, aveItems;
        String count;

        System.out.println(String.format(mainFormat2, "Store", "AvgTime[t]:", "AvgItems:"));

        for (InterStageStorage q : this.storageList)
        {
            name = q.getName();
            aveTime = String.format(itemFormat2, q.getAverageQueueTime());
            aveItems = String.format(itemFormat2, q.getAvgerageItemCount());
            sb2.append(String.format(mainFormat2, name, aveTime, aveItems));
            sb2.append("\n");
        }
        System.out.println(sb2.toString());
    }

    private void stampAvgItems()
    {
        for (InterStageStorage q : this.storageList)
        {
            q.stampCount();
        }
    }

    private void printPathsCount()
    {
        StringBuilder sb3 = new StringBuilder();
        System.out.println("Production Paths:");
        System.out.println("-----------------------------------");
        String mainFormat3 = "%-30s%-30s";

        System.out.println(String.format(mainFormat3, "Path:", "Items Produced:"));

        Map<String, Double> paths = this.prodStat.getPathsCount();
        for (Map.Entry<String, Double> entry : paths.entrySet())
        {
           sb3.append(String.format(mainFormat3, entry.getKey(), entry.getValue().intValue()));
           sb3.append("\n");
        }
        System.out.println(sb3.toString());

    }

    private void printStatus()
    {
        this.printStats();
        this.printIsqTime();
        this.printPathsCount();
    }

}