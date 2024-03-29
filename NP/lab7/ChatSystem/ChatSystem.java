package mk.ukim.finki.NP.lab7.ChatSystem;

import java.util.*;

public class ChatSystem {
    private Map<String, ChatRoom> mapByRoomName;
    private List<String> users;

    public ChatSystem() {
        this.mapByRoomName = new TreeMap<>(Comparator.naturalOrder());
        this.users = new ArrayList<>();
    }

    public void addRoom(String roomName) {
        mapByRoomName.putIfAbsent(roomName, new ChatRoom(roomName));
    }

    public void removeRoom(String roomName) {
        if (mapByRoomName.containsKey(roomName))
            mapByRoomName.remove(roomName);
    }

    public ChatRoom getRoom(String roomName) throws NoSuchRoomException {
        if (mapByRoomName.containsKey(roomName))
            return mapByRoomName.get(roomName);
        else
            throw new  NoSuchRoomException(roomName);
    }

    public void register(String userName) {
        users.add(userName);
        int tmp = Integer.MAX_VALUE;
        String key = null;

        for(Map.Entry<String, ChatRoom> entry : mapByRoomName.entrySet()) {
            int current = entry.getValue().numUsers();
            if (current < tmp) {
                tmp = current;
                key = entry.getKey();
            }
        }

        if (key != null) {
            mapByRoomName.get(key).addUser(userName);
        }
    }

    public void registerAndJoin(String userName, String roomName) {
        users.add(userName);
        mapByRoomName.get(roomName).addUser(userName);
    }

    public void joinRoom(String userName, String roomName) throws NoSuchUserException, NoSuchRoomException {
        if (!users.contains(userName))
            throw new NoSuchUserException(userName);

        if (!mapByRoomName.containsKey(roomName))
            throw new NoSuchRoomException(roomName);

        mapByRoomName.get(roomName).addUser(userName);
    }

    public void leaveRoom(String username, String roomName) throws NoSuchRoomException, NoSuchUserException {
        if (!mapByRoomName.containsKey(roomName))
            throw new NoSuchRoomException(roomName);

        if (!users.contains(username))
            throw new NoSuchUserException(username);

        mapByRoomName.get(roomName).removeUser(username);
    }

    public void followFriend(String username, String friend_username) throws NoSuchUserException {
        if (!users.contains(friend_username))
            throw new NoSuchUserException(friend_username);

        mapByRoomName.forEach((key, value) -> {
            if (value.hasUser(friend_username)) {
                value.addUser(username);
            }
        });
    }
}
