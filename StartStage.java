/*
Title:              Assignment3
Course:             SENG2200
Author:             Juyong Kim
Student No:         c3244203
Date:               21/05/2019
Description:        
*/
public class StartStage extends Stage
{
    private InterStageStorage output;

    // Constructor
    StartStage(String newName, InterStageStorage output, double mean, double range, Scheduler sched)
    {
        super(newName, mean, range, sched);
        this.output = output;
    }

    // Pre-req: check if stage is currently busy processing an item
    // before calling this function
    @Override
    public double processItem(double currentTime)
    {
        double duration = 0;

        if (this.currentState.getStatus().equals("Starve"))
        {

            // transition to BusyState
            duration = this.calculateProdDuration();

            this.processItem = new Item();
            this.processItem.setEntryTime(currentTime);

            this.currentState.setStatus("Busy");

            // directly call scheduler to add a Production
            this.scheduler.addNewProduction(duration, this);

            this.itemCount += 1;
        }
        else if (this.currentState.getStatus().equals("Block"))
        {
            if (this.output.checkFull())
            {
                // Continue BlockState
                duration = -1;
            }
            else
            {
                // Transition to BusyState
                this.processItem.setExitTime(currentTime);
                this.processItem.stampTime(this.name);

                this.output.enque(this.processItem, currentTime);

                this.processItem = new Item();
                this.processItem.setEntryTime(currentTime);

                duration = this.calculateProdDuration();
                // directly call scheduler to add a Production
                this.scheduler.addNewProduction(duration, this);

                this.currentState.setStatus("Busy");
                this.itemCount += 1;
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
        // Increase currentState's duration
        this.incStateDur(currentTime);

        if (this.output.checkFull())
        {
            // transition to BlockState
            this.currentState.setStatus("Block");
        }
        else
        {
            // Finish item
            this.processItem.setExitTime(currentTime);
            this.processItem.stampTime(this.name);
            this.output.enque(this.processItem, currentTime);

            this.processItem = null;
            this.currentState.setStatus("Starve");

            // Notify rightProd to Unstarve
            this.notifyRightProd(currentTime);
        }
    }

}