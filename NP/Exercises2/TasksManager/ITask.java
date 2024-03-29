package mk.ukim.finki.NP.ZadaciZaVezbanje2.TasksManager;

import java.time.Duration;

public interface ITask {
    public abstract int getPriority();
    public abstract Duration getTimeUntilNow();
}
