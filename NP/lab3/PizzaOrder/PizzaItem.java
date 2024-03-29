package mk.ukim.finki.NP.lab3.PizzaOrder;

import java.util.Objects;

public class PizzaItem implements Item{
    private String type;

    public PizzaItem(String type) throws InvalidPizzaTypeException {
        if (Objects.equals(type, "Standard") || Objects.equals(type, "Pepperoni") || Objects.equals(type, "Vegetarian")) {
            this.type = type;
        } else
            throw new InvalidPizzaTypeException();
    }

    @Override
    public int getPrice() {
        int price = 0;
        if (type.equals("Standard")) {
            price = 10;
        }
        if (type.equals("Pepperoni")) {
            price = 12;
        }
        if (type.equals("Vegetarian")) {
            price = 8;
        }
        return price;
    }

    @Override
    public String toString() {
        return type;
    }
}
