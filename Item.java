import java.util.List;
import java.util.LinkedList;
public class Item
{
    //need to create a timeStamp
    private double entryTime;
    private double exitTime;
    private List<TimeStamp> timeStampList;

    Item()
    {
        this.entryTime = 0;
        this.exitTime = 0;
        this.timeStampList = new LinkedList<TimeStamp>();        
    }

    public void setEntryTime(double time)
    {
        this.entryTime = time;
    }

    public void setExitTime(double time)
    {
        this.exitTime = time;
    }

    public void addStampTime(String stageName)
    {
        this.timeStampList.add(new TimeStamp(stageName, this.entryTime, this.exitTime));
        this.entryTime = 0;
        this.exitTime = 0;
    }

    public TimeStamp queueStampTime(String qName)
    {
        TimeStamp t = new TimeStamp(qName, this.entryTime, this.exitTime);
        this.entryTime = 0;
        this.exitTime = 0;
        return t;
    }

    public String getPath()
    {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < this.timeStampList.size(); i++)
        {
            sb.append(this.timeStampList.get(i).getStageName());
            if (i < this.timeStampList.size() - 1)
            {
                sb.append("->");
            }
        }
        return sb.toString();
    }

}