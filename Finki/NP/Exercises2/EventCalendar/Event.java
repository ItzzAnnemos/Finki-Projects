package mk.ukim.finki.NP.ZadaciZaVezbanje2.EventCalendar;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Event {
    private String name;
    private String location;

    private Date date;

    public Event(String name, String location, Date date) {
        this.name = name;
        this.location = location;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public Date getDate() {
        return date;
    }

    public static String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM, yyyy HH:mm");
        return dateFormat.format(date);
    }

    @Override
    public String toString() {
        return String.format("%s at %s, %s", formatDate(date), location, name);
    }
}
