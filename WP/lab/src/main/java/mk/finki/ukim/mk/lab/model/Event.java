package mk.finki.ukim.mk.lab.model;

public class Event {
    private String name;
    private String description;
    private double popularityScore;

    public Event(String name, String description, double popularityScore) {
        this.name = name;
        this.description = description;
        this.popularityScore = popularityScore;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPopularityScore() {
        return popularityScore;
    }

    @Override
    public String toString() {
        return "Name=" + name +
                ", Description:" + description +
                ", Rating" + popularityScore;
    }
}
