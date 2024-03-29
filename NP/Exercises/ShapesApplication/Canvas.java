package mk.ukim.finki.NP.ZadaciZaVezbanje.ShapesApplication;

import java.util.ArrayList;
import java.util.List;

public class Canvas {
    private String id;
    private List<Integer> sides;

    public Canvas(String id, ArrayList<Integer> sides) {
        this.id = id;
        this.sides = new ArrayList<>();
        this.sides.addAll(sides);
    }

    public static Canvas create(String line) {
        String [] tmp = line.split("\\s+");

        ArrayList<Integer> arr = new ArrayList<>();

        for (int i = 1;i < tmp.length; i++) {
            arr.add(Integer.parseInt(tmp[i]));
        }

        return new Canvas(tmp[0], arr);
    }

    public int calcPerimeter() {
        int sum = 0;
        for (int i = 0;i < sides.size();i++) {
            sum += sides.get(i);
        }

        sum *= 4;

        return sum;
    }

    @Override
    public String toString() {
        return id + " " + getSize() + " " + calcPerimeter();
    }

    public int getSize() {
        return sides.size();
    }
}
