package mk.ukim.finki.NP.ZadaciZaVezbanje2.LabExercises;

import java.util.List;

public class Student {
    private String index;
    private List<Integer> points;

    public Student(String index, List<Integer> points) {
        this.index = index;
        this.points = points;
    }

    public String getIndex() {
        return index;
    }

    public double getAveragePoints() {
        return points.stream().mapToInt(Integer::intValue).sum() / 10.0;
    }

    public String gettingSignature() {
        if (points.size() >= 8) {
            return "YES";
        } else {
            return "NO";
        }
    }

    public int getStudyYear() {
        return 20 - Integer.parseInt(index.substring(0,2));
    }

    @Override
    public String toString() {
        return String.format("%s %s %.2f", index, gettingSignature(), getAveragePoints());
    }
}
