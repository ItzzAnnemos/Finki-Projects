package mk.ukim.finki.NP.ZadaciZaVezbanje2.EventCalendar;

import java.time.LocalDate;
import java.time.Year;
import java.util.*;

public class EventCalendar {
    private int year;
    private List<Event> events;
    private Map<Date, List<Event>> mapByDate;
    private Map<Integer, Integer> mapByMonth;

    public EventCalendar(int year) {
        this.year = year;
        this.events = new ArrayList<>();
        this.mapByDate = new HashMap<>();
        this.mapByMonth = new TreeMap<>(Integer::compareTo);
        for (int i = 1;i < 13;i++) {
            mapByMonth.put(i, 0);
        }
    }

    public void addEvent(String name, String location, Date date) throws WrongDateException {
        LocalDate localDate = date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
        int year = Year.from(localDate).getValue();
        if (year != this.year) {
            throw new WrongDateException(date.toString());
        }

        mapByDate.putIfAbsent(date, new ArrayList<>());
        mapByDate.get(date).add(new Event(name, location, date));

        events.add(new Event(name, location, date));

        mapByMonth.put(localDate.getMonthValue(), mapByMonth.get(localDate.getMonthValue()) + 1);
    }

    public void listEvents(Date date) {
        Comparator<Event> comparator = Comparator.comparing(Event::getDate).thenComparing(Event::getName);

        StringBuilder sc = new StringBuilder();
        mapByDate.entrySet().stream()
                .filter(entry -> isSameDay(entry.getKey(), date))
                .flatMap(entry -> entry.getValue().stream())
                .sorted(comparator)
                .forEach(event -> sc.append(event).append('\n'));

        if (sc.length() == 0)
            System.out.println("No events on this day!");
        else {
            System.out.print(sc);
        }
    }

    public void listByMonth() {
        mapByMonth.forEach((key, value) -> System.out.printf("%d : %d\n", key, value));
    }

    private boolean isSameDay(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
                && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)
                && cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);
    }
}
