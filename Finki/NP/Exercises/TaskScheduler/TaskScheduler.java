package mk.ukim.finki.NP.ZadaciZaVezbanje.TaskScheduler;

import java.util.List;

public interface TaskScheduler <T> {

    public List<T> schedule(T[] list);
}
