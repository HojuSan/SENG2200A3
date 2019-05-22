public class Production
{
    private double duration;
    private double remainingDuration;
    private Stage stage;

    Production(double duration, Stage newStage)
    {
        this.duration = duration;
        this.remainingDuration = duration;
        this.stage = newStage;
    }

    public double getduration()
    {
        return this.duration;
    }

    public double getRemainingDuration()
    {
        return this.remainingDuration;
    }

    //finish the processing the item with at the current time
    public void finishProduction(double currentTime)
    {
        this.stage.finishItem(currentTime);
    }

    public Stage getStage()
    {
        return this.stage;
    }

    public void updateRemainingDuration(double time)
    {
        this.remainingDuration -= time;
    }
}