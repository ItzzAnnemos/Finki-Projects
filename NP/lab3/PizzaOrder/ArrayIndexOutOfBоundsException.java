package mk.ukim.finki.NP.lab3.PizzaOrder;

public class ArrayIndexOutOfBоundsException extends Exception {

    public ArrayIndexOutOfBоundsException(int index) {
        super(index + " is out of bounds");
    }
}
