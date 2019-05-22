import java.util.List;
import java.util.LinkedList;

public class Inventory
{
    private List<Item> finishedItems;

    Inventory(List<Stage> stageList)
    {
        this.finishedItems = new LinkedList<>();
    }

    public void add(Item e)
    {
        this.finishedItems.add(e);
    }

    public int getTotalItems()
    {
        return finishedItems.size();
    }

    //??????????????
    public void printValues()
    {
        System.out.println("wub wub");
    }

}