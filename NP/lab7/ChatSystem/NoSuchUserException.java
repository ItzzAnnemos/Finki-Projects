package mk.ukim.finki.NP.lab7.ChatSystem;

public class NoSuchUserException extends Exception {
    public NoSuchUserException(String username) {
        super(username);
    }
}
