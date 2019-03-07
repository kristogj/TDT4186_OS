

public class Printer extends Thread{

    // Two words that I am going to write to the Screen done by this Printer
    private String firstWord, secondWord;

    // Reference to Screen
    private Screen screen;

    public Printer(Screen screen, String firstWord, String secondWord){
        super();
        this.screen = screen;
        this.firstWord = firstWord;
        this.secondWord = secondWord;
    }

    public void run(){
        for(int i = 0; i < 5; i++){
            // Print the two words
            screen.printOut(firstWord, secondWord);
            // Wait a bit
            try {
                Thread.sleep((int) (Math.random() * 700));
            }catch (InterruptedException e){
                // We come here if anyone calls interrupt() on this Thread
                // This does not happen in this application
            }
        }
    }

    // Make the method that we run when the application starts
    public static void main(String[] args){
        //Make the Screen
        Screen screen  = new Screen();
        // Start two PrinterThreads which writes the words given from the commandline
        Printer printer1, printer2;
        printer1 = new Printer(screen, args[0], args[1]);
        printer1.start();
        printer2 = new Printer(screen, args[2], args[3]);
        printer2.start();
    }



}
