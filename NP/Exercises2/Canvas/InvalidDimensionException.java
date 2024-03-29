package mk.ukim.finki.NP.ZadaciZaVezbanje2.Canvas;

public class InvalidDimensionException extends Exception {
    public InvalidDimensionException() {
        super("Dimension 0 is not allowed!");
    }
}
