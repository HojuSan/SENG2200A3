/*
Title:              Assignment3
Course:             SENG2200
Author:             Juproduct2ong Kim
Student No:         c3244203
Date:               21/05/2019
Description:        class that contains the comparator
*/
import java.util.Comparator;

//comparator that compares production based off duration processed
public class ProductionComparator implements Comparator<Production>
{
    @Override
    public int compare(Production product1, Production product2)
    {
        if (product1.getRemainingDuration() < product2.getRemainingDuration())
            return -1;
        else if (product1.getRemainingDuration() > product2.getRemainingDuration())
            return 1;
        else
            return 0;
    }
}