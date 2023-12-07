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
            String urlString = baseUrl + "?geoPoint=" + coordinates + "&radius=" + 50 + "&classificationName=" + classification
                    + "&keyword=" + artistName + "&apikey=" + apiKey;
//            List<JSONObject> events = fetchEvents(urlString, latlong);
            ////            favArtistEvents.add(events);

            EventsByUrl findEvents = new EventsByUrl();
            List<JSONObject> events = findEvents.fetchEvents(urlString, user);
            favArtistsEvents.add(events);
        }

        return favArtistsEvents;

    }
}

