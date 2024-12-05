package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.EventBooking;
import mk.finki.ukim.mk.lab.service.EventBookingService;
import mk.finki.ukim.mk.lab.service.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping()
public class EventBookingController {
    private final EventBookingService eventBookingService;
    private final EventService eventService;

    public EventBookingController(EventBookingService eventBookingService, EventService eventService) {
        this.eventBookingService = eventBookingService;
        this.eventService = eventService;
    }

    @PostMapping("/eventBooking")
    public String bookEvent(@RequestParam String attendeeName,
                            @RequestParam String attendeeAddress,
                            @RequestParam String eventName,
                            @RequestParam int numTickets,
                            Model model) {
        Event event = eventService.findByName(eventName).get();
        if (event.getNumTickets() > numTickets) {
            model.addAttribute("name", attendeeName);
            model.addAttribute("event", eventName);
            model.addAttribute("address", attendeeAddress);
            model.addAttribute("numTickets", numTickets);
            Optional<EventBooking> booking = eventBookingService.placeBooking(eventName, attendeeName, attendeeAddress, numTickets);
            eventService.reserveCard(event.getId(), numTickets);
            return "bookingConfirmation";
        } else {
            return "redirect:/events?error=Not enough available tickets!";
        }
    }

}
