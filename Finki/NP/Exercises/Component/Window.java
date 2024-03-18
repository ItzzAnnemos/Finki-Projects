package mk.ukim.finki.NP.ZadaciZaVezbanje.Component;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Window {
    private String name;
    private Map<Integer, Component> list;

    public Window(String name) {
        this.name = name;
        this.list = new TreeMap<>();
    }

    public void addComponent(int position, Component component) throws InvalidPositionException {
        if (list.containsKey(position)) {
            throw new InvalidPositionException(position);
        }
        list.put(position, component);
    }

    public void changeColor(int weight, String color) {
        list.values().forEach(component -> component.changeColor(weight, color));
    }

    public void swichComponents(int pos1, int pos2) {
        Component tmp = list.get(pos1);
        list.put(pos1, list.get(pos2));
        list.put(pos2, tmp);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("WINDOW "+name+"\n");
        list.entrySet().forEach(entry -> sb.append(entry.getKey()+ ":" + entry.getValue()));
        return sb.toString();
    }
}
