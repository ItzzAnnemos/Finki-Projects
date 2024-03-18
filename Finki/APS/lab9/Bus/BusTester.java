package mk.ukim.finki.lab9.Bus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Person {
    String depart;
    String arrive;

    public Person(String depart, String arrive) {
        this.depart = depart;
        this.arrive = arrive;
    }

    public int getDepart() {
        String[] parts = depart.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);

        return hours * 60 + minutes;
    }

    public int getArrival() {
        String[] parts = arrive.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);

        return hours * 60 + minutes;
    }
}

public class BusTester {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int capacity = Integer.parseInt(br.readLine());

        int n = Integer.parseInt(br.readLine());

        List<Person> people = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            String[] parts = line.split("\\s+");
            people.add(new Person(parts[0], parts[1]));
        }

        people.sort(Comparator.comparing(Person::getDepart));

        int current = 0;

        for (int j = 0; j < 1440; j++) {
            for (int i = 0; i < people.size(); i++) {
                if (people.get(i).getDepart() == j) {
                    current++;
                }
                if (people.get(i).getArrival() == j) {
                    current--;
                }
                if (current > capacity) {
                    System.out.println(false);
                    return;
                }
            }
        }
        System.out.println(true);
    }
}
