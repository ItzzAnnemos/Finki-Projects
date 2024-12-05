package mk.finki.ukim.mk.lab.service.Impl;

import mk.finki.ukim.mk.lab.model.EventBooking;
import mk.finki.ukim.mk.lab.repository.inMemory.InMemEventBookingRepository;
import mk.finki.ukim.mk.lab.repository.inMemory.InMemEventRepository;
import mk.finki.ukim.mk.lab.repository.jpa.EventBookingRepository;
import mk.finki.ukim.mk.lab.repository.jpa.EventRepository;
import mk.finki.ukim.mk.lab.service.EventBookingService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EventBookingServiceImpl implements EventBookingService {
    private final EventBookingRepository eventBookingRepository;
    private final EventRepository eventRepository;

    public EventBookingServiceImpl(EventBookingRepository eventBookingRepository, EventRepository eventRepository) {
        this.eventBookingRepository = eventBookingRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public Optional<EventBooking> placeBooking(String eventName, String attendeeName, String attendeeAddress, int numberOfTickets) {
        EventBooking eventBooking = new EventBooking(eventName, attendeeName, attendeeAddress, (long) numberOfTickets);
        eventBookingRepository.save(eventBooking);
        return Optional.of(eventBooking);
    }
}
