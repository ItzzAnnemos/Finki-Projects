package mk.ukim.finki.NP.ZadaciZaVezbanje2.TasksManager;

import java.time.Duration;
import java.time.LocalDateTime;

public  class Task implements ITask{
    private String name;
    private String description;

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }


    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public int getPriority() {
        return 5;
    }

    @Override
    public Duration getTimeUntilNow() {
        return Duration.ZERO;
    }
}
