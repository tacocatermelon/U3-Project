import java.util.Scanner;

/**
 * Main Executor Class.
 *
 * @author Oliver Taub
 **/
public class Main {

    /** seconds to run program for */
    private static int runTime;
    /** random event chance */
    private static int mod;
    /** value to check against for end conditions */
    private static int randS;
    /**value from random event */
    private static int check;
    /**true = repeat infinitely
     * false = terminates */
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

    /**
     * main executable.
     *
     * @param args main method
     */
    public static void main(String[] args) {
        //new Scanner object
        Scanner scan = new Scanner(System.in);
        //new GraphDrawing object
        GraphDrawing draw = new GraphDrawing();
        //thank you for those
        System.out.println("I've stolen your kidneys.");
        //starting value and valid range check
        System.out.print("Please enter your starting value(1-100): ");
        double start = scan.nextDouble();
        while (start >100|| start <1){
            System.out.print("Please enter a valid value(1-100): ");
            start = scan.nextDouble();
        }
        //max and min change % and valid range check
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
        //time per cycle and valid range check
        System.out.print("How often would you like the values to update? (in seconds): ");
        int s = scan.nextInt();
        while (s <1){
            System.out.print("Please enter a value greater than 1: ");
            s = scan.nextInt();
        }
        //creates new DataStorage object with inputted values
        DataStorage a = new DataStorage(start, max, min);

        //user input for end conditions
        System.out.println("""
                How long would you like the loop to run for?
                   1. specific amount of time
                   2. until an end condition
                   3. infinitely""");
        int tempInt = scan.nextInt();
        //valid option checker
        while (!(tempInt==1||tempInt==2||tempInt==3)) {
            System.out.print("Please enter a value from 1 to 3: ");
            tempInt = scan.nextInt();
        }
        //specific amount of time end condition
        //determines number of seconds and valid range checker
        if (tempInt == 1) {
            System.out.print("How long would you like the graph to run for? (in seconds): ");
            tempInt = scan.nextInt();
            while (tempInt<1){
                System.out.print("Please enter a value greater than 1: ");
                tempInt = scan.nextInt();
            }
            runTime = tempInt;
        //user input for end condition
        }else if(tempInt == 2){
            System.out.println("""
                    What would you like to check for?
                       1. point greater than a value
                       2. point less than a value
                       3. avg value greater than a value
                       4. avg value less than a value""");
            tempInt = scan.nextInt();
            //valid option checker
            while (!(tempInt==1||tempInt==2||tempInt==3||tempInt==4)){
                System.out.print("Please enter a value from 1 to 4: ");
                tempInt = scan.nextInt();
            }
            //condition greater and avg set based on user responses
            condition = true;
            //point greater than a value: greater than but not checking avg
            if(tempInt==1){
                greater = true;
                avg = false;
            //point less than a value: not greater than or not checking avg
            }else if(tempInt==2){
                greater = true;
                avg = false;
            //avg greater than a value: greater than and checking avg
            }else if(tempInt==3){
                greater = true;
                avg = true;
            //avg less than a value: not greater than but checking avg
            }else{
                greater = false;
                avg = true;
            }
            //value to check against and valid range check
            System.out.print("What is the value you would like to check against: ");
            check = scan.nextInt();
            while (check<1||check>100){
                System.out.print("Please enter a valid value (1-100): ");
                check = scan.nextInt();
            }
        //if option 3 runs infinitely
        }else{
            inf = true;
        }

        //random events prompt
        scan.nextLine();
        System.out.print("Would you like a chance for random events? (y/n): ");
        boolean rand = false;
        String temp = scan.nextLine();
        while (!(temp.equals("y")||temp.equals("n"))){
            System.out.print("Please enter either y or n: ");
            temp = scan.nextLine();
        }
        if(temp.equals("y")){
            rand = true;
            //random value chance modifier and valid range checker
            System.out.print("What chance for a random event would you like (0-100): ");
            mod = scan.nextInt();
            while (mod<0||mod>100){
                System.out.print("Please enter a value value (0-100): ");
                mod = scan.nextInt();
            }
            System.out.print("How often would you like random events to occur? (in seconds) ");
            randS = scan.nextInt();
            while (randS<1){
                System.out.print("Please enter a value greater than 1: ");
                randS = scan.nextInt();
            }
        }

        //creates new MainRunner logic object with all user inputted info
        MainRunner runner = new MainRunner(a, draw, runTime, s, mod, check, randS, inf, condition, greater, avg, rand);
        //starts execution
        runner.start();

        //prints info about graph
        //prints avg value, min value, max value, and overall number of points made
        System.out.printf("\nThe average value is $%.2f%n",a.getAvg());
        System.out.printf("The max value is $%.2f%n",a.getMax());
        System.out.printf("The minimum value is $%.2f%n",a.getMin());
        System.out.println("There were "+runner.getIdx()+" points added total.");
        System.out.println("There were "+runner.getRandCount()+" random events.");
    }
}
