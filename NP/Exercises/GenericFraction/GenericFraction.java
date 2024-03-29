package mk.ukim.finki.NP.ZadaciZaVezbanje.GenericFraction;

public class GenericFraction<T extends Number, U extends Number> {
    private T numerator;
    private U denominator;

    public GenericFraction(T numerator, U denominator) throws ZeroDenominatorException {
        if (denominator.doubleValue() == 0.0) {
            throw new ZeroDenominatorException();
        }
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public GenericFraction<Double, Double> add(GenericFraction<? extends Number, ? extends Number> gf) throws ZeroDenominatorException {

        return new GenericFraction<Double, Double>(numerator.doubleValue()
                * gf.denominator.doubleValue()
                + denominator.doubleValue() * gf.numerator.doubleValue(),
                denominator.doubleValue() * gf.denominator.doubleValue());
    }

    private static double gcd(double a, double b) {
        if (b == 0)
            return a;
        if (a < b)
            return gcd(a, b - a);
        else
            return gcd(b, a - b);
    }

    public double gcd() {
        return gcd(this.numerator.doubleValue(),
                this.denominator.doubleValue());
    }


    public double toDouble() {
        return numerator.doubleValue() / denominator.doubleValue();
    }

    @Override
    public String toString() {
        double gcd = gcd();
        return String.format("%.2f / %.2f", numerator.doubleValue() / gcd,
                denominator.doubleValue() / gcd);
    }
}
