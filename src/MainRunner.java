import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MainRunner {

    DataStorage a;
    GraphDrawing draw;
    private static int mod;
    private static int idx = 0;
    private static int runTime;
    private static int check;
    private static int cycles = 0;
    private static int s;
    private static double randomized;
    private static boolean run = true;
    private static boolean inf;
    private static boolean random;
    private static boolean condition;
    private static boolean greater;
    private static boolean avg;
    private static final TimeUnit time = TimeUnit.SECONDS;

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

    public int getCycles(){
        return cycles;
    }

    private static void run(DataStorage data, GraphDrawing draw) throws InterruptedException {
        while (run){
            idx++;
            cycles ++;
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
