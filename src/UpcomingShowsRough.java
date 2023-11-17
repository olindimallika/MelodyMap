import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class UpcomingShowsRough extends LocationFinder {
    private static final double r2d = 180.0D / 3.141592653589793D;
    private static final double d2r = 3.141592653589793D / 180.0D;
    private static final double d2km = 111189.57696D * r2d;
    static LocationFinder lf = new LocationFinder();
    public static List<Double> latlong = lf.latlongy();
    private final String apiKey;

    public UpcomingShowsRough(String apiKey) {
        this.apiKey = apiKey;
    }

    public List<JSONObject> getEventsFromLatLong(List<Double> latlong) throws IOException {
        return getEventsFromLatLong(latlong, 0, null, "music");
    }
    //...
    public List<JSONObject> getEventsFromLatLong(List<Double> latlong, int radius, String unit, String classification) throws IOException {
        double lat1 = latlong.get(0);
        double lat2 = latlong.get(1);
        String strLatlong = Double.toString(lat1) + "," + Double.toString(lat2);

        String baseUrl = "https://app.ticketmaster.com/discovery/v2/events.json";
        String urlString = baseUrl + "?geoPoint=" + strLatlong;

        // Append radius, unit, and classification if provided
        if (radius > 0 && unit != null) {
            urlString += "&radius=" + radius + "&unit=" + unit;
        }

        if (classification != null) {
            urlString += "&classificationName=" + classification;
        }

        urlString += "&apikey=" + apiKey;

        URL url = new URL(urlString);
        Scanner scanner = new Scanner(url.openStream());
        StringBuilder jsonContent = new StringBuilder();

        while (scanner.hasNext()) {
            jsonContent.append(scanner.nextLine());
        }

        scanner.close();
        // Parse the JSON response and return a list of events
        List<JSONObject> events = new ArrayList<>();
        JSONObject obj = new JSONObject(jsonContent.toString());

        // Check if _embedded is a JSONArray
        if (obj.has("_embedded") && obj.get("_embedded") instanceof JSONObject) {
            JSONObject embedded = obj.getJSONObject("_embedded");

            if (embedded.has("events")) {
                JSONArray eventsArray = embedded.getJSONArray("events");

                for (int i = 0; i < eventsArray.length(); i++) {
                    events.add(eventsArray.getJSONObject(i));
                }

                // Sort events based on distance
                events.sort(Comparator.comparingDouble(event ->
                        calculateDistance(latlong,
                                event.getJSONObject("_embedded").getJSONArray("venues").getJSONObject(0).getJSONObject("location").getDouble("latitude"),
                                event.getJSONObject("_embedded").getJSONArray("venues").getJSONObject(0).getJSONObject("location").getDouble("longitude"))));
            }
        }

        return events;
    }

    // Finds length between users location and the venue to sort it so closest venue is shown
    public double calculateDistance(List<Double> latlong, double lat2, double lon2) {
        double lat1 = latlong.get(0);
        double lon1 = latlong.get(1);
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

    public static void main(String[] args) {
        // Replace 'YOUR_API_KEY' with your actual Ticketmaster API key.
        String apiKey = "GKzgIWcoAk5rfAb5VtGpaTiqsyMeBjJP";

        TicketmasterAPI ticketmasterAPI = new TicketmasterAPI(apiKey);

        try {
            System.out.println("\n");
            int radius = 10;
            String unit = "miles";
            // Example: Get only music events based on geoPoint without specifying radius and unit
            List<JSONObject> eventL = ticketmasterAPI.getEventsFromLatLong(latlong, radius, unit, "music");
            ticketmasterAPI.printEventUrls(eventL);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
