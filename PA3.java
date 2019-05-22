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
        int m = Double.parseDouble(args[0]);
        int n = Double.parseDouble(args[1]);
        int maxQ = Integer.parseInt(args[2]);

        //parameter checking
        if (args.length != 3)
        {
            //end program
            System.out.println("Not enough Parameters");
            return;
        }

        // initialize prodManager
        this.prodManager = new ProductionLineManager(this.m, this.n, this.maxQ);
        // run prodManager
        this.prodManager.Begin();
        
    }
}