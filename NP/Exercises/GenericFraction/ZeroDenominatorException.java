package mk.ukim.finki.NP.ZadaciZaVezbanje.GenericFraction;

public class ZeroDenominatorException extends Exception {

    public ZeroDenominatorException() {
        super("Denominator cannot be zero");
    }
}
