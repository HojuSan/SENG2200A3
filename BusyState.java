/*
Title:              Assignment3 PA3.java
Course:             SENG2200
Author:             Juyong Kim
Student No:         c3244203
Date:               21/05/2019
Description:        
*/
public class BusyState extends State
{
    BusyState()
    {
        super("Busy State");
    }

    BusyState(double dur)
    {
        super("Busy State", dur);
    }
}