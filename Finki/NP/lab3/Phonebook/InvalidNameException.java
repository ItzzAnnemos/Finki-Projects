package mk.ukim.finki.NP.lab3.Phonebook;

public class InvalidNameException extends Exception {
    public String name;

    public InvalidNameException(String name) {
        super();
        this.name = name;
    }
}
