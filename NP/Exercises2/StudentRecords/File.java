package mk.ukim.finki.NP.ZadaciZaVezbanje2.StudentRecords;

import java.util.List;

public class File {
    private String code;
    private String direction;
    private List<Integer> grades;

    public File(String code, String direction, List<Integer> grades) {
        this.code = code;
        this.direction = direction;
        this.grades = grades;
    }

    public double getAverage() {
        return grades.stream().mapToInt(Integer::intValue).average().orElse(0.0);
    }

    public String getCode() {
        return code;
    }

    public int getNumOfTens() {
        return (int) grades.stream()
                .filter(i -> i == 10)
                .count();
    }
    public int getNumOfNines() {
        return (int) grades.stream()
                .filter(i -> i == 9)
                .count();
    }
    public int getNumOfEights() {
        return (int) grades.stream()
                .filter(i -> i == 8)
                .count();
    }
    public int getNumOfSevens() {
        return (int) grades.stream()
                .filter(i -> i == 7)
                .count();
    }
    public int getNumOfSixes() {
        return (int) grades.stream()
                .filter(i -> i == 6)
                .count();
    }

    @Override
    public String toString() {
        return String.format("%s %.2f", code, getAverage());
    }
}
