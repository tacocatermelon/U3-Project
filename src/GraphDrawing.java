import java.util.ArrayList;

/**
 * String Building Class.<p>
 * builds graph to display as a String.
 *
 * @author Oliver Taub
 **/
public class GraphDrawing {

    /** instantiates a GraphDrawing object */
    public GraphDrawing(){}

    /**
     * builds a String for the graph.<p>
     * builds row by row, top down right to left.<p>
     * highlights max value.<p>
     * displays points as rounded to the nearest 10.
     *
     * @param points ArrayList of points to graph
     * @param max Largest value on the graph
     * @return graph as a String
     */
    public String drawGraph(ArrayList<Double> points, int max){

        //top border
        StringBuilder graph = new StringBuilder("\n||====================================================================================================||\n");

        for(int i = 0;i<10;i++){
            //right border
            graph.append("||");
            for(int x = 0;x<20;x++){
                //if top row for given point changes character
                if (x<points.size()&&Math.round(points.get(x)/10)==10-i){
                    //checks if max value and adds appropriate character
                    if(x == max){
                        graph.append("  x  ");
                    }else {
                        graph.append("  o  ");
                    }
                //checks if row number * 10 is less than given point and adds a line under the point if so
                }else if(x<points.size()&&points.get(x)/10>10-i){
                    graph.append("  |  ");
                }else{
                    graph.append("     ");
                }
            }
            //left border
            graph.append("||\n");
        }

        //bottom border+spaces for start of value display
        graph.append("||====================================================================================================||\n   ");
        //if first point has 4 characters (2 digits e.g 10.0) removes 1 space from in front of it
        if(String.format("%.1f", points.getFirst()).length()==4){
            graph = new StringBuilder(graph.substring(0,graph.length()-1));
        }
        //adds value of point under graph
        for(int y = 0;y<20;y++){
            //checks if point exists, stops OutOfBounds Exception
            if(y<points.size()) {
                //adds point rounded to nearest tenth
                graph.append(String.format("%.1f", points.get(y)));
                //adds spaces after graph to have 5-length of value spaces between values
                for (int z = 0; z < 5 - ((String.format("%.1f", points.get(y)).length())); z++) {
                        graph.append(" ");
                }
                //if next point has more digits removes a space
                if(y + 1 < points.size() && (String.format("%.1f", points.get(y+1)).length()>(String.format("%.1f", points.get(y)).length()))) {
                    graph = new StringBuilder(graph.substring(0, graph.length() - 1));
                }
                //if next point has less digits add a space
                if (y + 1 < points.size() && (String.format("%.1f", points.get(y + 1)).length()) < (String.format("%.1f", points.get(y)).length())) {
                    graph.append(" ");
                }
            }
        }

        //ends graph with a new line and returns it as a String
        graph.append("\n");
        return graph.toString();
    }
}
