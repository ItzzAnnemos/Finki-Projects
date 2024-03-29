package mk.ukim.finki.NP.ZadaciZaVezbanje2.Faculty;

import java.util.*;
import java.util.stream.IntStream;

public class Student {
    private String index;
    private int yearsOfStudies;
    private Map<Integer, List<Integer>> gradesByTerm;
    private Set<String> courses;

    public Student(String index, int yearsOfStudies) {
        this.index = index;
        this.yearsOfStudies = yearsOfStudies;
        this.gradesByTerm = new HashMap<>();
        this.courses = new TreeSet<>();
        IntStream.range(1, yearsOfStudies * 2 + 1)
                .forEach(i -> gradesByTerm.put(i, new ArrayList<>()));
    }

    public void addGrade(int term, String course, int grade) throws OperationNotAllowedException {
        if (!gradesByTerm.containsKey(term))
            throw new OperationNotAllowedException(String.format("Term %d is not possible for student with ID %s", term, index));

        if (gradesByTerm.get(term).size() == 3) {
            throw new OperationNotAllowedException("Student " + index + " already has 3 grades in term " + term);
        }

        gradesByTerm.get(term).add(grade);
        courses.add(course);
    }

    public String getIndex() {
        return index;
    }

    public boolean checkGraduation() {
        return getPassedCourses() == 18 && yearsOfStudies == 3 ||
                getPassedCourses() == 24 && yearsOfStudies == 4;
    }

    public double getAverageGrade() {
        return gradesByTerm.values().stream()
                .flatMap(Collection::stream)
                .mapToInt(Integer::intValue)
                .average()
                .orElse(5.00);
    }

    public double getAverageGradeForTerm(int term) {
        return gradesByTerm.get(term).stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(5.00);
    }

    public String getLog() {
        return String.format("Student with ID %s graduated with average grade %.02f in %d years.",
                index, getAverageGrade(), yearsOfStudies);
    }

    public String termReport(int term) {
        return String.format("Term %d\nCourses: %d\nAverage grade for term: %.02f\n"
                , term, gradesByTerm.get(term).size(), getAverageGradeForTerm(term));
    }

    public String getDetailedReport() {
        StringBuilder sc = new StringBuilder();
        sc.append("Student: ").append(index).append("\n");
        gradesByTerm.keySet().forEach(t -> sc.append(termReport(t)));
        sc.append(String.format("Average grade: %.02f", getAverageGrade())).append("\n");
        sc.append(String.format("Courses attended: %s", String.join(",", courses)));

        return sc.toString();
    }

    public int getPassedCourses() {
        return gradesByTerm.values().stream()
                .mapToInt(List::size)
                .sum();
    }

    @Override
    public String toString() {
        return String.format("Student: %s Courses passed: %d Average grade: %.2f",
                index, getPassedCourses(), getAverageGrade());
    }
}
