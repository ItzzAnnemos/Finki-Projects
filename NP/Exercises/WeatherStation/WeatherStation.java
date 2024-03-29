package mk.ukim.finki.NP.ZadaciZaVezbanje.WeatherStation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class WeatherStation {
    private int days;
    private ArrayList<Weather> measures;

    public WeatherStation(int x) {
        this.days = x;
        this.measures = new ArrayList<>();
    }

    public void addMeasurment(float temperature, float wind, float humidity, float visibility, Date date) {
        Weather tmp = new Weather(temperature, wind, humidity, visibility, date);

        if (measures.isEmpty()) {
            measures.add(tmp);
            return;
        } else {

            if (Math.abs(measures.get(measures.size() - 1).getDate().getTime()
                    - tmp.getDate().getTime()) < 2.5 * 60 * 1000)
                return;
            measures.add(tmp);


            List<Weather> toRemove = measures.stream()
                    .filter(mes -> (Math.abs(mes.getDate().getTime() -
                            tmp.getDate().getTime())) > (long) getDays() * 24 * 60 * 60 * 1000)
                    .collect(Collectors.toList());

            measures.removeAll(toRemove);
        }
    }

    private long getDays() {
        return days;
    }

    public int total() {
        return measures.size();
    }

    public void status(Date from, Date to) {
        List<Weather> ms = measures.stream()
                .filter(measurment -> (measurment.getDate().after(from) || measurment.getDate().equals(from))
                        && (measurment.getDate().before(to) || measurment.getDate().equals(to)))
                .collect(Collectors.toList());

        if (ms.isEmpty())
            throw new RuntimeException();

        ms.forEach(System.out::println);
        System.out.printf("Average temperature: %.02f",
                ms.stream().mapToDouble(Weather::getTemp).average().getAsDouble());

        /*int flag = 1;
        for (int i = 0; i < measures.size(); i++) {
            if (!(measures.get(i).getDate().before(from) || measures.get(i).getDate().after(to))) {
                flag = 0;
                System.out.println(measures.get(i).toString());
            }
        }

        if (flag == 1) {
            throw new RuntimeException();
        }

        System.out.printf("Average temperature: %.2f%n", averageTemp());*/
    }
}
