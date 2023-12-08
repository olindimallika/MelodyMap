package data_access;

import entity.*;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

/**
 * The UpcomingStrategy class implements the EventStrategy interface and provides a strategy for
 * retrieving a list of upcoming events based on a specified user's location.
 */

public class UpcomingStrategy implements EventStrategy<List<JSONObject>> {

    /**
     * Retrieves a list of upcoming events based on the specified user's location using the Ticketmaster API.
     *
     * @param user The user for whom upcoming events are being retrieved.
     * @return A list of JSONObject representing upcoming events.
     * @throws IOException If an I/O error occurs during the process of fetching upcoming events.
     */
    @Override
    public List<JSONObject> getEvents(User user) throws IOException {

        String apiKey = "Add your key";
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
        return findEvents.fetchEvents(urlString, user);
    }
}
