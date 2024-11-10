package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Category;
import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.Location;

import java.util.List;
import java.util.Optional;

public interface EventService {
    List<Event> listAll();
    List<Event> searchEvents(String text);
    List<Event> searchEventsByCategory(String category);
    List<Event> searchEventsByNameAndCategory(String text, String category);
    Optional<Event> findById(Long Id);
    Optional<Event> save(String name, String description, double popularityScore,
                         Long category, Long location);
    void deleteById(Long Id);
}
