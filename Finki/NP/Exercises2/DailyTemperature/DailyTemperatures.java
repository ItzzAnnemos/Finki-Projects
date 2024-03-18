package mk.ukim.finki.NP.ZadaciZaVezbanje2.DailyTemperature;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;

public class DailyTemperatures {
    private Map<Integer, List<Double>> temperaturesByDay;

    public DailyTemperatures() {
        this.temperaturesByDay = new TreeMap<>(Integer::compareTo);
    }

    public void readTemperatures(InputStream inputStream) {
        Scanner sc = new Scanner(inputStream);

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] parts = line.split("\\s+");

            int day = Integer.parseInt(parts[0]);

            temperaturesByDay.putIfAbsent(day, new ArrayList<>());

            List<Double> list = new ArrayList<>();

            for (int i = 1; i < parts.length; i++) {
                double temp;
                if (parts[i].endsWith("F")) {
                    double far = Double.parseDouble(parts[i].substring(0, parts[i].length() - 1));
                    temp = (far - 32) * 5 / 9;
                } else {
                    temp = Double.parseDouble(parts[i].substring(0, parts[i].length() - 1));
                }
                list.add(temp);
            }

            temperaturesByDay.get(day).addAll(list);
        }
    }

    public void writeDailyStats(OutputStream outputStream, char scale) {
        PrintWriter pw = new PrintWriter(outputStream);
        for (int i = 1; i < 367; i++) {
            DoubleSummaryStatistics dss = new DoubleSummaryStatistics();
            if (temperaturesByDay.get(i) != null) {
                for (int j = 0; j < temperaturesByDay.get(i).size(); j++) {
                    dss.accept((scale == 'C') ? temperaturesByDay.get(i).get(j) :
                            toFahrenheit(temperaturesByDay.get(i).get(j)));
                }
                pw.println(String.format("%3d: Count: %3d Min: %6.2f%c Max: %6.2f%c Avg: %6.2f%c",
                        i, dss.getCount(), dss.getMin(), scale, dss.getMax(), scale, dss.getAverage(), scale));

            }
        }

        pw.flush();
    }

    private double toFahrenheit(double value) {
        return (value * 9 / 5) + 32;
    }
}
