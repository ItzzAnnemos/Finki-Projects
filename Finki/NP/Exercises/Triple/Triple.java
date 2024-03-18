package mk.ukim.finki.NP.ZadaciZaVezbanje.Triple;

import java.util.ArrayList;
import java.util.Collections;

public class Triple<T extends Number> {
    private ArrayList<T> elements;

    public Triple(T first, T second, T third) {
        this.elements = new ArrayList<>();
        elements.add(first);
        elements.add(second);
        elements.add(third);
    }

    public double max() {
        return elements.stream()
                .mapToDouble(Number::doubleValue)
                .max()
                .orElse(0.00);
    }

    public double avarage() {
        return elements.stream()
                .mapToDouble(Number::doubleValue)
                .average()
                .orElse(0.00);
    }

    public void sort() {
        elements.sort(Collections.reverseOrder().reversed());
    }

    @Override
    public String toString() {
        return String.format("%.2f %.2f %.2f", elements.get(0).doubleValue(), elements.get(1).doubleValue(), elements.get(2).doubleValue());
    }
}
