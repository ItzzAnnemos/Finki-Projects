package mk.ukim.finki.NP.lab5.Scheduler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Scheduler <T> {
    private List<Timestamp<T>> list;

    public Scheduler() {
        this.list = new ArrayList<>();
    }

    public void add(Timestamp<T> t) {
        list.add(t);
    }

    public boolean remove(Timestamp<T> t) {
        return list.remove(t);
    }

    public Timestamp<T> next() {
        list.sort(Comparator.comparing(Timestamp::getTime));
        for (int i = 0;i < list.size() - 1;i++) {
            if (list.get(i).getTime().isBefore(LocalDateTime.now()) && list.get(i+1).getTime().isAfter(LocalDateTime.now())) {
                return list.get(i + 1);
            }
        }
        return list.get(list.size() - 1);
    }

    public Timestamp<T> last() {
        list.sort(Comparator.comparing(Timestamp::getTime));
        for (int i = 0;i < list.size() - 1;i++) {
            if (list.get(i).getTime().isBefore(LocalDateTime.now()) && list.get(i+1).getTime().isAfter(LocalDateTime.now())) {
                return list.get(i);
            }
        }
        return list.get(0);
    }

    public List<Timestamp<T>> getAll(LocalDateTime begin, LocalDateTime end) {
        list.sort(Comparator.comparing(Timestamp::getTime));

        List<Timestamp<T>> list1 = new ArrayList<>();

        for (int i = 0;i < list.size();i++) {
            if (list.get(i).getTime().isBefore(end) && list.get(i).getTime().isAfter(begin)) {
                list1.add(list.get(i));
            }
        }

        return list1;
    }
}
