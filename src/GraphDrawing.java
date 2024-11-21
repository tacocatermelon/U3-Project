import java.util.ArrayList;

public class GraphDrawing {

    public String drawGraph(ArrayList<Double> points, int max){

        StringBuilder graph = new StringBuilder("\n||====================================================================================================||\n");

        for(int i = 0;i<10;i++){
            graph.append("||");
            for(int x = 0;x<20;x++){
                if (x<points.size()&&Math.round(points.get(x)/10)==10-i){
                    if(x == max){
                        graph.append("  x  ");
                    }else {
                        graph.append("  o  ");
                    }
                }else if(x<points.size()&&points.get(x)/10>10-i){
                    graph.append("  |  ");
                }else{
                    graph.append("     ");
                }
            }
            graph.append("||\n");
        }

        graph.append("||====================================================================================================||\n   ");
        if(String.format("%.1f", points.getFirst()).length()==4){
            graph = new StringBuilder(graph.substring(0,graph.length()-1));
        }
        for(int y = 0;y<20;y++){
            if(y<points.size()) {
                graph.append(String.format("%.1f", points.get(y)));
                for (int z = 0; z < 5 - ((String.format("%.1f", points.get(y)).length())); z++) {
                        graph.append(" ");
                }
                if(y + 1 < points.size() && (String.format("%.1f", points.get(y+1)).length()>(String.format("%.1f", points.get(y)).length()))) {
                    graph = new StringBuilder(graph.substring(0, graph.length() - 1));
                }
                if (y + 1 < points.size() && (String.format("%.1f", points.get(y + 1)).length()) < (String.format("%.1f", points.get(y)).length())) {
                    graph.append(" ");
                }
            }
        }

        graph.append("\n");
        return graph.toString();
    }
}
