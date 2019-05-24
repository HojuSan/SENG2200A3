/*
Title:              Assignment3
Course:             SENG2200
Author:             Juyong Kim
Student No:         c3244203
Date:               21/05/2019
Description:        
*/
public class MiddleStage extends Stage
{
    private InterStageStorage input;
    private InterStageStorage output;

    // MiddleStage
    MiddleStage(String name, InterStageStorage input, InterStageStorage output, double mean, double range, Scheduler sched)
    {
        super(name, mean, range, sched);
        this.input = input;
        this.output = output;
    }

    // Time-bound
    @Override
    public double processItem(double currentTime)
    {
        double duration = 0;

        if (this.currentState.getStatus().equals("Starve"))
        {
            if (this.input.checkEmpty())
            {
                // Continue Starving
                duration = -1;
            }
            else
            {
                // transition to BusyState
                this.processItem = this.input.deque(currentTime);
                this.processItem.setEntryTime(currentTime);

                duration = this.calculateProdDuration();

                this.currentState.setStatus("Busy");
                
                // directly call the scheduler's addNewProduction
                this.scheduler.addNewProduction(duration, this);
            }
        }
        else if (this.currentState.getStatus().equals("Block"))
        {
            if (this.output.checkFull())
            {
                // Continue blocking
                duration = -1;
            }
            else
            {
                // Unblock
                this.processItem.setExitTime(currentTime);
                this.processItem.stampTime(this.name);
                this.output.enque(this.processItem, currentTime);

                this.processItem = null;

                if (this.input.checkEmpty())
                {
                    // transition to StarveState
                    duration = -1;
                    this.currentState.setStatus("Starve");
                }
                else
                {
                    // transition to BusyState
                    this.processItem = this.input.deque(currentTime);
                    this.processItem.setEntryTime(currentTime);

                    duration = this.calculateProdDuration();
                    this.scheduler.addNewProduction(duration, this);

                    this.currentState.setStatus("Busy");

                }

            }
        }
        else
        {
            // Continue BusyState
            duration = -1;
        }
        return duration;
    }

    // Time-bound
    @Override
    public void finishItem(double currentTime)
    {
        // Increase currentState's duration
        this.incStateDur(currentTime);

        if (this.output.checkFull())
        {
            this.currentState.setStatus("Block");
        }
        else
        {
            // Finish item
            this.processItem.setExitTime(currentTime);
            this.processItem.stampTime(this.name);
            this.output.enque(this.processItem, currentTime);

            // Notify after to unstarve
            this.notifyRightProd(currentTime);

            if (this.input.checkEmpty())
            {
                // transition to StarveState
                this.processItem = null;
                this.currentState.setStatus("Starve");
            }
            else
            {
                double duration = 0;

                // transition to BusyState
                this.processItem = this.input.deque(currentTime);
                this.processItem.setEntryTime(currentTime);

                duration = this.calculateProdDuration();

                this.scheduler.addNewProduction(duration, this);

                this.currentState.setStatus("Busy");

                // Notify before to unblock
                this.notifyLeftProd(currentTime);
            }
        }

    }

}