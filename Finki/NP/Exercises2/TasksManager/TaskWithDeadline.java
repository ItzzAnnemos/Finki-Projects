package mk.ukim.finki.NP.ZadaciZaVezbanje2.TasksManager;

import java.time.Duration;
import java.time.LocalDateTime;

public class TaskWithDeadline extends Task {
    private LocalDateTime deadline;

    public TaskWithDeadline(String name, String description, LocalDateTime deadline) throws DeadlineNotValidException {
        super(name, description);
        if (deadline.isBefore(LocalDateTime.of(2020, 6, 2, 0, 0, 0))) {
            throw new DeadlineNotValidException(deadline.toString());
        }
        this.deadline = deadline;
    }

    public Duration getTimeUntilNow() {
        return Duration.between(LocalDateTime.now(), deadline);
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", deadline=" + deadline +
                '}';
    }
}
