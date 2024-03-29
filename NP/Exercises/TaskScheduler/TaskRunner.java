package mk.ukim.finki.NP.ZadaciZaVezbanje.TaskScheduler;

import java.util.List;

public class TaskRunner <T> {
    public void run(TaskScheduler<T> scheduler, T[] tasks) {
        List<T> order = scheduler.schedule(tasks);
        order.forEach(System.out::println);
    }

}
