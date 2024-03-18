package mk.ukim.finki.NP.ZadaciZaVezbanje2.EventCalendar;

public class WrongDateException extends Exception {
    public WrongDateException(String date) {
        super("Wrong date: " + date);
    }
}
