package mk.ukim.finki.NP.ZadaciZaVezbanje2.Cluster;

public class DistanceElementPair {
    private double distance;
    private Point2D element;

    public DistanceElementPair(double distance, Point2D element) {
        this.distance = distance;
        this.element = element;
    }

    public double getDistance() {
        return distance;
    }

    public Point2D getElement() {
        return element;
    }
}
