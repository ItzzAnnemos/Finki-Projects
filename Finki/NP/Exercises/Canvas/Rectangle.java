package mk.ukim.finki.NP.ZadaciZaVezbanje.Canvas;

public class Rectangle extends Form implements Scalable, Stackable{
    private float height;
    private float width;
    public Rectangle(String id, Color color, float height, float width) {
        super(id, color);
        this.height = height;
        this.width = width;
    }

    @Override
    public void scale(float scaleFactor) {
        this.height = height * scaleFactor;
        this.width = width * scaleFactor;
    }

    @Override
    public float weight() {
        return height * width;
    }

    @Override
    public String toString() {
        return String.format("R: %-5s-%-10s%10.2f", getId(), getColor(), weight());
    }
}
