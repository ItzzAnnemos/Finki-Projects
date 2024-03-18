package mk.ukim.finki.NP.ZadaciZaVezbanje2.StopCorona;

public class UserAlreadyExistException extends Exception {
    public UserAlreadyExistException(String id) {
        super("User with id " + id + " already exists");
    }

}
