/**
 * This class implements a customer, which is used for holding data and update the statistics
 *
 */
public class Customer {


    private int id;

    /**
     *  Creates a new Customer.
     *  Each customer should be given a unique ID
     */
    public Customer(int id) {
        this.id = id;
    }


    /**
     * Here you should implement the functionality for ordering food as described in the assignment.
     */
    public synchronized void order(){
        // Take the order
        // A customer has to order at least 1 and maximum SushiBar.maxOrder
        int numberOfOrders = (int)(Math.random()*SushiBar.maxOrder + 1);
        int eatenOrders = (int)(Math.random()*numberOfOrders);
        int takeAwayOrders = numberOfOrders - eatenOrders;

        // Update the statistics
        SushiBar.totalOrders.add(numberOfOrders);
        SushiBar.servedOrders.add(eatenOrders);
        SushiBar.takeawayOrders.add(takeAwayOrders);

    }

    /**
     *
     * @return Should return the customerID
     */
    public int getCustomerID() {
        return this.id;
    }

    // Add more methods as you see fit

    public static void main(String[] args){
        for (int x = 0; x < 50; x++){
            System.out.println((int)(Math.random()*10));
        }
    }
}
