import java.util.ArrayList;

public class GraphDrawing {

    public String drawGraph(ArrayList<Double> points, int max){

        String graph = "||====================================================================================================||\n";

        for(int i = 0;i<10;i++){
            graph += "||";
            for(int x = 0;x<20;x++){
                if (x<points.size()&&Math.round(points.get(x)/10)==10-i){
                    if(x == max){
                        graph += "  x  ";
                    }else {
                        graph += "  o  ";
                    }
                }else if(x<points.size()&&points.get(x)/10>10-i){
                    graph += "  |  ";
                }else{
                    graph+= "     ";
                }
            }
            graph+= "||\n";
        }

        graph+="||====================================================================================================||\n  ";

        for(int y = 0;y<20;y++){
            if(y<points.size()) {
                graph += String.format("%.1f", points.get(y));
                for (int z = 0; z < 5 - ((String.format("%.1f", points.get(y)).length())); z++) {
                        graph += " ";
                }
                if(y + 1 < points.size() && (String.format("%.1f", points.get(y+1)).length()>(String.format("%.1f", points.get(y)).length()))) {
                    graph = graph.substring(0,graph.length()-1);
                }
                if (y + 1 < points.size() && (String.format("%.1f", points.get(y + 1)).length()) < (String.format("%.1f", points.get(y)).length())) {
                    graph += " ";
                }
            }
        }

        graph+="\n";
        return graph;
    }
}
