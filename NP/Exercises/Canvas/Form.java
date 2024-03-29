package mk.ukim.finki.NP.ZadaciZaVezbanje.Canvas;

public class Form implements Scalable, Stackable{
    private String id;
    private Color color;

    public Form(String id, Color color) {
        this.id = id;
        this.color = color;
    }

    public String getId() {
        return id;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public void scale(float scaleFactor) {

    }

    @Override
    public float weight() {
        return 0;
    }
}
