import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {

    static int max, min;
    static double start;
    static boolean run = true;
    static TimeUnit time = TimeUnit.SECONDS;

    public static void main(String[] args) throws InterruptedException {
        Scanner scan = new Scanner(System.in);
        GraphDrawing draw = new GraphDrawing();
        System.out.println("Welcome to the Potato Chip Factory");
        System.out.print("Please enter your starting value(0-100): ");
        start = scan.nextDouble();
        System.out.print("Please enter your max change percent (out of 100): ");
        max = scan.nextInt();
        System.out.print("Please enter your minimum change percent (out of 100): ");
        min = scan.nextInt();
        System.out.print("How often would you like the values to update? (in seconds): ");
        int s = scan.nextInt();
        DataStorage a = new DataStorage(start,max,min);

        while (run){
            a.newPoint();
            System.out.print(draw.drawGraph(a.getGraphPoints(),a.getMaxIdx()));
            time.sleep(s);
        }
    }
}
