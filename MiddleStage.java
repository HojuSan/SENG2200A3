public class MiddleStage extends Stage
{
    private InterStageStorage output;
    private InterStageStorage input;

    MiddleStage(String prodName, double mean, double range, Scheduler sch, InterStageStorage output, InterStageStorage input)
    {
        super(prodName, mean, range, sch);
        this.input = input;
        this.output = output;
    }
}