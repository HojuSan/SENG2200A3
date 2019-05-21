public class Stage
{
    private double duration;
    private String name;

    Stage()
    {
        this.duration = 0;
        this.name = "";
    }

    Stage(String name)
    {
        this.duration = 0;
        this.name = name;
    }

    Stage(String name, double dur)
    {
        this.duration = dur;
        this.name = name;
    }

    public void incrementDuration(double dur)
    {
        this.duration += dur;
    }

    public double getDuration()
    {
        return this.duration;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    //what was the point of override again
    @Override
    public String toString()
    {
        return this.name;
    }

}