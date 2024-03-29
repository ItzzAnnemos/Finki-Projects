package mk.ukim.finki.NP.ZadaciZaVezbanje2.Canvas;

public abstract class Shape {
    private String id;

    public Shape(String id) throws InvalidIDException {
        if (id.length() != 6) {
            throw new InvalidIDException(id);
        }
        if (id.contains(".") || id.contains("!") || id.contains("@") || id.contains("#") || id.contains("$") || id.contains("%")
                || id.contains("&") || id.contains("*")) {
            throw new InvalidIDException(id);
        }
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public abstract void changeDimensions(double coef);

    public abstract double getArea();

    public abstract double getPerimeter();
}
