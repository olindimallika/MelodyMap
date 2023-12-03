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
import data_access.LocationFinder;

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

//                 Sort events based on distance
                events.sort(Comparator.comparingDouble(event ->
                        calculateDistance(
                                event.getJSONObject("_embedded").getJSONArray("venues").getJSONObject(0).getJSONObject("location").getDouble("latitude"),
                                event.getJSONObject("_embedded").getJSONArray("venues").getJSONObject(0).getJSONObject("location").getDouble("longitude"),
                                user)));
            }
        }

        return events;
    }

    private double calculateDistance(double lat2, double lon2, User user) {
        // user have a get.coordinates(); // we will store the users coordinates once we get it

        LocationFinder helper = new LocationFinder();
        // call location finder
        double lat1 = helper.locationFinder(user).get(0);
        double lon1 = helper.locationFinder(user).get(1);
        double x = lat1 * (Math.PI / 180);
        double y = lat2 * (Math.PI / 180);
        // Equation - need to fix
        return Math.acos(Math.sin(x) * Math.sin(y) + Math.cos(x) * Math.cos(y) * Math.cos((lon1 - lon2) * (Math.PI / 180))) * 6371; // Earth radius in km
    }

    public void printEventUrls(List<JSONObject> events) {
        for (JSONObject event : events) {
            String url = event.getString("url");
            System.out.println("Event URL: " + url);
        }
    }
}

//    public static void main(String[] args) {
//        try {
//            // Sample user and coordinates
//
//            UserFactory userFactory = new UserModelFactory();
//            ArtistFactory artistFactory = new ArtistModelFactory();
//            Artist artist1 = artistFactory.create("Taylor Swift");
//
//            ArrayList<Artist> artist = new ArrayList<>();
//            artist.add(artist1);
//
//
//            User user = userFactory.create("l1p0e4", artist);
//            LocationFinder helper = new LocationFinder();
//            List<Double> latlong = helper.locationFinder(user);
//
////            List<Double> latlong = List.of(37.7749, -122.4194);
//
//            // Example URL with parameters
//            double lat1 = latlong.get(0);
//            double lat2 = latlong.get(1);
//            String strLatlong = Double.toString(lat1) + "," + Double.toString(lat2);
////            String artistName = "Taylor Swift";
//
//            String baseUrl = "https://app.ticketmaster.com/discovery/v2/events.json";
//            String urlString = baseUrl + "?geoPoint=" + strLatlong + "&classificationName=" + "music" + "&apikey=" + "GKzgIWcoAk5rfAb5VtGpaTiqsyMeBjJP";
//            // Create an instance of EventsByUrl
//            EventsByUrl eventsByUrl = new EventsByUrl();
//
//            // Call fetchEvents method
//            List<JSONObject> events = eventsByUrl.fetchEvents(urlString, user);
//
//            // Display the fetched events
//            eventsByUrl.printEventUrls(events);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}

//+ "&keyword=" + artistName

