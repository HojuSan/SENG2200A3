import java.util.PriorityQueue;
import java.util.Queue;

public class Scheduler
{
    private double currentTime;
    private Queue<Production> productionQueue;

    Scheduler(int num)
    {
        System.out.println("Priority queue setup inside scheduler might be effed up");
        this.productionQueue = new PriorityQueue<Production>(num);
        this.currentTime = 0;
    }

    public void addNewProduction(double duration, Stage stage)
    {
        productionQueue.add(new Production(duration, stage));
    }

    public Stage processNextStage()
    {
        Production topProduction = this.productionQueue.poll();

        // Caution: Update time first, before finishing the job
        // because finishJob relies on Scheduler's curTime!
        this.currentTime += topProduction.getRemainingDuration();

        // WARNING: Update the current jobs in Queue First
        //          Before calling finishJob()
        //          To avoid updating remaining duration of new jobs
        //          Created by finishJob, when the prodStage performs
        //          Its left and right unblock/unstarve notification
        for (Production p : this.jobQueue)
        {
            // update the remainingDuration of the remainig
            // jobs to push them up in the queue
            p.updateRemainingDuration(topProduction.getRemainingDuration());
        }

        // Finish the Job
        // WARNING: Performs left and Right notification
        //          Resulting in possibly new jobs added to queue
        topProduction.finishProduction(this.currentTime);

        return topProduction.getStage();
    }

    //getters
    public String getContents()
    {
        return "Fix reading in Scheduler";
    }
    public int getProductionCount()
    {
        return this.productionQueue.size();
    }
    public double getCurrentTime()
    {
        return currentTime;
    }
}