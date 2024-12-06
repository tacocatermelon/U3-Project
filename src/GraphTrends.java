/**
 * Value Determination Class.<p>
 * Determines new values for a graph based on the current value.
 *
 * @author Oliver Taub
 **/

public class GraphTrends {
    /** maximum change % */
    static double max;

    /** minimum change % */
    static double min;

    /** instantiates a GraphTrends object */
    public GraphTrends(){}

    /**
     * setter method that sets maximum and minimum change intervals.
     * <p>
     * PRECONDITION: max and min are both integers from 1-200.
     *
     * @param max maximum change %
     * @param min minimum change %
     */
    public void setMaxMin(int max, int min){
        GraphTrends.max = max/100.0;
        GraphTrends.min = min/100.0;
    }

    /**
     * creates a new value from the inputted value and returns it as a double.<p>
     * uses min and max values to determine change interval.
     *
     * @param old previous value
     * @return new value
     */
    public double updateVal(double old){
        //50% chance for increase 50% chance for decrease
        //increases/decreases by a random % between the max and min values
        if(Math.random()>=0.5){
            old *=(1+(Math.random() * (max - min) + min));
        }else{
            old *=(1-(Math.random() * (max - min) + min));
        }
        //if value exceeds 100 decreases it by a smaller random amount
        if(old>100){
            old = 100*(1-(Math.random() * (max/2 - min/2) + min/2));
        }
        //if value is 1 or less increases it by a larger random amount
        if(old<=1){
            old = 1.5*(1+(Math.random() * (max*2 - min*2) + min*2));
        }
        return old;
    }

    /**
     * creates a new value with more extreme price shifts for when a random event is triggered.<p>
     * uses min and max values and returns a double.
     *
     * @param old previous value
     * @return new value
     */
    public double updateRandom(double old){
        double rand = Math.random();
        //10% chance for value to be increased by a larger amount
        if(rand<=.1){ //0-0.1 (0.1) - 10%
            old *=(1+(Math.random() * (2*(max - min)) + min));
        //15% chance for value to be increased by a larger amount
        }else if(rand<=.25){ //0.1-0.25 (0.15) - 15%
            old *=100*(Math.random() * (2*(max - min)) + min);
        //25% chance for value to be 60 increased or decreased by up to 50%
        }else if(rand<=.5){ //0.25-0.5 (0.25) - 25%
            old = 60*(1+(Math.random()-0.5));
        //10% chance for value to be decreased by a larger amount
        }else if(rand<=.6){ //0.5-0.6 (0.1) - 10%
            old *=(1-(Math.random() * (2*(max - min)) + min));
        //15% chance for value to be set to a negative
        }else if(rand<=.75){ //0.6-0.75(0.15) - 15%
            old *=100*(Math.random() * (2*(min - max)) + min);
        //25% chance to use normal rules
        }else{ //0.75-0.99 (0.25) - 25%
            old = updateVal(old);
        }
        //limit checks
        if(old>100){
            old = 100*(1-(Math.random() * (max/2 - min/2) + min/2));
        }
        if(old<=1){
            old = 1.5*(1+(Math.random() * (max*2 - min*2) + min*2));
        }

        return old;
    }
}
