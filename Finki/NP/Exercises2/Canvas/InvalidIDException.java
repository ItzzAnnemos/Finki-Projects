package mk.ukim.finki.NP.ZadaciZaVezbanje2.Canvas;

public class InvalidIDException extends Exception {
    public InvalidIDException(String id) {
        super("ID " + id + " is not valid");
    }
}
