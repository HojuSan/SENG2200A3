/*
Title:              Assignment3 PA3.java
Course:             SENG2200
Author:             Juyong Kim
Student No:         c3244203
Date:               21/05/2019
Description:        
*/
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.lang.Thread;

public class ProductionManager
{
    private double m;
    private double n;
    private int maxQ;

    private ArrayList<InterStageStorage> interStageList;
    private ArrayList<Stage> stageList;

    private Inventory statistics;
    private Scheduler scheduler;

    ProductionManager()
    {
        this.m = 0;
        this.n = 0;
        this.maxQ = 0;
    }
    
    ProductionLineManager(double newM, double newN, int newMaxQ)
    {
        this.m = newM;
        this.n = newN;
        this.maxQ = newMaxQ;

        this.stageList = new ArrayList<Stage>();
        this.interStageList = new ArrayList<InterStageStorage>();

        this.statistics = new Inventory(this.stageList);

        // same as the number of Production stages
        this.scheduler = new Scheduler(8);
    }

    public void begin()
    {
        this.createProductionLine();
    }

    //instantiates all the stages,states and interstagestorage
    public void createProductionLine()
    {
        //creation of interStageStorage
        InterStageStorage q01, q02, q03, q04, q05;
        q01 = new InterStageStorage("Q01", this.maxQ);
        q02 = new InterStageStorage("Q12", this.maxQ);
        q03 = new InterStageStorage("Q23", this.maxQ);
        q04 = new InterStageStorage("Q34", this.maxQ);
        q05 = new InterStageStorage("Q45", this.maxQ);

        //add them to the list of storages
        this.interStageList.add(q01);
        this.interStageList.add(q02);
        this.interStageList.add(q03);
        this.interStageList.add(q04);
        this.interStageList.add(q05);
    }
}