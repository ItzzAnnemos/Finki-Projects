package mk.ukim.finki.NP.ZadaciZaVezbanje.TaskScheduler;

public class TimedTask implements Task {
    private final int time;

    public TimedTask(int time) {
        this.time = time;
    }

    @Override
    public int getOrder() {
        return time;
    }

    @Override
    public String toString() {
        return String.format("TT -> %d", getOrder());
    }
}
