package mk.ukim.finki.NP.ZadaciZaVezbanje.Times;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class TimeTable {
    private ArrayList<Time> times;

    public TimeTable() {
        this.times = new ArrayList<>();
    }

    public void readTimes(InputStream inputStream) throws InvalidTimeException, UnsupportedFormatException {
        Scanner sc = new Scanner(inputStream);

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] parts = line.split(" ");

            for (int i = 0; i < parts.length; i++) {
                times.add(new Time(parts[i]));
            }
        }

        sc.close();
    }

    public void writeTimes(OutputStream outputStream, TimeFormat format) {
        PrintWriter pw = new PrintWriter(outputStream);

        Collections.sort(times);

        if (format == TimeFormat.FORMAT_24) {
            for (Time time : times) {
                pw.println(time.toString());
            }
        } else {
            for (Time time : times) {
                pw.println(time.convert());
            }
        }

        pw.flush();
    }
}
