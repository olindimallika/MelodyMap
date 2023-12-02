package data_access;

import entity.*;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import use_case.EventStrategy;
import data_access.EventsByUrl;

import java.io.IOException;
import java.util.*;

public class ArtistStrategy implements EventStrategy<List<List<JSONObject>>> {

    // returns a list of lists of events, where every inner list is are a bunch of events for one specific user
    //  Doesn't run right now as there is an error in the LocationFinder

    @Override
    public List<List<JSONObject>> getEvents(User user) throws IOException {

        //LocationFinder helper = new LocationFinder();
        //        List<Double> latlong = helper.locationFinder(user);
        //
        //        double lat1 = latlong.get(0);
        //        double lat2 = latlong.get(1);
        //        String strLatlong = lat1 + "," + lat2;

        String apiKey = "GKzgIWcoAk5rfAb5VtGpaTiqsyMeBjJP";
        String classification = "music";

        LocationFinder helper = new LocationFinder();
//        List<Double> latlong = helper.locationFinder(user);

        double lat1 = helper.locationFinder(user).get(0);
        double lat2 = helper.locationFinder(user).get(1);
        String coordinates = lat1 + "," + lat2;

        List<Artist> favArtists = user.getFavouriteArtist();
        List<List<JSONObject>> favArtistsEvents = new ArrayList<>();

        for (Artist artist : favArtists) {

            String artistName = artist.getName();
            String baseUrl = "https://app.ticketmaster.com/discovery/v2/events.json";
            String urlString = baseUrl + "?geoPoint=" + coordinates + "&classificationName=" + classification
                    + "&keyword=" + artistName + "&apikey=" + apiKey;
//            List<JSONObject> events = fetchEvents(urlString, latlong);
            ////            favArtistEvents.add(events);

            EventsByUrl findEvents = new EventsByUrl();
            List<JSONObject> events = findEvents.fetchEvents(urlString, user);
            favArtistsEvents.add(events);
        }

        return favArtistsEvents;

    }

    public static void main(String[] args) {
        // Checking to see if i get an output


        try {
            // Create a sample user
            UserFactory userFactory = new UserModelFactory();
            ArtistFactory artistFactory = new ArtistModelFactory();
            Artist artist1 = artistFactory.create("Taylor Swift");
            Artist artist2 = artistFactory.create("Olivia Rodrigo");
            ArrayList<Artist> artists = new ArrayList<>();
            artists.add(artist1);
            artists.add(artist2);
            String postal = "L1C0K1";
            User user = userFactory.create(postal, artists);

            // Create an instance of ArtistStrategy
            ArtistStrategy artistStrategy = new ArtistStrategy();

            // Call the getEvents method
            List<List<JSONObject>> eventsList = artistStrategy.getEvents(user);

            // Display or process the fetched events
            printArtistEvents(eventsList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printArtistEvents(List<List<JSONObject>> eventsList) {
        for (List<JSONObject> events : eventsList) {
            for (JSONObject event : events) {
                String url = event.getString("url");
                System.out.println("Event URL: " + url);
            }
            System.out.println("----"); // Separate events from different artists
        }
    }
}

