package use_case.upcoming_shows;

import entity.User;
import org.json.JSONObject;

import java.util.*;

public interface UpcomingDataAccess {

    List<Double> locationFinder(User user);

    List<JSONObject> getEventsFromLatLong(int radius, String unit, String classification, User user) throws Exception;

    double calculateDistance(double lat2, double lon2, User user);

    String getEventUrl(JSONObject event);

    String getArtistName(JSONObject event);

    LinkedHashMap<String, String> getUpcomingShows(List<JSONObject> events);
    boolean existsInCoords(String postalCode);
}
