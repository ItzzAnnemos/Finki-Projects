package mk.ukim.finki.NP.ZadaciZaVezbanje.TaskScheduler;

public class PriorityTask implements Task {
    private final int priority;

    public PriorityTask(int priority) {
        this.priority = priority;
    }

    @Override
    public int getOrder() {
        return priority;
    }

    @Override
    public String toString() {
        return String.format("PT -> %d", getOrder());
    }
}
