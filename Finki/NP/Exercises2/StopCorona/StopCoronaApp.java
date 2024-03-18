package mk.ukim.finki.NP.ZadaciZaVezbanje2.StopCorona;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class StopCoronaApp {
    private Map<String, User> users;
    private Map<String, LocalDateTime> infected;

    public StopCoronaApp() {
        this.users = new HashMap<>();
        this.infected = new HashMap<>();
    }

    public void addUser(String name, String id) throws UserAlreadyExistException {
        if (users.containsKey(id)) {
            throw new UserAlreadyExistException(id);
        }

        users.put(id, new User(name, id));
    }

    public void addLocations (String id, List<ILocation> iLocations) {
        users.get(id).setLocations(iLocations);
    }

    public void detectNewCase (String id, LocalDateTime timestamp) {
        infected.put(id, timestamp);
    }

    public Map<User, Integer> getDirectContacts (User u) {
        Map<User, Integer> map = new TreeMap<>(Comparator.comparing(User::getId));

        users.values().stream()
                .filter(user -> !user.equals(u))
                .filter(user -> user.directContacts(u) != 0)
                .forEach(user -> map.put(user, user.directContacts(u)));

        return map;
    }

    public Collection<User> getIndirectContacts (User u) {
        Comparator<User> comparator = Comparator.comparing(User::getName)
                .thenComparing(User::getId);

        Map<User, Integer> direct = getDirectContacts(u);

        return direct.keySet().stream()
                .flatMap(user -> getDirectContacts(user).keySet().stream())
                .filter(user -> !direct.containsKey(user) && !user.equals(u))
                .collect(Collectors.toCollection(() -> new TreeSet<>(comparator)));
    }

    public void createReport () {
        List<Integer> countDirect = new ArrayList<>();
        List<Integer> countIndirect = new ArrayList<>();

        infected.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(u -> printInfectedUser(u, countDirect, countIndirect));

        System.out.printf("Average direct contacts: %.4f\n", countDirect.stream().
                mapToInt(Integer::intValue).
                average().
                getAsDouble());

        System.out.printf("Average indirect contacts: %.4f\n", countIndirect.stream()
                .mapToInt(Integer::intValue)
                .average()
                .getAsDouble());
    }

    private void printInfectedUser(Map.Entry<String, LocalDateTime> entry, List<Integer> countDirect,
                                   List<Integer> countIndirect) {
        User user = users.get(entry.getKey());
        System.out.printf("%s %s\n", user.toString(), entry.getValue());
        System.out.println("Direct contacts:");

        Map<User, Integer> direct = getDirectContacts(user);

        direct.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(e -> System.out.printf("%s %d\n", e.getKey().toStringShort(), e.getValue()));

        int countDirectContacts = direct.values().stream()
                .mapToInt(i -> i)
                .sum();

        System.out.printf("Count of direct contacts: %d\n", countDirectContacts);
        countDirect.add(countDirectContacts);
        System.out.println("Indirect contacts: ");

        Collection<User> indirect = getIndirectContacts(user);
        indirect.forEach(user1 -> System.out.println(user1.toStringShort()));
        System.out.printf("Count of indirect contacts: %d\n", indirect.size());
        countIndirect.add(indirect.size());
    }
}
