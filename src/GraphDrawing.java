import java.util.ArrayList;

public class GraphDrawing {
    public String drawGraph(ArrayList<Double> points){
        String graph = "";
        for(int i = 0;i<10;i++){
            graph += "||";
            for(int x = 0;x<20;x++){
                if(x<points.size()&&points.get(x)/10==10-i){
                    graph += "  o  ";
                }else if(x<points.size()&&points.get(x)/10>10-i){
                    graph += "  |  ";
                }else{
                    graph+= "     ";
                }
            }
            graph+= "||\n";
        }
        return graph;
    }
}
