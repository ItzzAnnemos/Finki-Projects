package mk.ukim.finki.NP.lab7.ChatSystem;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class ChatRoom {
    private String name;
    private Set<String> users;

    public ChatRoom(String name) {
        this.name = name;
        this.users = new TreeSet<>(Comparator.naturalOrder());
    }

    public void addUser(String username) {
        users.add(username);
    }

    public void removeUser(String username) {
        if (users.contains(username))
            users.remove(username);
    }

    public boolean hasUser(String username) {
        return users.contains(username);
    }

    public int numUsers() {
        return users.size();
    }

    public String toString() {
        StringBuilder sc = new StringBuilder();
        sc.append(name).append('\n');
        if (users.isEmpty()) {
            sc.append("EMPTY\n");
            return sc.toString();
        }

        for (String user : users) {
            sc.append(user).append('\n');
        }

        return sc.toString();
    }
}
