package mk.ukim.finki.NP.ZadaciZaVezbanje.ShapesApplication2;

public class Square extends Shapes {
    private int side;

    public Square(int side, String canvasID) throws IrregularCanvasException {
        this.side = side;
        if (this.getArea() > ShapesApplication.MAX_AREA) {
            throw new IrregularCanvasException(canvasID);
        }
    }

    public double getArea() {
        return side * side;
    }
}
