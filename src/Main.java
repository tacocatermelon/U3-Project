import java.util.Scanner;

public class Main {
    static int max, min;
    static double start;
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        GraphDrawing draw = new GraphDrawing();
        System.out.println("Welcome to the Potato Chip Factory");
        System.out.print("Please enter your starting value(0-100): ");
        start = scan.nextDouble();
        System.out.print("Please enter your max change percent (out of 100): ");
        max = scan.nextInt();
        System.out.print("Please enter your minimum change percent (out of 100): ");
        min = scan.nextInt();
        DataStorage a = new DataStorage(start,max,min);
        a.newPoint();
        a.newPoint();
        a.newPoint();
        a.newPoint();
        System.out.print(draw.drawGraph(a.getGraphPoints()));
    }
}
