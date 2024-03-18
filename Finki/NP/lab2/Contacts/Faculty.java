package mk.ukim.finki.NP.lab2.Contacts;

import java.util.Arrays;

public class Faculty {
    private String name;
    private Student[] students;

    public Faculty(String name, Student[] students) {
        this.name = name;
        this.students = students;
    }

    public int countStudentsFromCity(String cityName) {
        int sum = 0;
        for (int i = 0; i < students.length; i++) {
            if (students[i].getCity().equals(cityName)) {
                sum++;
            }
        }
        return sum;
    }

    public Student getStudent(long index) {
        Student tmp = students[0];
        for (int i = 0;i < students.length;i++) {
            if (students[i].getIndex() == index) {
                tmp = students[i];
            }
        }
        return tmp;
    }

    public double getAverageNumberOfContacts() {
        int sum = 0;
        for (int i = 0;i < students.length;i++) {
            sum += students[i].getNumContacts();
        }

        return (double) sum / students.length;
    }

    public Student getStudentWithMostContacts() {
        Student most = students[0];
        for (int i = 0;i < students.length;i++) {
            if (students[i].getNumContacts() > most.getNumContacts()) {
                most = students[i];
            } else if (students[i].getNumContacts() == most.getNumContacts()) {
                if (students[i].getIndex() > most.getIndex()) {
                    most = students[i];
                }
            }
        }

        return most;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("{\"fakultet\":\"").append(name).append("\", ");
        result.append("\"studenti\":[");

        for (int i = 0; i < students.length; i++) {
            result.append(students[i].toString());
            if (i < students.length - 1) {
                result.append(", ");
            }
        }

        result.append("]}");

        return result.toString();
    }
}
