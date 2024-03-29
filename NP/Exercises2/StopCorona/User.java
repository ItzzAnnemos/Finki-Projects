package mk.ukim.finki.NP.ZadaciZaVezbanje2.StopCorona;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    private String name;
    private String id;
    private List<ILocation> locations;

    public User(String name, String id) {
        this.name = name;
        this.id = id;
        this.locations = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public List<ILocation> getLocations() {
        return locations;
    }

    public void setLocations(List<ILocation> locations) {
        this.locations = locations;
    }

    public int directContacts(User u) {

        return locations.stream()
                .flatMapToInt(i -> u.locations.stream().
                        mapToInt(j -> LocationUtilities.isDanger(i, j) ? 1 : 0))
                .sum();
    }

    public String toStringShort() {
        return String.format("%s %s***", name, id.substring(0, 4));
    }

    @Override
    public String toString() {
        return String.format("%s %s", name, id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) && Objects.equals(id, user.id) && Objects.equals(locations, user.locations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, locations);
    }
}
