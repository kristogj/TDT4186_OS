public class Screen {

    // Since this function is syncronized, only one Thread can be inside here at a time
    public synchronized void printOut(String firstWord, String secondWord){
        System.out.print(firstWord);
        // Wait one second
        try{
            Thread.sleep(100);
        } catch(InterruptedException e){}
        System.out.println(secondWord);
    }

}
