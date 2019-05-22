/*
Title:              Assignment3 PA3.java
Course:             SENG2200
Author:             Juyong Kim
Student No:         c3244203
Date:               09/05/2019
Description:        
*/
public class PA3
{
    public static void main(String[] args) 
    {
        //no negative parameters, all ints
        double m = Double.parseDouble(args[0]);
        double n = Double.parseDouble(args[1]);
        int maxQ = Integer.parseInt(args[2]);
        ProductionManager prodManager;

        //parameter checking
        if (args.length != 3)
        {
            //end program
            System.out.println("Not enough Parameters");
            return;
        }

        // initialize prodManager
        prodManager = new ProductionManager(m, n, maxQ);
        // run prodManager
        prodManager.begin();
        
    }
}