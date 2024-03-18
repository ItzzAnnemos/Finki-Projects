package mk.ukim.finki.NP.lab3.PizzaOrder;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Item> items;
    private boolean locked;
    int standardCount;
    int pepperoniCount;
    int vegetarianCount;
    int ketchupCount;
    int cokeCount;

    public Order() {
        items = new ArrayList<>();
        locked = false;
        this.standardCount = 0;
        this.pepperoniCount = 0;
        this.vegetarianCount = 0;
        this.ketchupCount = 0;
        this.cokeCount = 0;
    }

    public void addItem(Item item, int count) throws ItemOutOfStockException, EmptyOrder {
        if (locked) {
            throw new EmptyOrder();
        }
        if (count > 10) {
            throw new ItemOutOfStockException(item);
        }

        String itemName = item.toString();

        boolean itemExists = false;
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).toString().equals(itemName)) {
                itemExists = true;
                switch (itemName) {
                    case "Standard":
                        standardCount = count;
                        break;
                    case "Pepperoni":
                        pepperoniCount = count;
                        break;
                    case "Vegetarian":
                        vegetarianCount = count;
                        break;
                    case "Ketchup":
                        ketchupCount = count;
                        break;
                    case "Coke":
                        cokeCount = count;
                        break;
                }
                break;
            }
        }

        if (!itemExists) {
            if (item instanceof PizzaItem) {
                try {
                    items.add(new PizzaItem(itemName));
                    switch (itemName) {
                        case "Standard":
                            standardCount += count;
                            break;
                        case "Pepperoni":
                            pepperoniCount += count;
                            break;
                        case "Vegetarian":
                            vegetarianCount += count;
                            break;
                    }
                } catch (InvalidPizzaTypeException e) {
                    System.out.println(e.getMessage());
                }
            } else if (item instanceof ExtraItem) {
                try {
                    items.add(new ExtraItem(itemName));
                    switch (itemName) {
                        case "Ketchup":
                            ketchupCount += count;
                            break;
                        case "Coke":
                            cokeCount += count;
                            break;
                    }
                } catch (InvalidExtraTypeException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public int getPrice() {
        int sum = 0;
        for (int i = 0; i < items.size(); i++) {
            int count = 0;
            switch (items.get(i).toString()) {
                case "Standard":
                    count = standardCount;
                    break;
                case "Pepperoni":
                    count = pepperoniCount;
                    break;
                case "Vegetarian":
                    count = vegetarianCount;
                    break;
                case "Ketchup":
                    count = ketchupCount;
                    break;
                case "Coke":
                    count = cokeCount;
                    break;
            }
            sum += items.get(i).getPrice() * count;
        }

        return sum;
    }


    public void displayOrder() {
        StringBuilder sc = new StringBuilder();
        int num = 1;
        for (int i = 0; i < items.size(); i++) {
            int counter = 0;
            switch (items.get(i).toString()) {
                case "Standard":
                    counter = standardCount;
                    break;
                case "Pepperoni":
                    counter = pepperoniCount;
                    break;
                case "Vegetarian":
                    counter = vegetarianCount;
                    break;
                case "Ketchup":
                    counter = ketchupCount;
                    break;
                case "Coke":
                    counter = cokeCount;
                    break;
            }
            sc.append(String.format("%3d.%-15sx%2d%5d$\n", num++, items.get(i).toString(), counter, items.get(i).getPrice() * counter));
        }
        String total = "Total:";
        sc.append(String.format("%-22s%5d$", total, this.getPrice()));
        System.out.println(sc.toString());
    }


    public void removeItem(int idx) throws ArrayIndexOutOfBоundsException, OrderLockedException {
        if (locked) {
            throw new OrderLockedException();
        }
        if (idx > items.size() || idx < 0) {
            throw new ArrayIndexOutOfBоundsException(idx);
        }

        switch (items.get(idx).toString()) {
            case "Standard":
                standardCount--;
                break;
            case "Pepperoni":
                pepperoniCount--;
                break;
            case "Vegetarian":
                vegetarianCount--;
                break;
            case "Ketchup":
                ketchupCount--;
                break;
            case "Coke":
                cokeCount--;
                break;
        }
        items.remove(idx);
    }

    public void lock() throws EmptyOrder {
        if (items.isEmpty()) {
            throw new EmptyOrder();
        }
        locked = true;
    }
}