package mk.ukim.finki.NP.ZadaciZaVezbanje.F1Race;

import java.util.ArrayList;
import java.util.List;

public class Driver {
    private String name;
    private List<Lap> laps;

    public Driver(String name, List<Lap> laps) {
        this.name = name;
        this.laps = laps;
    }

    public Lap getBestLap() {
        Lap best, tmp;
        tmp = laps.get(0).compare(laps.get(1));
        best = tmp.compare(laps.get(2));

        return best;
    }

    @Override
    public String toString() {
        return String.format("%-10s%10s", name, getBestLap().toString());
    }
}
