package mk.ukim.finki.NP.ZadaciZaVezbanje.Component;

public class InvalidPositionException extends Exception {

    public InvalidPositionException(int position) {
        super("Invalid position " + position + ", alredy taken!");
    }
}
