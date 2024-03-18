package mk.ukim.finki.NP.ZadaciZaVezbanje2.TasksManager;

import java.time.Duration;
import java.time.LocalDateTime;

public class TaskWithPriorityAndDeadline extends Task{
    private int priority;
    private LocalDateTime deadline;

    public TaskWithPriorityAndDeadline(String name, String description, LocalDateTime deadline, int priority) throws DeadlineNotValidException {
        super(name, description);
        if (deadline.isBefore(LocalDateTime.of(2020, 6, 2, 0, 0, 0))) {
            throw new DeadlineNotValidException(deadline.toString());
        }
        this.priority = priority;
        this.deadline = deadline;
    }

    public int getPriority() {
        return priority;
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
                ", priority=" + priority +
                '}';
    }
}
