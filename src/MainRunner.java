import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Main Logic Class.
 *
 * @author Oliver Taub
 **/

public class MainRunner {

    /**DataStorage object referenced */
    DataStorage a;
    /**GraphDrawing object referenced */
    GraphDrawing draw;
    /** seconds to run program for */
    private static int runTime;
    /** value to check against for end conditions */
    private static int check;
    /**random event chance */
    private static int mod;
    /**number of run cycles */
    private static int idx = 0;
    /**number of seconds per cycle */
    private static int s;
    /**value from random event */
    private static double randomized;
    /**true = run program
     * false = program ended */
    private static boolean run = true;
    /** */
    private static boolean inf;
    /**true = end condition
     * false = no end condition */
    private static boolean condition = false;
    /**true = greater than check
     * false = less than check */
    private static boolean greater = false;
    /**true = compare to avg value
     * false = compare to min/max value */
    private static boolean avg = false;
    /**true = random event occurred
     * false = normal value */
    private static boolean random;
    /**time unit of seconds */
    private static final TimeUnit time = TimeUnit.SECONDS;

    /**
     * instantiates a MainRunner object.<p>
     * imports most variables from Main.java.
     *
     * @param data DataStorage object used in Main.java
     * @param drawing GraphDrawing object used in Main.java
     * @param runTime seconds to run program for
     * @param s seconds per cycle
     * @param mod random event chance
     * @param check value to check end conditions against
     * @param inf program run infinitely
     * @param condition program have an end condition
     * @param greater check for greater than check value
     * @param avg check avg instead of min/max
     */
    public MainRunner(DataStorage data, GraphDrawing drawing, int runTime, int s, int mod, int check, boolean inf, boolean condition, boolean greater, boolean avg){
        a = data;
        draw = drawing;
        MainRunner.runTime = runTime;
        MainRunner.s = s;
        MainRunner.mod = mod;
        MainRunner.inf = inf;
        MainRunner.condition = condition;
        MainRunner.greater = greater;
        MainRunner.avg = avg;
        MainRunner.check = check;
    }

    /**
     * start method, calls run and randomEvent in parallel.
     */
    public void start() {
        Callable<Void> callable1 = new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                run(a, draw);
                return null;
            }
        };
        Callable<Void> callable2 = new Callable<Void>() {
            @Override
            public Void call() throws Exception {
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
        } catch (InterruptedException _) {}
    }

    /**
     * getter method for number of cycles.
     *
     * @return number of cycles
     */
    public int getIdx(){
        return idx;
    }

    /**
     * main runner method.
     *
     * @param data DataStorage object used
     * @param draw GraphDrawing object used
     * @throws InterruptedException catches InterruptedException from time.sleep
     */
    private static void run(DataStorage data, GraphDrawing draw) throws InterruptedException {
        while (run){
            idx++;
            if(end(data)){
                run = false;
                break;
            }
            if(idx!=1) {
                time.sleep(s);
            }
            data.newPoint();
            System.out.print(draw.drawGraph(data.getGraphPoints(),data.getMaxIdx()));
            System.out.printf("Price: $%.2f%n",data.getCurrentPoint());
            System.out.printf("Avg Price: $%.2f%n",data.getAvg());
            if(random){
                System.out.printf("RANDOM EVENT! new value: $%.2f%n",randomized);
                random = false;
            }
        }
    }

    /**
     * random events method.<p>
     * randomly decides if random event occurs every cycle, creates new data point with random event rules
     *
     * @param mod random event chance modifier
     * @param data DataStorage object used
     * @throws InterruptedException catches InterruptedException from time.sleep
     */
    private static void randomEvent(int mod, DataStorage data) throws InterruptedException {
        while (run) {
            if (Math.random() * (1+(mod / 100.0)) >= 0.95) {
                random = true;
                randomized = data.newRandom();
            }
            time.sleep(s);
        }
    }

    /**
     * end condition checker.<p>
     * checks end conditions and returns true to terminate the program.
     *
     * @param data DataStorage object used
     * @return true if end condition met, false otherwise
     */
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
