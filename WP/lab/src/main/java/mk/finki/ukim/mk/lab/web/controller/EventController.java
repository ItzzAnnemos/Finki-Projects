package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Category;
import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.Location;
import mk.finki.ukim.mk.lab.service.CategoryService;
import mk.finki.ukim.mk.lab.service.EventService;
import mk.finki.ukim.mk.lab.service.LocationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;
    private final CategoryService categoryService;
    private final LocationService locationService;

    public EventController(EventService eventService,
                           CategoryService categoryService,
                           LocationService locationService) {
        this.eventService = eventService;
        this.categoryService = categoryService;
        this.locationService = locationService;
    }

    @GetMapping
    public String getEventsPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Event> events = this.eventService.listAll();
        List<Category> categories = this.categoryService.listAll();
        model.addAttribute("events", events);
        model.addAttribute("categories", categories);
        return "listEvents";
    }

    @GetMapping("/edit-form/{id}")
    public String getEditEventForm(@PathVariable Long id, Model model) {
        if (this.eventService.findById(id).isPresent()) {
            Event event = this.eventService.findById(id).get();
            List<Category> categories = this.categoryService.listAll();
            List<Location> locations = this.locationService.findAll();
            model.addAttribute("event", event);
            model.addAttribute("categories", categories);
            model.addAttribute("locations", locations);
            return "addEvent";
        }
        return "redirect:/events?error=EventNotFound";
    }

    @PostMapping("/edit/{eventId}")
    public String editEvent(@PathVariable Long eventId,
                            @RequestParam String name,
                            @RequestParam String description,
                            @RequestParam double popularityScore,
                            @RequestParam Long categoryId,
                            @RequestParam Long locationId) {
        if (this.eventService.findById(eventId).isPresent()) {
            Event event = this.eventService.findById(eventId).get();
            event.setName(name);
            event.setDescription(description);
            event.setPopularityScore(popularityScore);
            if (this.categoryService.findById(categoryId).isPresent())
                event.setCategory(this.categoryService.findById(categoryId).get());
            if (this.locationService.findById(locationId).isPresent())
                event.setLocation(this.locationService.findById(locationId).get());
        }
        return "redirect:/events";
    }


    @PostMapping("/delete/{id}")
    public String deleteEvent(@PathVariable Long id) {
        this.eventService.deleteById(id);
        return "redirect:/events";
    }

    @GetMapping("/add-form")
    public String getAddEventPage(Model model) {
        List<Category> categories = this.categoryService.listAll();
        List<Location> locations = this.locationService.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("locations", locations);
        return "addEvent";
    }

    @PostMapping("/add")
    public String saveEvent(@RequestParam String name,
                            @RequestParam String description,
                            @RequestParam(name = "rating") double popularityScore,
                            @RequestParam Long category,
                            @RequestParam Long location) {
        this.eventService.save(name, description, popularityScore, category, location);
        return "redirect:/events";
    }

    @PostMapping("/search")
    public String searchEvents(@RequestParam(required = false) String queryParam,
                               @RequestParam(required = false) String ratingParam,
                               @RequestParam(required = false) String categoryParam,
                               Model model) {

        Integer rating = (ratingParam != null && !ratingParam.isEmpty()) ? Integer.parseInt(ratingParam) : 0;
        List<Event> events = new ArrayList<>();
        if (queryParam != null && !queryParam.isEmpty() && categoryParam != null &&
                !categoryParam.isEmpty() && !categoryParam.equals("Choose category")) {

            events = this.eventService.searchEventsByNameAndCategory(queryParam, categoryParam);
        } else if (queryParam != null && !queryParam.isEmpty()) {

            events = this.eventService.searchEvents(queryParam);
        } else if (categoryParam != null && !categoryParam.isEmpty()
                && !categoryParam.equals("Choose category")) {

            events = this.eventService.searchEventsByCategory(categoryParam);
        }

        model.addAttribute("events", events);
        model.addAttribute("rating", rating);

        return "searchResults";
    }
}