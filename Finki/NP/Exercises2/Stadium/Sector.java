package mk.ukim.finki.NP.ZadaciZaVezbanje2.Stadium;

import java.util.ArrayList;
import java.util.List;

public class Sector {
    private String code;
    private int numSeats;
    private int type;
    private List<Integer> seats;

    public Sector(String code, int numSeats) {
        this.code = code;
        this.numSeats = numSeats;
        this.seats = new ArrayList<>(numSeats);
        this.type = -1;
        for (int i = 0;i < numSeats;i++) {
            seats.add(-1);
        }
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public int getNumFreeSeats() {
        return (int) seats.stream()
                .filter(element -> element == -1)
                .count();
    }

    public double getFreePercentage() {
        return (1 - (double) getNumFreeSeats() / numSeats) * 100;
    }

    public List<Integer> getSeats() {
        return seats;
    }

    @Override
    public String toString() {
        return String.format("%s\t%d/%d\t%.1f%%",
                code, getNumFreeSeats(), numSeats, getFreePercentage());
    }
}
