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

//fifo method is being used for this class
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

    //adds to the top of the queue
    public boolean enqueue(Item newItem, double currentTime)
    {
        if(que.size())
        {
            newItem.setEntryTime(currentTime);
            this.count += 1;
            return que.add(newItem);
        }
        else
        {
            return false;
        }
    }
    //
    public Item deque(double currentTime)
    {
        Item deque = que.poll();                              //poll is queue function, removes the head of the list
        deque.setExitTime(currentTime);                       //but returns the value  
        //working on this, time stamps are important
        this.timeQ.add(deque.queueStampTime(this.name));
        this.count -= 1;
        return deque;
    }
    //keeps track of the amounts for average calculation
    public void stampCount()
    {
        this.countQ.add(new Integer(this.count));
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

    //getters
    public String getName()
    {
        return this.name;
    }
    public int getCount()
    {
        return this.count;
    }

    //calculates average time an item is within the queue
    public double getIsqQueueTime()
    {
        double totalItems = this.qStamp.size();
        double totalDuration = 0;
        for (TimeStamp t : this.qStamp)
        {
            totalDuration += t.getDuration();
        }
        return (totalItems==0)?0:totalDuration/totalItems;
    }

    //calculates average number of items in the queue at anytime
    //utilizes time stamps
    public double getIsqAvgItemCount()
    {
        double totalStamps = this.countStamp.size();
        double totalCount = 0;
        for (Integer i : this.countStamp)
        {
            totalCount += i;
        }
        return (totalStamps==0)?0:(totalCount/totalStamps);
    }
}