package mk.ukim.finki.NP.ZadaciZaVezbanje2.Cluster;

public class Point2D {
    private long id;
    private float x;
    private float y;

    public Point2D(long id, float x, float y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public long getId() {
        return id;
    }

    public double getDistance(Point2D other) {
        return Math.sqrt(Math.pow(x - other.x, 2) + Math.pow((y - other.y), 2));
    }

    @Override
    public String toString() {
        return String.format("%d", id);
    }
}
