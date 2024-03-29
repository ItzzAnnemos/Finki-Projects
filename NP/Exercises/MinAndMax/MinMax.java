package mk.ukim.finki.NP.ZadaciZaVezbanje.MinAndMax;

public class MinMax <T extends Comparable<T>> {
    private T min;

    private T max;

    private int countMin;
    private int countMax;
    private int total;

    public MinMax() {
        this.countMin = 0;
        this.countMax = 0;
        this.total = 0;
    }

    public void update(T element) {
        if (total == 0) {
            max = element;
            min = element;
        }

        if (min.compareTo(element) > 0) {
            min = element;
            countMin = 1;
        } else if (min.compareTo(element) == 0) {
            countMin++;
        }

        if (max.compareTo(element) < 0) {
            max = element;
            countMax = 1;
        } else if (max.compareTo(element) == 0) {
            countMax++;
        }

        total++;
    }

    public T min() {
        return min;
    }

    public T max() {
        return max;
    }

    @Override
    public String toString() {
        return min + " " + max + " " + (total - countMax - countMin) + "\n";
    }
}