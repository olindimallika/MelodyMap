package data_access;

import entity.*;
import org.json.JSONArray;
import use_case.notify_user_tour.NotifyDataAccess;
import use_case.show_concerts.ShowConcertsDataAccess;
import use_case.similar_artist_venue.SimilarDataAccess;
import use_case.upcoming_shows.UpcomingDataAccess;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

public class FileUserDataAccessObject implements UpcomingDataAccess, NotifyDataAccess, ShowConcertsDataAccess,
        SimilarDataAccess {
    private static final String CLIENT_ID = "d8de2f3b15464375938c514ed2e44270";
    private static final String CLIENT_SECRET = "f8fe086793894d13a54a778e1bad78e7";

    private List<String> listFormatSimilarArtists = new ArrayList<>();
    private String finalFormatSimilarArtists = "";

    static String apiKey = "GKzgIWcoAk5rfAb5VtGpaTiqsyMeBjJP";

    private final LinkedHashMap<String, String> shows = new LinkedHashMap<>();

    private static final String locationFinderApiKey = "d121a538d4924ef0a8951e8463b063e7";

    private static final String seatGeekApiKey = "Mzg2MzEwODZ8MTcwMTM3MjE3Ny43MzQwMTQ3";

    private static final List<Double> geoPoint = new ArrayList<>();
    private String postalCode;

    private JSONObject artistInfo;

    public FileUserDataAccessObject() {
    }

    public List<Double> locationFinder(User user){
        postalCode = user.getPostalCode();

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

//    @Override
    public boolean existsInCoord(String postalCode) {
        return false;
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
    public JSONObject getPerformerInfo(String artistName){

        try {
            // Replace with your specific API endpoint and parameters
            String baseUrl = "https://api.seatgeek.com/2/performers?";
            String apiUrl = baseUrl + "slug=" + artistName + "&client_id=" + seatGeekApiKey;

            // Create URL object
            URL url = new URL(apiUrl);

            // Open connection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set request method
            connection.setRequestMethod("GET");

            connection.setRequestProperty("Cache-Control", "no-cache");
            connection.setRequestProperty("Pragma", "no-cache");
            connection.setRequestProperty("Expires", "no-cache");

            // Read the response
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Parse the JSON response
            JSONObject jsonResponse = new JSONObject(response.toString());

            JSONArray artistArray = (JSONArray) jsonResponse.get("performers");
            artistInfo = artistArray.getJSONObject(0);

            // Close the connection
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return artistInfo;
    }

    /**
     * @return whether the api call can be made to find the artist's information
     */
    @Override
    public boolean existsInApi() {
        // if artistInfo is null, that means the artistName could not be assigned through the getPerformerInfo method
        return artistInfo != null;
    }

    public String formatSimilarArtists(HashMap<String, List<String>> similarArtistsMap) {

        StringBuilder formattedConcerts = new StringBuilder();

        List<String> concerts = new ArrayList<>();

        for (Map.Entry<String, List<String>> entry : similarArtistsMap.entrySet()) {

            String key = entry.getKey();
            String value = entry.getValue().toString();
            concerts.add(key + ": " + value);
        }

        for (int i = 0; i < 5; i++){
            formattedConcerts.append(concerts.get(i));
            formattedConcerts.append("\n");
        }
        return formattedConcerts.toString();
    }

    public String formatOutputSimilarArtist(String favArtist, String similarArtist, String artUrl){
        String result = (favArtist + "\n" + ": "+ similarArtist +"\n"+ " Event Link: "+ artUrl + "\n"+ "\n");
        listFormatSimilarArtists.add(result);
        return result;
    };
    public String getFormatOutputPresale() {
        finalFormatSimilarArtists = String.join("", listFormatSimilarArtists);
        return finalFormatSimilarArtists;
        //return listFormatOutputPresale;
    }

    @Override
    public HashMap<String, List<String>> getSimilarArtists(List<String> favouriteArtists) {
        return null;
    }

    //////////////////////// FOR SIMILAR ARTIST USE CASE /////////////////////////////
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
    public static double calculateDistance(List<Double> latlong, double lat2, double lon2) {
        double lat1 = latlong.get(0);
        double lon1 = latlong.get(1);
        double x = lat1 * (Math.PI / 180);
        double y = lat2 * (Math.PI / 180);
        // Equation - need to fix
        return Math.acos(Math.sin(x) * Math.sin(y) + Math.cos(x) * Math.cos(y) * Math.cos((lon1 - lon2) * (Math.PI / 180))) * 6371; // Earth radius in km
    }
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
