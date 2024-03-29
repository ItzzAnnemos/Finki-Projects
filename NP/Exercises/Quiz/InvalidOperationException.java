package mk.ukim.finki.NP.ZadaciZaVezbanje.Quiz;

public class InvalidOperationException extends Exception {

    public InvalidOperationException(char c) {
        super(c + " is not allowed option for this question");
    }
}
