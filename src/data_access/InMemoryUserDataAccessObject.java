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
import use_case.notify_user_tour.NotifyDataAccess;
import use_case.show_concerts.ShowConcertsDataAccess;
import use_case.upcoming_shows.UpcomingDataAccess;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class InMemoryUserDataAccessObject implements UpcomingDataAccess, NotifyDataAccess, ShowConcertsDataAccess {
    private final LinkedHashMap<String, String> shows = new LinkedHashMap<>();

    private static final String locationFinderApiKey = "f4802c41d44f4bf0a66c3bc96ff4c0de";

    private static final String ticketmasterApiKey = "uxoAAPe38AqJZwxwxFNDw74mgWMdpJ3B";
    private static final List<Double> geoPoint = new ArrayList<>();

    private String postalCode;

    /**
     * @param user the user's postal code
     * @return the user's geoPoint as a list of 2 doubles, latitude and longitude.
     */
    @Override
    public List<Double> locationFinder(User user){
        postalCode = user.getPostalCode();

        try {
            String url = "https://api.opencagedata.com/geocode/v1/json?key=" + locationFinderApiKey + "&q=" + postalCode + "&countrycode=CA";
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

        urlString += "&apikey=" + ticketmasterApiKey;

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
     * @param postalCode the user's postal code
     * @return whether the coordinates of the user's postal code exists
     */
    @Override
    public boolean existsInCoords(String postalCode) {
        return !geoPoint.isEmpty();
    }


    //////////////////////// FOR NOTIFY USER TOUR USE CASE /////////////////////////////
    /**
     * @param artistName the user's favourite artist
     * @param classification the type of event; (music event, concert)
     * @return whether the artist has upcoming concerts
     */
    @Override
    public String hasATour(String artistName, String classification) throws IOException, InterruptedException {

        String baseUrl = "https://app.ticketmaster.com/discovery/v2/events.json";
        String urlString = baseUrl + "?keyword=" + artistName;

        if (classification != null) {
            urlString += "&classificationName=" + classification;
        }

        urlString += "&apikey=" + ticketmasterApiKey;

        URL url = new URL(urlString);
        Scanner scanner = new Scanner(url.openStream());
        StringBuilder jsonContent = new StringBuilder();

        while (scanner.hasNext()) {
            jsonContent.append(scanner.nextLine());
        }

        scanner.close();

        JSONObject obj = new JSONObject(jsonContent.toString());

        String output = "";
        if (obj.has("_embedded") && obj.get("_embedded") instanceof JSONObject) {
            JSONObject embedded = obj.getJSONObject("_embedded");

            if (embedded.has("events")) {
                output = "has a tour";
            }

        } else {
            output = "doesn't have a tour";
        }

        // Introduce a 1 second delay to avoid hitting the rate limit
        Thread.sleep(500); // Adjust the sleep duration as needed

        return output;
    }

    public String getPostalCode(){
        return postalCode;
    }


}
