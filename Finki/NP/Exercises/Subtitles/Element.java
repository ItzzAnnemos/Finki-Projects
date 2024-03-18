package mk.ukim.finki.NP.ZadaciZaVezbanje.Subtitles;

public class Element {
    private String idNum;
    private String time;
    private String subs;

    public Element(String idNum, String time, String subs) {
        this.idNum = idNum;
        this.time = time;
        this.subs = subs;
    }

    @Override
    public String toString() {
        String ret = "";
        ret += idNum + "\n";
        ret += time + "\n";
        ret += subs + "\n";

        return ret;
    }

    public void shift(int ms) {
        if (ms > 0) {
            shiftForward(ms);
        } else
            shiftBackwards(ms);
    }

    public void shiftForward(int ms) {
        String[] times = time.substring(0, 12).split("\\:");
        int hours = Integer.parseInt(times[0]);
        int mins = Integer.parseInt(times[1]);
        times = times[2].split("\\,");
        int secs = Integer.parseInt(times[0]);
        int msecs = Integer.parseInt(times[1]);

        times = time.substring(18).split("\\:");
        int hours2 = Integer.parseInt(times[0]);
        int mins2 = Integer.parseInt(times[1]);
        times = times[2].split("\\,");
        int secs2 = Integer.parseInt(times[0]);
        int msecs2 = Integer.parseInt(times[1]);

        msecs += ms;
        if (msecs > 999) {
            secs++;
            msecs -= 1000;
        }
        if (secs > 59) {
            mins++;
            secs -= 60;
        }
        if (mins > 59) {
            hours++;
            mins -= 60;
        }

        msecs2 += ms;
        if (msecs2 > 999) {
            secs2++;
            msecs2 -= 1000;
        }
        if (secs2 > 59) {
            mins2++;
            secs2 -= 60;
        }
        if (mins2 > 59) {
            hours2++;
            mins2 -= 60;
        }

        String newTime = String.format("%02d:%02d:%02d,%03d --> %2d:%2d:%2d,%3d", hours, mins, secs, msecs, hours2, mins2, secs2, msecs2);
        this.time = newTime;
    }
    public void shiftBackwards(int ms) {
        String[] times = time.substring(0, 12).split("\\:");
        int hours = Integer.parseInt(times[0]);
        int mins = Integer.parseInt(times[1]);
        times = times[2].split("\\,");
        int secs = Integer.parseInt(times[0]);
        int msecs = Integer.parseInt(times[1]);

        times = time.substring(18).split("\\:");
        int hours2 = Integer.parseInt(times[0]);
        int mins2 = Integer.parseInt(times[1]);
        times = times[2].split("\\,");
        int secs2 = Integer.parseInt(times[0]);
        int msecs2 = Integer.parseInt(times[1]);

        ms = Math.abs(ms);

        msecs -= ms;
        if (msecs < 0) {
            secs --;
            msecs += 1000;
        }
        if (secs < 0) {
            mins--;
            secs += 60;
        }
        if (mins < 0) {
            hours--;
            mins += 60;
        }

        msecs2 -= ms;
        if (msecs2 < 0) {
            secs2--;
            msecs2 += 1000;
        }
        if (secs2 < 0) {
            mins2--;
            secs2 += 60;
        }
        if (mins2 < 0) {
            hours2--;
            mins2 += 60;
        }

        String newTime = String.format("%02d:%02d:%02d,%03d --> %02d:%02d:%02d,%03d", hours, mins, secs, msecs, hours2, mins2, secs2, msecs2);
        this.time = newTime;
    }
}
