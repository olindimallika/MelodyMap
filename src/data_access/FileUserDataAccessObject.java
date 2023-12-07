package data_access;

import entity.*;
import org.json.JSONArray;
import use_case.notify_user_tour.NotifyDataAccess;
import use_case.show_concerts.ShowConcertsDataAccess;
import use_case.upcoming_shows.UpcomingDataAccess;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class FileUserDataAccessObject implements UpcomingDataAccess, NotifyDataAccess, ShowConcertsDataAccess {
    private final LinkedHashMap<String, String> shows = new LinkedHashMap<>();

    private static final String locationFinderApiKey = "daf00ad4979542568d5801316ffd22dd";

    private static final String seatGeekApiKey = "Mzg2MzEwODZ8MTcwMTM3MjE3Ny43MzQwMTQ3";

    private static final List<Double> geoPoint = new ArrayList<>();

    static String apiKey = "GKzgIWcoAk5rfAb5VtGpaTiqsyMeBjJP";

    private static final String CLIENT_ID = "d8de2f3b15464375938c514ed2e44270";
    private static final String CLIENT_SECRET = "f8fe086793894d13a54a778e1bad78e7";

    private List<String> listFormatSimilarArtists = new ArrayList<>();
    private String finalFormatSimilarArtists = "";

    private JSONObject artistInfo;

    public FileUserDataAccessObject() {
    }

    public List<Double> locationFinder(User user){
        String postalCode = user.getPostalCode();

        try {
            String url = "https://api.opencagedata.com/geocode/v1/json?key=" + locationFinderApiKey + "&q=" + postalCode + "&countrycode=CA";
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                String responseBody = response.body().string();
                JSONObject json = new JSONObject(responseBody);
                JSONObject location = json.getJSONArray("results").getJSONObject(0).getJSONObject("geometry");

                double latitude = location.getDouble("lat");
                double longitude = location.getDouble("lng");

                geoPoint.add(latitude);
                geoPoint.add(longitude);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return geoPoint;
    }

    public List<JSONObject> getEventsFromLatLong(int radius, String unit, String classification, User user) throws IOException {
        List<Double> latlong = locationFinder(user);
        double lat1 = latlong.get(0);
        double lat2 = latlong.get(1);
        String strLatlong = lat1 + "," + lat2;

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
        if (obj.has("_embedded") && obj.get("_embedded") instanceof JSONObject && obj.getJSONObject("_embedded").has("events")) {
            JSONObject embedded = obj.getJSONObject("_embedded");
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

        return events;
    }

    // Finds length between users location and the venue to sort it so closest venue is shown
    public double calculateDistance(double lat2, double lon2, User user) {
        List<Double> latlong = locationFinder(user);
        double lat1 = latlong.get(0);
        double lon1 = latlong.get(1);
        double x = lat1 * (Math.PI / 180);
        double y = lat2 * (Math.PI / 180);
        return Math.acos(Math.sin(x) * Math.sin(y) + Math.cos(x) * Math.cos(y) * Math.cos((lon1 - lon2) * (Math.PI / 180))) * 6371; // Earth radius in km
    }

    public String getEventUrl(JSONObject event) {
        String url = event.getString("url");
        return url;
    }

    public String getArtistName(JSONObject event) {
        Artist artist = new ArtistModelFactory().create(event.getString("name"));
        return artist.getName();
    }

    public LinkedHashMap<String, String> getUpcomingShows(List<JSONObject> events) {
        for (JSONObject event : events) {
            shows.put(getArtistName(event), getEventUrl(event));
        }
        return shows;
    }

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


    //////////////////////// FOR NOTIFY USER TOUR USE CASE /////////////////////////////
    public String hasATour(String artistName, String classification) throws IOException, InterruptedException {

        String baseUrl = "https://app.ticketmaster.com/discovery/v2/events.json";
        String urlString = baseUrl + "?keyword=" + artistName;

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

        // Introduce a 0.2 second delay to avoid hitting the rate limit
        Thread.sleep(200); // Adjust the sleep duration as needed

        return output;
    }

    @Override
    public String getPostalCode() {
        return null;
    }

    /**
     * @return whether the api call can be made to find the artist's information
     */
    @Override
    public boolean existsInApi() {
        // if artistInfo is null, that means the artistName could not be assigned through the getPerformerInfo method
        return artistInfo != null;
    }

    //////////////////////// FOR SIMILAR ARTIST USE CASE /////////////////////////////
    /**
     * Finds events within a specified radius from a given latitude and longitude using the Ticketmaster Discovery API.
     *
     * @param latlong A List containing latitude and longitude values for the center point.
     * @param radius The search radius for events around the specified location.
     * @param unit The unit of measurement for the search radius (e.g., "km" or "miles").
     * @param classification The classification or category of events to filter the search.
     * @param artistName The name of the artist to filter events by.
     * @return A List of JSONObjects representing events matching the search criteria.
     * @throws IOException If an error occurs during the HTTP request or response handling.
     * @throws InterruptedException If the thread is interrupted during the HTTP request.
     */
    public List<JSONObject> findEventsFromLatLong(List<Double> latlong, int radius, String unit, String classification, String artistName) throws IOException, InterruptedException {
        double lat1 = latlong.get(0);
        double long2 = latlong.get(1);
        String strLatlong = Double.toString(lat1) + "," + Double.toString(long2);

        String baseUrl = "https://app.ticketmaster.com/discovery/v2/events.json";
        String urlString = baseUrl + "?geoPoint=" + strLatlong;

        // Append radius, unit, classification, and artist name if provided
        if (radius > 0 && unit != null) {
            urlString += "&radius=" + radius + "&unit=" + unit;
        }

        if (classification != null) {
            urlString += "&classificationName=" + classification;
        }

        if (artistName != null) {
            urlString += "&keyword=" + artistName;
        }
        urlString += "&apikey=" + apiKey;
        URL url = new URL(urlString);
        Scanner scanner = new Scanner(url.openStream());
        StringBuilder jsonContent = new StringBuilder();
        while (scanner.hasNext()) {
            jsonContent.append(scanner.nextLine());
        }

        scanner.close();
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

    /**
     * Formats a HashMap of shows into a human-readable string, including key-value pairs.
     *
     * @param shows A HashMap containing show names as keys and details as values.
     * @return A formatted string representation of the shows, limited to the first 5 entries.
     */
    public String formatSimilarArtists(HashMap<String, String> shows) {

        StringBuilder formattedConcerts = new StringBuilder();

        ArrayList<String> concerts = new ArrayList<>();

        // Iterate through the provided HashMap to create a list of formatted concert entries.
        for (Map.Entry<String, String> entry : shows.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            concerts.add(key + ": " + value);
        }

        // Append the first 5 entries from the concerts list to the formatted string.
        for (int i = 0; i < 5; i++){
            formattedConcerts.append(concerts.get(i));
            formattedConcerts.append("\n");
        }
        return formattedConcerts.toString();
    }

    /**
     * Placeholder method returning null.
     * Implementation is expected in subclasses or future development.
     *
     * @param favouriteArtists A list of favorite artists for similarity comparison.
     * @return A HashMap of similar artists with associated events.
     */
    @Override
    public HashMap<String, List<String>> getSimilarArtists(List<String> favouriteArtists) {
        return null;
    }

    /**
     * Calculates the distance between two geographic points using their latitude and longitude.
     *
     * @param latlong A List containing latitude and longitude values of the first point.
     * @param lat2 The latitude of the second point.
     * @param lon2 The longitude of the second point.
     * @return The distance between the two points in kilometers.
     */
    public static double calculateDistance(List<Double> latlong, double lat2, double lon2) {
        double lat1 = latlong.get(0);
        double lon1 = latlong.get(1);
        double x = lat1 * (Math.PI / 180);
        double y = lat2 * (Math.PI / 180);
        // Equation - need to fix
        return Math.acos(Math.sin(x) * Math.sin(y) + Math.cos(x) * Math.cos(y) * Math.cos((lon1 - lon2) * (Math.PI / 180))) * 6371; // Earth radius in km
    }

    /**
     * Retrieves the URL of the first event from a list of JSONObjects representing events.
     *
     * @param eventList A list of JSONObjects containing information about events.
     * @return The URL of the first event, or a message if no URL is found.
     */
    public String getEventUrls(List<JSONObject> eventList) {
        for (JSONObject event : eventList) {
            if (event.has("url")) {
                return event.getString("url");
            } else {
                return ("No URL found for this event.");
            }
        }
        return "";
    }

    /**
     * Retrieves an access token using the Spotify API client credentials flow.
     *
     * @return The access token for authenticating Spotify API requests.
     * @throws Exception If an error occurs during the token retrieval process.
     */
    public static String getToken() throws Exception {
        String authString = CLIENT_ID + ":" + CLIENT_SECRET;
        String authBase64 = Base64.getEncoder().encodeToString(authString.getBytes());

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://accounts.spotify.com/api/token"))
                .header("Authorization", "Basic " + authBase64)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString("grant_type=client_credentials"))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JSONObject jsonResult = new JSONObject(response.body());
        return jsonResult.getString("access_token");
    }

    /**
     * Searches for an artist on Spotify using the provided access token and artist name.
     *
     * @param token The access token for authenticating the API request.
     * @param artistName The name of the artist to search for.
     * @return A JSONObject containing information about the searched artist.
     * @throws Exception If an error occurs during the API request or artist not found.
     */
    public static JSONObject searchForArtist(String token, String artistName) throws Exception {
        artistName = artistName.replace(" ", "%20");  // replace spaces with %20
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://api.spotify.com/v1/search?q=" + artistName + "&type=artist&limit=1"))
                .header("Authorization", "Bearer " + token)
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JSONObject jsonResult = new JSONObject(response.body());
        JSONArray items = jsonResult.getJSONObject("artists").getJSONArray("items");
        if (items.length() == 0) {
            System.out.println("No artist with this name exists");
            return null;
        }
        return items.getJSONObject(0);
    }

    /**
     * Retrieves a list of artists similar to the specified artist from the Spotify API.
     *
     * @param token The access token for authenticating the API request.
     * @param artistId The unique Spotify ID of the artist to find similar artists for.
     * @return A JSONObject containing information about similar artists.
     * @throws Exception If an error occurs during the API request or no similar artists found.
     */
    public static JSONObject getSimilarArtists(String token, String artistId) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://api.spotify.com/v1/artists/" + artistId + "/related-artists"))
                .header("Authorization", "Bearer " + token)
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JSONObject jsonResult = new JSONObject(response.body());
        if (jsonResult.getJSONArray("artists").length() == 0) {
            System.out.println("No similar artists");
            return null;
        }
        return jsonResult;
    }


}
