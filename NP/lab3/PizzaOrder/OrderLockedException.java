package mk.ukim.finki.NP.lab3.PizzaOrder;

public class OrderLockedException extends Exception {

    public OrderLockedException() {
        super("Order is locked");
    }
}
