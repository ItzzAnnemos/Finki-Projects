package mk.ukim.finki.NP.ZadaciZaVezbanje2.Canvas;

public class Rectangle extends Shape {
    private double length;
    private double height;

    public Rectangle(String id, double length, double height) throws InvalidIDException, InvalidDimensionException {
        super(id);
        if (length == 0.0 || height == 0.0) {
            throw new InvalidDimensionException();
        }
        this.length = length;
        this.height = height;
    }

    @Override
    public void changeDimensions(double coef) {
        this.length *= coef;
        this.height *= coef;
    }

    @Override
    public double getArea() {
        return length * height;
    }

    public double getPerimeter() {
        return 2 * (length + height);
    }

    @Override
    public String toString() {
        return String.format("Rectangle: -> Sides: %.2f, %.2f Area: %.2f Perimeter: %.2f",
                length, height, getArea(), getPerimeter());
    }
}
