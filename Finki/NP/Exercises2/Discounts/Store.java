package mk.ukim.finki.NP.ZadaciZaVezbanje2.Discounts;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Store {
    private String name;
    private List<Item> items;

    public Store(String name, List<String> prices) {
        this.name = name;
        this.items = new ArrayList<>();
        for (String price : prices) {
            String[] tmp = price.split(":");
            items.add(new Item(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1])));
        }
    }

    public double avgDiscount() {
        double sum = 0.0;
        for (int i = 0;i < items.size();i++) {
            sum += items.get(i).getDiscount();
        }

        return items.stream()
                .mapToDouble(Item::getDiscount)
                .average()
                .orElse(0.0);
    }

    public int sumAbsoluteDiscount() {
        int sum = 0;
        for (int i = 0;i < items.size();i++) {
            sum += items.get(i).absoluteDiscount();
        }

        return sum;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append(name);
        ret.append('\n');

        ret.append(String.format("Average discount: %.1f%%\n", avgDiscount()));
        ret.append(String.format("Total discount: %d\n", sumAbsoluteDiscount()));

        Comparator<Item> comparator = Comparator.comparing(Item::getDiscount).reversed()
                .thenComparing(Comparator.comparing(Item::absoluteDiscount).reversed());

        items.sort(comparator);

        for (int i = 0;i < items.size();i++) {
            ret.append(items.get(i));
            if (i != items.size() - 1)
                ret.append('\n');
        }

        return ret.toString();
    }
}
