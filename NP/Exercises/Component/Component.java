package mk.ukim.finki.NP.ZadaciZaVezbanje.Component;

import java.util.*;

public class Component implements Comparable<Component> {
    private String color;
    private int weight;
    private Set<Component> components;

    public Component(String color, int weight) {
        this.color = color;
        this.weight = weight;
        this.components = new TreeSet<>();
    }

    public void addComponent(Component component) {
        components.add(component);
    }

    public void changeColor(int weight, String color) {
        if (this.weight < weight)
            this.color = color;
        components.forEach(component -> component.changeColor(weight, color));
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public int getWeight() {
        return weight;
    }

    public String format(String line) {
        String ret = String.format("%s%d:%s\n", line, weight, color);
        for (Component component : components) {
            ret += component.format(line + "---");
        }
        return ret;
    }

    @Override
    public String toString() {
        return format("");
    }

    @Override
    public int compareTo(Component o) {
        return Comparator.comparing(Component::getWeight)
                .thenComparing(Component::getColor).compare(this, o);
    }
}
