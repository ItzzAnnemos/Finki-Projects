package mk.ukim.finki.NP.ZadaciZaVezbanje2.Airports;

import java.time.*;

public class Flight {
    private String from;
    private String to;
    private int time;
    private int duration;

    public Flight(String from, String to, int time, int duration) {
        this.from = from;
        this.to = to;
        this.time = time;
        this.duration = duration;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public int getTime() {
        return time;
    }

    public LocalTime getStartingTime() {
        int hour = time / 60;
        int minutes = time % 60;

        return LocalTime.of(hour, minutes);
    }

    public LocalTime getEndingTime() {
        return getStartingTime().plusMinutes(duration);
    }

    public Duration getDuration() {
        return Duration.ofMinutes(getEndingTime().minusMinutes(getStartingTime().getMinute()).getMinute());
    }

    public String duration() {
        int startHour = time / 60;
        int startMinute = time % 60;
        int endHour = (time + duration) / 60;
        int endMinute = (time + duration) % 60;
        boolean day = false;

        int durMinute = endMinute - startMinute;
        if (endHour >= 24) {
            day = true;
        }
        if (durMinute < 0) {
            durMinute += 60;
            endHour--;
        }
        int durHour = endHour - startHour;
        if (endHour >= 24) {
            day = true;
        }
        if (day)
            return String.format("+1d %dh%02dm", durHour, durMinute);
        else
            return String.format("%dh%02dm", durHour, durMinute);
    }

    public static String formatDuration(Duration duration) {
        long days = duration.toDays();
        long hours = duration.toHoursPart();
        long minutes = duration.toMinutesPart();

        StringBuilder formattedDuration = new StringBuilder();

        if (days > 0) {
            formattedDuration.append("+").append(days).append("d ");
        }

        formattedDuration.append((duration.isNegative() ? "+" : "")).append(hours).append("h").append(minutes).append("m");

        return formattedDuration.toString();
    }

    @Override
    public String toString() {
        return String.format("%s-%s %s-%s %s", from, to, getStartingTime(), getEndingTime(), duration());
    }
}
