package mk.finki.ukim.mk.lab.model;

import lombok.Data;

@Data
public class Event {
    private Long Id;
    private String name;
    private String description;
    private double popularityScore;
    private Category category;
    private Location location;

    public Event(String name, String description, double popularityScore, Category category, Location location) {
        this.Id = (long) (Math.random() * 1000);
        this.name = name;
        this.description = description;
        this.popularityScore = popularityScore;
        this.category = category;
        this.location = location;
    }

    @Override
    public String toString() {
        return "Name=" + name +
                ", Description:" + description +
                ", Rating" + popularityScore;
    }
}
