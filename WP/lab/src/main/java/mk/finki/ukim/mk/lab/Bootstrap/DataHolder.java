package mk.finki.ukim.mk.lab.Bootstrap;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.Category;
import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.Location;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.model.enumerations.Role;
import mk.finki.ukim.mk.lab.repository.jpa.CategoryRepository;
import mk.finki.ukim.mk.lab.repository.jpa.EventRepository;
import mk.finki.ukim.mk.lab.repository.jpa.LocationRepository;
import mk.finki.ukim.mk.lab.repository.jpa.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Event> events = null;
    public static List<Category> categories = null;
    public static List<Location> locations = null;
    public static List<User> users = null;

    private final CategoryRepository categoryRepository;
    private final LocationRepository locationRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public DataHolder(CategoryRepository categoryRepository, LocationRepository locationRepository, EventRepository eventRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.categoryRepository = categoryRepository;
        this.locationRepository = locationRepository;
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @PostConstruct
    public void init() {
        categories = new ArrayList<>();
        if (this.categoryRepository.count() == 0) {
            categories.add(new Category("Expo"));
            categories.add(new Category("Festival"));
            categories.add(new Category("Competition"));
            categories.add(new Category("Other"));
            this.categoryRepository.saveAll(categories);
        } else {
            categories = this.categoryRepository.findAll();
        }

        locations = new ArrayList<>();
        if (locationRepository.count() == 0) {
            locations.add(new Location("Tech Haven",
                    "123 Byte Boulevard, Silicon City, CA 94010",
                    "150",
                    "Tech Haven is a spacious, high-tech venue designed for hackathons and coding workshops."));
            locations.add(new Location("Code & Brew",
                    "456 Java Lane, Maple-wood, NJ 07040",
                    "50",
                    "A cozy, casual space in the heart of Maple-wood, Code & Brew combines a café vibe with tech amenities."));
            locations.add(new Location("Festival Grounds",
                    "1001 Celebration Drive, Orlando, FL 32801",
                    "5000",
                    "Festival Grounds is an expansive outdoor venue."));
            locations.add(new Location("Competitor’s Coliseum",
                    "555 Victory Lane, Las Vegas, NV 89109",
                    "7000",
                    "Competitor’s Coliseum is a massive, arena-style venue in Las Vegas."));
            locations.add(new Location("Expo Center Park",
                    "300 Expo Boulevard, Austin, TX 78702",
                    "10000",
                    "Expo Center Park is a versatile event space with both indoor and outdoor areas."));
            this.locationRepository.saveAll(locations);
        } else {
            locations = this.locationRepository.findAll();
        }

        events = new ArrayList<>();
        if (eventRepository.count() == 0) {
            events.add(new Event("Tech Innovation Expo 2024",
                    "A global gathering of tech enthusiasts, innovators, and entrepreneurs, showcasing the latest advancements in AI, blockchain, IoT, and other cutting-edge technologies.",
                    90,
                    categories.get(0),
                    locations.get(0),
                    100));
            events.add(new Event("Music Festival: Summer Sounds",
                    " A vibrant outdoor music festival featuring multiple genres, live performances, food trucks, and art installations. Attracts artists from around the world.",
                    85,
                    categories.get(1),
                    locations.get(2),
                    1000));
            events.add(new Event("Annual Charity Run",
                    "A charity marathon supporting local communities. Participants can join a 5K, 10K, or half-marathon race, with all proceeds going to charitable causes.",
                    70,
                    categories.get(3),
                    locations.get(1),
                    50));
            events.add(new Event("World Gaming Championship",
                    "A massive international competition where professional gamers compete in popular games for the title of world champion and significant prize money.",
                    95,
                    categories.get(2),
                    locations.get(3),
                    200));
            events.add(new Event("International Book Fair",
                    "A literary event where publishers, authors, and readers come together to celebrate literature. Includes author signings, readings, and panel discussions.",
                    80,
                    categories.get(3),
                    locations.get(4),
                    70));
            events.add(new Event("Food Truck Festival",
                    "A community event featuring diverse food trucks offering gourmet options, street foods, and unique culinary creations. Includes live music and family activities.",
                    75,
                    categories.get(1),
                    locations.get(2),
                    500));
            events.add(new Event("Startup Pitch Competition",
                    "Entrepreneurs pitch their innovative ideas to a panel of investors for funding opportunities and networking. Focused on startups in tech, healthcare, and green energy.",
                    88,
                    categories.get(2),
                    locations.get(4),
                    150));
            events.add(new Event("Comic and Anime Convention",
                    "A fan-based event featuring cosplay, comic artists, anime screenings, and panels with voice actors. Known for its vibrant community and interactive events.",
                    92,
                    categories.get(3),
                    locations.get(4),
                    60));
            events.add(new Event("Winter Wonderland Market",
                    "A seasonal holiday market offering unique handmade crafts, food stalls, and holiday entertainment. Popular for families and tourists alike.",
                    77,
                    categories.get(3),
                    locations.get(4),
                    750));
            events.add(new Event("Eco-Friendly Home Expo",
                    "Focused on sustainable living, this event showcases eco-friendly home solutions, green energy products, and sustainable building practices.",
                    65,
                    categories.get(0),
                    locations.get(4),
                    400));
            this.eventRepository.saveAll(events);
        }

        users = new ArrayList<>();
        if (userRepository.count() == 0) {
            users.add(new User("nikola.serafimov", passwordEncoder.encode("ns"), "Nikola", "Serafimov", "Oblesevo", Role.ROLE_USER));
            users.add(new User("branko.milenkov", passwordEncoder.encode("bm"), "Branko", "Milenkov", "Radovish", Role.ROLE_USER));
            users.add(new User("admin", passwordEncoder.encode("admin"), "admin", "admin", "-", Role.ROLE_ADMIN));
            this.userRepository.saveAll(users);
        }
    }
}
