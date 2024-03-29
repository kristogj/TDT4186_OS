import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SushiBar {

    //SushiBar settings
    private static int waitingAreaCapacity = 5;
    private static int waitressCount = 2;
    private static int duration = 4;
    public static int maxOrder = 5;
    public static int waitressWait = 50; // Used to calculate the time the waitress spends before taking the order
    public static int customerWait = 2000; // Used to calculate the time the customer spends eating
    public static int doorWait = 100; // Used to calculate the interval at which the door tries to create a customer
    public static boolean isOpen = true;

    //Creating log file
    private static File log;
    private static String path = "./";

    //Variables related to statistics
    public static SynchronizedInteger customerCounter;
    public static SynchronizedInteger servedOrders;
    public static SynchronizedInteger takeawayOrders;
    public static SynchronizedInteger totalOrders;


    public static void main(String[] args) {
        log = new File(path + "log.txt");

        //Initializing shared variables for counting number of orders
        customerCounter = new SynchronizedInteger(0);
        totalOrders = new SynchronizedInteger(0);
        servedOrders = new SynchronizedInteger(0);
        takeawayOrders = new SynchronizedInteger(0);

        // First we need to start the Clock
        Clock clock  = new Clock(SushiBar.duration);

        // Initialize the buffer
        WaitingArea waitingArea = new WaitingArea(waitingAreaCapacity);

        // Now initialize the threads
        Door door = new Door(waitingArea);
        List<Waitress> waitresses = createWaitresses(waitingArea);

        // Start the threads
        new Thread(door).start();
        waitresses.forEach(waitress -> new Thread(waitress).start());
    }

    //Writes actions in the log file and console
    public static void write(String str) {
        try {
            FileWriter fw = new FileWriter(log.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(Clock.getTime() + ", " + str + "\n");
            bw.close();
            System.out.println(Clock.getTime() + ", " + str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Waitress> createWaitresses(WaitingArea waitingArea){
        List<Waitress> res = new ArrayList<Waitress>();
        for(int i = 0; i < waitressCount; i++){
            res.add(new Waitress(waitingArea));
        }
        return res;
    }
}
