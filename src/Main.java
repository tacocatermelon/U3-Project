import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    private static int min, idx = 0, runTime, s, mod;
    private static double randomized;
    private static boolean run = true, inf, random;
    private static final TimeUnit time = TimeUnit.SECONDS;

    public static void main(String[] args) throws InterruptedException {
        Scanner scan = new Scanner(System.in);
        GraphDrawing draw = new GraphDrawing();
        System.out.println("Welcome to the Potato Chip Factory");
        System.out.print("Please enter your starting value(1-100): ");
        double start = scan.nextDouble();
        while (start >100|| start <1){
            System.out.print("Please enter a valid value(1-100): ");
            start = scan.nextDouble();
        }
        System.out.print("Please enter your max change percent (1-200): ");
        int max = scan.nextInt();
        while (max >200|| max <1){
            System.out.print("Please enter a valid percent (1-200): ");
            max = scan.nextInt();
        }
        System.out.print("Please enter your minimum change percent (1-200): ");
        min = scan.nextInt();
        while (min>200||min<1){
            System.out.print("Please enter a valid percent (1-200): ");
            min = scan.nextInt();
        }
        while (min> max){
            System.out.print("Please enter a value less than the max value (1-200): ");
            min = scan.nextInt();
        }
        System.out.print("How often would you like the values to update? (in seconds): ");
        s = scan.nextInt();
        while (s<1){
            System.out.print("Please enter a value greater than 1: ");
            s = scan.nextInt();
        }
        DataStorage a = new DataStorage(start, max,min);

        scan.nextLine();
        System.out.print("How long would you like the graph to run for? (in seconds, \"X\" if infinite loop): ");
        String temp = scan.nextLine();
        while (!temp.equals("X")&&Integer.parseInt(temp)<1){
            System.out.print("Please enter a value greater than 1, or X to run infinitely: ");
            temp = scan.nextLine();
        }
        if(temp.equals("X")){
            inf = true;
        }else{
            runTime = Integer.parseInt(temp);
        }

        System.out.print("Would you like a chance for random events? (y/n): ");
        if(scan.nextLine().equals("y")){
            System.out.print("What chance for a random event would you like (0-100): ");
            mod = scan.nextInt();
        }

        Callable<Void> callable1 = new Callable<Void>() {
            @Override
            public Void call() throws Exception
            {
                run(a, draw);
                return null;
            }
        };
        Callable<Void> callable2 = new Callable<Void>() {
            @Override
            public Void call() throws Exception
            {
                randomEvent(mod, a);
                return null;
            }
        };

        List<Callable<Void>> taskList = new ArrayList<Callable<Void>>();
        taskList.add(callable1);
        taskList.add(callable2);
        ExecutorService executor = Executors.newFixedThreadPool(2);
        try {
            executor.invokeAll(taskList);
        } catch (InterruptedException _){}

        System.out.printf("The average value was %.2f%n",a.getAvg());
        System.out.printf("The max value was %.2f%n",a.getMax());
        System.out.printf("The minimum value was %.2f%n",a.getMin());
    }


    private static void run(DataStorage data, GraphDrawing draw) throws InterruptedException {
        while (run){
            idx++;
            if(!inf&&idx>=runTime/s){
                run = false;
                break;
            }
            data.newPoint();
            System.out.print(draw.drawGraph(data.getGraphPoints(),data.getMaxIdx()));
            System.out.printf("Price: $%.2f%n",data.getCurrentPoint());
            if(random){
                System.out.printf("RANDOM EVENT! new value: $%.2f%n",randomized);
                random = false;
            }
            time.sleep(s);
        }
    }


    private static void randomEvent(int mod, DataStorage data) throws InterruptedException {
        while (run) {
            if (Math.random() * (1+(mod / 100.0)) >= 0.95) {
                random = true;
                randomized = data.newRandom();
            }
            time.sleep(s);
        }
    }
}
