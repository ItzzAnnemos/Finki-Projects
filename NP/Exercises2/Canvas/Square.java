package mk.ukim.finki.NP.ZadaciZaVezbanje2.Canvas;

public class Square extends Shape {
    private double side;

    public Square(String id, double side) throws InvalidIDException, InvalidDimensionException {
        super(id);
        if (side == 0.0) {
            throw new InvalidDimensionException();
        }
        this.side = side;
    }

    @Override
    public void changeDimensions(double coef) {
        this.side *= coef;
    }

    @Override
    public double getArea() {
        return side * side;
    }

    public double getPerimeter() {
        return 4 * side;
    }

    @Override
    public String toString() {
        return String.format("Square: -> Side: %.2f Area: %.2f Perimeter: %.2f",
                side, getArea(), getPerimeter());
    }
}
