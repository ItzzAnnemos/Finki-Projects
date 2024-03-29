package mk.ukim.finki.NP.lab5.ComplexNumber;

public class ComplexNumber<T extends Number, U extends Number> implements Comparable<ComplexNumber<T, U>> {
    private T real;
    private U imaginary;

    public ComplexNumber(T real, U imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public T getReal() {
        return real;
    }

    public U getImaginary() {
        return imaginary;
    }

    public double modul() {
        double a = real.doubleValue();
        double b = imaginary.doubleValue();

        return Math.sqrt(a * a + b * b);
    }

    @Override
    public int compareTo(ComplexNumber o) {
        return Double.compare(this.modul(), o.modul());
    }

    @Override
    public String toString() {
        String ret = "";
        if (imaginary.doubleValue() >= 0)
            ret = String.format("%.2f+%.2fi", real.doubleValue(), imaginary.doubleValue());
        else {
            ret = String.format("%.2f%.2fi", real.doubleValue(), imaginary.doubleValue());
        }

        return ret;
    }
}
