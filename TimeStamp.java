public class TimeStamp
{
    private double entryTime;
    private double exitTime;
    private String currentStage;

    TimeStamp()
    {
        this.currentStage = "nothing";
        this.entryTime = 0;
        this.exitTime = 0;
    }

    TimeStamp(String stageName, double entTime, double exTime)
    {
        this.currentStage = stageName;
        this.entryTime = entTime;
        this.exitTime = exTime;
    }

    public void setStageName(String name)
    {
        this.currentStage = name;
    }

    public String getStageName()
    {
        return this.currentStage;
    }

    public void setEntryTime(double time)
    {
        this.entryTime = time;
    }

    public void setExitTime(double time)
    {
        this.exitTime = time;
    }

    public double getDuration()
    {
        return this.exitTime - this.entryTime;
    }
}