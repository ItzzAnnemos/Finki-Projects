package mk.ukim.finki.NP.ZadaciZaVezbanje2.PhoneBook;

import java.util.*;

public class PhoneBook {
    private Map<String, List<String>> mapByName;
    private Map<String, String> mapByNumber;

    public PhoneBook() {
        this.mapByName = new HashMap<>();
        this.mapByNumber = new TreeMap<>(Comparator.naturalOrder());
    }
    public void addContact(String name, String number) throws DuplicateNumberException {
        mapByName.putIfAbsent(name, new ArrayList<>());
        if (mapByName.get(name).contains(number)) {
            throw new DuplicateNumberException(number);
        }
        mapByName.get(name).add(number);
        mapByNumber.put(number, name);
    }

    public void contactsByNumber(String number) {
        StringBuilder sc = new StringBuilder();
        Comparator<Map.Entry<String, String>> comparator = Map.Entry.comparingByValue();
        mapByNumber.entrySet().stream()
                .filter(entry -> entry.getKey().contains(number))
                .sorted(comparator)
                .forEach(contact -> sc.append(String.format("%s %s\n", contact.getValue(), contact.getKey())));

        if (sc.length() == 0) {
            System.out.println("NOT FOUND");
        } else {
            System.out.print(sc);
        }
    }

    public void contactsByName(String name) {
        if (!mapByName.containsKey(name)) {
            System.out.println("NOT FOUND");
            return;
        }
        Comparator<String> comparator = Comparator.comparing(String::valueOf);
        mapByName.get(name).stream()
                .sorted(comparator)
                .forEach(contact -> System.out.printf("%s %s\n",name, contact));
    }
}
