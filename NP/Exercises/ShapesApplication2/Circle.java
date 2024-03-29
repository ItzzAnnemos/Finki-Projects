package mk.ukim.finki.NP.ZadaciZaVezbanje.ShapesApplication2;

public class Circle extends Shapes {
    private int radius;

    public Circle(int radius, String canvasID) throws IrregularCanvasException {
        this.radius = radius;
        if (this.getArea() > ShapesApplication.MAX_AREA) {
            throw new IrregularCanvasException(canvasID);
        }
    }

    public double getArea() {
        return Math.PI * (radius * radius);
    }
}
