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

        this.prodStat = new Inventory(this.stageList);

        // Job priority queue should only have length
        // same as the number of Production stages
        // TODO: Hard coded job queue length!
        this.des_pq = new Scheduler(8);

    }
}