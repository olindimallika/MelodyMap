package data_access;

import entity.*;
import org.json.JSONObject;

import java.io.IOException;
import java.util.*;


/**
 * The ArtistStrategy class implements the EventStrategy interface and provides a strategy for
 * retrieving a list of events for each favorite artist of a specified user.
 */
public class ArtistStrategy implements EventStrategy<List<List<JSONObject>>> {

    /**
     * Retrieves a list of events for each favorite artist of the specified user using the Ticketmaster API.
     *
     * @param user The user for whom events for favorite artists are being retrieved.
     * @return A list of lists of JSONObject, where the inner lists represent events for a specific artist.
     * @throws IOException If an I/O error occurs during the process of fetching events.
     */

    @Override
    public List<List<JSONObject>> getEvents(User user) throws IOException {

        String apiKey = "GKzgIWcoAk5rfAb5VtGpaTiqsyMeBjJP";
        String classification = "music";

        LocationFinder helper = new LocationFinder();

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

            EventsByUrl findEvents = new EventsByUrl();
            List<JSONObject> events = findEvents.fetchEvents(urlString, user);
            favArtistsEvents.add(events);
        }

        return favArtistsEvents;

    }
}
