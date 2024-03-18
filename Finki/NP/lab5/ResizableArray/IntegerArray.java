package mk.ukim.finki.NP.lab5.ResizableArray;

import java.util.HashSet;

public class IntegerArray extends ResizableArray<Integer> {

    public IntegerArray() {}

    public double sum() {
        double sum = 0.0;
        for (int i = 0;i < count();i++) {
            sum += elementAt(i);
        }

        return sum;
    }

    public double mean() {
        return sum() / count();
    }

    public int countNonZero() {
        int count = 0;
        for (int i = 0;i < count();i++) {
            if (elementAt(i) != 0) {
                count++;
            }
        }

        return count;
    }

    public IntegerArray distinct() {
        IntegerArray result = new IntegerArray();

        HashSet<Integer> uniqueElements = new HashSet<>();

        for (int i = 0; i < count(); i++) {
            Integer element = elementAt(i);
            if (uniqueElements.add(element)) {
                result.addElement(element);
            }
        }

        return result;
    }

    public IntegerArray increment(int offset) {
        IntegerArray result = new IntegerArray();

        for (int i = 0; i < count(); i++) {
            result.addElement(elementAt(i) + offset);
        }

        return result;
    }
}
