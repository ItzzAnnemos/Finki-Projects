package mk.ukim.finki.NP.ZadaciZaVezbanje2.Canvas;

public class Circle extends Shape {
    private double radius;

    public Circle(String id, double radius) throws InvalidIDException, InvalidDimensionException {
        super(id);
        if (radius == 0.0) {
            throw new InvalidDimensionException();
        }
        this.radius = radius;
    }

    @Override
    public void changeDimensions(double coef) {
        this.radius *= coef;
    }

    @Override
    public double getArea() {
        return Math.pow (radius , 2) * Math.PI ;
    }

    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public String toString() {
        return String.format("Circle -> Radius: %.2f Area: %.2f Perimeter: %.2f",
                radius, getArea(), getPerimeter());
    }
}
