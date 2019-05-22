//only needs to consider busy and block, since infinite amount of resources
public class StartStage extends Stage
{
    private InterStageStorage output;
    private int itemCount;

    StartStage(String prodName, double mean, double range, Scheduler schd, InterStageStorage output)
    {
        super(prodName, mean, range, schd);
        this.output = output;

        this. itemCount = 0;
    }

    // Pre-req: check if stage is currently busy processing an item
    // before calling this function
    @Override
    public double processItem(double currentTime)
    {
        double duration = 0;

        //Stage becomes busy
        if (this.currentState.getStatus().equals("Starve"))
        {

            // transition to Busy
            duration = this.generateProductionDuration();

            this.processItem = new Item();
            this.processItem.setEntryTime(currentTime);

            this.currentState.setStatus("Busy");

            // directly call scheduler to add a new production 
            this.sch.addNewProduction(duration, this);

            this.itemCount += 1;
        }
        else if (this.currentState.getStatus().equals("Block"))
        {

            /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            if (this.output.checkFull())
            {
                // Continue BlockState
                duration = -1;
            }
            else
            {
                // Transition to BusyState
                this.processItem.setExitTime(currentTime);
                this.processItem.stampTime(this.prodName);

                this.output.enque(this.processItem, currentTime);

                this.processItem = new Item();
                this.processItem.setEntryTime(currentTime);

                duration = this.calculateProdDuration();
                // directly call scheduler to add a job
                this.jobSched.addNewJob(duration, this);

                this.currentState = new BusyState();
                this.itemCount += 1;
            }
        }
        else
        {
            //Continue being busy
            duration = -1;
        }
        return duration;
    }

    @Override
    public void finishItem(double currentTime)
    {
        // Increase currentState's duration
        this.incStateDur(currentTime);

        if (this.output.isFull())
        {
            // transition to BlockState
            this.currentState = new BlockState();
        }
        else
        {
            // Finish item
            this.processItem.setExitTime(currentTime);
            this.processItem.stampTime(this.prodName);
            this.output.enque(this.processItem, currentTime);

            this.processItem = null;
            this.currentState = new StarveState();

            // Notify rightProd to Unstarve
            this.notifyRightProd(currentTime);
        }
    }

    public int getTotalItemsCreated()
    {
        return this.itemCount;
    }
}