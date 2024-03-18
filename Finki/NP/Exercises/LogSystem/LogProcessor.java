package mk.ukim.finki.NP.ZadaciZaVezbanje.LogSystem;

import java.util.ArrayList;

public interface LogProcessor<T> {
    ArrayList<T> processLogs(ArrayList<T> logs);
}
