package mk.ukim.finki.NP.ZadaciZaVezbanje.F1Race;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class F1Race {
    private List<Driver> drivers;

    public F1Race() {
        this.drivers = new ArrayList<>();
    }

    public void readResults(InputStream inputStream) {
        Scanner sc = new Scanner(inputStream);

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String [] parts = line.split("\\s+");

            String name = parts[0];
            List<Lap> laps = new ArrayList<>();

            for (int i = 1;i < parts.length;i++) {
                String [] times = parts[i].split(":");
                Lap tmp = new Lap(Integer.parseInt(times[0]), Integer.parseInt(times[1]), Integer.parseInt(times[2]));
                laps.add(tmp);
            }

            Driver driver = new Driver(name, laps);
            drivers.add(driver);
        }
    }

    public void printSorted(OutputStream outputStream) {
        PrintWriter pw =  new PrintWriter(outputStream);

        for (int i = 0;i < drivers.size();i++) {
            for (int j = 0;j < drivers.size() - i - 1;j++) {
                if (drivers.get(j).getBestLap().getSecs() > drivers.get(j + 1).getBestLap().getSecs()) {
                    Driver tmp = drivers.get(j);
                    drivers.set(j, drivers.get(j + 1));
                    drivers.set(j + 1, tmp);
                }
            }
        }

        for (int i = 0;i < drivers.size();i++) {
            pw.println(i + 1 + ". " + drivers.get(i).toString());
        }

        pw.flush();
    }
}
