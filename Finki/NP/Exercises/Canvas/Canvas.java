package mk.ukim.finki.NP.ZadaciZaVezbanje.Canvas;

import java.util.ArrayList;

public class Canvas {
    private ArrayList<Form> forms;

    public Canvas() {
        forms = new ArrayList<>();
    }

    private int indexToAdd(Form shape) {
        if (forms.isEmpty()) {
            return 0;
        }

        for (int i = 0; i < forms.size(); i++) {
            if (forms.get(i).weight() < shape.weight()) {
                return i;
            }
        }

        return forms.size();
    }

    public void add(String id, Color color, float radius) {
        Circle circle = new Circle(id, color, radius);
        forms.add(indexToAdd(circle), circle);
    }

    public void add(String id, Color color, float width, float height) {
        Rectangle rectangle = new Rectangle(id, color, height, width);
        forms.add(indexToAdd(rectangle), rectangle);
    }

    public void scale(String id, float scaleFactor) {
        Form tmp = null;
        for(int i = 0;i < forms.size();i++) {
            if (forms.get(i).getId().equals(id)) {
                tmp = forms.get(i);
                break;
            }
        }

        if (tmp != null) {
            forms.remove(tmp);
            tmp.scale(scaleFactor);
            forms.add(indexToAdd(tmp), tmp);
        }
    }

    @Override
    public String toString() {
        String rez = "";
        for (int i = 0;i < forms.size();i++) {
            rez += forms.get(i).toString();
            rez += "\n";
        }
        return rez;
    }
}
