package mk.ukim.finki.NP.ZadaciZaVezbanje2.Faculty;

import java.util.*;
import java.util.stream.Collectors;

public class Faculty {
    private Map<String, Student> students;
    private Map<String, Course> courses;
    private List<String> logs;

    public Faculty() {
        this.students = new HashMap<>();
        this.courses = new HashMap<>();
        this.logs = new ArrayList<>();
    }

    void addStudent(String id, int yearsOfStudies) {
        students.put(id, new Student(id, yearsOfStudies));
    }

    void addGradeToStudent(String studentId, int term, String courseName, int grade) throws OperationNotAllowedException {
        students.get(studentId).addGrade(term, courseName, grade);
        courses.computeIfAbsent(courseName, c -> new Course(courseName)).addGrade(grade);
        if (students.get(studentId).checkGraduation()) {
            logs.add(students.get(studentId).getLog());
            students.remove(studentId);
        }
    }

    String getFacultyLogs() {
        return String.join("\n", logs);
    }

    String getDetailedReportForStudent(String id) {
        return students.get(id).getDetailedReport();
    }

    void printFirstNStudents(int n) {
        Comparator<Student> comparator = Comparator.comparing(Student::getPassedCourses)
                .thenComparing(Student::getAverageGrade).thenComparing(Student::getIndex).reversed();

        TreeSet<Student> sorted = new TreeSet<>(comparator);
        sorted.addAll(students.values());

        sorted.stream()
                .limit(n)
                .forEach(System.out::println);
    }

    void printCourses() {
        Comparator<Course> comparator = Comparator.comparing(Course::listeners)
                .thenComparing(Course::getCourseAverageGrade).thenComparing(Course::getCourseName);

        TreeSet<Course> sorted = new TreeSet<>(comparator);

        sorted.addAll(courses.values());

        sorted.forEach(System.out::println);
    }
}
