package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.Bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.Category;
import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.Location;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class EventRepository {

    public List<Event> findAll() {
        return DataHolder.eventList.stream()
                .sorted(Comparator.comparing(Event::getId))
                .toList();
    }

    public List<Event> searchEvents(String text) {
        List<Event> newList = new ArrayList<>();
        DataHolder.eventList.forEach(event -> {
            if (event.getName().contains(text) || event.getDescription().contains(text))
                newList.add(event);
        });

        return newList;
    }

    public List<Event> searchEventsByCategory(Category category) {
        List<Event> newList = new ArrayList<>();
        DataHolder.eventList.forEach(event -> {
            if (event.getCategory().equals(category)) {
                newList.add(event);
            }
        });

        return newList;
    }

    public List<Event> searchEventsByNameAndCategory(String text, String category) {
        List<Event> newList = new ArrayList<>();
        searchEvents(text).forEach(event -> {
            if (event.getCategory().equals(new Category(category))) {
                newList.add(event);
            }
        });

        return newList;
    }


    public Optional<Event> save(String name, String description, double popularityScore,
                                Category category, Location location) {
        if (location == null || category == null) {
            throw new IllegalArgumentException();
        }

        Event event = new Event(name, description, popularityScore, category, location);
        DataHolder.eventList.removeIf(e -> e.getName().equals(name));
        DataHolder.eventList.add(event);
        return Optional.of(event);
    }

    public void deleteById(Long Id) {
        DataHolder.eventList.removeIf(e -> e.getId().equals(Id));
    }
}
