package data_access;

import entity.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class EventsByUrl {
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

                // Sort events based on distance
//                events.sort(Comparator.comparingDouble(event ->
//                        calculateDistance(
//                                event.getJSONObject("_embedded").getJSONArray("venues").getJSONObject(0).getJSONObject("location").getDouble("latitude"),
//                                event.getJSONObject("_embedded").getJSONArray("venues").getJSONObject(0).getJSONObject("location").getDouble("longitude"),
//                                user)));
            }
        }

        return events;
    }

//    private double calculateDistance(double lat2, double lon2, User user) {
//        // user have a get.coordinates(); // we will store the users coordinates once we get it
//        latLong = user.getPostalCode();
//        double lat1 = latlong.get(0);
//        double lon1 = latlong.get(1);
//        double x = lat1 * (Math.PI / 180);
//        double y = lat2 * (Math.PI / 180);
//        // Equation - need to fix
//        return Math.acos(Math.sin(x) * Math.sin(y) + Math.cos(x) * Math.cos(y) * Math.cos((lon1 - lon2) * (Math.PI / 180))) * 6371; // Earth radius in km
//    }

}
