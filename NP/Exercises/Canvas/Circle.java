package mk.ukim.finki.NP.ZadaciZaVezbanje.Canvas;

public class Circle extends Form implements Scalable, Stackable {
    private float radius;
    public Circle(String id, Color color, float radius) {
        super(id, color);
        this.radius = radius;
    }

    @Override
    public void scale(float scaleFactor) {
        this.radius = radius * scaleFactor;
    }

    @Override
    public float weight() {
        return (float) Math.PI * (radius * radius);
    }

    @Override
    public String toString() {
        return String.format("C: %-5s%-10s%10.2f", getId(), getColor(), weight());
    }
}
