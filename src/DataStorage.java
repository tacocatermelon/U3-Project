import java.util.ArrayList;

public class DataStorage {

    ArrayList<Double> graphPoints = new ArrayList<>();
    ArrayList<Double> points = new ArrayList<>();
    GraphTrends a = new GraphTrends();
    int maxIdx= 0;
    double c;
    double last;
    double min = 100;
    double randVal;
    boolean rand;

    public DataStorage (double start, int max, int min){
        c = start;
        graphPoints.add(start);
        points.add(start);
        a.setMaxMin(max, min);
    }

    public ArrayList<Double> getGraphPoints(){
        return graphPoints;
    }

    public double getCurrentPoint(){
        return graphPoints.getLast();
    }

    public int getMaxIdx(){
        return maxIdx;
    }

    public double getAvg(){
        double sum = 0;
        for(int i = 0;i<graphPoints.size();i++){
            sum += graphPoints.get(i);
        }
        return sum/graphPoints.size();
    }

    public double getMax(){
        return graphPoints.get(maxIdx);
    }

    public double getMin(){
        return min;
    }

    public void newPoint(){
        last = graphPoints.getLast();
        if(!rand) {
            graphPoints.add(a.updateVal(last));
            points.add(a.updateVal(last));
            if (graphPoints.size() > 20) {
                graphPoints.remove(graphPoints.getFirst());
            }
            for (int i = 0; i < graphPoints.size(); i++) {
                if (graphPoints.get(i) > graphPoints.get(maxIdx)) {
                    maxIdx = i;
                }
                if (graphPoints.get(i) < min) {
                    min = graphPoints.get(i);
                }
            }
        }else{
            graphPoints.add(randVal);
            points.add(randVal);
            if (graphPoints.size() > 20) {
                graphPoints.remove(graphPoints.getFirst());
            }
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

    public double newRandom(){
        rand = true;
        last = graphPoints.getLast();
        randVal = a.updateRandom(last);
        return randVal;
    }
}
