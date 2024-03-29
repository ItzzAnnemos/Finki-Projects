package mk.ukim.finki.NP.ZadaciZaVezbanje.F1Race;

public class Lap {
    private int min;
    private int sec;
    private int msec;

    public Lap(int min, int sec, int msec) {
        this.min = min;
        this.sec = sec;
        this.msec = msec;
    }

    public Lap compare(Lap o) {
        if (this.min < o.min) {
            return this;
        } else if (this.min > o.min) {
            return o;
        } else {
            if (this.sec < o.sec) {
                return this;
            } else if (this.sec > o.sec) {
                return o;
            } else {
                if (this.msec < o.msec) {
                    return this;
                } else {
                    return o;
                }
            }
        }
    }

    public double getSecs() {
        return min * 60 + sec + (msec / 1000.0);
    }

    @Override
    public String toString() {
        return String.format("%d:%02d:%03d", min, sec, msec);
    }
}
