import java.util.ArrayList;

public class DataStorage {
    double c, last;
    ArrayList<Double> graphPoints = new ArrayList<>();
    ArrayList<Double> points = new ArrayList<>();
    GraphTrends a = new GraphTrends();

    public DataStorage (double start, int max, int min){
        c = start;
        graphPoints.add(start);
        points.add(start);
        a.setMaxMin(max, min);
    }

    public void newPoint(){
        last = graphPoints.getLast();
        graphPoints.add(a.updateVal(last));
        points.add(a.updateVal(last));
        if(graphPoints.size()>20){
            graphPoints.remove(graphPoints.getFirst());
        }
    }

    public ArrayList<Double> getGraphPoints(){
        return graphPoints;
    }

}
