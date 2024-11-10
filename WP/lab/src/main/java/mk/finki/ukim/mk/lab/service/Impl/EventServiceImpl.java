package mk.finki.ukim.mk.lab.service.Impl;

import mk.finki.ukim.mk.lab.model.Category;
import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.Location;
import mk.finki.ukim.mk.lab.model.exeptions.CategoryNotFoundException;
import mk.finki.ukim.mk.lab.model.exeptions.LocationNotFoundException;
import mk.finki.ukim.mk.lab.repository.CategoryRepository;
import mk.finki.ukim.mk.lab.repository.EventRepository;
import mk.finki.ukim.mk.lab.repository.LocationRepository;
import mk.finki.ukim.mk.lab.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final CategoryRepository categoryRepository;
    private final LocationRepository locationRepository;

    public EventServiceImpl(EventRepository eventRepository, CategoryRepository categoryRepository, LocationRepository locationRepository) {
        this.eventRepository = eventRepository;
        this.categoryRepository = categoryRepository;
        this.locationRepository = locationRepository;
    }

    @Override
    public List<Event> listAll() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> searchEvents(String text) {
        return eventRepository.searchEvents(text);
    }

    @Override
    public List<Event> searchEventsByCategory(String category) {
        return eventRepository.searchEventsByCategory(new Category(category));
    }

    @Override
    public List<Event> searchEventsByNameAndCategory(String text, String category) {
        return eventRepository.searchEventsByNameAndCategory(text, category);
    }

    @Override
    public Optional<Event> findById(Long Id) {
        return eventRepository.findAll().stream().filter(e -> e.getId().equals(Id)).findFirst();
    }

    @Override
    public Optional<Event> save(String name, String description, double popularityScore,
                                Long categoryId, Long locationId) {
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
        Location location = this.locationRepository.findById(locationId)
                .orElseThrow(() -> new LocationNotFoundException(locationId));

        return this.eventRepository.save(name, description, popularityScore, category, location);
    }

    @Override
    public void deleteById(Long Id) {
        eventRepository.deleteById(Id);
    }
}
