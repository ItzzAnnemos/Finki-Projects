package mk.ukim.finki.NP.ZadaciZaVezbanje2.TasksManager;

public class TaskWithPriority extends Task {
    private int priority;

    public TaskWithPriority(String name, String description, int priority) {
        super(name, description);
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", priority=" + priority +
                '}';
    }
}
