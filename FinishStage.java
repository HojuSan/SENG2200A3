/*
Title:              Assignment3
Course:             SENG2200
Author:             Juyong Kim
Student No:         c3244203
Date:               21/05/2019
Description:        
*/
public class FinishStage extends Stage
{
    private InterStageStorage input;
    private Inventory output;

    // FinishStage
    FinishStage(String newName, InterStageStorage input, double mean, double range, Inventory prodStat, Scheduler sched)
    {
        super(newName, mean, range, sched);
        this.input = input;
        this.output = prodStat;
    }

    // Pre-req: check if stage is currently busy processing an item
    // before calling this function
    @Override
    public double processItem(double currentTime)
    {
        // refer to the final output statistics
        double duration = 0;

        if (this.currentState.getStatus().equals("Starve"))
        {
            if (this.input.checkEmpty())
            {
                // Continue StarveState
                duration = -1;
            }
            else
            {
                // BusyState
                this.processItem = this.input.deque(currentTime);
                this.processItem.setEntryTime(currentTime);

                duration = this.calculateProdDuration();

                this.currentState.setStatus("Busy");

                this.scheduler.addNewProduction(duration, this);
            }
        }
        else
        {
            // Continue BusyState
            duration = -1;
        }
        return duration;
    }

    @Override
    public void finishItem(double currentTime)
    {
        this.incStateDur(currentTime);

        // Finish
        this.processItem.setExitTime(currentTime);
        this.processItem.stampTime(this.name);

        // put the item in InventoryClass

        this.output.add(this.processItem);


        if (this.input.checkEmpty())
        {
            this.processItem = null;
            this.currentState.setStatus("Starve");
        }
        else
        {
            double duration = 0;

            // Add new Production
            // notify leftProd
            this.processItem = this.input.deque(currentTime);
            this.processItem.setEntryTime(currentTime);

            duration = this.calculateProdDuration();

            this.currentState.setStatus("Busy");

            this.scheduler.addNewProduction(duration, this);

            this.notifyLeftProd(currentTime);
        }
    }

}