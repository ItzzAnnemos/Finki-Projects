package mk.ukim.finki.NP.ZadaciZaVezbanje2.Audition;

import java.util.*;

public class Audition {
    private Map<String, HashSet<String>> map;
    private Map<String, List<Participant>> participants;

    public Audition() {
        this.map = new HashMap<>();
        this.participants = new HashMap<>();
    }

    public void addParticpant(String city, String code, String name, int age) {
        if (map.containsKey(city)) {
            if (!map.get(city).contains(code)) {
                participants.get(city).add(new Participant(city, code, name, age));
                map.get(city).add(code);
            }
        } else {
            map.put(city, new HashSet<>());
            map.get(city).add(code);
            participants.put(city, new ArrayList<>());
            participants.get(city).add(new Participant(city, code, name, age));
        }
    }

    public void listByCity(String city) {
        Comparator<Participant> comparator = Comparator.comparing(Participant::getName)
                .thenComparing(Participant::getAge).thenComparing(Participant::getCode);

        List<Participant> list = participants.get(city);

        list.sort(comparator);

        for (int i = 0;i < list.size();i++) {
            System.out.println(list.get(i));
        }
    }
}
