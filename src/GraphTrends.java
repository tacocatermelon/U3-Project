/**
 * This class determines new values for a graph based on the current value
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
        if(Math.random()>=0.5){
            old *=(1+(Math.random() * (max - min) + min));
        }else{
            old *=(1-(Math.random() * (max - min) + min));
        }
        if(old>100){
            old = 100*(1-(Math.random() * (max/2 - min/2) + min/2));
        }
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
        if(rand<=.1){ //0-0.1 (0.1)
            old *=(1+(Math.random() * (2*(max - min)) + min));
        }else if(rand<=.25){ //0.1-0.25 (0.15)
            old *=100*(Math.random() * (2*(max - min)) + min);
        }else if(rand<=.5){ //0.25-0.5 (0.25)
            old = 100*(Math.random()-0.5);
        }else if(rand<=.6){ //0.5-0.6 (0.1)
            old *=(1-(Math.random() * (2*(max - min)) + min));
        }else if(rand<=.75){ //0.6-0.75(0.15)
            old *=100*(Math.random() * (2*(min - max)) + min);
        }else{ //0.75-0.99 (0.25)
            old = updateVal(old);
        }
        if(old>100){
            old = 100*(1-(Math.random() * (max/2 - min/2) + min/2));
        }
        if(old<=1){
            old = 1.5*(1+(Math.random() * (max*2 - min*2) + min*2));
        }
        return old;
    }
}
