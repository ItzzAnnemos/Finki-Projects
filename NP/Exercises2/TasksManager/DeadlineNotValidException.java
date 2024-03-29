package mk.ukim.finki.NP.ZadaciZaVezbanje2.TasksManager;

public class DeadlineNotValidException extends Exception {
    public DeadlineNotValidException(String deadline) {
        super("The deadline " + deadline + " has already passed");
    }
}
