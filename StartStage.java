public class StartStage extends Stage
{
    private InterStageStorage output;
    private int itemCount;

    StartStage(String prodName, double mean, double range, Scheduler sch, InterStageStorage output)
    {
        super(prodName, mean, range, sch);
        this.output = output;

        this. itemCount = 0;
    }
}