/*
Title:              Assignment3
Course:             SENG2200
Author:             Juyong Kim
Student No:         c3244203
Date:               21/05/2019
Description:        
*/
//libraries
//TODO delete libraries that im not using anymore
import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.Hashtable;

public class Inventory
{
    //variables
    private List<Item> finishedItems;               //storage that contains finished items

    //constructor
    Inventory(List<Stage> prodStages)
    {
        this.finishedItems = new LinkedList<>();
    }

    public void add(Item e)
    {
        this.finishedItems.add(e);
    }

    public int getTotalItems()
    {
        return finishedItems.size();
    }

    public Map<String, Double> getPathsCount()
    {
        Map<String, Double> pathsCount = new Hashtable<String, Double>();
        String path;
        Double count;
        for (Item i : this.finishedItems)
        {
            path = i.getPath();
            count = pathsCount.get(path);
            if (pathsCount.containsKey(path))
            {
                // add the path as key to dictionary
                // assign the value 1
                pathsCount.put(path, new Double(count + 1));
            }
            else
            {
                // assign an incremented value
                count = new Double(1);
                pathsCount.put(path, count);
            }
        }
        return pathsCount;
    }

}