public class GraphTrends {

    static double max, min;

    public GraphTrends(){
    }

    public void setMaxMin(int max, int min){
        GraphTrends.max = max/100.0;
        GraphTrends.min = -min/100.0;
    }

    public double updateVal(double old){
        return old *= (Math.random() * (max - min) + min);
    }
}
