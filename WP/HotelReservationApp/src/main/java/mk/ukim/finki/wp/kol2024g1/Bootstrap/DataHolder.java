package mk.ukim.finki.wp.kol2024g1.Bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.kol2024g1.model.Hotel;
import mk.ukim.finki.wp.kol2024g1.model.Reservation;
import mk.ukim.finki.wp.kol2024g1.model.RoomType;
import mk.ukim.finki.wp.kol2024g1.repository.HotelRepository;
import mk.ukim.finki.wp.kol2024g1.repository.ReservationRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DataHolder {
    public static List<Reservation> reservations;
    public static List<Hotel> hotels;

    private final ReservationRepository reservationRepository;
    private final HotelRepository hotelRepository;

    public DataHolder(ReservationRepository reservationRepository, HotelRepository hotelRepository) {
        this.reservationRepository = reservationRepository;
        this.hotelRepository = hotelRepository;
    }

    public void init() {
        hotels = new ArrayList<>();
        if (this.hotelRepository.count() == 0) {
            hotels.add(new Hotel("Marriot"));
            hotels.add(new Hotel("Hilton"));
            hotelRepository.saveAll(hotels);
        } else {
            hotels = hotelRepository.findAll();
        }

        reservations = new ArrayList<>();
        if (this.reservationRepository.count() == 0) {
            reservations.add(new Reservation("Nikola", LocalDate.now(), 7, RoomType.SINGLE, hotels.get(0)));
            reservations.add(new Reservation("Branko", LocalDate.now(), 10, RoomType.DOUBLE, hotels.get(0)));
            reservations.add(new Reservation("Dushan", LocalDate.now(), 3, RoomType.SINGLE, hotels.get(1)));
            reservationRepository.saveAll(reservations);
        } else {
            reservations = reservationRepository.findAll();
        }
    }
}
