/*
Title:              Assignment3
Course:             SENG2200
Author:             Juyong Kim
Student No:         c3244203
Date:               21/05/2019
Description:        main file
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
        if (args.length != 3 || m < 0 || n < 0 || maxQ < 0)
        {
            //end program
            System.out.println("Not enough Parameters or wrong parameters given");
            return;
        }

        //initialize prodManager
        prodManager = new ProductionManager(m, n, maxQ);
        //begin production
        prodManager.begin();
    }

}