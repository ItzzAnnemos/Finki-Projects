package mk.ukim.finki.NP.lab3.PizzaOrder;

import java.util.Objects;

public class ExtraItem implements Item {
    private String type;

    public ExtraItem(String type) throws InvalidExtraTypeException {
        if (Objects.equals(type, "Coke") || Objects.equals(type, "Ketchup")) {
            this.type = type;
        } else
            throw new InvalidExtraTypeException();
    }

    @Override
    public int getPrice() {
        int price = 0;
        if (type.equals("Coke")) {
            price = 5;
        }
        if (type.equals("Ketchup")) {
            price = 3;
        }
        return price;
    }

    @Override
    public String toString() {
        return type;
    }
}
