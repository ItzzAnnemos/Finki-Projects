package mk.ukim.finki.NP.ZadaciZaVezbanje2.Discounts;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Discounts {
    private List<Store> stores;
    public Discounts() {
        this.stores = new ArrayList<>();
    }

    public int readStores(InputStream inputStream) {
        Scanner sc = new Scanner(inputStream);

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String [] parts = line.split("\\s+");
            String name = parts[0];

            List<String> prices = new ArrayList<>();
            for (int i = 1;i < parts.length;i++) {
                prices.add(parts[i]);
            }

            stores.add(new Store(name, prices));
        }

        return stores.size();
    }

    public List<Store> byAverageDiscount() {
        Comparator<Store> comparator = Comparator.comparing(Store::avgDiscount).reversed()
                .thenComparing(Store::getName);

        stores.sort(comparator);

        return stores.stream()
                .limit(3)
                .collect(Collectors.toList());
    }

    public List<Store> byTotalDiscount() {
        Comparator<Store> comparator = Comparator.comparing(Store::sumAbsoluteDiscount)
                .thenComparing(Store::getName);

        stores.sort(comparator);

        return stores.stream()
                .limit(3)
                .collect(Collectors.toList());
    }
}
