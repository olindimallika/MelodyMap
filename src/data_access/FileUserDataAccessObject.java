package data_access;

import entity.Artist;
import org.json.JSONArray;
import use_case.upcoming_shows.UpcomingDataAccess;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class FileUserDataAccessObject implements UpcomingDataAccess {
    private static final double r2d = 180.0D / 3.141592653589793D;
    private static final double d2r = 3.141592653589793D / 180.0D;
    private static final double d2km = 111189.57696D * r2d;

    private final Map<String, String> shows = new HashMap<>();

    private static final String API_KEY = "0e7e66d3a7b44c6a8e5d7b6c7d61f4f7";

    private final String apiKey;

    public FileUserDataAccessObject(String apiKey) {
        this.apiKey = apiKey;
    }

    public static List<Double> geoPoint = new ArrayList<>();

    public static List<Double> locationFinder(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Canadian postal code (capital, no space): ");
        String postalCode = scanner.nextLine();

        try {
            String url = "https://api.opencagedata.com/geocode/v1/json?key=" + API_KEY + "&q=" + postalCode + "&countrycode=CA";
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();
                    JSONObject json = new JSONObject(responseBody);
                    JSONObject location = json.getJSONArray("results").getJSONObject(0).getJSONObject("geometry");

                    double latitude = location.getDouble("lat");
                    double longitude = location.getDouble("lng");

                    geoPoint.add(latitude);
                    geoPoint.add(longitude);
                    System.out.println(geoPoint);

                } else {
                    System.out.println("Error, please use a valid postal code: " + response.code() + " - " + response.message());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(geoPoint);
        return geoPoint;
    }

    public List<JSONObject> getEventsFromLatLong(int radius, String unit, String classification) throws IOException {
        List<Double> latlong = locationFinder();
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



}
