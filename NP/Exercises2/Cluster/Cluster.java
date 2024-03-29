package mk.ukim.finki.NP.ZadaciZaVezbanje2.Cluster;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Cluster <T extends Point2D> {
    private List<T> points;

    public Cluster() {
        this.points = new ArrayList<>();
    }

    public void addItem(T element) {
        points.add(element);
    }

    public void near(long id, int top) {
        T target = null;
        for (T point : points) {
            if (point.getId() == id) {
                target = point;
                break;
            }
        }

        if (target != null) {
            List<DistanceElementPair> sorted = new ArrayList<>();
            for (T element : points) {
                if (!element.equals(target)) {
                    double distance = target.getDistance(element);
                    sorted.add(new DistanceElementPair(distance, element));
                }
            }

            sorted.sort(Comparator.comparingDouble(DistanceElementPair::getDistance));

            int i = 0;
            for (DistanceElementPair pair : sorted) {
                if (i >= top)
                    break;

                System.out.printf("%d. %s -> %.3f\n", (i + 1), pair.getElement(), pair.getDistance());
                i++;
            }
        }
    }
}
