import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    private static int idx = 0;
    private static int runTime;
    private static int s;
    private static int mod;
    private static int cycles = 0;
    private static int check;
    private static double randomized;
    private static boolean run = true;
    private static boolean inf;
    private static boolean random;
    private static boolean condition = false;
    private static boolean greater = false;
    private static boolean avg = false;
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
        int min = scan.nextInt();
        while (min >200|| min <1){
            System.out.print("Please enter a valid percent (1-200): ");
            min = scan.nextInt();
        }
        while (min > max){
            System.out.print("Please enter a value less than the max value (1-200): ");
            min = scan.nextInt();
        }
        System.out.print("How often would you like the values to update? (in seconds): ");
        s = scan.nextInt();
        while (s<1){
            System.out.print("Please enter a value greater than 1: ");
            s = scan.nextInt();
        }
        DataStorage a = new DataStorage(start, max, min);


        System.out.println("""
                How long would you like the loop to run for?
                   1. specific amount of time
                   2. until an end condition
                   3. infinitely""");
        int tempInt = scan.nextInt();
        while (!(tempInt==1||tempInt==2||tempInt==3)) {
            System.out.print("Please enter a value from 1 to 3: ");
            tempInt = scan.nextInt();
        }
        if (tempInt == 1) {
            System.out.print("How long would you like the graph to run for? (in seconds): ");
            tempInt = scan.nextInt();
            while (tempInt<1){
                System.out.print("Please enter a value greater than 1: ");
                tempInt = scan.nextInt();
            }
            runTime = tempInt;
        }else if(tempInt == 2){
            System.out.println("""
                    What would you like to check for?
                       1. point greater than a value
                       2. point less than a value
                       3. avg value greater than a value
                       4. avg value less than a value""");
            tempInt = scan.nextInt();
            while (!(tempInt==1||tempInt==2||tempInt==3||tempInt==4)){
                System.out.print("Please enter a value from 1 to 4: ");
                tempInt = scan.nextInt();
            }
            if(tempInt==1){
                condition = true;
                greater = true;
                avg = false;
            }else if(tempInt==2){
                condition = true;
                greater = true;
                avg = false;
            }else if(tempInt==3){
                condition = true;
                greater = true;
                avg = true;
            }else{
                condition = true;
                greater = false;
                avg = true;
            }
            System.out.print("What is the value you would like to check against: ");
            check = scan.nextInt();
            while (check<1||check>100){
                System.out.print("Please enter a valid value (1-100): ");
                check = scan.nextInt();
            }
        }else{
            inf = true;
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

        List<Callable<Void>> taskList = new ArrayList<>();
        taskList.add(callable1);
        taskList.add(callable2);
        ExecutorService executor = Executors.newFixedThreadPool(2);
        try {
            executor.invokeAll(taskList);
        } catch (InterruptedException _){}

        System.out.printf("The average value is $%.2f%n",a.getAvg());
        System.out.printf("The max value is $%.2f%n",a.getMax());
        System.out.printf("The minimum value is $%.2f%n",a.getMin());
        System.out.println("There were "+cycles+" points added total.");
    }


    private static void run(DataStorage data, GraphDrawing draw) throws InterruptedException {
        while (run){
            idx++;
            if(end(data)){
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
            cycles ++;
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

    private static boolean end(DataStorage data){
        if(condition){
            if(avg){
                if(greater){
                    return data.getAvg() >= check;
                }else{
                    return data.getAvg() <= check;
                }
            }else{
                if(greater){
                    return data.getMax() >= check;
                }else{
                    return data.getMin() <= check;
                }
            }
        }else{
            if(inf){
                return false;
            }else{
                return idx >= runTime / s;
            }
        }
    }
}
