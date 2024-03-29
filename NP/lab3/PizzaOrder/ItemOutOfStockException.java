package mk.ukim.finki.NP.lab3.PizzaOrder;

import java.util.concurrent.ExecutionException;

public class ItemOutOfStockException extends Exception {

    public ItemOutOfStockException(Item item) {
        super(item.toString() + " is out of stock");
    }
}
