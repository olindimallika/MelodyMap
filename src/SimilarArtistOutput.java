import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;

public class SimilarArtistOutput extends LocationFinder{
    private static final String CLIENT_ID = "d8de2f3b15464375938c514ed2e44270";
    private static final String CLIENT_SECRET = "f8fe086793894d13a54a778e1bad78e7";
    SimilarArtistInput in = new SimilarArtistInput();
    static ArrayList<String> userInput = SimilarArtistInput.similar_Artists();
    static String apiKey = "GKzgIWcoAk5rfAb5VtGpaTiqsyMeBjJP";

    public SimilarArtistOutput(String apiKey) {
        this.apiKey = apiKey;
    }

    final static int radius = 20;
    final static String unit = "miles";
    final static String classification = "music";

    static HashMap<String, ArrayList<String>> map = new HashMap<>();

//    public static void main(String[] args) throws Exception {
//        // Define your postal code and favorite artists here
//        String postalCode = "Your postal code";
//        ArrayList<Artist> favArtists = new ArrayList<>(); // Add your favorite artists to this list
//
//        // Call the artistEvents method to get the events for each artist
//        List<List<JSONObject>> allArtistEvents = artistEvents(postalCode, favArtists);
//
//        // Print the events for each artist
//        for (List<JSONObject> artistEvents : allArtistEvents) {
//            printEventUrls(artistEvents);
//        }
//    }


