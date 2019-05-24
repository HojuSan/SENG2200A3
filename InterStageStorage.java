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
import java.util.Queue;

public class InterStageStorage
{
    private int maxQ;
    private Queue<Item> itemList;
    private int count;                      //counts the cmoun
    private String name;                    
    private List<TimeStamp> qStamp;
    private List<Integer> countStamp;

    //constructor
    InterStageStorage()
    {
        this.maxQ = 10;                      
        this.itemList = new LinkedList<Item>();
        this.count = 0;
        this.name = "";
        this.qStamp = new LinkedList<TimeStamp>();
        this.countStamp = new LinkedList<Integer>();
    }
    InterStageStorage(String name, int max)
    {
        this.maxQ = max;
        this.itemList = new LinkedList<Item>();
        this.count = 0;
        this.name = name;
        this.qStamp = new LinkedList<TimeStamp>();
        this.countStamp = new LinkedList<Integer>();
    }

    //functions
    public boolean enque(Item newItem, double currentTime)      //adds an element at the top
    {
        if (!this.checkFull())
        {
            // enque the item
            newItem.setEntryTime(currentTime);                  //sets entry time using the global currenttime
            this.count += 1;
            return itemList.add(newItem);
        }
        else
        {
            return false;
        }
    }
    public Item deque(double currentTime)                       //removes an element from the bottom
    {
        Item deque = itemList.poll();
        deque.setExitTime(currentTime);                         //sets exit time using the global currenttime
        this.qStamp.add(deque.queueStampTime(this.name));       //add the time stamp to the list
        this.count -= 1;
        return deque;
    }
    public void stampCount()
    {
        this.countStamp.add(new Integer(this.count));
    }

    //checkers
    public boolean checkFull()                                  //checks if storage full
    {
        if (this.itemList.size() < this.maxQ)
            return false;
        else
            return true;
    }
    public boolean checkEmpty()                                    //checks if storage is empty
    {
        return this.itemList.checkEmpty();
    }

    //getters
    public String getName()
    {
        return this.name;
    }
    public int getCurrentCount()
    {
        return this.count;
    }
    //both functions just loop to get all the values from the lists, then divide
    //to get the average
    public double getAverageQueueTime()                                         //returns average time an item is within the storage
    {
        double totalItems = this.qStamp.size();
        //System.out.println("total items is "+totalItems);                     //bug testing
        double totalDuration = 0;
        for (TimeStamp t : this.qStamp)
        {
            //System.out.println("entry"+t.getEntryTime());
            //System.out.println("exit"+t.getExitTime());
            //System.out.println("duration"+t.getDuration());
            totalDuration += t.getDuration();
        }
        return totalDuration/totalItems;
    }
    public double getAvgerageItemCount()                                        //returns average item counts for the storage
    {
        double totalStamps = this.countStamp.size();
        double totalCount = 0;
        for (Integer i : this.countStamp)
        {
            totalCount += i;
        }
        return totalCount/totalStamps;
    }
}