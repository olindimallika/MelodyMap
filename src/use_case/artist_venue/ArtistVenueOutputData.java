package use_case.artist_venue;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class ArtistVenueOutputData {

    private final String upcomingConcerts;

    public ArtistVenueOutputData(String upcomingConcerts){

        this.upcomingConcerts = upcomingConcerts;
    }

    public String getUpcomingConcerts() {

        return upcomingConcerts;
    }

}


// package use_case.artist_venue;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class ArtistVenueOutputData {
//
//    private Map<String, List<Event>> artistVenues = new HashMap<>();
//
//    // Default constructor
//    public ArtistVenueOutputData() {
//        // Constructor can be empty or include any initialization logic you need
//    }
//
//    // Getter for the artistVenues map
//    public Map<String, List<Event>> getArtistVenues() {
//        return artistVenues;
//    }
//
//    // You can add methods to manipulate the map as needed
//    public void addEvent(String artistName, Event event) {
//        artistVenues.computeIfAbsent(artistName, k -> new ArrayList<>()).add(event);
//    }
//
//    // You can add more methods as needed
//
//    // Inner class representing an Event
//    public static class Event {
//        private String eventName;
//
//        public Event(String eventName) {
//            this.eventName = eventName;
//        }
//
//        public String getEventName() {
//            return eventName;
//        }
//    }
//}