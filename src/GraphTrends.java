public class GraphTrends {

    static double max, min;

    public GraphTrends(){}

    public void setMaxMin(int max, int min){
        GraphTrends.max = max/100.0;
        GraphTrends.min = min/100.0;
    }

    public double updateVal(double old){
        if(Math.random()>=0.5){
            old *=(1+(Math.random() * (max - min) + min));
        }else{
            old *=(1-(Math.random() * (max - min) + min));
        }
        if(old>100){
            old *=(1-(Math.random() * (max/2 - min/2) + min/2));
        }
        if(old<=1){
            old *=(1+(Math.random() * (max*2 - min*2) + min*2));
        }
        return old;
    }
}
