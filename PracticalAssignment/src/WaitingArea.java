import java.util.LinkedList;
import java.util.Queue;

/**
 * This class implements a waiting area used as the bounded buffer, in the producer/consumer problem.
 */
public class WaitingArea {

    private int size; // Max size of WaitingArea
    private Queue<Customer> queue; // The queue of Customers

    /**
     * Creates a new waiting area.
     *
     * @param size The maximum number of Customers that can be waiting.
     */
    public WaitingArea(int size) {
        this.size = size;
        queue = new LinkedList<Customer>();
    }

    /**
     * This method should put the customer into the waitingArea
     *
     * @param customer A customer created by Door, trying to enter the waiting area
     */
    public synchronized void enter(Customer customer) {
        while(isFull()){
            try {
                wait();
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        // Add the customer to the WaitingArea (the Queue)
        this.queue.add(customer);
        // Increment the total amount of customers
        SushiBar.customerCounter.increment();
        // Log what happened
        SushiBar.write(Thread.currentThread().getName() + "Customer " + customer.getCustomerID() + " is now waiting.");

        // TODO Now there are more than one customer in the queue --> notify a Waitress
        // Notifies the next one in the Monitor queue (either it is a Door or a Waitress)
        notify();


    }

    /**
     * @return The customer that is first in line.
     */
    public synchronized Customer next() {
        // If it is empty the waitress can just wait
        while(isEmpty()){
            try{
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        // Fetch the customer from the queue
        Customer fetchedCustomer = queue.poll();
        // Log what happened
        SushiBar.write(Thread.currentThread().getName() + "Customer " + fetchedCustomer.getCustomerID() + " is now waiting.");

        // TODO Now there are one less customer in the queue --> notify the Door
        // Notifies the next one in the Monitor queue (either it is a Door or a Waitress)
        notify();

        return fetchedCustomer;
    }


    public boolean isEmpty(){
        return queue.isEmpty();
    }

    private boolean isFull(){
        return queue.size() == size;
    }

    public int getSize(){
        return queue.size();
    }

    // Add more methods as you see fit
}
