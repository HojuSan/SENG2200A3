/*
Title:              Assignment3
Course:             SENG2200
Author:             Juyong Kim
Student No:         c3244203
Date:               21/05/2019
Description:        a class that keeps tab on the stage and duration it needs to be processed
Note to self:       if you have time maybe join Item and production together, but then ill have issues with comparator??
                    Doubt you'll have time though
*/
public class Production
{
    //variables
    private double duration;
    private double remainingDuration;
    private Stage stage;

    // Constructor
    Production(double duration, Stage stage)
    {
        this.duration = duration;
        this.remainingDuration = duration;
        this.stage = stage;
    }

    //functions
    public void finishProduction(double currentTime)            //within Scheduler finishs production and boots it to the next
    {                                                           //stage that it needs to be processed by
        this.stage.finishItem(currentTime);
    }
    public void updateRemainingDuration(double duration)        //process the time left, simple minus
    {
        this.remainingDuration -= duration;
    }

    //getters
    public double getduration()
    {
        return this.duration;
    }
    public double getRemainingDuration()
    {
        return this.remainingDuration;
    }
    public Stage getStage()
    {
        return this.stage;
    }
}