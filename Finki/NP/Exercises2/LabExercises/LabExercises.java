package mk.ukim.finki.NP.ZadaciZaVezbanje2.LabExercises;

import java.util.*;
import java.util.stream.Collectors;

public class LabExercises {
    private List<Student> students;
    private Map<Integer, List<Student>> mapByStudyYear;

    public LabExercises() {
        this.students = new ArrayList<>();
        this.mapByStudyYear = new TreeMap<>(Comparator.naturalOrder());
    }

    public void addStudent (Student student) {
        students.add(student);
        mapByStudyYear.putIfAbsent(student.getStudyYear(), new ArrayList<>());
        mapByStudyYear.get(student.getStudyYear()).add(student);
    }

    public void printByAveragePoints (boolean ascending, int n) {
        Comparator<Student> comparator = Comparator.comparing(Student::getAveragePoints)
                .thenComparing(Student::getIndex);

        if (!ascending)
            comparator = comparator.reversed();

        students.stream()
                .sorted(comparator)
                .limit(n)
                .forEach(System.out::println);
    }

    public List<Student> failedStudents() {
        Comparator<Student> comparator = Comparator.comparing(Student::getIndex)
                .thenComparing(Student::getAveragePoints);

        return students.stream()
                .filter(student -> student.gettingSignature().equals("NO"))
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    public Map<Integer,Double> getStatisticsByYear() {
        Map<Integer, Double> map = new TreeMap<>(Comparator.naturalOrder());
        mapByStudyYear.entrySet()
                .forEach(entry -> {
                    map.put(entry.getKey(),
                    entry.getValue().stream()
                            .filter(student -> student.gettingSignature().equals("YES"))
                            .mapToDouble(Student::getAveragePoints)
                            .average()
                            .orElse(0.0));
                });

        map.entrySet().removeIf(entry -> entry.getValue() == 0);

        return map;
    }
}
