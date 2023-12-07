package data_access;

import entity.*;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;


public class UpcomingStrategy implements EventStrategy<List<JSONObject>> {
    @Override
    public List<JSONObject> getEvents(User user) throws IOException {

        String apiKey = "GKzgIWcoAk5rfAb5VtGpaTiqsyMeBjJP";
        String classification = "music";

        LocationFinder helper = new LocationFinder();
        List<Double> latlong = helper.locationFinder(user);

        double lat1 = latlong.get(0);
        double lat2 = latlong.get(1);
        String coordinates = lat1 + "," + lat2;
        String baseUrl = "https://app.ticketmaster.com/discovery/v2/events.json";
        String urlString = baseUrl + "?geoPoint=" + coordinates + "&radius=" + 10 + "&unit=" + "miles" +
                "&classificationName=" + classification + "&apikey=" + apiKey;

        EventsByUrl findEvents = new EventsByUrl();
        List<JSONObject> upcomingEvents = findEvents.fetchEvents(urlString, user);
        return upcomingEvents;
    }
}
//    public static void main(String[] args) {
//        try {
//            // Create a sample user
//            UserFactory userFactory = new UserModelFactory();
//            ArtistFactory artistFactory = new ArtistModelFactory();
//            Artist artist1 = artistFactory.create("Taylor Swift");
//            Artist artist2 = artistFactory.create("Selena Gomez");
//            ArrayList<Artist> arrayList = new ArrayList<>();
//            arrayList.add(artist1);
//            arrayList.add(artist2);
//            User user = userFactory.create("L1C0K1", arrayList);
//
//            // Create an instance of UpcomingStrategy
//            UpcomingStrategy upcomingStrategy = new UpcomingStrategy();
//
//            // Call the getEvents method
//            List<JSONObject> events = upcomingStrategy.getEvents(user);
//
//            // Display the event URLs using the printEventUrls method
//            printEventUrls(events);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void printEventUrls(List<JSONObject> events) {
//        for (JSONObject event : events) {
//            String url = event.getString("url");
//            System.out.println("Event URL: " + url);
//        }
//    }
//
//}