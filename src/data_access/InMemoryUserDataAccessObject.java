package data_access;

import entity.Artist;
import entity.ArtistFactory;
import entity.ArtistModelFactory;
import entity.User;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import use_case.upcoming_shows.UpcomingDataAccess;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class InMemoryUserDataAccessObject implements UpcomingDataAccess {
    private static final String API_KEY = "0e7e66d3a7b44c6a8e5d7b6c7d61f4f7";

    public static List<Double> geoPoint = new ArrayList<>();

    private final LinkedHashMap<String, String> shows = new LinkedHashMap<>();

    /**
     * @param user the user's postal code
     * @return the user's geoPoint as a list of 2 doubles, latitude and longitude.
     */
    @Override
    public List<Double> locationFinder(User user){
        String postalCode = user.getPostalCode();

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

                } else {
                    System.out.println("Error, please use a valid postal code: " + response.code() + " - " + response.message());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return geoPoint;
    }

    /**
     * @param radius FILL IN LATER
     * @param unit FILL IN LATER
     * @param classification FILL IN LATER
     * @param user the user's postal code to get the closest concerts
     * @return the user's geoPoint as a list of 2 doubles, latitude and longitude.
     */
    @Override
    public List<JSONObject> getEventsFromLatLong(int radius, String unit, String classification, User user) throws IOException {
        List<Double> latlong = locationFinder(user);
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

        urlString += "&apikey=" + "GKzgIWcoAk5rfAb5VtGpaTiqsyMeBjJP";

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
                        calculateDistance(
                                event.getJSONObject("_embedded").getJSONArray("venues").getJSONObject(0).getJSONObject("location").getDouble("latitude"),
                                event.getJSONObject("_embedded").getJSONArray("venues").getJSONObject(0).getJSONObject("location").getDouble("longitude"),
                                user)));
            }
        }

        return events;
    }

    /**
     * @param lat2 the user's latitude coordinate from their postal code
     * @param lon2 the user's longitude coordinate from their postal code
     * @param user the user's postal code to make calculations from
     * @return the length between the user's location and the concert venue
     */
    @Override
    public double calculateDistance(double lat2, double lon2, User user) {
        List<Double> latlong = locationFinder(user);
        double lat1 = latlong.get(0);
        double lon1 = latlong.get(1);
        double x = lat1 * (Math.PI / 180);
        double y = lat2 * (Math.PI / 180);
        // Equation - need to fix
        return Math.acos(Math.sin(x) * Math.sin(y) + Math.cos(x) * Math.cos(y) * Math.cos((lon1 - lon2) * (Math.PI / 180))) * 6371; // Earth radius in km
    }

    /**
     * @param event concert event from the ticket master api
     * @return a link to ticketmaster website with the closest concert where user can purchase tickets
     */
    @Override
    public String getEventUrl(JSONObject event) {
        String url = event.getString("url");
        return url;
    }

    /**
     * @param event concert event from the ticketmaster api
     * @return the name of the artist who is holding the given ticketmaster concert
     */
    @Override
    public String getArtistName(JSONObject event) {
        Artist artist = new ArtistModelFactory().create(event.getString("name"));
        return artist.getName();
    }

    /**
     * @param events concert events from ticketmaster api
     * @return a hashmap of the artist and a link for the user to buy tickets
     */
    @Override
    public LinkedHashMap<String, String> getUpcomingShows(List<JSONObject> events) {
        for (JSONObject event : events) {
            shows.put(getArtistName(event), getEventUrl(event));
        }
        return shows;
    }

    /**
     * @param shows a hashmap of the artist and a link for the user to buy tickets
     * @return a string listing the artist and the link to their upcoming concert
     */
    @Override
    public String formatShows(LinkedHashMap<String, String> shows) {

        StringBuilder formattedConcerts = new StringBuilder();

        ArrayList<String> concerts = new ArrayList<>();

        for (Map.Entry<String, String> entry : shows.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            concerts.add(key + ": " + value);
        }

        for (int i = 0; i < 5; i++){
            formattedConcerts.append(concerts.get(i));
            formattedConcerts.append("\n");
        }
        return formattedConcerts.toString();
    }

    /**
     * @param postalCode the user's postal code
     * @return whether the coordinates of the user's postal code exists
     */
    @Override
    public boolean existsInCoords(String postalCode) {
        return !geoPoint.isEmpty();
    }



}
