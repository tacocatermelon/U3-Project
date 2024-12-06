import java.util.ArrayList;

/**
 * Point Storage/Logic Class.<p>
 * stores points and creates new points.
 *
 * @author Oliver Taub
 **/
public class DataStorage {

    /**Arraylist to store up to 20 most recent points */
    ArrayList<Double> graphPoints = new ArrayList<>();
    /**GraphTrends object used */
    GraphTrends a = new GraphTrends();
    /**index of the max value */
    int maxIdx= 0;
    /**most recent value */
    double last;
    /**lowest value */
    double min = 100;
    /**value of randomized point */
    double randVal;
    /**true = random value added
     * false = no random value added */
    boolean rand;

    /**
     * instantiates a GraphDrawing object.
     *
     * @param start starting value
     * @param max max change interval
     * @param min minimum change interval
     */
    public DataStorage (double start, int max, int min){
        graphPoints.add(start);
        a.setMaxMin(max, min);
    }

    /**
     * getter method for graphPoints.
     *
     * @return ArrayList with 20 most recent points
     */
    public ArrayList<Double> getGraphPoints(){
        return graphPoints;
    }

    /**
     * getter method for most recent point.
     *
     * @return most recent point
     */
    public double getCurrentPoint(){
        return graphPoints.getLast();
    }

    /**
     * getter method for maxIdx.
     *
     * @return index of max value
     */
    public int getMaxIdx(){
        return maxIdx;
    }

    /**
     * returns the average of up to the 20 most recent points.
     *
     * @return avg value on the graph
     */
    public double getAvg(){
        //adds all points on graph and divides by number of points
        double sum = 0;
        for(int i = 0;i<graphPoints.size();i++){
            sum += graphPoints.get(i);
        }
        return sum/graphPoints.size();
    }

    /**
     * getter method for the max value on the graph.
     *
     * @return max value on the graph
     */
    public double getMax(){
        return graphPoints.get(maxIdx);
    }

    /**
     * getter method for min.
     *
     * @return min value on the graph
     */
    public double getMin(){
        return min;
    }

    /**
     * creates a new point and adds it to the graph if there is no random point added.<p>
     * adds value of the random point if there is one.
     */
    public void newPoint(){
        //gets most recent point
        last = graphPoints.getLast();
        //checks if there is already a randomized value
        if(!rand) {
            //new point made and added to the graph
            graphPoints.add(a.updateVal(last));
            //if graph has more than 20 points oldest point is removed
            if (graphPoints.size() > 20) {
                graphPoints.remove(graphPoints.getFirst());
            }
            //checks if new point is new maximum value
            for (int i = 0; i < graphPoints.size(); i++) {
                if (graphPoints.get(i) > graphPoints.get(maxIdx)) {
                    maxIdx = i;
                }
                if (graphPoints.get(i) < min) {
                    min = graphPoints.get(i);
                }
            }
        //if there is a randomized value it is added to the graph
        }else{
            graphPoints.add(randVal);
            //checks number of points in graph
            if (graphPoints.size() > 20) {
                graphPoints.remove(graphPoints.getFirst());
            }
            //checks if new max value
            for (int i = 0; i < graphPoints.size(); i++) {
                if (graphPoints.get(i) > graphPoints.get(maxIdx)) {
                    maxIdx = i;
                }
                if (graphPoints.get(i) < min) {
                    min = graphPoints.get(i);
                }
            }
        }
        rand = false;
    }

    /**
     * creates new random point.
     *
     * @return value of new point
     */
    public double newRandom(){
        //shows there is a random value and returns it
        rand = true;
        last = graphPoints.getLast();
        randVal = a.updateRandom(last);
        return randVal;
    }
}
