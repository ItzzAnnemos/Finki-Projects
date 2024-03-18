package mk.ukim.finki.NP.lab5.Scheduler;

import java.time.LocalDateTime;

public class Timestamp <T> implements Comparable<Timestamp<T>> {
    private final LocalDateTime localDateTime;
    private final T element;

    public Timestamp(LocalDateTime localDateTime, T element) {
        this.localDateTime = localDateTime;
        this.element = element;
    }

    public LocalDateTime getTime() {
        return localDateTime;
    }

    public T getElement() {
        return element;
    }

    @Override
    public int compareTo(Timestamp t) {
        return localDateTime.compareTo(t.getTime());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Timestamp<?> timestamp = (Timestamp<?>) o;

        return localDateTime.equals(timestamp.localDateTime);
    }

    @Override
    public String toString() {
        return localDateTime + " " + element;
    }
}
