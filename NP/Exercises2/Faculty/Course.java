package mk.ukim.finki.NP.ZadaciZaVezbanje2.Faculty;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private final String courseName;
    private final List<Integer> grades;

    public Course(String courseName) {
        this.courseName = courseName;
        this.grades = new ArrayList<>();
    }

    public void addGrade(int grade) {
        grades.add(grade);
    }

    public double getCourseAverageGrade() {
        return grades.stream().mapToDouble(g -> g).average().orElse(0);
    }

    public int listeners() {
        return grades.size();
    }

    public String getCourseName() {
        return courseName;
    }

    @Override
    public String toString() {
        return String.format("%s %d %.02f", courseName, listeners(), getCourseAverageGrade());
    }
}
