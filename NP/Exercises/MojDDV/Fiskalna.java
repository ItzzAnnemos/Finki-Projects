package mk.ukim.finki.NP.ZadaciZaVezbanje.MojDDV;

import java.util.ArrayList;

public class Fiskalna {
    private String id;
    private ArrayList<Item> items;

    public Fiskalna(String id, ArrayList<Item> fiskalni) throws AmountNotAllowedException {
        if (fiskalni.stream().mapToInt(Item::getPrice).sum() > 30000) {
            throw new AmountNotAllowedException(fiskalni.stream().mapToInt(Item::getPrice).sum());
        }
        this.id = id;
        this.items = fiskalni;
    }

    public int sum() {
        int sum = 0;
        for (Item item : items) {
            sum += item.getPrice();
        }

        return sum;
    }

    public double taxReturn() {
        double sum = 0.00;
        for (Item item : items) {
            sum += item.getTaxReturn();
        }

        return sum;
    }

    @Override
    public String toString() {
        return String.format("%s %d %.02f", this.id, sum(), taxReturn());
    }

    public String toStringR() {
        return String.format("%10s\t%10d\t%10.5f", this.id, sum(), taxReturn());
    }
}
