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
    //production line settings
    private double m;
    private double n;
    private int maxQ;

    //production line
    private InterStageStorage q01, q02, q03, q04, q05;
    private Stage s0, s1, s2a, s2b, s3, s4a, s4b, s5; 

    //list of components of the production line
    private ArrayList<InterStageStorage> interStageList;
    private ArrayList<Stage> stageList;

    //upper management
    //private Inventory statistics;
    private Scheduler scheduler;

    //constructor
    ProductionManager()
    {
        this.m = 0;
        this.n = 0;
        this.maxQ = 0;
    }
    ProductionManager(double newM, double newN, int newMaxQ)
    {
        this.m = newM;
        this.n = newN;
        this.maxQ = newMaxQ;

        this.stageList = new ArrayList<Stage>();
        this.interStageList = new ArrayList<InterStageStorage>();

        //this.statistics = new Inventory(this.stageList);

        // same as the number of Production stages
        this.scheduler = new Scheduler(8);
    }

    //function that does the heavy lifting and starts the produciton line
    public void begin()
    {
        this.createProductionLine();
    }

    //instantiates all the stages,states and interstagestorage
    public void createProductionLine()
    {
        //creation of interStageStorage
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

        //creation of stages
        s0 = new StartStage("S0", m, n, scheduler, q01);
        s1 = new MiddleStage("S1", m, n, scheduler, q01, q02);
        s2a = new MiddleStage("S2a", m, n, scheduler, q02, q03);
        s2b = new MiddleStage("S2b", m, n, scheduler, q02, q03);
        s3 = new MiddleStage("S3", m, n, scheduler, q03, q04);
        s4a = new MiddleStage("S4a", m, n, scheduler, q04, q05);
        s4b = new MiddleStage("S4b", m, n, scheduler, q04, q05);
        s5 = new EndStage("S5", m, n, scheduler, q05);

        //Connecting the stages
        s0.setRight(s1);

        s1.setLeft(s0);
        s1.setRight(s2a);
        s1.setRight(s2b);

        s2a.setLeft(s1);
        s2a.setRight(s3);

        s2b.setLeft(s1);
        s2b.setRight(s3);

        s3.setLeft(s2a);
        s3.setLeft(s2b);
        s3.setRight(s4a);
        s3.setRight(s4b);

        s4a.setLeft(s3);
        s4a.setRight(s5);

        s4b.setLeft(s3);
        s4b.setRight(s5);

        s5.setLeft(s4a);
        s5.setLeft(s4b);

        //add them to the list of stages
        this.stageList.add(s0);
        this.stageList.add(s1);
        this.stageList.add(s2a);
        this.stageList.add(s2b);
        this.stageList.add(s3);
        this.stageList.add(s4a);
        this.stageList.add(s4b);
        this.stageList.add(s5);
        System.out.println("Production line creation completed");
    }
}