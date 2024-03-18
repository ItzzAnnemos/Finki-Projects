package mk.ukim.finki.NP.ZadaciZaVezbanje2.Airports;

import java.util.*;
import java.util.stream.Collectors;

public class Airports {
    private List<Airport> airports;
    private Map<Airport, List<Flight>> flights;
    private Map<String, List<Flight>> flightsTo;

    public Airports() {
        this.airports = new ArrayList<>();
        this.flights = new HashMap<>();
        this.flightsTo = new HashMap<>();
    }

    public void addAirport(String name, String country, String code, int passengers) {
        airports.add(new Airport(name, country, code, passengers));
    }

    public void addFlights(String from, String to, int time, int duration) {
        for (Airport airport : airports) {
            if (airport.getCode().equals(from)) {
                flights.putIfAbsent(airport, new ArrayList<>());
                flights.get(airport).add(new Flight(from, to, time, duration));
                flightsTo.putIfAbsent(to, new ArrayList<>());
                flightsTo.get(to).add(new Flight(from, to, time, duration));
                break;
            }
        }
    }

    public void showFlightsFromAirport(String code) {
        Comparator<Flight> comparator = Comparator.comparing(Flight::getTo).
                thenComparing(Flight::getStartingTime).thenComparing(Flight::getEndingTime);

        for (Airport airport : airports) {
            if (airport.getCode().equals(code)) {
                System.out.println(airport);
                List<Flight> tmp = flights.get(airport);
                tmp.sort(comparator);
                for (int i = 0; i < tmp.size(); i++) {
                    System.out.printf("%d. %s\n", i + 1, tmp.get(i));
                }
                break;
            }
        }
    }

    public void showDirectFlightsFromTo(String from, String to) {
        Airport tmp = null;
        for (Airport airport : airports) {
            if (airport.getCode().equals(from)) {
                tmp = airport;
                break;
            }
        }

        List<Flight> list = flights.get(tmp).stream()
                .filter(flight -> flight.getTo().equals(to))
                .collect(Collectors.toList());

        if (list.isEmpty()) {
            System.out.println("No flights from " + from + " to " + to);
        } else {
            Comparator<Flight> comparator = Comparator.comparing(Flight::getStartingTime).
                    thenComparing(Flight::getEndingTime);
            list.sort(comparator);
            list.forEach(System.out::println);
        }
    }

    public void showDirectFlightsTo(String to) {
        List<Flight> list = flightsTo.get(to);
        list.sort(Comparator.comparing(Flight::getStartingTime).thenComparing(Flight::getEndingTime));
        list.forEach(System.out::println);
    }
}
