import java.util.ArrayList;

public class DataStorage {

    ArrayList<Double> graphPoints = new ArrayList<>();
    ArrayList<Double> points = new ArrayList<>();
    GraphTrends a = new GraphTrends();
    double c, last;
    int maxIdx= 0;

    public DataStorage (double start, int max, int min){
        c = start;
        graphPoints.add(start);
        points.add(start);
        a.setMaxMin(max, min);
    }

    public ArrayList<Double> getGraphPoints(){
        return graphPoints;
    }

    public int getMaxIdx(){
        return maxIdx;
    }

    public void newPoint(){
        last = graphPoints.getLast();
        graphPoints.add(a.updateVal(last));
        points.add(a.updateVal(last));
        if(graphPoints.size()>20){
            graphPoints.remove(graphPoints.getFirst());
        }
        for(int i = 0;i<graphPoints.size();i++){
            if(graphPoints.get(i)>graphPoints.get(maxIdx)){
                maxIdx = i;
            }
        }
    }
}