    public static void main(String[] args) throws Exception {
        String baseUrl = "https://app.ticketmaster.com/discovery/v2/events.json";
        HttpClient client = HttpClient.newHttpClient();
        List<JSONObject> eventList = new ArrayList<>();
        String token = getToken();
        for (int i = 0; i < userInput.size(); i++) {
            String list_artist = userInput.get(i);
            JSONObject artist = searchForArtist(token, list_artist);
            if (artist != null) {
                String artistId = artist.getString("id");
                JSONObject similarArtists = getSimilarArtists(token, artistId);
                if (similarArtists != null) {
                    ArrayList<String> similarArtistNames = new ArrayList<>();
                    JSONArray artists = similarArtists.getJSONArray("artists");
                    for (int j = 0; j < Math.min(4, artists.length()) ; j++) {
                        similarArtistNames.add(artists.getJSONObject(j).getString("name"));
                        System.out.println(artists.getJSONObject(j).getString("name"));
                    }
                    map.put(userInput.get(i), similarArtistNames);
                    System.out.println(map);
                } else {
                    System.out.println("No similar artists found for " + list_artist);
                }
            } else {
                System.out.println("No artist found with the name " + list_artist);
            }
        }
        for (Map.Entry<String, ArrayList<String>> entry : map.entrySet()) {
            for (String artist : entry.getValue()) {
                List<JSONObject> events = getEventUrlsFromLatLong(latlong, radius, unit, classification, artist);
                System.out.println("Events for " + artist + ":");
                printEventUrls(events);
            }
        }
    }

//    public static List<JSONObject> getEventUrlsFromLatLong(List<Double> latlong, int radius, String unit, String classification, String artistName) throws IOException {
//        double userLat = latlong.get(0);
//        double userLong = latlong.get(1);
//        String strLatlong = Double.toString(userLat) + "," + Double.toString(userLong);
//
//        StringBuilder urlString = new StringBuilder("https://app.ticketmaster.com/discovery/v2/events.json");
//        urlString.append("?geoPoint=").append(strLatlong);
//
//        // Append radius, unit, classification, and artist name if provided
//        if (radius > 0 && unit != null) {
//            urlString.append("&radius=").append(radius).append("&unit=").append(unit);
//        }
//
//        if (classification != null) {
//            urlString.append("&classificationName=").append(classification);
//        }
//
//        if (artistName != null) {
//            urlString.append("&keyword=").append(artistName);
//        }
//
//        urlString.append("&apikey=").append(apiKey);
//
//        URL url = new URL(urlString.toString());
//        Scanner scanner = new Scanner(url.openStream());
//        StringBuilder jsonContent = new StringBuilder();
//
//        while (scanner.hasNext()) {
//            jsonContent.append(scanner.nextLine());
//        }
//        scanner.close();
//
//        // Parse the JSON response and return a list of event URLs
//        List<JSONObject> events = new ArrayList<>();
//        JSONObject obj = new JSONObject(jsonContent.toString());
//
//        // Check if _embedded is a JSONArray
//        if (obj.has("_embedded") && obj.get("_embedded") instanceof JSONObject) {
//            JSONObject embedded = obj.getJSONObject("_embedded");
//
//            if (embedded.has("events")) {
//                JSONArray eventsArray = embedded.getJSONArray("events");
//
//                for (int i = 0; i < eventsArray.length(); i++) {
//                    JSONObject event = eventsArray.getJSONObject(i);
//                    if (event.has("_embedded") && event.getJSONObject("_embedded").has("venues")) {
//                        JSONArray venues = event.getJSONObject("_embedded").getJSONArray("venues");
//                        for (int j = 0; j < venues.length(); j++) {
//                            JSONObject venue = venues.getJSONObject(j);
//                            if (venue.has("location")) {
//                                double venueLat = venue.getJSONObject("location").getDouble("latitude");
//                                double venueLong = venue.getJSONObject("location").getDouble("longitude");
//                                double distance = calculateDistance(latlong, venueLat, venueLong);
//                                event.put("distance", distance);
//                            }
//                        }
//                    }
//                    events.add(event);
//                }
//            }
//        }
//
//        // Sort events by distance
//        Collections.sort(events, Comparator.comparingDouble(o -> o.getDouble("distance")));
//
//        return events;
//    }
//    public static List<JSONObject> getEventUrlsFromLatLong(List<Double> latlong, int radius, String unit, String classification, String artistName) throws IOException {
//        double lat1 = latlong.get(0);
//        double lat2 = latlong.get(1);
//        String strLatlong = Double.toString(lat1) + "," + Double.toString(lat2);
//
//        StringBuilder urlString = new StringBuilder("https://app.ticketmaster.com/discovery/v2/events.json");
//        urlString.append("?geoPoint=").append(strLatlong);
//
//        // Append radius, unit, classification, and artist name if provided
//        if (radius > 0 && unit != null) {
//            urlString.append("&radius=").append(radius).append("&unit=").append(unit);
//        }
//
//        if (classification != null) {
//            urlString.append("&classificationName=").append(classification);
//        }
//
//        if (artistName != null) {
//            urlString.append("&keyword=").append(artistName);
//        }
//
//        urlString.append("&apikey=").append(apiKey);
//
//        System.out.println(urlString);
//        URL url = new URL(urlString.toString());
//        Scanner scanner = new Scanner(url.openStream());
//        StringBuilder jsonContent = new StringBuilder();
//
//        while (scanner.hasNext()) {
//            jsonContent.append(scanner.nextLine());
//        }
//        scanner.close();
//
//        // Parse the JSON response and return a list of event URLs
//        List<JSONObject> events = new ArrayList<>();
//        JSONObject obj = new JSONObject(jsonContent.toString());
//
//        // Check if _embedded is a JSONArray
//        if (obj.has("_embedded") && obj.get("_embedded") instanceof JSONObject) {
//            JSONObject embedded = obj.getJSONObject("_embedded");
//
//            if (embedded.has("events")) {
//                JSONArray eventsArray = embedded.getJSONArray("events");
//
//                for (int i = 0; i < eventsArray.length(); i++) {
//                    JSONObject event = eventsArray.getJSONObject(i);
//                    String eventUrl = event.getString("url");
//                    events.add(eventUrl);
//                }
//            }
//        }
//
//        return events;
//    }

    public static double calculateDistance(List<Double> latlong, double lat2, double lon2) {
        double lat1 = latlong.get(0);
        double lon1 = latlong.get(1);
        double x = lat1 * (Math.PI / 180);
        double y = lat2 * (Math.PI / 180);
        // Equation - need to fix
        return Math.acos(Math.sin(x) * Math.sin(y) + Math.cos(x) * Math.cos(y) * Math.cos((lon1 - lon2) * (Math.PI / 180))) * 6371; // Earth radius in km
    }

    public static void printEventUrls(List<JSONObject> eventList) {
        for (JSONObject event : eventList) {
            if (event.has("url")) {
                System.out.println(event.getString("url"));
            } else {
                System.out.println("No URL found for this event.");
            }
        }
    }


    private static String getToken() throws Exception {
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

    private static JSONObject searchForArtist(String token, String artistName) throws Exception {
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


    private static JSONObject getSimilarArtists(String token, String artistId) throws Exception {
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



