package mk.ukim.finki.NP.ZadaciZaVezbanje2.PhoneBook;

public class DuplicateNumberException extends Exception {
    public DuplicateNumberException(String number) {
        super("Duplicate number: " + number);
    }
}
