import java.util.ArrayList;

public class GraphDrawing {
    public String drawGraph(ArrayList<Double> points){
        String graph = "";
        for(int i = -1;i<=10;i++){
            if(i == -1){
                graph += "==";
            }else {
                graph += "||";
            }
            for(int x = 0;x<20;x++){
                if(i == -1){
                    if(x==0){
                        System.out.print("||===");
                    }else{
                        System.out.print("=====");
                    }
                }else if (x<points.size()&&Math.round(points.get(x)/10)==10-i){
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
