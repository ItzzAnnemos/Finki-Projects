package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.service.EventBookingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping()
public class EventBookingController {
    private final EventBookingService eventBookingService;

    public EventBookingController(EventBookingService eventBookingService) {
        this.eventBookingService = eventBookingService;
    }

    @PostMapping("/eventBooking")
    public String bookEvent(@RequestParam String attendeeName,
                            @RequestParam String attendeeAddress,
                            @RequestParam String eventName,
                            @RequestParam int numTickets,
                            Model model) {
        this.eventBookingService.placeBooking(eventName, attendeeName, attendeeAddress, numTickets);
        model.addAttribute("name", attendeeName);
        model.addAttribute("event", eventName);
        model.addAttribute("address", attendeeAddress);
        model.addAttribute("numTickets", numTickets);
        return "bookingConfirmation";
    }

}
