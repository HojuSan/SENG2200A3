public class EndStage extends Stage
{
    private InterStageStorage input;

    EndStage(String prodName, double mean, double range, Scheduler sch, InterStageStorage input)
    {
        super(prodName, mean, range, schd);
        this.input = input;
    }
}