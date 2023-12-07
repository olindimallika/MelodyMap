package data_access;

import entity.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * The EventsByUrl class fetches events from a specified URL, processes the JSON content,
 * and returns a list of JSONObject representing events. It also has a method that calculates
 * the distance between the event venues and input user's location.
 */

public class EventsByUrl {

    /**
     * @param urlString The URL from which events are fetched.
     * @param user The user for whom events are being fetched.
     * @return A list of JSONObject representing events.
     * @throws IOException If an I/O error occurs during the process of fetching events.
     */

    public List<JSONObject> fetchEvents(String urlString, User user) throws IOException {
        URL url = new URL(urlString);
        Scanner scanner = new Scanner(url.openStream());
        StringBuilder jsonContent = new StringBuilder();

        while (scanner.hasNext()) {
            jsonContent.append(scanner.nextLine());
        }

        scanner.close();

        List<JSONObject> events = new ArrayList<>();
        JSONObject obj = new JSONObject(jsonContent.toString());

        if (obj.has("_embedded") && obj.get("_embedded") instanceof JSONObject) {
            JSONObject embedded = obj.getJSONObject("_embedded");

            if (embedded.has("events")) {
                JSONArray eventsArray = embedded.getJSONArray("events");

                for (int j = 0; j < eventsArray.length(); j++) {
                    events.add(eventsArray.getJSONObject(j));
                }

                events.sort(Comparator.comparingDouble(event ->
                        calculateDistance(
                                event.getJSONObject("_embedded").getJSONArray("venues").getJSONObject(0).getJSONObject("location").getDouble("latitude"),
                                event.getJSONObject("_embedded").getJSONArray("venues").getJSONObject(0).getJSONObject("location").getDouble("longitude"),
                                user)));
            }
        }

        return events;
    }

    /**
     * Calculates the distance between two geographical points (latitude and longitude)
     * using the Haversine formula.
     *
     * @param lat2 The latitude of the event venue.
     * @param lon2 The longitude of the event venue.
     * @param user The user's location for whom the distance is calculated.
     * @return The distance between the event venue and the user's location in kilometers.
     */

    private double calculateDistance(double lat2, double lon2, User user) {

        LocationFinder helper = new LocationFinder();
        double lat1 = helper.locationFinder(user).get(0);
        double lon1 = helper.locationFinder(user).get(1);
        double x = lat1 * (Math.PI / 180);
        double y = lat2 * (Math.PI / 180);
        return Math.acos(Math.sin(x) * Math.sin(y) + Math.cos(x) * Math.cos(y) *
                Math.cos((lon1 - lon2) * (Math.PI / 180))) * 6371;
    }
}
