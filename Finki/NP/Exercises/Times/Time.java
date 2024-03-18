package mk.ukim.finki.NP.ZadaciZaVezbanje.Times;

public class Time implements Comparable<Time> {
    private int hours;
    private int minutes;

    public Time(int hours, int minutes) {
        this.hours = hours;
        this.minutes = minutes;
    }

    public Time(String time)throws UnsupportedFormatException, InvalidTimeException {
        String[] temp = time.split("\\.");
        if(temp.length == 1) {
            temp = time.split(":");
        }
        if(temp.length == 1) {
            throw new UnsupportedFormatException(time);
        }
        int h = Integer.parseInt(temp[0]);
        int m = Integer.parseInt(temp[1]);
        if(h < 0 || h > 23 || m < 0 || m > 59) {
            throw new InvalidTimeException(time);
        }
        this.hours = h;
        this.minutes = m;
    }

    public String convert() {
        String rez = "";

        if (hours == 0) {
            rez += String.format("%2d:%02d AM", (hours + 12), minutes);
        } else if (hours < 12) {
            rez += String.format("%2d:%02d AM", hours, minutes);
        } else if (hours == 12) {
            rez += String.format("%2d:%02d PM", hours, minutes);
        } else {
            rez += String.format("%2d:%02d PM", (hours - 12), minutes);
        }

        return rez;
    }

    @Override
    public int compareTo(Time o) {
        if(this.hours == o.hours)
            return this.minutes - o.minutes;
        return this.hours - o.hours;
    }

    @Override
    public String toString() {
        return String.format("%2d:%02d", hours, minutes);
    }
}
