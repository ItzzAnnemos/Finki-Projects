package mk.ukim.finki.NP.lab7.ChatSystem;

public class NoSuchRoomException extends Exception {
    public NoSuchRoomException(String name) {
        super(name);
    }
}
