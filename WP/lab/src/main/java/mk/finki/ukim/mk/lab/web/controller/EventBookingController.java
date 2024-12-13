package mk.finki.ukim.mk.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.EventBooking;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.service.EventBookingService;
import mk.finki.ukim.mk.lab.service.EventService;
import mk.finki.ukim.mk.lab.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping()
public class EventBookingController {
    private final EventBookingService eventBookingService;
    private final EventService eventService;
    private final UserService userService;

    public EventBookingController(EventBookingService eventBookingService, EventService eventService, UserService userService) {
        this.eventBookingService = eventBookingService;
        this.eventService = eventService;
        this.userService = userService;
    }

    @GetMapping("/bookings")
    private String getEventBookingPage(HttpServletRequest request, Model model) {
        String username = request.getRemoteUser();
        List<EventBooking> bookings = this.eventBookingService.findByUser(username);
        model.addAttribute("bookings", bookings);
        return "userBookings";
    }

    @PostMapping("/bookings/cancel/{id}")
    public String cancelBooking(@PathVariable("id") Long bookingId) {
        eventBookingService.cancelBooking(bookingId);
        return "redirect:/bookings";
    }

    @PostMapping("/eventBooking")
    public String bookEvent(@RequestParam String eventName,
                            @RequestParam int numTickets,
                            Model model,
                            HttpServletRequest request) {
        Event event = eventService.findByName(eventName).get();
        if (event.getNumTickets() > numTickets) {
            String username = request.getRemoteUser();
            User user = userService.findByUsername(username);
            Optional<EventBooking> booking = eventBookingService.placeBooking(user, eventName, user.getName(), user.getAddress(), numTickets);
            model.addAttribute("name", user.getName());
            model.addAttribute("event", eventName);
            model.addAttribute("address", user.getAddress());
            model.addAttribute("numTickets", numTickets);
            model.addAttribute("bookingDate", booking.get().getBookingDate());
            eventService.reserveCard(event.getId(), numTickets);
            return "bookingConfirmation";
        } else {
            return "redirect:/events?error=Not enough available tickets!";
        }
    }

}
