public class Item
{
    //need to create a timeStamp
    private double entryTime;
    private double exitTime;

    Item()
    {
        this.entryTime = 0;
        this.exitTime = 0;        
    }

    public void setEntryTime(double time)
    {
        this.entryTime = time;
    }

    public void setExitTime(double time)
    {
        this.exitTime = time;
    }

}