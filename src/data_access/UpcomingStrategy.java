package data_access;

import entity.User;
import entity.UserFactory;
import entity.UserModelFactory;
import org.json.JSONObject;
import use_case.EventStrategy;
import data_access.EventsByUrl;
import data_access.LocationFinder;

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
        String strLatlong = lat1 + "," + lat2;
        String baseUrl = "https://app.ticketmaster.com/discovery/v2/events.json";
        String urlString = baseUrl + "?geoPoint=" + strLatlong + "&classificationName=" + classification + "&apikey=" + apiKey;

        EventsByUrl findEvents = new EventsByUrl();
        List<JSONObject> upcomingEvents = findEvents.fetchEvents(urlString, user);
        return upcomingEvents;
    }


}





