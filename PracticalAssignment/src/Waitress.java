/**
 * This class implements the consumer part of the producer/consumer problem.
 * One waitress instance corresponds to one consumer.
 */
public class Waitress implements Runnable {

    private WaitingArea waitingArea;
    /**
     * Creates a new waitress. Make sure to save the parameter in the class
     *
     * @param waitingArea The waiting area for customers
     */
    Waitress(WaitingArea waitingArea) {
        this.waitingArea = waitingArea;
    }

    /**
     * This is the code that will run when a new thread is
     * created for this instance
     */
    @Override
    public void run() {
        // As long as the Sushibar is open or there are customers in the WaitingArea
        while (SushiBar.isOpen || !waitingArea.isEmpty()){

            // Fetch next customer
            Customer fetchedCustomer = waitingArea.next();

            // The time a Waitress use before it takes an order
            try{
                Thread.sleep(SushiBar.waitressWait);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

            // Take order from customer
            fetchedCustomer.order();
            SushiBar.write(Thread.currentThread().getName() + "Customer " + fetchedCustomer.getCustomerID() + " is now eating.");

            // The time a Waitress is busy waiting for the Customer to be done eating
            try{
                Thread.sleep(SushiBar.customerWait);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

            // The Waitress can now fetch a new customer
            SushiBar.write(Thread.currentThread().getName() + "Customer " + fetchedCustomer.getCustomerID() + " is now leaving.");

            // Decrement the number of customers in the WaitingArea
            SushiBar.customerCounter.decrement();

            // If the SushiBar is closed and there are no Customers in the WaitingArea
            if(!SushiBar.isOpen && SushiBar.customerCounter.get() == 0){
                SushiBar.write(Thread.currentThread().getName() + "***** NO MORE CUSTOMERS - THE SHOP IS CLOSED NOW. *****");
                SushiBar.write(Thread.currentThread().getName() + "TotalOrders:" + SushiBar.totalOrders.get());
                SushiBar.write(Thread.currentThread().getName() + "ServedOrders:" + SushiBar.servedOrders.get());
                SushiBar.write(Thread.currentThread().getName() + "TakeAwayOrders:" + SushiBar.takeawayOrders.get());
            }

        }


    }


}

