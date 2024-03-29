package mk.ukim.finki.NP.ZadaciZaVezbanje.Times;

public class InvalidTimeException extends Exception {

    public InvalidTimeException(String time) {
        super(time);
    }
}
