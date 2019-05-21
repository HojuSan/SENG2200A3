/*
Title:              Assignment3 PA3.java
Course:             SENG2200
Author:             Juyong Kim
Student No:         c3244203
Date:               21/05/2019
Description:        
*/
import java.util.Queue;
import java.util.LinkedList;
import java.util.List;

public class InterStageStorage
{
    private int maxQ;
    private Queue<Item> que;
    private int count;
    private String name;
    private List<TimeStamp> timeQ;                  //timestamp queue
    private List<Integer> countQ;                   //count queue

    InterStageStorage()
    {
        this.maxQ = 10;                             //setting default to 10                      
        this.que = new LinkedList<Item>();
        this.count = 0;
        this.name = "";
        this.timeQ = new LinkedList<TimeStamp>();
        this.countQ = new LinkedList<Integer>();
    }

    InterStageStorage(String name, int num)
    {
        this.maxQ = num;                             //setting default to 10                      
        this.que = new LinkedList<Item>();
        this.count = 0;
        this.name = name;
        this.timeQ = new LinkedList<TimeStamp>();
        this.countQ = new LinkedList<Integer>();
    }

    public boolean checkFull()
    {
        if(this.que.size()<this.maxQ)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    //not 100% if this will work, gotta do some testing first
    public boolean checkEmpty()
    {
        if(this.que.size()>0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public boolean enqueue(Item newItem, double currentTime)
    {
        if(que.size())
        {

        }
        else
        {
            return false;
        }
    }
}