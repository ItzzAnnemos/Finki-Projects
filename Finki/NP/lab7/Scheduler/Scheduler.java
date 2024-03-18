package mk.ukim.finki.NP.lab7.Scheduler;

import java.util.*;

public class Scheduler<T> {
    private Map<Date, T> mapByDate;

    public Scheduler() {
        this.mapByDate = new TreeMap<>(Comparator.naturalOrder());
    }

    public void add(Date d, T t) {
        mapByDate.putIfAbsent(d, t);
    }

    public boolean remove(Date d) {
        if (mapByDate.containsKey(d)) {
            mapByDate.remove(d);
            return true;
        }
        return false;
    }

    public T last() {
        Date now = new Date();
        T tmp = null;
        for (Map.Entry<Date, T> entry : mapByDate.entrySet()) {
            if (entry.getKey().before(now)) {
                tmp = entry.getValue();
            }
        }

        return tmp;
    }

    public T next() {
        Date now = new Date();
        for (Map.Entry<Date, T> entry : mapByDate.entrySet()) {
            if (entry.getKey().after(now)) {
                return entry.getValue();
            }
        }

        return null;
    }

    public ArrayList<T> getAll(Date begin,Date end) {
        ArrayList<T> list = new ArrayList<>();
        for (Map.Entry<Date, T> entry : mapByDate.entrySet()) {
            if (entry.getKey().after(begin) && entry.getKey().before(end)) {
                list.add(entry.getValue());
            }
        }

        return list;
    }

    public T getFirst() {
        Date oldest = new Date();
        T oldestElement = null;

        for (Map.Entry<Date, T> entry : mapByDate.entrySet()) {
            Date currentDate = entry.getKey();

            if (currentDate.before(oldest)) {
                oldest = currentDate;
                oldestElement = entry.getValue();
            }
        }

        return oldestElement;
    }

    public T getLast() {
        Date now = new Date();
        Date newest = new Date(now.getTime() + 19000000);
        T tmp = null;
        for (Map.Entry<Date, T> entry : mapByDate.entrySet()) {
            if (entry.getKey().before(newest)) {
                tmp = entry.getValue();
            }
        }

        return tmp;
    }
}
