package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Event;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EventRepository {
    private final List<Event> eventList;

    public EventRepository() {
        eventList = new ArrayList<>();
        eventList.add(new Event("Tech Innovation Expo 2024",
                "A global gathering of tech enthusiasts, innovators, and entrepreneurs, showcasing the latest advancements in AI, blockchain, IoT, and other cutting-edge technologies.",
                90));
        eventList.add(new Event("Music Festival: Summer Sounds",
                " A vibrant outdoor music festival featuring multiple genres, live performances, food trucks, and art installations. Attracts artists from around the world.",
                85));
        eventList.add(new Event("Annual Charity Run",
                "A charity marathon supporting local communities. Participants can join a 5K, 10K, or half-marathon race, with all proceeds going to charitable causes.",
                70));
        eventList.add(new Event("World Gaming Championship",
                "A massive international competition where professional gamers compete in popular games for the title of world champion and significant prize money.",
                95));
        eventList.add(new Event("International Book Fair",
                "A literary event where publishers, authors, and readers come together to celebrate literature. Includes author signings, readings, and panel discussions.",
                80));
        eventList.add(new Event("Food Truck Festival",
                "A community event featuring diverse food trucks offering gourmet options, street foods, and unique culinary creations. Includes live music and family activities.",
                75));
        eventList.add(new Event("Startup Pitch Competition",
                "Entrepreneurs pitch their innovative ideas to a panel of investors for funding opportunities and networking. Focused on startups in tech, healthcare, and green energy.",
                88));
        eventList.add(new Event("Comic and Anime Convention",
                "A fan-based event featuring cosplay, comic artists, anime screenings, and panels with voice actors. Known for its vibrant community and interactive events.",
                92));
        eventList.add(new Event("Winter Wonderland Market",
                "A seasonal holiday market offering unique handmade crafts, food stalls, and holiday entertainment. Popular for families and tourists alike.",
                77));
        eventList.add(new Event("Eco-Friendly Home Expo",
                "Focused on sustainable living, this event showcases eco-friendly home solutions, green energy products, and sustainable building practices.",
                65));

    }

    public List<Event> findAll() {
        return eventList;
    }

    public List<Event> searchEvents(String text) {
        List<Event> newList = new ArrayList<>();
        eventList.forEach(event -> {
            if (event.getName().contains(text) || event.getDescription().contains(text))
                newList.add(event);
        });

        return newList;
    }
}
