import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {

    static int max, min, idx = 0, runTime;
    static double start;
    static boolean run = true, inf;
    static TimeUnit time = TimeUnit.SECONDS;

    public static void main(String[] args) throws InterruptedException {
        Scanner scan = new Scanner(System.in);
        GraphDrawing draw = new GraphDrawing();
        System.out.println("Welcome to the Potato Chip Factory");
        System.out.print("Please enter your starting value(1-100): ");
        start = scan.nextDouble();
        while (start>100||start<1){
            System.out.print("Please enter a valid value(1-100): ");
            start = scan.nextDouble();
        }
        System.out.print("Please enter your max change percent (1-100): ");
        max = scan.nextInt();
        while (max>100||max<1){
            System.out.print("Please enter a valid percent (1-100): ");
            max = scan.nextInt();
        }
        System.out.print("Please enter your minimum change percent (1-100): ");
        min = scan.nextInt();
        while (min>100||min<1){
            System.out.print("Please enter a valid percent (1-100): ");
            min = scan.nextInt();
        }
        System.out.print("How often would you like the values to update? (in seconds): ");
        int s = scan.nextInt();
        while (s<1){
            System.out.print("Please enter a value greater than 1: ");
            s = scan.nextInt();
        }
        DataStorage a = new DataStorage(start,max,min);

        scan.nextLine();
        System.out.print("How long would you like the graph to run for? (in seconds, \"X\" if infinite loop): ");
        String temp = scan.nextLine();
        while (!temp.equals("X")&&Integer.parseInt(temp)<1){
            System.out.print("Please enter a value greater than 1, or X to run infinitely");
            temp = scan.nextLine();
        }
        if(temp.equals("X")){
            inf = true;
        }else{
            runTime = Integer.parseInt(temp);
        }


        while (run){
            idx++;
            if(!inf&&idx>=runTime/s){
                run = false;
                break;
            }
            a.newPoint();
            System.out.print(draw.drawGraph(a.getGraphPoints(),a.getMaxIdx()));
            time.sleep(s);
        }

        System.out.printf("The average value was %.2f%n",a.getAvg());
        System.out.printf("The max value was %.2f%n",a.getMax());
        System.out.printf("The minimum value was %.2f%n",a.getMin());

    }
}
